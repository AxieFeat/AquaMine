package net.aquamine.spark

import me.lucko.spark.common.util.classfinder.ClassFinder

class AquaClassFinder : ClassFinder {

    override fun findClass(clazz: String): Class<*>? {
        return Class.forName(clazz)
    }

}