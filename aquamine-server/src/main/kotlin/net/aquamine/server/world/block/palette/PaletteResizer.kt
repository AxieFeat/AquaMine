package net.aquamine.server.world.block.palette

fun interface PaletteResizer<T> {

    fun onResize(newBits: Int, value: T): Int
}
