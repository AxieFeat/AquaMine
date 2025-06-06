package net.aquamine.api.entity.projectile

import net.aquamine.api.item.ItemStackLike

/**
 * A projectile that was thrown from an item.
 */
interface ThrowableProjectile : Projectile, ItemStackLike
