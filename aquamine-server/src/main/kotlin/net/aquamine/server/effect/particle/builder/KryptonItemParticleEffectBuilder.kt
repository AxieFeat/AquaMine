package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.ItemParticleType
import net.aquamine.api.effect.particle.builder.ItemParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.item.ItemStack
import net.aquamine.server.effect.particle.data.AquaItemParticleData
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.downcast

class KryptonItemParticleEffectBuilder(type: ItemParticleType) : AbstractParticleEffectBuilder<ApiItem>(type), ApiItem {

    private var item: KryptonItemStack = KryptonItemStack.EMPTY

    override fun item(item: ItemStack): ApiItem = apply { this.item = item.downcast() }

    override fun buildData(): ParticleData = AquaItemParticleData(item)
}

private typealias ApiItem = ItemParticleEffectBuilder
