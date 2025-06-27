package net.aquamine.server.world.damage

import net.aquamine.api.world.damage.DamageSource
import net.aquamine.server.util.downcastApiType

fun DamageSource.downcast(): AquaDamageSource = downcastApiType("DamageSource")
