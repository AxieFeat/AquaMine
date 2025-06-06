package net.aquamine.api.plugin.annotation

import com.google.inject.BindingAnnotation
import java.nio.file.Path

/**
 * This is a marker annotation to signal that the requested [Path] to be
 * injected should be the path to the plugin's data folder.
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@BindingAnnotation
@MustBeDocumented
annotation class DataFolder
