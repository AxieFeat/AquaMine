package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Hand
import net.aquamine.api.world.damage.IndirectEntityDamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.item.AquaItemStack

class AquaIndirectEntityDamageSource(
    type: DamageType,
    entity: AquaEntity,
    override val indirectEntity: AquaEntity?
) : AquaEntityDamageSource(type, entity), IndirectEntityDamageSource {

    override fun directEntity(): AquaEntity = entity

    override fun entity(): AquaEntity? = indirectEntity

    override fun formatDeathMessage(target: AquaLivingEntity): Component {
        val displayName = indirectEntity?.displayName ?: entity.displayName
        val heldItem = if (indirectEntity is AquaLivingEntity) indirectEntity.getHeldItem(Hand.MAIN) else AquaItemStack.EMPTY
        val baseMessage = "death.attack.${type.translationKey()}"
        if (!heldItem.isEmpty() && heldItem.meta.name != null) {
            return Component.translatable("$baseMessage.item", target.displayName, displayName, heldItem.meta.name)
        }
        return Component.translatable(baseMessage, target.displayName, displayName)
    }
}
