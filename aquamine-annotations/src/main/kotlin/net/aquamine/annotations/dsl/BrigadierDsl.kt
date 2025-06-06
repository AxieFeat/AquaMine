package net.aquamine.annotations.dsl

/**
 * Indicates the annotated element is part of the built-in DSL for Brigadier.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@DslMarker
annotation class BrigadierDsl
