package net.aquamine.server.entity.attribute

import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.server.util.downcastApiType

fun AttributeType.downcast(): KryptonAttributeType = downcastApiType("AttributeType")
