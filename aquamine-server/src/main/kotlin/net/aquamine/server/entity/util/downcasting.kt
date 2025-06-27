package net.aquamine.server.entity.util

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.EntityType
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.util.downcastApiType

inline fun <A : Entity, reified I : AquaEntity> A.downcast(): I = downcastApiType("Entity")

fun Entity.downcastBase(): AquaEntity = downcast()

fun <E : Entity, KE : AquaEntity> EntityType<E>.downcast(): AquaEntityType<KE> = downcastApiType("EntityType")
