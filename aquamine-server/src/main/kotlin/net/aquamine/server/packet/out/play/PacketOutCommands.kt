package net.aquamine.server.packet.out.play

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.tree.ArgumentCommandNode
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import com.mojang.brigadier.tree.RootCommandNode
import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import it.unimi.dsi.fastutil.ints.IntSet
import it.unimi.dsi.fastutil.ints.IntSets
import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntMaps
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.kyori.adventure.key.Key
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.SuggestionProviders
import net.aquamine.server.command.argument.ArgumentSerializers
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import java.util.function.BiPredicate
import java.util.function.Predicate

@JvmRecord
data class PacketOutCommands(
    val nodes: List<Node>,
    val rootIndex: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        nodes = reader.readList(::readNode),
        rootIndex = reader.readVarInt()
    ) {
        validateEntries(nodes) { node, indices -> node.canBuild(indices) }
        validateEntries(nodes) { node, indices -> node.canResolve(indices) }
    }

    override fun write(writer: BinaryWriter) {
        writer.writeCollection(nodes) { it.write(writer) }
        writer.writeVarInt(rootIndex)
    }

    @JvmRecord
    @Suppress("ArrayInDataClass")
    data class Node(val flags: Int, val children: IntArray, val redirectNode: Int, val data: NodeData?) : Writable {

        fun canBuild(indices: IntSet): Boolean = if (flags and FLAG_REDIRECT != 0) !indices.contains(redirectNode) else true

        fun canResolve(indices: IntSet): Boolean = children.all { !indices.contains(it) }

        override fun write(writer: BinaryWriter) {
            writer.writeByte(flags.toByte())
            writer.writeVarIntArray(children)
            if (flags and FLAG_REDIRECT != 0) writer.writeVarInt(redirectNode)
            data?.write(writer)
        }
    }

    sealed interface NodeData : Writable

    @JvmRecord
    data class LiteralNodeData(val name: String) : NodeData {

        override fun write(writer: BinaryWriter) {
            writer.writeString(name)
        }
    }

    @JvmRecord
    data class ArgumentNodeData<T>(val name: String, val type: ArgumentType<T>, val suggestionId: Key?) : NodeData {

        constructor(node: ArgumentCommandNode<CommandSourceStack, T>) : this(node.name, node.type, getId(node.customSuggestions))

        override fun write(writer: BinaryWriter) {
            writer.writeString(name)
            ArgumentSerializers.write(writer, type)
            if (suggestionId != null) writer.writeKey(suggestionId)
        }
    }

    companion object {

        private const val FLAG_EXECUTABLE = 0x04
        private const val FLAG_REDIRECT = 0x08
        private const val FLAG_SUGGESTIONS = 0x10
        private const val TYPE_ROOT = 0
        private const val TYPE_LITERAL = 1
        private const val TYPE_ARGUMENT = 2

        @JvmStatic
        fun createFromRootNode(root: RootCommandNode<CommandSourceStack>): PacketOutCommands {
            val enumerations = enumerate(root)
            return PacketOutCommands(createNodes(enumerations), enumerations.getInt(root))
        }

        @JvmStatic
        private fun createNodes(enumerations: Object2IntMap<CommandNode<CommandSourceStack>>): List<Node> {
            val nodes = ObjectArrayList<Node>(enumerations.size).apply { size(enumerations.size) }
            Object2IntMaps.fastForEach(enumerations) { nodes.set(it.intValue, createNode(it.key, enumerations)) }
            return nodes
        }

        @JvmStatic
        private fun createNode(node: CommandNode<CommandSourceStack>, enumerations: Object2IntMap<CommandNode<CommandSourceStack>>): Node {
            var flags = 0
            if (node.redirect != null) flags = flags or FLAG_REDIRECT
            val redirectNode = if (node.redirect != null) enumerations.getInt(node.redirect) else 0
            if (node.command != null) flags = flags or FLAG_EXECUTABLE
            val data = when (node) {
                is RootCommandNode<*> -> {
                    flags = flags or TYPE_ROOT
                    null
                }
                is ArgumentCommandNode<*, *> -> {
                    flags = flags or TYPE_ARGUMENT
                    if (node.customSuggestions != null) flags = flags or FLAG_SUGGESTIONS
                    ArgumentNodeData(node as ArgumentCommandNode<CommandSourceStack, *>)
                }
                is LiteralCommandNode<*> -> {
                    flags = flags or TYPE_LITERAL
                    LiteralNodeData(node.literal)
                }
                else -> throw UnsupportedOperationException("Unknown node type $node!")
            }
            val children = node.children.stream().mapToInt { enumerations.getInt(it) }.toArray()
            return Node(flags, children, redirectNode, data)
        }

        @JvmStatic
        private fun readNode(reader: BinaryReader): Node {
            val flags = reader.readByte().toInt()
            val children = reader.readVarIntArray()
            val redirectNode = if (flags and FLAG_REDIRECT != 0) reader.readVarInt() else 0
            return Node(flags, children, redirectNode, readNodeData(reader, flags))
        }

        @JvmStatic
        private fun readNodeData(reader: BinaryReader, flags: Int): NodeData? {
            return when (flags and 3) {
                2 -> {
                    val name = reader.readString()
                    val serializer = ArgumentSerializers.getById<ArgumentType<*>>(reader.readVarInt()) ?: return null
                    val type = serializer.serializer.read(reader)
                    val suggestionsType = if (flags and FLAG_SUGGESTIONS != 0) reader.readKey() else null
                    return ArgumentNodeData(name, type, suggestionsType)
                }
                1 -> LiteralNodeData(reader.readString())
                else -> null
            }
        }

        @JvmStatic
        private fun validateEntries(nodes: List<Node>, predicate: BiPredicate<Node, IntSet>) {
            val set = IntOpenHashSet(IntSets.fromTo(0, nodes.size))
            while (set.isNotEmpty()) {
                require(set.removeIf(Predicate { predicate.test(nodes.get(it), set) })) { "Server sent an impossible command tree!" }
            }
        }

        // a breadth-first search algorithm to enumerate a root node
        @JvmStatic
        private fun enumerate(root: RootCommandNode<CommandSourceStack>): Object2IntMap<CommandNode<CommandSourceStack>> {
            val result = Object2IntOpenHashMap<CommandNode<CommandSourceStack>>()
            val queue = ArrayDeque<CommandNode<CommandSourceStack>>()
            queue.add(root)

            while (queue.isNotEmpty()) {
                val element = queue.removeFirst()
                if (result.containsKey(element)) continue
                val size = result.size
                result.put(element, size)
                queue.addAll(element.children)
                if (element.redirect != null) queue.add(element.redirect)
            }

            return result
        }

        @JvmStatic
        private fun getId(provider: SuggestionProvider<CommandSourceStack>?): Key? = provider?.let(SuggestionProviders::getName)
    }
}
