package net.aquamine.server.world.block.palette

class MissingPaletteEntryException(index: Int) : RuntimeException("Missing palette entry at index $index!")
