package net.aquamine.annotations.dsl

/**
 * Indicates the annotated element is part of the DSL for inventories.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@DslMarker
annotation class InventoryDsl