package net.aquamine.annotations.dsl

/**
 * Indicates the annotated element is part of the DSL for dimension types.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@DslMarker
annotation class DimensionTypeDsl
