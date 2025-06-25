package net.aquamine.server.entity.components

import net.aquamine.api.entity.Entity
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.server.world.damage.KryptonDamageSource
import net.aquamine.server.world.damage.downcast

interface Damageable : Entity {

    fun isInvulnerableTo(source: KryptonDamageSource): Boolean

    fun damage(source: KryptonDamageSource, damage: Float): Boolean

    override fun damage(source: DamageSource, damage: Float): Boolean = damage(source.downcast(), damage)
}
