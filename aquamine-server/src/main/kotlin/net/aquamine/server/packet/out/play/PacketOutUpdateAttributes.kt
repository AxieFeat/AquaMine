package net.aquamine.server.packet.out.play

import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.attribute.BasicModifierOperation
import net.aquamine.server.entity.attribute.AquaAttribute
import net.aquamine.server.entity.attribute.AquaAttributeModifier
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.registry.AquaRegistries
import java.util.Collections

@JvmRecord
data class PacketOutUpdateAttributes(override val entityId: Int, val attributes: Collection<AttributeSnapshot>) : EntityPacket {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readList { _ ->
        val type = reader.readKey().let { requireNotNull(AquaRegistries.ATTRIBUTE.get(it)) { "Cannot find attribute type with key $it!" } }
        val base = reader.readDouble()
        val modifiers = reader.readList {
            val uuid = it.readUUID()
            val amount = it.readDouble()
            val operation = AquaAttributeModifier.getOperationById(it.readByte().toInt())
            AquaAttributeModifier(uuid, "Unknown read attribute", amount, operation)
        }
        AttributeSnapshot(type, base, modifiers)
    })

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeCollection(attributes) { attribute ->
            writer.writeKey(AquaRegistries.ATTRIBUTE.getKey(attribute.type)!!)
            writer.writeDouble(attribute.base)
            writer.writeCollection(attribute.modifiers) inner@{
                val operation = it.operation
                if (operation !is BasicModifierOperation) return@inner
                writer.writeUUID(it.uuid)
                writer.writeDouble(it.amount)
                writer.writeByte(operation.ordinal.toByte())
            }
        }
    }

    @JvmRecord
    data class AttributeSnapshot(val type: AttributeType, val base: Double, val modifiers: Collection<AttributeModifier>) {

        companion object {

            @JvmStatic
            fun from(attribute: AquaAttribute): AttributeSnapshot {
                return AttributeSnapshot(attribute.type, attribute.baseValue, Collections.unmodifiableCollection(attribute.modifiers))
            }
        }
    }

    companion object {

        @JvmStatic
        fun create(id: Int, attributes: Iterable<AquaAttribute>): PacketOutUpdateAttributes {
            return PacketOutUpdateAttributes(id, attributes.map { AttributeSnapshot.from(it) })
        }
    }
}
