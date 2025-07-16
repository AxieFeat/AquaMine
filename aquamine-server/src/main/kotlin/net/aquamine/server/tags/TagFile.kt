package net.aquamine.server.tags

import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class TagFile(val entries: List<TagEntry>, val replace: Boolean) {

    companion object {

        @JvmField
        val CODEC: Codec<TagFile> = RecordCodecBuilder.create { instance ->
            instance.group(
                TagEntry.CODEC.listOf().fieldOf("values").getting { it.entries },
                Codec.BOOLEAN.optionalFieldOf("replace", false).getting { it.replace }
            ).apply(instance, ::TagFile)
        }
    }
}
