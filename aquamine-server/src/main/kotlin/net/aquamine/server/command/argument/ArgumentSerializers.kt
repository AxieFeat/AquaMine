package net.aquamine.server.command.argument

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.kyori.adventure.key.Key
import net.aquamine.server.command.argument.serializer.ArgumentSerializer
import net.aquamine.server.command.argument.serializer.DoubleArgumentSerializer
import net.aquamine.server.command.argument.serializer.EntityArgumentSerializer
import net.aquamine.server.command.argument.serializer.FloatArgumentSerializer
import net.aquamine.server.command.argument.serializer.IntegerArgumentSerializer
import net.aquamine.server.command.argument.serializer.LongArgumentSerializer
import net.aquamine.server.command.argument.serializer.SingletonArgumentSerializer
import net.aquamine.server.command.argument.serializer.StringArgumentSerializer
import net.aquamine.server.command.arguments.GameProfileArgument
import net.aquamine.server.command.arguments.NBTArgument
import net.aquamine.server.command.arguments.NBTCompoundArgument
import net.aquamine.server.command.arguments.SummonEntityArgument
import net.aquamine.server.command.arguments.VectorArgument
import net.aquamine.server.command.arguments.item.ItemStackArgumentType
import net.aquamine.server.command.arguments.item.ItemStackPredicateArgument
import net.aquamine.server.network.buffer.BinaryWriter
import java.util.concurrent.ConcurrentHashMap

/**
 * Holds all of the built-in argument serializers for all of the argument
 * types that we use that need to be sent to the client.
 */
object ArgumentSerializers {

    private val BY_CLASS = ConcurrentHashMap<Class<*>, Entry<*>>()
    private val BY_ID = Int2ObjectOpenHashMap<Entry<*>>()

    @JvmStatic
    fun bootstrap() {
        // Brigadier serializers
        singleton(0, "brigadier:bool", BoolArgumentType.bool())
        register(1, "brigadier:float", FloatArgumentSerializer)
        register(2, "brigadier:double", DoubleArgumentSerializer)
        register(3, "brigadier:integer", IntegerArgumentSerializer)
        register(4, "brigadier:long", LongArgumentSerializer)
        register(5, "brigadier:string", StringArgumentSerializer)

        // Built-in serializers
        register(6, "entity", EntityArgumentSerializer)
        singleton(7, "game_profile", GameProfileArgument)
        singleton(10, "vec3", VectorArgument.normal())
        singleton(14, "item_stack", ItemStackArgumentType)
        singleton(15, "item_predicate", ItemStackPredicateArgument)
        singleton(19, "nbt_compound_tag", NBTCompoundArgument)
        singleton(20, "nbt_tag", NBTArgument)
        singleton(40, "entity_summon", SummonEntityArgument)
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T : ArgumentType<*>> getByType(type: T): Entry<T>? = BY_CLASS[type.javaClass] as? Entry<T>

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T : ArgumentType<*>> getById(id: Int): Entry<T>? = BY_ID.get(id) as? Entry<T>

    @JvmStatic
    private inline fun <reified T : ArgumentType<*>> register(id: Int, name: String, serializer: ArgumentSerializer<T>) {
        register(id, name, T::class.java, serializer)
    }

    @JvmStatic
    private fun <T : ArgumentType<*>> register(id: Int, name: String, type: Class<T>, serializer: ArgumentSerializer<T>) {
        val entry = Entry(id, Key.key(name), type, serializer)
        BY_CLASS.put(type, entry)
        BY_ID.put(id, entry)
    }

    @JvmStatic
    private inline fun <reified T : ArgumentType<*>> singleton(id: Int, name: String, value: T) {
        register(id, name, SingletonArgumentSerializer(value))
    }

    @JvmStatic
    fun <T : ArgumentType<*>> write(writer: BinaryWriter, type: T) {
        val entry = checkNotNull(getByType(type)) { "Argument type for node must have registered serializer!" }
        writer.writeVarInt(entry.id)
        entry.serializer.write(writer, type)
    }

    @JvmRecord
    data class Entry<T : ArgumentType<*>>(val id: Int, val name: Key, val clazz: Class<T>, val serializer: ArgumentSerializer<T>)
}
