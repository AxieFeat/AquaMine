package net.aquamine.server.shapes.util

fun interface BooleanOperator {

    fun apply(left: Boolean, right: Boolean): Boolean

    companion object {

        @JvmField
        val TRUE: BooleanOperator = BooleanOperator { _, _ -> true }
        @JvmField
        val FALSE: BooleanOperator = BooleanOperator { _, _ -> false }
        @JvmField
        val FIRST: BooleanOperator = BooleanOperator { left, _ -> left }
        @JvmField
        val SECOND: BooleanOperator = BooleanOperator { _, right -> right }
        @JvmField
        val NOT_FIRST: BooleanOperator = BooleanOperator { left, _ -> !left }
        @JvmField
        val NOT_SECOND: BooleanOperator = BooleanOperator { _, right -> !right }
        @JvmField
        val ONLY_FIRST: BooleanOperator = BooleanOperator { left, right -> left && !right }
        @JvmField
        val ONLY_SECOND: BooleanOperator = BooleanOperator { left, right -> !left && right }
        @JvmField
        val AND: BooleanOperator = BooleanOperator { left, right -> left && right }
        @JvmField
        val OR: BooleanOperator = BooleanOperator { left, right -> left || right }
        @JvmField
        val NOT_AND: BooleanOperator = BooleanOperator { left, right -> !left || !right }
        @JvmField
        val NOT_OR: BooleanOperator = BooleanOperator { left, right -> !left && !right }
        @JvmField
        val SAME: BooleanOperator = BooleanOperator { left, right -> left == right }
        @JvmField
        val NOT_SAME: BooleanOperator = BooleanOperator { left, right -> left != right }
        @JvmField
        val CAUSES: BooleanOperator = BooleanOperator { left, right -> !left || right }
        @JvmField
        val CAUSED_BY: BooleanOperator = BooleanOperator { left, right -> left || !right }
    }
}
