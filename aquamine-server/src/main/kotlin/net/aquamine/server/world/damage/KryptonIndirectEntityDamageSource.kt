package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Hand
import net.aquamine.api.world.damage.IndirectEntityDamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.item.KryptonItemStack

class KryptonIndirectEntityDamageSource(
    type: DamageType,
    entity: KryptonEntity,
    override val indirectEntity: KryptonEntity?
) : KryptonEntityDamageSource(type, entity), IndirectEntityDamageSource {

    override fun directEntity(): KryptonEntity = entity

    override fun entity(): KryptonEntity? = indirectEntity

    override fun formatDeathMessage(target: KryptonLivingEntity): Component {
        val displayName = indirectEntity?.displayName ?: entity.displayName
        val heldItem = if (indirectEntity is KryptonLivingEntity) indirectEntity.getHeldItem(Hand.MAIN) else KryptonItemStack.EMPTY
        val baseMessage = "death.attack.${type.translationKey()}"
        if (!heldItem.isEmpty() && heldItem.meta.name != null) {
            return Component.translatable("$baseMessage.item", target.displayName, displayName, heldItem.meta.name)
        }
        return Component.translatable(baseMessage, target.displayName, displayName)
    }
}
