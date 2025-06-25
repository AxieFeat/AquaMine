package net.aquamine.server.util.serialization.visitors

import xyz.axie.nbt.ByteArrayTag
import xyz.axie.nbt.ByteTag
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.DoubleTag
import xyz.axie.nbt.EndTag
import xyz.axie.nbt.FloatTag
import xyz.axie.nbt.IntArrayTag
import xyz.axie.nbt.IntTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.LongArrayTag
import xyz.axie.nbt.LongTag
import xyz.axie.nbt.MutableCompoundTag
import xyz.axie.nbt.MutableListTag
import xyz.axie.nbt.ShortTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.Tag
import xyz.axie.nbt.TagType
import xyz.axie.nbt.visitor.StreamingTagVisitor
import java.util.ArrayDeque
import java.util.function.Consumer

@Suppress("MethodOverloading") // Inherited from supertype, not our problem.
open class CollectToTag : StreamingTagVisitor {

    private var lastName = ""
    private var rootTag: Tag? = null
    private val consumerStack = ArrayDeque<Consumer<Tag>>()

    fun result(): Tag? = rootTag

    protected fun depth(): Int = consumerStack.size

    protected fun appendEntry(tag: Tag) {
        consumerStack.last.accept(tag)
    }

    override fun visitEnd(): StreamingTagVisitor.ValueResult {
        appendEntry(EndTag.INSTANCE)
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: String): StreamingTagVisitor.ValueResult {
        appendEntry(StringTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Byte): StreamingTagVisitor.ValueResult {
        appendEntry(ByteTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Short): StreamingTagVisitor.ValueResult {
        appendEntry(ShortTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Int): StreamingTagVisitor.ValueResult {
        appendEntry(IntTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Long): StreamingTagVisitor.ValueResult {
        appendEntry(LongTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Float): StreamingTagVisitor.ValueResult {
        appendEntry(FloatTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: Double): StreamingTagVisitor.ValueResult {
        appendEntry(DoubleTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: ByteArray): StreamingTagVisitor.ValueResult {
        appendEntry(ByteArrayTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: IntArray): StreamingTagVisitor.ValueResult {
        appendEntry(IntArrayTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visit(value: LongArray): StreamingTagVisitor.ValueResult {
        appendEntry(LongArrayTag.of(value))
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visitList(type: TagType<*>, size: Int): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visitElement(type: TagType<*>, index: Int): StreamingTagVisitor.EntryResult {
        enterContainerIfNeeded(type)
        return StreamingTagVisitor.EntryResult.ENTER
    }

    override fun visitEntry(type: TagType<*>): StreamingTagVisitor.EntryResult = StreamingTagVisitor.EntryResult.ENTER

    override fun visitEntry(type: TagType<*>, name: String): StreamingTagVisitor.EntryResult {
        lastName = name
        enterContainerIfNeeded(type)
        return StreamingTagVisitor.EntryResult.ENTER
    }

    private fun enterContainerIfNeeded(type: TagType<*>) {
        if (type === ListTag.TYPE) {
            val list = MutableListTag.empty()
            appendEntry(list)
            consumerStack.addLast { list.add(it) }
        } else if (type === CompoundTag.TYPE) {
            val compound = MutableCompoundTag.empty()
            appendEntry(compound)
            consumerStack.addLast { compound.put(lastName, it) }
        }
    }

    override fun visitContainerEnd(): StreamingTagVisitor.ValueResult {
        consumerStack.removeLast()
        return StreamingTagVisitor.ValueResult.CONTINUE
    }

    override fun visitRootEntry(type: TagType<*>): StreamingTagVisitor.ValueResult {
        when (type) {
            ListTag.TYPE -> {
                val list = MutableListTag.empty()
                rootTag = list
                consumerStack.addLast { list.add(it) }
            }
            CompoundTag.TYPE -> {
                val compound = MutableCompoundTag.empty()
                rootTag = compound
                consumerStack.addLast { compound.put(lastName, it) }
            }
            else -> consumerStack.addLast { rootTag = it }
        }
        return StreamingTagVisitor.ValueResult.CONTINUE
    }
}
