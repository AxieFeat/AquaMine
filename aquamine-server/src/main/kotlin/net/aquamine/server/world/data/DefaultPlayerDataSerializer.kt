package net.aquamine.server.world.data

import org.apache.logging.log4j.LogManager
import net.aquamine.server.KryptonPlatform
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.serializer.player.PlayerSerializer
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.io.TagIO
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.UUID

/**
 * Responsible for loading and saving player data files.
 */
class DefaultPlayerDataSerializer(val folder: Path) : PlayerDataSerializer {

    override fun loadById(uuid: UUID): CompoundTag? {
        val playerFile = folder.resolve("$uuid.dat")
        if (!Files.exists(playerFile)) {
            try {
                Files.createFile(playerFile)
            } catch (exception: Exception) {
                LOGGER.warn("Failed to create player file for player with UUID $uuid!", exception)
            }
            return null
        }

        return try {
            TagIO.read(playerFile, TagCompression.GZIP)
        } catch (exception: IOException) {
            LOGGER.warn("Failed to load player data for player $uuid!", exception)
            null
        }
    }

    override fun load(player: KryptonPlayer): CompoundTag? {
        val nbt = loadById(player.uuid) ?: return null

        PlayerSerializer.load(player, nbt)

        return nbt
    }

    override fun save(player: KryptonPlayer): CompoundTag {
        val data = player.saveWithPassengers().build()

        // Create temp file and write data
        val temp = Files.createTempFile(folder, player.uuid.toString(), ".dat")
        TagIO.write(temp, data, TagCompression.GZIP)

        // Resolve actual file, and if it doesn't exist, rename the temp file
        val dataPath = folder.resolve("${player.uuid}.dat")
        if (!Files.exists(dataPath)) {
            Files.move(temp, dataPath, StandardCopyOption.REPLACE_EXISTING)
            return data
        }

        // Save the old data and then save the new data
        val oldDataPath = folder.resolve("${player.uuid}.dat_old")
        Files.deleteIfExists(oldDataPath)
        Files.move(dataPath, oldDataPath, StandardCopyOption.REPLACE_EXISTING)
        Files.deleteIfExists(dataPath)
        Files.move(temp, dataPath, StandardCopyOption.REPLACE_EXISTING)
        Files.deleteIfExists(temp)
        return data
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
