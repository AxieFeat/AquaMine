package net.aquamine.server.entity.util

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.EntityType
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.util.downcastApiType

inline fun <A : Entity, reified I : KryptonEntity> A.downcast(): I = downcastApiType("Entity")

fun Entity.downcastBase(): KryptonEntity = downcast()

fun <E : Entity, KE : KryptonEntity> EntityType<E>.downcast(): KryptonEntityType<KE> = downcastApiType("EntityType")
