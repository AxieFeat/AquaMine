package net.aquamine.server.adventure.provider

import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import java.util.function.Consumer

@Suppress("UnstableApiUsage")
class AquaGsonComponentSerializerProvider : GsonComponentSerializer.Provider {

    override fun gson(): GsonComponentSerializer =
        GsonComponentSerializer.builder().legacyHoverEventSerializer(NBTLegacyHoverEventSerializer).build()

    override fun gsonLegacy(): GsonComponentSerializer =
        GsonComponentSerializer.builder().legacyHoverEventSerializer(NBTLegacyHoverEventSerializer).downsampleColors().build()

    override fun builder(): Consumer<GsonComponentSerializer.Builder> = Consumer {}
}
