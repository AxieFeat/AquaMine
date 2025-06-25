package net.aquamine.server.util.serialization.visitors

import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.TagType
import xyz.axie.nbt.visitor.StreamingTagVisitor
import java.util.ArrayDeque

class SkipFields(vararg selectors: FieldSelector) : CollectToTag() {

    private val stack = ArrayDeque<FieldTree>()

    init {
        val rootTree = FieldTree.createRoot()
        selectors.forEach { rootTree.addEntry(it) }
        stack.push(rootTree)
    }

    override fun visitEntry(type: TagType<*>, name: String): StreamingTagVisitor.EntryResult {
        val tree = stack.element()
        if (tree.isSelected(type, name)) return StreamingTagVisitor.EntryResult.SKIP
        if (type == CompoundTag.TYPE) {
            val childTree = tree.fieldsToRecurse.get(name)
            if (childTree != null) stack.push(childTree)
        }
        return super.visitEntry(type, name)
    }

    override fun visitContainerEnd(): StreamingTagVisitor.ValueResult {
        if (depth() == stack.element().depth) stack.pop()
        return super.visitContainerEnd()
    }
}
