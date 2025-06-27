package net.aquamine.server.locale

import com.google.gson.stream.JsonReader
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.renderer.TranslatableComponentRenderer
import net.kyori.adventure.translation.TranslationRegistry
import java.text.MessageFormat
import java.util.Locale

object MinecraftTranslationManager {

    // A regex for an old format that was used previously by Minecraft.
    // This comes from vanilla's Language, and is only here because it's what vanilla does.
    private val UNSUPPORTED_FORMAT_REGEX = "%(\\d+\\\$)?[\\d.]*[df]".toRegex()

    @JvmField
    val REGISTRY: TranslationRegistry = TranslationRegistry.create(Key.key("aquamine", "minecraft_translations"))
    private val RENDERER = TranslatableComponentRenderer.usingTranslationSource(REGISTRY)

    @JvmStatic
    fun init() {
        val inputStream = checkNotNull(ClassLoader.getSystemResourceAsStream("en_us.json")) {
            "Unable to find built-in Minecraft locale file in JAR!"
        }
        JsonReader(inputStream.reader()).use { reader ->
            reader.beginObject()
            while (reader.hasNext()) {
                val key = reader.nextName()
                val value = reader.nextString().replace(UNSUPPORTED_FORMAT_REGEX, "%\$1s")
                REGISTRY.register(key, Locale.US, MessageFormat(value, Locale.US))
            }
            reader.endObject()
        }
    }

    @JvmStatic
    fun render(message: Component): Component = RENDERER.render(message, Locale.ENGLISH)
}
