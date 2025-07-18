/*
package net.aquamine.server.world.generator

import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.aquamine.api.block.BlockState
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.generator.GenerationUnit
import net.aquamine.api.world.generator.UnitModifier
import org.jetbrains.annotations.ApiStatus
import java.util.*
import java.util.List
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer

@ApiStatus.Internal
object AquaGenerator {

    private val SECTION = Vec3i(16, 16, 16)

    private fun Vec3i.section(): Int {
        return this.y shr 4
    }

    fun section(
        biomeRegistry: DynamicRegistryReference<Biome>,
        section: GenSection,
        sectionX: Int, sectionY: Int, sectionZ: Int,
        fork: Boolean,
    ): GenerationUnit {
        val start = SECTION.multiply(sectionX, sectionY, sectionZ)

        val end = start.add(SECTION)

        val modifier: UnitModifier = SectionModifierImpl(
            biomeRegistry, SECTION,
            start, end, section, fork
        )

        return AquaGenerator.unit(biomeRegistry, modifier, start, end, null)
    }

    fun section(
        biomeRegistry: DynamicRegistryReference<Biome>,
        section: GenSection,
        sectionX: Int,
        sectionY: Int,
        sectionZ: Int,
    ): GenerationUnit {
        return section(biomeRegistry, section, sectionX, sectionY, sectionZ, false)
    }

    fun chunk(
        biomeRegistry: DynamicRegistryReference<Biome>,
        chunkSections: Array<GenSection>,
        chunkX: Int,
        minSection: Int,
        chunkZ: Int,
    ): UnitImpl {
        val start = SECTION.multiply(chunkX, minSection, chunkZ)
        return area(biomeRegistry, start, 1, chunkSections.size, 1, chunkSections)
    }

    fun area(
        biomeRegistry: DynamicRegistryReference<Biome>,
        start: Vec3i,
        width: Int,
        height: Int,
        depth: Int,
        areaSections: Array<GenSection>,
    ): UnitImpl {
        require(!(width == 0 || height == 0 || depth == 0)) { "Width, height and depth must be greater than 0, got $width, $height, $depth" }
        require(areaSections.size == width * height * depth) { "Invalid section count, expected " + width * height * depth + " but got " + areaSections.size }

        var sections: MutableList<GenerationUnit> = ArrayList<GenerationUnit>()
        for (i in areaSections.indices) {
            val section = areaSections[i]
            val point = to3D(i, width, height, depth)
            val sectionX: Int = point.x + start.chunkX()
            val sectionY: Int = point.y + start.section()
            val sectionZ: Int = point.z + start.chunkZ()
            sections.add(section(biomeRegistry, section, sectionX, sectionY, sectionZ))
        }
        sections = List.copyOf<GenerationUnit>(sections)

        val size = SECTION.multiply(width, height, depth)
        val end = start.add(size)
        val modifier: UnitModifier = AreaModifierImpl(size, start, end, width, height, depth, sections)
        return unit(biomeRegistry, modifier, start, end, sections)
    }

    fun unit(
        biomeRegistry: DynamicRegistryReference<Biome>,
        modifier: UnitModifier,
        start: Vec3i, end: Vec3i,
        divided: MutableList<GenerationUnit>?,
    ): UnitImpl {
        require(!(start.x > end.x || start.y > end.y || start.z > end.z)) { "absoluteStart must be before absoluteEnd" }
        require(!(start.x % 16 != 0 || start.y % 16 != 0 || start.z % 16 != 0)) { "absoluteStart must be a multiple of 16" }
        require(!(end.x % 16 != 0 || end.y % 16 != 0 || end.z % 16 != 0)) { "absoluteEnd must be a multiple of 16" }
        val size = end.subtract(start)
        return UnitImpl(biomeRegistry, modifier, size, start, end, divided, CopyOnWriteArrayList<UnitImpl?>())
    }

    private fun findAbsolute(
        units: MutableList<GenerationUnit>,
        start: Vec3i,
        width: Int, height: Int, depth: Int,
        x: Int, y: Int, z: Int,
    ): GenerationUnit {
        val sectionX: Int = globalToChunk(x - start.x())
        val sectionY: Int = globalToChunk(y - start.y())
        val sectionZ: Int = globalToChunk(z - start.z())
        val index = findIndex(width, height, depth, sectionX, sectionY, sectionZ)
        return units.get(index)
    }

    private fun findIndex(
        width: Int, height: Int, depth: Int,
        x: Int, y: Int, z: Int,
    ): Int {
        assert(width > 0 && height > 0 && depth > 0)
        return (z * width * height) + (y * width) + x
    }

    private fun to3D(idx: Int, width: Int, height: Int, depth: Int): Vec3i {
        var idx = idx
        val z = idx / (width * height)
        idx -= (z * width * height)
        val y = idx / width
        val x = idx % width
        return Vec3i(x, y, z)
    }

    class GenSection(blocks: Palette?, biomes: Palette?, specials: Int2ObjectMap<Block?>?) {
        @JvmOverloads
        constructor(blocks: Palette? = Palette.blocks(), biomes: Palette? = Palette.biomes()) : this(
            blocks,
            biomes,
            Int2ObjectOpenHashMap<Block?>(0)
        )

        val blocks: Palette?
        val biomes: Palette?
        val specials: Int2ObjectMap<Block?>?

        init {
            this.blocks = blocks
            this.biomes = biomes
            this.specials = specials
        }
    }

    internal class DynamicFork(biomeRegistry: DynamicRegistry<Biome?>?) : Block.Setter {
        val biomeRegistry: DynamicRegistry<Biome?>?
        var minSection: Vec? = null
        var width: Int = 0
        var height: Int = 0
        var depth: Int = 0
        var sections: MutableList<GenerationUnit>? = null

        init {
            this.biomeRegistry = biomeRegistry
        }

        override fun setBlock(x: Int, y: Int, z: Int, block: BlockState) {
            resize(x, y, z)
            val section = findAbsolute(sections!!, minSection, width, height, depth, x, y, z)
            assert(
                section.absoluteStart().chunkX() === globalToChunk(x) && section.absoluteStart()
                    .section() === globalToChunk(y) && section.absoluteStart().chunkZ() === globalToChunk(z)
            ) { "Invalid section " + section.absoluteStart() + " for " + x + ", " + y + ", " + z }
            section.modifier().setBlock(x, y, z, block)
        }

        private fun resize(x: Int, y: Int, z: Int) {
            val sectionX: Int = globalToChunk(x)
            val sectionY: Int = globalToChunk(y)
            val sectionZ: Int = globalToChunk(z)
            if (sections == null) {
                this.minSection = Vec.SECTION.mul(sectionX, sectionY, sectionZ)
                this.width = 1
                this.height = 1
                this.depth = 1
                this.sections =
                    List.of<GenerationUnit?>(section(biomeRegistry, GenSection(), sectionX, sectionY, sectionZ, true))
            } else if (x < minSection.x() || y < minSection.y() || z < minSection.z() || x >= minSection.x() + width * 16 || y >= minSection.y() + height * 16 || z >= minSection.z() + depth * 16) {
                // Resize necessary
                val newMin: Vec = Vec(
                    Math.min(minSection.x(), sectionX * 16),
                    Math.min(minSection.y(), sectionY * 16),
                    Math.min(minSection.z(), sectionZ * 16)
                )
                val newMax: Vec = Vec(
                    Math.max(minSection.x() + width * 16, sectionX * 16 + 16),
                    Math.max(minSection.y() + height * 16, sectionY * 16 + 16),
                    Math.max(minSection.z() + depth * 16, sectionZ * 16 + 16)
                )
                val newWidth: Int = globalToChunk(newMax.x() - newMin.x())
                val newHeight: Int = globalToChunk(newMax.y() - newMin.y())
                val newDepth: Int = globalToChunk(newMax.z() - newMin.z())
                // Resize
                val newSections = arrayOfNulls<GenerationUnit>(newWidth * newHeight * newDepth)
                // Copy old sections
                for (s in sections) {
                    val start: Point = s.absoluteStart()
                    val newX: Int = globalToChunk(start.x() - newMin.x())
                    val newY: Int = globalToChunk(start.y() - newMin.y())
                    val newZ: Int = globalToChunk(start.z() - newMin.z())
                    val index = findIndex(newWidth, newHeight, newDepth, newX, newY, newZ)
                    newSections[index] = s
                }
                // Fill new sections
                val startX: Int = newMin.chunkX()
                val startY: Int = newMin.section()
                val startZ: Int = newMin.chunkZ()
                for (i in newSections.indices) {
                    if (newSections[i] == null) {
                        val coordinates: Point = to3D(i, newWidth, newHeight, newDepth)
                        val newX: Int = coordinates.blockX() + startX
                        val newY: Int = coordinates.blockY() + startY
                        val newZ: Int = coordinates.blockZ() + startZ
                        val unit = section(biomeRegistry, GenSection(), newX, newY, newZ, true)
                        newSections[i] = unit
                    }
                }
                this.sections = List.of<GenerationUnit?>(*newSections)
                this.minSection = newMin
                this.width = newWidth
                this.height = newHeight
                this.depth = newDepth
            }
        }
    }

    class UnitImpl(
        biomeRegistry: DynamicRegistryReference<Biome>, modifier: UnitModifier?, size: Vec3i,
        absoluteStart: Vec3i, absoluteEnd: Vec3i,
        divided: MutableList<GenerationUnit>,
        forks: MutableList<UnitImpl>,
    ) : GenerationUnit {
        public override fun fork(start: Point, end: Point): GenerationUnit {
            val minSectionX: Int = floorSection(start.blockX()) / 16
            val minSectionY: Int = floorSection(start.blockY()) / 16
            val minSectionZ: Int = floorSection(start.blockZ()) / 16

            val maxSectionX: Int = ceilSection(end.blockX()) / 16
            val maxSectionY: Int = ceilSection(end.blockY()) / 16
            val maxSectionZ: Int = ceilSection(end.blockZ()) / 16

            val width = maxSectionX - minSectionX
            val height = maxSectionY - minSectionY
            val depth = maxSectionZ - minSectionZ

            val units = arrayOfNulls<GenerationUnit>(width * height * depth)
            var index = 0
            // Z -> Y -> X order is important for indexing
            for (sectionZ in minSectionZ..<maxSectionZ) {
                for (sectionY in minSectionY..<maxSectionY) {
                    for (sectionX in minSectionX..<maxSectionX) {
                        val unit = section(biomeRegistry, GenSection(), sectionX, sectionY, sectionZ, true)
                        units[index++] = unit
                    }
                }
            }
            val sections = List.of<GenerationUnit?>(*units)
            val startSection: Point = Vec.SECTION.mul(minSectionX, minSectionY, minSectionZ)
            return registerFork(startSection, sections, width, height, depth)
        }

        public override fun fork(consumer: Consumer<Block.Setter>) {
            val dynamicFork = DynamicFork(biomeRegistry)
            consumer.accept(dynamicFork)
            val startSection: Point? = dynamicFork.minSection
            if (startSection == null) return  // No block has been placed

            val width = dynamicFork.width
            val height = dynamicFork.height
            val depth = dynamicFork.depth
            val sections = dynamicFork.sections
            registerFork(startSection, sections!!, width, height, depth)
        }

        override fun subdivide(): MutableList<GenerationUnit?> {
            return Objects.requireNonNullElseGet<MutableList<GenerationUnit?>?>(divided, super::subdivide)
        }

        private fun registerFork(
            start: Point, sections: MutableList<GenerationUnit>,
            width: Int, height: Int, depth: Int,
        ): GenerationUnit {
            val end: Point = start.add(width * 16, height * 16, depth * 16)
            val size: Point? = end.sub(start)
            val modifier = AreaModifierImpl(size, start, end, width, height, depth, sections)
            val fork = UnitImpl(biomeRegistry, modifier, size, start, end, sections, forks)
            forks!!.add(fork)
            return fork
        }

        val biomeRegistry: DynamicRegistry<Biome?>?
        val modifier: UnitModifier?
        val size: Point?
        val absoluteStart: Point?
        val absoluteEnd: Point?
        val divided: MutableList<GenerationUnit>
        val forks: MutableList<UnitImpl?>?

        init {
            this.biomeRegistry = biomeRegistry
            this.modifier = modifier
            this.size = size
            this.absoluteStart = absoluteStart
            this.absoluteEnd = absoluteEnd
            this.divided = divided
            this.forks = forks
        }
    }

    class SectionModifierImpl(
        biomeRegistry: DynamicRegistry<Biome?>?, size: Point?, start: Point?, end: Point?,
        genSection: GenSection?, fork: Boolean,
    ) : GenericModifier {
        public override fun setBiome(x: Int, y: Int, z: Int, biome: RegistryKey<Biome?>) {
            check(!fork) { "Cannot modify biomes of a fork" }
            val id: Int = biomeRegistry.getId(biome)
            Check.argCondition(id == -1, "Biome has not been registered: {0}", biome)
            this.genSection!!.biomes.set(
                globalToSectionRelative(x) / 4,
                globalToSectionRelative(y) / 4,
                globalToSectionRelative(z) / 4, id
            )
        }

        public override fun setBlock(x: Int, y: Int, z: Int, block: Block) {
            val localX: Int = globalToSectionRelative(x)
            val localY: Int = globalToSectionRelative(y)
            val localZ: Int = globalToSectionRelative(z)
            handleCache(localX, localY, localZ, block)
            this.genSection!!.blocks.set(localX, localY, localZ, retrieveBlockId(block))
        }

        public override fun setRelative(x: Int, y: Int, z: Int, block: Block) {
            handleCache(x, y, z, block)
            this.genSection!!.blocks.set(x, y, z, retrieveBlockId(block))
        }

        override fun setAllRelative(supplier: Supplier) {
            this.genSection!!.blocks.setAll({ x, y, z ->
                val block: Block = supplier.get(x, y, z)
                handleCache(x, y, z, block)
                retrieveBlockId(block)
            })
        }

        override fun fill(block: Block) {
            if (requireCache(block)) {
                for (x in 0..15) {
                    for (y in 0..15) {
                        for (z in 0..15) {
                            this.genSection!!.specials!!.put(chunkBlockIndex(x, y, z), block)
                        }
                    }
                }
            }
            this.genSection!!.blocks.fill(retrieveBlockId(block))
        }

        public override fun fillBiome(biome: RegistryKey<Biome?>) {
            check(!fork) { "Cannot modify biomes of a fork" }
            val id: Int = biomeRegistry.getId(biome)
            Check.argCondition(id == -1, "Biome has not been registered: {0}", biome)
            this.genSection!!.biomes.fill(id)
        }

        private fun retrieveBlockId(block: Block): Int {
            val stateId: Int = block.stateId()
            return if (fork) stateId + 1 else stateId
        }

        private fun handleCache(x: Int, y: Int, z: Int, block: Block) {
            if (requireCache(block)) {
                this.genSection!!.specials!!.put(chunkBlockIndex(x, y, z), block)
            } else if (!genSection!!.specials!!.isEmpty()) {
                this.genSection.specials.remove(chunkBlockIndex(x, y, z))
            }
        }

        private fun requireCache(block: Block): Boolean {
            return block.hasNbt() || block.handler() != null || block.registry().isBlockEntity()
        }

        val biomeRegistry: DynamicRegistry<Biome?>?
        val size: Point?
        val start: Point?
        val end: Point?
        val genSection: GenSection?
        val fork: Boolean

        init {
            this.biomeRegistry = biomeRegistry
            this.size = size
            this.start = start
            this.end = end
            this.genSection = genSection
            this.fork = fork
        }
    }

    class AreaModifierImpl(
        size: Point?, start: Point, end: Point?,
        width: Int, height: Int, depth: Int,
        sections: MutableList<GenerationUnit>?,
    ) : GenericModifier {
        public override fun setBlock(x: Int, y: Int, z: Int, block: Block) {
            var y = y
            checkBorder(x, y, z)
            val section = findAbsoluteSection(x, y, z)
            y -= start.y()
            section.modifier().setBlock(x, y, z, block)
        }

        public override fun setBiome(x: Int, y: Int, z: Int, biome: RegistryKey<Biome?>) {
            var y = y
            checkBorder(x, y, z)
            val section = findAbsoluteSection(x, y, z)
            y -= start.y()
            section.modifier().setBiome(x, y, z, biome)
        }

        public override fun setRelative(x: Int, y: Int, z: Int, block: Block) {
            var x = x
            var y = y
            var z = z
            require(!(x < 0 || x >= size.x() || y < 0 || y >= size.y() || z < 0 || z >= size.z())) { "x, y and z must be in the chunk: " + x + ", " + y + ", " + z }
            val section = findRelativeSection(x, y, z)
            x = globalToSectionRelative(x)
            y = globalToSectionRelative(y)
            z = globalToSectionRelative(z)
            section.modifier().setBlock(x, y, z, block)
        }

        override fun setAll(supplier: Supplier) {
            for (section in sections!!) {
                val start: Point = section.absoluteStart()
                val startX: Int = start.blockX()
                val startY: Int = start.blockY()
                val startZ: Int = start.blockZ()
                section.modifier().setAllRelative({ x, y, z -> supplier.get(x + startX, y + startY, z + startZ) })
            }
        }

        override fun setAllRelative(supplier: Supplier) {
            val start: Point = this.start
            for (section in sections!!) {
                val sectionStart: Point = section.absoluteStart()
                val offsetX: Int = sectionStart.blockX() - start.blockX()
                val offsetY: Int = sectionStart.blockY() - start.blockY()
                val offsetZ: Int = sectionStart.blockZ() - start.blockZ()
                section.modifier().setAllRelative({ x, y, z -> supplier.get(x + offsetX, y + offsetY, z + offsetZ) })
            }
        }

        override fun fill(block: Block) {
            for (section in sections!!) {
                section.modifier().fill(block)
            }
        }

        public override fun fillBiome(biome: RegistryKey<Biome?>) {
            for (section in sections!!) {
                section.modifier().fillBiome(biome)
            }
        }

        override fun fillHeight(minHeight: Int, maxHeight: Int, block: Block) {
            val start: Point = this.start
            val width = this.width
            val depth = this.depth
            val startX: Int = start.blockX()
            val startZ: Int = start.blockZ()
            val minMultiple: Int = floorSection(minHeight)
            val maxMultiple: Int = ceilSection(maxHeight)
            val startOffset = minMultiple != minHeight
            val endOffset = maxMultiple != maxHeight
            if (startOffset || endOffset) {
                val firstFill = min(minMultiple + 16, maxHeight)
                val lastFill =
                    if (startOffset) Math.max(firstFill, floorSection(maxHeight)) else floorSection(maxHeight)
                for (x in 0..<width) {
                    for (z in 0..<depth) {
                        val sectionX = startX + x * 16
                        val sectionZ = startZ + z * 16
                        // Fill start
                        if (startOffset) {
                            val section = findAbsoluteSection(sectionX, minMultiple, sectionZ)
                            section.modifier().fillHeight(minHeight, firstFill, block)
                        }
                        // Fill end
                        if (endOffset) {
                            val section = findAbsoluteSection(sectionX, maxHeight, sectionZ)
                            section.modifier().fillHeight(lastFill, maxHeight, block)
                        }
                    }
                }
            }
            // Middle sections (to fill)
            val startSection = (minMultiple) / 16 + (if (startOffset) 1 else 0)
            val endSection = (maxMultiple) / 16 + (if (endOffset) -1 else 0)
            for (i in startSection..<endSection) {
                for (x in 0..<width) {
                    for (z in 0..<depth) {
                        val section = findAbsoluteSection(startX + x * 16, i * 16, startZ + z * 16)
                        section.modifier().fill(block)
                    }
                }
            }
        }

        private fun findAbsoluteSection(x: Int, y: Int, z: Int): GenerationUnit {
            return GeneratorImpl.findAbsolute(sections!!, start, width, height, depth, x, y, z)
        }

        private fun findRelativeSection(x: Int, y: Int, z: Int): GenerationUnit {
            return GeneratorImpl.findAbsolute(sections!!, Vec.ZERO, width, height, depth, x, y, z)
        }

        private fun checkBorder(x: Int, y: Int, z: Int) {
            if (x < start.x() || x >= end.x() || y < start.y() || y >= end.y() || z < start.z() || z >= end.z()) {
                val format = String.format("Invalid coordinates: %d, %d, %d for area %s %s", x, y, z, start, end)
                throw IllegalArgumentException(format)
            }
        }

        val size: Point?
        val start: Point
        val end: Point?
        val width: Int
        val height: Int
        val depth: Int
        val sections: MutableList<GenerationUnit>?

        init {
            this.size = size
            this.start = start
            this.end = end
            this.width = width
            this.height = height
            this.depth = depth
            this.sections = sections
        }
    }

    internal interface GenericModifier : UnitModifier {
        fun size(): Point

        fun start(): Point

        fun end(): Point

        override fun setAll(supplier: Supplier) {
            val start: Point = start()
            val end: Point = end()
            val endX: Int = end.blockX()
            val endY: Int = end.blockY()
            val endZ: Int = end.blockZ()
            for (x in start.blockX()..<endX) {
                for (y in start.blockY()..<endY) {
                    for (z in start.blockZ()..<endZ) {
                        setBlock(x, y, z, supplier.get(x, y, z))
                    }
                }
            }
        }

        override fun setAllRelative(supplier: Supplier) {
            val size: Point = size()
            val endX: Int = size.blockX()
            val endY: Int = size.blockY()
            val endZ: Int = size.blockZ()
            for (x in 0..<endX) {
                for (y in 0..<endY) {
                    for (z in 0..<endZ) {
                        setRelative(x, y, z, supplier.get(x, y, z))
                    }
                }
            }
        }

        override fun fill(block: Block) {
            fill(start(), end(), block)
        }

        override fun fill(start: Point, end: Point, block: Block) {
            val endX: Int = end.blockX()
            val endY: Int = end.blockY()
            val endZ: Int = end.blockZ()
            for (x in start.blockX()..<endX) {
                for (y in start.blockY()..<endY) {
                    for (z in start.blockZ()..<endZ) {
                        setBlock(x, y, z, block)
                    }
                }
            }
        }

        override fun fillHeight(minHeight: Int, maxHeight: Int, block: Block) {
            val start: Point = start()
            val end: Point = end()
            val startY: Int = start.blockY()
            val endY: Int = end.blockY()
            if (startY >= minHeight && endY <= maxHeight) {
                // Fast path if the unit is fully contained in the height range
                fill(start, end, block)
            } else {
                // Slow path if the unit is not fully contained in the height range
                fill(start.withY(max(minHeight, startY)), end.withY(min(maxHeight, endY)), block)
            }
        }
    }
}
*/
