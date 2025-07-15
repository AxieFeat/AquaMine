package net.aquamine.server.util.serialization.visitors

import com.google.common.collect.ImmutableSet
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.TagType
import xyz.axie.nbt.visitor.StreamingTagVisitor
import java.util.ArrayDeque

class CollectFields(vararg selectors: FieldSelector) : CollectToTag() {

    private var fieldsToGetCount = selectors.size
    private val wantedTypes: Set<TagType<*>>
    private val stack = ArrayDeque<FieldTree>()

    init {
        val types = ImmutableSet.builder<TagType<*>>()
        val tree = FieldTree.createRoot()
        selectors.forEach {
            tree.addEntry(it)
            types.add(it.type)
        }
        stack.push(tree)
        types.add(CompoundTag.TYPE)
        wantedTypes = types.build()
    }

    override fun visitRootEntry(type: TagType<*>): StreamingTagVisitor.ValueResult {
        if (type != CompoundTag.TYPE) return StreamingTagVisitor.ValueResult.HALT
        return super.visitRootEntry(type)
    }

    override fun visitEntry(type: TagType<*>): StreamingTagVisitor.EntryResult {
        val tree = stack.element()
        if (depth() > tree.depth) return super.visitEntry(type)
        if (fieldsToGetCount <= 0) return StreamingTagVisitor.EntryResult.HALT
        return if (!wantedTypes.contains(type)) StreamingTagVisitor.EntryResult.SKIP else super.visitEntry(type)
    }

    override fun visitEntry(type: TagType<*>, name: String): StreamingTagVisitor.EntryResult {
        val tree = stack.element()
        if (depth() > tree.depth) return super.visitEntry(type, name)
        if (tree.selectedFields.remove(name, type)) {
            --fieldsToGetCount
            return super.visitEntry(type, name)
        }
        if (type == CompoundTag.TYPE) {
            val childTree = tree.fieldsToRecurse[name]
            if (childTree != null) {
                stack.push(childTree)
                return super.visitEntry(type, name)
            }
        }
        return StreamingTagVisitor.EntryResult.SKIP
    }

    override fun visitContainerEnd(): StreamingTagVisitor.ValueResult {
        if (depth() == stack.element().depth) stack.pop()
        return super.visitContainerEnd()
    }

    fun missingFieldCount(): Int = fieldsToGetCount
}
