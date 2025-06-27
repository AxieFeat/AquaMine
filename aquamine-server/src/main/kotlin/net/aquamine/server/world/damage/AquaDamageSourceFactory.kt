package net.aquamine.server.world.damage

import net.aquamine.api.entity.Entity
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.api.world.damage.EntityDamageSource
import net.aquamine.api.world.damage.IndirectEntityDamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.util.downcast

object AquaDamageSourceFactory : DamageSource.Factory {

    override fun of(type: DamageType): DamageSource = AquaDamageSource(type)

    override fun entity(type: DamageType, entity: Entity): EntityDamageSource = AquaEntityDamageSource(type, entity.downcast())

    override fun indirectEntity(type: DamageType, entity: Entity, indirectEntity: Entity): IndirectEntityDamageSource =
        AquaIndirectEntityDamageSource(type, entity.downcast(), indirectEntity.downcast())
}
