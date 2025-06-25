package net.aquamine.server.util.collection

object ListExtras {

    @JvmStatic
    inline fun <T> toIntArray(list: List<T>, converter: (T) -> Int): IntArray {
        val array = IntArray(list.size)
        for (i in list.indices) {
            array[i] = converter(list.get(i))
        }
        return array
    }
}
