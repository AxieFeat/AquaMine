package net.aquamine.server.adventure.provider

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import java.util.function.Consumer

class AquaPlainTextComponentSerializerProvider : PlainTextComponentSerializer.Provider {

    override fun plainTextSimple(): PlainTextComponentSerializer = PlainTextComponentSerializer.builder().build()

    override fun plainText(): Consumer<PlainTextComponentSerializer.Builder> = Consumer {}
}
