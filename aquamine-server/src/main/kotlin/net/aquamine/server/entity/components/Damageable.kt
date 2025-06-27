package net.aquamine.server.entity.components

import net.aquamine.api.entity.Entity
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.server.world.damage.AquaDamageSource
import net.aquamine.server.world.damage.downcast

interface Damageable : Entity {

    fun isInvulnerableTo(source: AquaDamageSource): Boolean

    fun damage(source: AquaDamageSource, damage: Float): Boolean

    override fun damage(source: DamageSource, damage: Float): Boolean = damage(source.downcast(), damage)
}
