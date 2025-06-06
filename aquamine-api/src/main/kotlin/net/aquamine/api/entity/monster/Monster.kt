package net.aquamine.api.entity.monster

import net.aquamine.api.entity.Mob

/**
 * An entity that will attack the player under certain conditions.
 *
 * Generally, most implementations of this will attack the player regardless of
 * the conditions, for example, a [Zombie]. However, other types may not attack
 * the player if certain conditions are met.
 *
 * For more information on each type's conditions, see the respective type.
 */
interface Monster : Mob
