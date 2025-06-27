package net.aquamine.server.world.rule

import com.google.common.collect.ImmutableMap
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.kyori.adventure.translation.Translatable
import org.apache.logging.log4j.LogManager
import net.aquamine.api.world.rule.GameRule
import net.aquamine.server.AquaServer
import net.aquamine.server.command.CommandSourceStack
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import org.kryptonmc.serialization.DynamicLike
import java.util.function.BiConsumer
import java.util.function.Function
import java.util.function.Supplier

class WorldGameRules(private val rules: MutableMap<Key<*>, Value<*>>) {

    constructor() : this(GameRuleKeys.types().entries.stream().collect(ImmutableMap.toImmutableMap({ it.key }, { it.value.createRule() })))

    constructor(data: DynamicLike<*>) : this() {
        loadFromTag(data)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Value<T>> getRule(key: Key<T>): T = rules.get(key)!! as T

    fun getBoolean(key: Key<BooleanValue>): Boolean = getRule(key).get()

    fun getInt(key: Key<IntegerValue>): Int = getRule(key).get()

    private fun loadFromTag(data: DynamicLike<*>) {
        rules.forEach { (key, value) -> data.get(key.id).asString().result().ifPresent { value.deserialize(it) } }
    }

    fun save(): CompoundTag = compound {
        rules.forEach { (key, value) -> putString(key.id, value.serialize()) }
    }

    class Key<T : Value<T>>(val id: String, val category: Category) : GameRule<Any>, Translatable {

        override val name: String
            get() = id
        override val defaultValue: Any
            get() = get(GameRuleKeys.types().get(this)!!.createRule())

        fun get(value: Value<*>): Any = when (value) {
            is BooleanValue -> value.get()
            is IntegerValue -> value.get()
        }

        override fun equals(other: Any?): Boolean = this === other || other is Key<*> && id == other.id

        override fun hashCode(): Int = id.hashCode()

        override fun toString(): String = id

        override fun translationKey(): String = "gamerule.$id"
    }

    enum class Category(private val descriptionId: String) : Translatable {

        PLAYER("gamerule.category.player"),
        MOBS("gamerule.category.mobs"),
        SPAWNING("gamerule.category.spawning"),
        DROPS("gamerule.category.drops"),
        UPDATES("gamerule.category.updates"),
        CHAT("gamerule.category.chat"),
        MISC("gamerule.category.misc");

        override fun translationKey(): String = descriptionId
    }

    class Type<T : Value<T>>(
        private val argument: Supplier<ArgumentType<*>>,
        private val constructor: Function<Type<T>, T>,
        internal val callback: BiConsumer<AquaServer, T>,
        private val visitorCaller: VisitorCaller<T>
    ) {

        fun createArgument(name: String): RequiredArgumentBuilder<CommandSourceStack, *> = RequiredArgumentBuilder.argument(name, argument.get())

        fun createRule(): T = constructor.apply(this)

        fun callVisitor(visitor: TypeVisitor, key: Key<T>) {
            visitorCaller.call(visitor, key, this)
        }
    }

    sealed class Value<T : Value<T>>(protected val type: Type<T>) {

        protected abstract fun updateFromArgument(context: CommandContext<CommandSourceStack>, parameterName: String)

        fun setFromArgument(context: CommandContext<CommandSourceStack>, parameterName: String) {
            updateFromArgument(context, parameterName)
            onUpdate(context.source.server)
        }

        protected fun onUpdate(server: AquaServer?) {
            if (server != null) type.callback.accept(server, self())
        }

        abstract fun deserialize(value: String)

        abstract fun serialize(): String

        override fun toString(): String = serialize()

        abstract fun commandResult(): Int

        protected abstract fun self(): T

        protected abstract fun copy(): T

        abstract fun setFrom(value: T, server: AquaServer?)
    }

    class BooleanValue(type: Type<BooleanValue>, private var value: Boolean) : Value<BooleanValue>(type) {

        override fun updateFromArgument(context: CommandContext<CommandSourceStack>, parameterName: String) {
            value = BoolArgumentType.getBool(context, parameterName)
        }

        fun get(): Boolean = value

        fun set(newValue: Boolean, server: AquaServer?) {
            value = newValue
            onUpdate(server)
        }

        override fun serialize(): String = value.toString()

        override fun deserialize(value: String) {
            this.value = value.toBoolean()
        }

        override fun commandResult(): Int = if (value) 1 else 0

        override fun self(): BooleanValue = this

        override fun copy(): BooleanValue = BooleanValue(type, value)

        override fun setFrom(value: BooleanValue, server: AquaServer?) {
            this.value = value.value
            onUpdate(server)
        }

        companion object {

            @JvmStatic
            fun create(defaultValue: Boolean, changeListener: BiConsumer<AquaServer, BooleanValue>): Type<BooleanValue> =
                Type({ BoolArgumentType.bool() }, { BooleanValue(it, defaultValue) }, changeListener, TypeVisitor::visitBoolean)

            @JvmStatic
            fun create(defaultValue: Boolean): Type<BooleanValue> = create(defaultValue) { _, _ -> }
        }
    }

    class IntegerValue(type: Type<IntegerValue>, private var value: Int) : Value<IntegerValue>(type) {

        override fun updateFromArgument(context: CommandContext<CommandSourceStack>, parameterName: String) {
            value = IntegerArgumentType.getInteger(context, parameterName)
        }

        fun get(): Int = value

        fun set(newValue: Int, server: AquaServer?) {
            value = newValue
            onUpdate(server)
        }

        override fun serialize(): String = value.toString()

        override fun deserialize(value: String) {
            this.value = safeParse(value)
        }

        fun tryDeserialize(value: String): Boolean {
            try {
                this.value = value.toInt()
                return true
            } catch (_: NumberFormatException) {
                return false
            }
        }

        override fun commandResult(): Int = value

        override fun self(): IntegerValue = this

        override fun copy(): IntegerValue = IntegerValue(type, value)

        override fun setFrom(value: IntegerValue, server: AquaServer?) {
            this.value = value.value
            onUpdate(server)
        }

        companion object {

            @JvmStatic
            fun create(defaultValue: Int, changeListener: BiConsumer<AquaServer, IntegerValue>): Type<IntegerValue> =
                Type({ IntegerArgumentType.integer() }, { IntegerValue(it, defaultValue) }, changeListener, TypeVisitor::visitInteger)

            @JvmStatic
            fun create(defaultValue: Int): Type<IntegerValue> = create(defaultValue) { _, _ -> }

            @JvmStatic
            private fun safeParse(input: String): Int {
                if (input.isNotEmpty()) {
                    try {
                        return input.toInt()
                    } catch (exception: NumberFormatException) {
                        LOGGER.warn("Failed to parse integer $input!", exception)
                    }
                }
                return 0
            }
        }
    }

    interface TypeVisitor {

        fun <T : Value<T>> visit(key: Key<T>, type: Type<T>) {
            // Do nothing by default
        }

        fun visitBoolean(key: Key<BooleanValue>, type: Type<BooleanValue>) {
            // Do nothing by default
        }

        fun visitInteger(key: Key<IntegerValue>, type: Type<IntegerValue>) {
            // Do nothing by default
        }
    }

    fun interface VisitorCaller<T : Value<T>> {

        fun call(visitor: TypeVisitor, key: Key<T>, type: Type<T>)
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
