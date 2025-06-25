package net.aquamine.server.world.data

import org.apache.logging.log4j.LogManager
import net.aquamine.server.KryptonPlatform
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.io.TagIO
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

class DefaultWorldDataSerializer(private val folder: Path) : WorldDataSerializer {

    override fun load(name: String): WorldData? {
        val path = folder.resolve(name)
        if (!Files.exists(path) || !Files.isDirectory(path)) return null
        val levelData = path.resolve("level.dat")
        if (Files.exists(levelData)) return read(path, levelData)
        val oldLevelData = path.resolve("level.dat_old")
        if (Files.exists(oldLevelData)) return read(path, oldLevelData)
        return null
    }

    private fun read(folder: Path, path: Path): PrimaryWorldData? {
        return try {
            val data = TagIO.read(path, TagCompression.GZIP).getCompound("Data")

            PrimaryWorldData.parse(data)
        } catch (exception: Exception) {
            LOGGER.error("Error whilst trying to read world at $folder!", exception)
            null
        }
    }

    override fun save(name: String, data: WorldData) {
        try {
            val path = folder.resolve(name)
            if (!Files.exists(path)) Files.createDirectories(path)

            val temp = Files.createTempFile(path, "level", ".dat")
            TagIO.write(temp, data.save(), TagCompression.GZIP)

            val dataPath = path.resolve("level.dat")
            if (!Files.exists(dataPath)) {
                Files.move(temp, dataPath, StandardCopyOption.REPLACE_EXISTING)
                return
            }

            val oldDataPath = path.resolve("level.dat_old")
            Files.deleteIfExists(oldDataPath)
            Files.move(dataPath, oldDataPath, StandardCopyOption.REPLACE_EXISTING)
            Files.deleteIfExists(dataPath)
            Files.move(temp, dataPath, StandardCopyOption.REPLACE_EXISTING)
        } catch (exception: Exception) {
            LOGGER.error("Failed to save data for world ${data.name}!", exception)
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
