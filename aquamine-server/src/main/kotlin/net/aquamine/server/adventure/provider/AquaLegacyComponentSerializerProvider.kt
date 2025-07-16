package net.aquamine.server.adventure.provider

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import java.util.function.Consumer

@Suppress("UnstableApiUsage")

class AquaLegacyComponentSerializerProvider : LegacyComponentSerializer.Provider {

    override fun legacy(): Consumer<LegacyComponentSerializer.Builder> = Consumer {}

    override fun legacyAmpersand(): LegacyComponentSerializer = createSerializer(LegacyComponentSerializer.AMPERSAND_CHAR)

    override fun legacySection(): LegacyComponentSerializer = createSerializer(LegacyComponentSerializer.SECTION_CHAR)

    private fun createSerializer(char: Char): LegacyComponentSerializer = LegacyComponentSerializer.builder()
        .character(char)
        .hexCharacter(LegacyComponentSerializer.HEX_CHAR)
        .build()
}
