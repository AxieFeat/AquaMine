package net.aquamine.server.command.arguments.coordinates

@JvmRecord
data class TextCoordinates(val x: Char, val y: Char, val z: Char) {

    companion object {

        const val LOCAL_MODIFIER: Char = '^'
        const val RELATIVE_MODIFIER: Char = '~'

        @JvmField
        val CENTER_LOCAL: TextCoordinates = TextCoordinates(LOCAL_MODIFIER, LOCAL_MODIFIER, LOCAL_MODIFIER)
        @JvmField
        val CENTER_GLOBAL: TextCoordinates = TextCoordinates(RELATIVE_MODIFIER, RELATIVE_MODIFIER, RELATIVE_MODIFIER)
    }
}
