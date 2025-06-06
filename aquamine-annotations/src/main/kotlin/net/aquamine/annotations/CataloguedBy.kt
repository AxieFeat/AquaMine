package net.aquamine.annotations

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * Indicates that the annotated type is catalogued by the
 * given [catalogue] type.
 *
 * For requirements of catalogue types, see the [Catalogue] annotation.
 *
 * @param catalogue the type of the catalogue
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Inherited
@MustBeDocumented
annotation class CataloguedBy(public val catalogue: KClass<*>)
