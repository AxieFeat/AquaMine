package net.aquamine.server.util.serialization.visitors

import xyz.axie.nbt.TagType
import xyz.axie.nbt.visitor.StreamingTagVisitor

@Suppress("MethodOverloading") // Inherited from supertype, not our problem.
object SkipAll : StreamingTagVisitor {

    override fun visitEnd(): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: String): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Byte): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Short): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Int): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Long): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Float): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: Double): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: ByteArray): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: IntArray): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visit(value: LongArray): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visitList(type: TagType<*>, size: Int): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visitElement(type: TagType<*>, index: Int): StreamingTagVisitor.EntryResult = StreamingTagVisitor.EntryResult.SKIP

    override fun visitEntry(type: TagType<*>): StreamingTagVisitor.EntryResult = StreamingTagVisitor.EntryResult.SKIP

    override fun visitEntry(type: TagType<*>, name: String): StreamingTagVisitor.EntryResult = StreamingTagVisitor.EntryResult.SKIP

    override fun visitContainerEnd(): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE

    override fun visitRootEntry(type: TagType<*>): StreamingTagVisitor.ValueResult = StreamingTagVisitor.ValueResult.CONTINUE
}
