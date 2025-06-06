package net.aquamine.api.entity

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * A category of entity that applies certain spawning mechanics and behaviors.
 */
@CataloguedBy(EntityCategories::class)
@ImmutableType
interface EntityCategory : Keyed {

    /**
     * If the mob is friendly towards the player.
     */
    val isFriendly: Boolean

    /**
     * The distance that the mob has to be from the player to be despawned.
     */
    val despawnDistance: Int
}
