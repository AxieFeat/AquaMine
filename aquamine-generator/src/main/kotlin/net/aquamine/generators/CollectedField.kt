package net.aquamine.generators

import java.lang.reflect.Field

/**
 * This class exists to bridge the gap between actual fields and synthetic
 * fields.
 *
 * When generating sound events, we need to account for the really annoying
 * "GOAT_HORN_SOUND_VARIANTS" field, which is a generated list of sound events.
 * What we do with this is collect all the normal fields, then get that
 * specific list and turn it in to a list of collected fields, all with names
 * like "GOAT_HORN_SOUND_VARIANT_{index}" and the event as the value.
 */
@JvmRecord
data class CollectedField<T>(val name: String, val value: T) {

    constructor(field: Field, type: Class<T>) : this(field.name, type.cast(field.get(null)))
}
