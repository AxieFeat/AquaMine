package net.aquamine.server.world.chunk

import net.kyori.adventure.key.InvalidKeyException
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.biome.Biome
import net.aquamine.server.AquaPlatform
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.entity.EntityFactory
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.util.nbt.getDataVersion
import net.aquamine.server.util.nbt.putDataVersion
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.biome.BiomeKeys
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.palette.PaletteHolder
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.data.ChunkSection
import net.aquamine.server.world.chunk.data.Heightmap
import net.aquamine.server.world.region.RegionFileManager
import xyz.axie.nbt.ByteArrayTag
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ImmutableCompoundTag
import xyz.axie.nbt.ImmutableListTag
import xyz.axie.nbt.LongArrayTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.buildCompound
import xyz.axie.nbt.compound
import net.aquamine.serialization.nbt.NbtOps
import java.nio.file.Path
import java.util.EnumSet

class VanillaChunkLoader(worldFolder: Path) : ChunkLoader {

    private val regionManager = RegionFileManager(worldFolder.resolve("region"))
    private val entityRegionManager = RegionFileManager(worldFolder.resolve("entities"))

    override fun loadChunk(world: AquaWorld, pos: ChunkPos): AquaChunk? {
        val nbt = regionManager.read(pos.x, pos.z) ?: return null
        return loadData(world, pos, nbt)
    }

    private fun loadData(world: AquaWorld, pos: ChunkPos, nbt: CompoundTag): AquaChunk {
        val heightmaps = nbt.getCompound(HEIGHTMAPS_TAG)
        val biomeRegistry = world.registryHolder.getRegistry(ResourceKeys.BIOME) as? AquaRegistry<Biome>
            ?: error("Cannot find biome registry in $world!")

        val sectionList = nbt.getList(SECTIONS_TAG, CompoundTag.ID)
        val sections = arrayOfNulls<ChunkSection>(world.sectionCount())
        for (i in 0 until sectionList.size) {
            val sectionData = sectionList.getCompound(i)
            val y = sectionData.getByte(Y_TAG).toInt()

            val index = world.getSectionIndexFromSectionY(y)
            if (index >= 0 && index < sections.size) {
                val blocks = if (sectionData.contains(BLOCK_STATES_TAG, CompoundTag.ID)) {
                    PaletteHolder.readBlocks(sectionData.getCompound(BLOCK_STATES_TAG))
                } else {
                    PaletteHolder(PaletteHolder.Strategy.BLOCKS, AquaBlocks.AIR.defaultState)
                }

                val biomes = if (sectionData.contains(BIOMES_TAG, CompoundTag.ID)) {
                    PaletteHolder.readBiomes(sectionData.getCompound(BIOMES_TAG), biomeRegistry)
                } else {
                    PaletteHolder(PaletteHolder.Strategy.biomes(biomeRegistry), biomeRegistry.get(BiomeKeys.PLAINS)!!)
                }

                val blockLight = if (sectionData.contains(BLOCK_LIGHT_TAG, ByteArrayTag.ID)) sectionData.getByteArray(BLOCK_LIGHT_TAG) else null
                val skyLight = if (sectionData.contains(SKY_LIGHT_TAG, ByteArrayTag.ID)) sectionData.getByteArray(SKY_LIGHT_TAG) else null
                val section = ChunkSection(blocks, biomes, blockLight, skyLight)
                sections[index] = section
            }
        }

        val chunk = AquaChunk(world, pos, fillMissingSections(world, sections))
        chunk.lastUpdate = nbt.getLong(LAST_UPDATE_TAG)
        chunk.inhabitedTime = nbt.getLong(INHABITED_TIME_TAG)

        val toPrime = EnumSet.noneOf(Heightmap.Type::class.java)
        Heightmap.Type.POST_FEATURES.forEach {
            if (heightmaps.contains(it.name, LongArrayTag.ID)) chunk.setHeightmap(it, heightmaps.getLongArray(it.name)) else toPrime.add(it)
        }
        Heightmap.prime(chunk, toPrime)

        return chunk
    }

    private fun fillMissingSections(world: AquaWorld, array: Array<ChunkSection?>): Array<ChunkSection> {
        val result = arrayOfNulls<ChunkSection>(world.sectionCount())
        if (result.size == array.size) {
            System.arraycopy(array, 0, result, 0, result.size)
        } else {
            LOGGER.warn("Failed to set chunk sections! Expected ${result.size} sections, got ${array.size}! Loaded data will not be used.")
        }
        replaceMissingSections(world, result)

        @Suppress("UNCHECKED_CAST") // The replacement replaces any null sections with empty sections, so the array contains no nulls after it.
        return result as Array<ChunkSection>
    }

