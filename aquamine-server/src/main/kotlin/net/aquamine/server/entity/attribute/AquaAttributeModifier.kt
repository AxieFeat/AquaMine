package net.aquamine.server.entity.attribute

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.entity.attribute.BasicModifierOperation
import net.aquamine.api.entity.attribute.ModifierOperation
import net.aquamine.server.util.nbt.getUUID
import xyz.axie.nbt.CompoundTag
import java.util.UUID
import java.util.function.Supplier

open class AquaAttributeModifier(
    final override val uuid: UUID,
    private val nameGetter: Supplier<String>,
    final override val amount: Double,
    override val operation: ModifierOperation
) : AttributeModifier {

    final override val name: String
        get() = nameGetter.get()

    constructor(uuid: UUID, name: String, amount: Double, operation: ModifierOperation) : this(uuid, { name }, amount, operation)

    final override fun equals(other: Any?): Boolean = this === other || other is AquaAttributeModifier && uuid == other.uuid

    final override fun hashCode(): Int = uuid.hashCode()

    override fun toString(): String = "AttributeModifier(uuid=$uuid, name=$name, amount=$amount, operation=$operation)"

    object Factory : AttributeModifier.Factory {

        override fun of(uuid: UUID, name: String, amount: Double, operation: ModifierOperation): AttributeModifier =
            AquaAttributeModifier(uuid, name, amount, operation)
    }

    @Suppress("JVM_STATIC_ON_CONST_OR_JVM_FIELD")
    companion object {

        @JvmStatic
        protected const val UUID_TAG = "UUID"
        @JvmStatic
        protected const val NAME_TAG = "Name"
        @JvmStatic
        protected const val AMOUNT_TAG = "Amount"
        @JvmStatic
        protected const val OPERATION_TAG = "Operation"

        @JvmStatic
        protected val LOGGER: Logger = LogManager.getLogger()
        private val BASIC_OPERATIONS = BasicModifierOperation.values()

        @JvmStatic
        fun load(data: CompoundTag): AquaAttributeModifier? {
            return try {
                AquaAttributeModifier(data.getUUID(UUID_TAG), data.getString(NAME_TAG), data.getDouble(AMOUNT_TAG), getOperation(data))
            } catch (exception: IllegalArgumentException) {
                LOGGER.warn("Unable to create attribute!", exception)
                null
            }
        }

        @JvmStatic
        protected fun getOperation(data: CompoundTag): BasicModifierOperation = getOperationById(data.getInt(OPERATION_TAG))

        @JvmStatic
        fun getOperationById(id: Int): BasicModifierOperation {
            require(id >= 0 && id < BASIC_OPERATIONS.size) { "No operation could be found with ID $id!" }
            return BASIC_OPERATIONS[id]
        }
    }
}
