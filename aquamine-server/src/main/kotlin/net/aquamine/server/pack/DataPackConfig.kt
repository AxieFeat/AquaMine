package net.aquamine.server.pack

import com.google.common.collect.ImmutableList
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

class DataPackConfig(enabled: List<String>, disabled: List<String>) {

    val enabled: List<String> = ImmutableList.copyOf(enabled)
    val disabled: List<String> = ImmutableList.copyOf(disabled)

    companion object {

        @JvmField
        val DEFAULT: DataPackConfig = DataPackConfig(ImmutableList.of("vanilla"), ImmutableList.of())
        @JvmField
        val CODEC: Codec<DataPackConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.listOf().fieldOf("Enabled").getting { it.enabled },
                Codec.STRING.listOf().fieldOf("Disabled").getting { it.disabled }
            ).apply(instance) { enabled, disabled -> DataPackConfig(enabled, disabled) }
        }
    }
}