    override fun loadAllEntities(chunk: AquaChunk) {
        val nbt = entityRegionManager.read(chunk.x, chunk.z) ?: return

        nbt.getList(ENTITIES_TAG, CompoundTag.ID).forEachCompound {
            val id = it.getString(ENTITY_ID_TAG)
            if (id.isBlank()) return@forEachCompound

            val key = try {
                Key.key(id)
            } catch (_: InvalidKeyException) {
                return@forEachCompound
            }
            val type = AquaRegistries.ENTITY_TYPE.get(key)

            val entity = EntityFactory.create(type, chunk.world) ?: return@forEachCompound
            entity.load(it)
            chunk.world.entityManager.spawnEntity(entity)
        }
    }

    override fun saveChunk(chunk: AquaChunk) {
        val data = saveData(chunk)
        regionManager.write(chunk.x, chunk.z, data)
    }

    private fun saveData(chunk: AquaChunk): CompoundTag {
        val data = buildCompound {
            putInt("DataVersion", AquaPlatform.worldVersion)
            putLong(LAST_UPDATE_TAG, chunk.lastUpdate)
            putLong(INHABITED_TIME_TAG, chunk.inhabitedTime)
            putString("Status", "full")
            putInt("xPos", chunk.position.x)
            putInt("zPos", chunk.position.z)
        }

        val sectionList = ImmutableListTag.builder(CompoundTag.ID)
        for (i in chunk.minimumLightSection() until chunk.maximumLightSection()) {
            val sectionIndex = chunk.world.getSectionIndexFromSectionY(i)
            // TODO: Handle light sections below and above the world
            if (sectionIndex >= 0 && sectionIndex < chunk.sections().size) {
                val section = chunk.sections()[sectionIndex]
                val sectionData = compound {
                    putByte(Y_TAG, i.toByte())
                    put(BLOCK_STATES_TAG, section.blocks.write { AquaBlockState.CODEC.encodeStart(it, NbtOps.INSTANCE).result().get() })
                    put(BIOMES_TAG, section.biomes.write { StringTag.of(it.key().asString()) })
                    if (section.blockLight != null) putByteArray(BLOCK_LIGHT_TAG, section.blockLight)
                    if (section.skyLight != null) putByteArray(SKY_LIGHT_TAG, section.skyLight)
                }
                sectionList.add(sectionData)
            }
        }
        data.put(SECTIONS_TAG, sectionList.build())

        val heightmapData = ImmutableCompoundTag.builder()
        chunk.heightmaps().forEach { if (it.key in Heightmap.Type.POST_FEATURES) heightmapData.putLongArray(it.key.name, it.value.rawData()) }
        data.put(HEIGHTMAPS_TAG, heightmapData.build())
        return data.build()
    }

    override fun saveAllEntities(chunk: AquaChunk) {
        val entities = chunk.world.entityTracker.entitiesInChunk(chunk.position)
        if (entities.isEmpty()) return

        val entityList = ImmutableListTag.builder(CompoundTag.ID)
        entities.forEach { if (it !is AquaPlayer) entityList.add(it.saveWithPassengers().build()) }

        entityRegionManager.write(chunk.x, chunk.z, compound {
            putDataVersion()
            putInts(ENTITY_POSITION_TAG, chunk.position.x, chunk.position.z)
            put(ENTITIES_TAG, entityList.build())
        })
    }

    override fun close() {
        regionManager.close()
        entityRegionManager.close()
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        // Chunk data tags
        private const val HEIGHTMAPS_TAG = "Heightmaps"
        private const val SECTIONS_TAG = "sections"
        private const val Y_TAG = "Y"
        private const val BLOCK_STATES_TAG = "block_states"
        private const val BIOMES_TAG = "biomes"
        private const val BLOCK_LIGHT_TAG = "BlockLight"
        private const val SKY_LIGHT_TAG = "SkyLight"
        private const val LAST_UPDATE_TAG = "LastUpdate"
        private const val INHABITED_TIME_TAG = "InhabitedTime"

        // Entity data tags
        private const val ENTITY_ID_TAG = "id"
        private const val ENTITY_POSITION_TAG = "Position"
        private const val ENTITIES_TAG = "Entities"

        @JvmStatic
        private fun replaceMissingSections(world: AquaWorld, sections: Array<ChunkSection?>) {
            val biomeRegistry = world.registryHolder.getRegistry(ResourceKeys.BIOME) as AquaRegistry<Biome>
            for (i in sections.indices) {
                if (sections[i] == null) sections[i] = ChunkSection(biomeRegistry)
            }
        }
    }
}
