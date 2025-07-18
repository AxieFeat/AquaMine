package net.aquamine.generators.data

import com.google.gson.JsonElement
import net.minecraft.SharedConstants
import net.minecraft.data.Main
import net.minecraft.server.Bootstrap
import java.nio.file.Files
import java.nio.file.Path

abstract class Generator<T> {

    abstract val names: Map<T, String>

    abstract fun generate(): JsonElement

    companion object {

        protected val DATA_FOLDER: Path

        init {
            SharedConstants.tryDetectVersion()
            Bootstrap.bootStrap()

            val tempDir = Files.createTempDirectory("mojang_gen_data")
            Main.main(
                arrayOf<String>(
                    "--all",
                    "--output=$tempDir"
                )
            )

            DATA_FOLDER = tempDir.resolve("data").resolve("minecraft")
        }
    }
}