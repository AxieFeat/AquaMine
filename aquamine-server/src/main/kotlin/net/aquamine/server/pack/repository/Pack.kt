package net.aquamine.server.pack.repository

import com.mojang.brigadier.arguments.StringArgumentType
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.apache.logging.log4j.LogManager
import net.aquamine.server.pack.PackResources
import net.aquamine.server.pack.PackType
import net.aquamine.server.pack.metadata.PackMetadataSection
import java.util.function.Function

class Pack(
    private val id: String,
    private val required: Boolean,
    private val resources: ResourcesSupplier,
    private val title: Component,
    info: Info,
    private val compatibility: PackCompatibility,
    private val defaultPosition: Position,
    private val fixedPosition: Boolean,
    private val source: PackSource
) {

    private val description = info.description

    fun id(): String = id

    fun defaultPosition(): Position = defaultPosition

    fun isRequired(): Boolean = required

    fun isFixedPosition(): Boolean = fixedPosition

    fun getChatLink(green: Boolean): Component = Component.translatable().key("chat.square_brackets").args(source.decorate(Component.text(id)))
        .style {
            it.color(if (green) NamedTextColor.GREEN else NamedTextColor.RED)
            it.insertion(StringArgumentType.escapeIfRequired(id))
            it.hoverEvent(HoverEvent.showText(Component.text().append(title).appendNewline().append(description)))
        }
        .build()

    fun open(): PackResources = resources.open(id)

    override fun equals(other: Any?): Boolean = this === other || other is Pack && id == other.id

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Pack(id=$id, title=$title, description=$description, compatibility=$compatibility, " +
            "defaultPosition=$defaultPosition, required=$required, fixedPosition=$fixedPosition)"

    @JvmRecord
    data class Info(val description: Component, val format: Int) {

        fun compatibility(type: PackType): PackCompatibility = PackCompatibility.forFormat(format, type)
    }

    enum class Position {

        TOP,
        BOTTOM;

        fun <T> insert(list: MutableList<T>, value: T, opposite: Boolean, converter: Function<T, Pack>): Int {
            val position = if (opposite) opposite() else this
            if (position == BOTTOM) {
                var index = 0
                while (index < list.size) {
                    val pack = converter.apply(list.get(index))
                    if (!pack.isFixedPosition() || pack.defaultPosition != this) break
                    ++index
                }
                list.add(index, value)
                return index
            }
            var index = list.size - 1
            while (index >= 0) {
                val pack = converter.apply(list.get(index))
                if (!pack.isFixedPosition() || pack.defaultPosition != this) break
                --index
            }
            list.add(index + 1, value)
            return index + 1
        }

        private fun opposite(): Position = if (this == TOP) BOTTOM else TOP
    }

    fun interface ResourcesSupplier {

        fun open(packId: String): PackResources
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        fun readMetaAndCreate(id: String, title: Component, required: Boolean, resources: ResourcesSupplier, type: PackType,
                              defaultPosition: Position, source: PackSource): Pack? {
            val info = readPackInfo(id, resources)
            return if (info != null) create(id, title, required, resources, info, type, defaultPosition, false, source) else null
        }

        @JvmStatic
        private fun create(id: String, title: Component, required: Boolean, resources: ResourcesSupplier, info: Info, type: PackType,
                           defaultPosition: Position, fixedPosition: Boolean, source: PackSource): Pack {
            return Pack(id, required, resources, title, info, info.compatibility(type), defaultPosition, fixedPosition, source)
        }

        @JvmStatic
        fun readPackInfo(id: String, resourcesSupplier: ResourcesSupplier): Info? {
            try {
                resourcesSupplier.open(id).use { resources ->
                    val metadata = resources.getMetadataSection(PackMetadataSection.Serializer)
                    if (metadata == null) {
                        LOGGER.warn("Missing metadata in pack $id. Ignoring...")
                        return null
                    }
                    return Info(metadata.description, metadata.format)
                }
            } catch (exception: Exception) {
                LOGGER.warn("Failed to read pack metadata!", exception)
                return null
            }
        }
    }
}
