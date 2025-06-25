package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Position
import net.aquamine.api.world.damage.EntityDamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.KryptonItemStack

open class KryptonEntityDamageSource(type: DamageType, final override val entity: KryptonEntity) : KryptonDamageSource(type), EntityDamageSource {

    override fun scalesWithDifficulty(): Boolean = entity is KryptonLivingEntity && entity !is KryptonPlayer

    override fun sourcePosition(): Position? = entity.position

    override fun entity(): KryptonEntity? = entity

    override fun formatDeathMessage(target: KryptonLivingEntity): Component {
        val heldItem = if (entity is KryptonLivingEntity) entity.getHeldItem(Hand.MAIN) else KryptonItemStack.EMPTY
        val baseMessage = "death.attack.${type.translationKey()}"
        if (!heldItem.isEmpty() && heldItem.meta.name != null) {
            return Component.translatable("$baseMessage.item", target.displayName, entity.displayName, heldItem.meta.name)
        }
        return Component.translatable(baseMessage, target.displayName, entity.displayName)
    }
}
