package net.aquamine.server.entity.components

import net.aquamine.api.entity.Entity
import net.aquamine.server.entity.system.EntityWaterPhysicsSystem

interface WaterPushable : Entity {

    val waterPhysicsSystem: EntityWaterPhysicsSystem

    override fun isInWater(): Boolean = waterPhysicsSystem.isInWater()

    override fun isInLava(): Boolean = waterPhysicsSystem.isInLava()

    override fun isUnderwater(): Boolean = waterPhysicsSystem.isUnderwater()

    fun isPushedByFluid(): Boolean = true
}
