package net.aquamine.generators.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.minecraft.SharedConstants
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

object DataGenerator {

    val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    private val output: Path = Path.of("./aquamine-server/src/main/resources/")

    @JvmStatic
    fun main(args: Array<String>) {
        GeneratorType.entries.forEach { type ->
            generate(type, type.generator)
        }
    }

    fun generate(type: GeneratorType, generator: Generator<*>) {
        val path = output.resolve(SharedConstants.getCurrentVersion().name + "_" + type.fileName + ".json")

        if (!Files.exists(path)) {
            Files.createDirectories(path.parent)
        }

        Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
            .use { writer ->
                gson.toJson(generator.generate(), writer)
            }
    }
}
