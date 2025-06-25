package net.aquamine.server.util.serialization

import xyz.axie.nbt.EndTag
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.util.Types
import xyz.axie.nbt.visitor.StreamingTagVisitor
import xyz.axie.nbt.visitor.StreamingTagVisitor.ValueResult
import java.io.DataInput
import java.io.DataInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

object TagIOExtras {

    @JvmStatic
    fun parse(path: Path, visitor: StreamingTagVisitor, compression: TagCompression) {
        parse(Files.newInputStream(path), visitor, compression)
    }

    @JvmStatic
    fun parse(input: InputStream, visitor: StreamingTagVisitor, compression: TagCompression) {
        DataInputStream(compression.decompress(input)).use { parse(it, visitor) }
    }

    @JvmStatic
    fun parse(input: DataInput, visitor: StreamingTagVisitor) {
        val type = Types.of(input.readByte().toInt())
        if (type == EndTag.TYPE) {
            if (visitor.visitRootEntry(EndTag.TYPE) == ValueResult.CONTINUE) visitor.visitEnd()
            return
        }
        when (visitor.visitRootEntry(type)) {
            ValueResult.BREAK -> {
                input.skipBytes(input.readUnsignedShort()) // from StringTagImpl.skipString
                type.skip(input)
            }
            ValueResult.CONTINUE -> {
                input.skipBytes(input.readUnsignedShort()) // from StringTagImpl.skipString
                type.parse(input, visitor)
            }
            else -> Unit
        }
    }
}
