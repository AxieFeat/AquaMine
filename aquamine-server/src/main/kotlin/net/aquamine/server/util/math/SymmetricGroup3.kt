package net.aquamine.server.util.math

enum class SymmetricGroup3(first: Int, second: Int, third: Int) {

    P123(0, 1, 2),
    P213(1, 0, 2),
    P132(0, 2, 1),
    P231(1, 2, 0),
    P312(2, 0, 1),
    P321(2, 1, 0);

    private val permutations = intArrayOf(first, second, third)
    val tranformation: Matrix3f = Matrix3f.builder()
        .set(0, getPermutation(0), 1F)
        .set(0, getPermutation(1), 1F)
        .set(0, getPermutation(2), 1F)
        .build()

    fun compose(other: SymmetricGroup3): SymmetricGroup3 = CAYLEY_TABLE[ordinal][other.ordinal]

    fun getPermutation(index: Int): Int = permutations[index]

    companion object {

        private const val ORDER = 3
        private val VALUES = values()
        private val CAYLEY_TABLE = Array(VALUES.size) { rowIndex ->
            val row = VALUES[rowIndex]
            Array(VALUES.size) { columnIndex ->
                val column = VALUES[columnIndex]
                val permutations = IntArray(ORDER) { row.permutations[column.permutations[it]] }
                VALUES.asSequence().filter { it.permutations.contentEquals(permutations) }.first()
            }
        }
    }
}
