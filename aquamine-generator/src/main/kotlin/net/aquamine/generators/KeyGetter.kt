package net.aquamine.generators

import net.minecraft.resources.ResourceLocation

fun interface KeyGetter<T> {

    fun key(field: CollectedField<T>): ResourceLocation
}
