package net.aquamine.server.potion

import net.aquamine.server.entity.AquaLivingEntity

/**
 * A general handler for a type of potion.
 */
class PotionEffectHandler(
    var applyHandler: HandlerSupplier? = null,
    var tickHandler: TickHandlerSupplier? = null,
    var endHandler: HandlerSupplier? = null
) {

    /**
     * Calls on applying potion effect to entity.
     */
    fun onApply(handler: HandlerSupplier) { applyHandler = handler }

    /**
     * Calls on every potion time tick.
     */
    fun onTick(handler: TickHandlerSupplier) { tickHandler = handler }

    /**
     * Calls on ending of effect.
     */
    fun onEnd(handler: HandlerSupplier) { endHandler = handler }

    fun interface HandlerSupplier {
        fun apply(entity: AquaLivingEntity, effect: AquaPotionEffect)
    }
    fun interface TickHandlerSupplier {
        fun apply(entity: AquaLivingEntity, effect: AquaPotionEffect, ticksToEnd: Int)
    }
}
