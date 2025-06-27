package net.aquamine.server.entity.aquatic

import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.aquatic.Pufferfish
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.ItemType
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.PufferfishSerializer
import net.aquamine.server.world.AquaWorld

class AquaPufferfish(world: AquaWorld) : AquaFish(world), Pufferfish {

    override val type: AquaEntityType<AquaPufferfish>
        get() = AquaEntityTypes.PUFFERFISH
    override val serializer: EntitySerializer<AquaPufferfish>
        get() = PufferfishSerializer
    override val bucketType: ItemType
        get() = ItemTypes.PUFFERFISH_BUCKET.get()

    // This gets ticked up when the puff goal sets it to 1, and reset when the puff goal is stopped.
    private var inflateCounter = 0
    // This gets ticked up when the puff goal is cancelled, and reset when the puff goal is started.
    private var deflateTimer = 0

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Pufferfish.PUFF_STATE, 0)
    }

    override fun tick() {
        super.tick()
        handleInflation()
    }

    private fun handleInflation() {
        if (!isAlive() || !hasAI) return
        if (inflateCounter >= START_INFLATION_THRESHOLD) {
            // If this is > 0, the pufferfish has detected a scary mob within 2x its bounding box.
            if (getPuffState() == STATE_SMALL) {
                // The pufferfish is the very small, un-inflated version.
                // This will run the first time the puff goal is triggered.
                playSound(SoundEvents.PUFFER_FISH_BLOW_UP.get(), soundVolume(), voicePitch())
                setPuffState(STATE_MEDIUM) // Set it to the medium size, in between
            } else if (inflateCounter > INFLATE_TO_LARGE_THRESHOLD && getPuffState() == STATE_MEDIUM) {
                // The inflation counter has reached 40 ticks, it takes 2 seconds for the pufferfish to go from medium to large.
                // This will run on the 40th tick.
                playSound(SoundEvents.PUFFER_FISH_BLOW_UP.get(), soundVolume(), voicePitch())
                setPuffState(STATE_LARGE) // Set it to the large size
            }
            inflateCounter++ // Tick this up
        } else if (getPuffState() != STATE_SMALL) {
            // This will be the case when the pufferfish has reached large puff state but the puff goal has been stopped, resetting
            // the inflate counter to 0.
            if (deflateTimer > DEFLATE_TO_MEDIUM_THRESHOLD && getPuffState() == STATE_LARGE) {
                // After the puff goal has been cancelled, it takes 3 seconds before a large pufferfish deflates to medium
                playSound(SoundEvents.PUFFER_FISH_BLOW_OUT.get(), soundVolume(), voicePitch())
                setPuffState(STATE_MEDIUM) // Set it to the medium size
            } else if (deflateTimer > DEFLATE_TO_SMALL_THRESHOLD && getPuffState() == STATE_MEDIUM) {
                // After the pufferfish has deflated to medium, it takes 2 more seconds before it deflates back down to small.
                playSound(SoundEvents.PUFFER_FISH_BLOW_OUT.get(), soundVolume(), voicePitch())
                setPuffState(STATE_SMALL) // Set it to the small size
            }
            deflateTimer++ // Tick this up
        }
    }

    private fun getPuffState(): Int = data.get(MetadataKeys.Pufferfish.PUFF_STATE)

    private fun setPuffState(state: Int) {
        data.set(MetadataKeys.Pufferfish.PUFF_STATE, state)
    }

    companion object {

        private const val STATE_SMALL = 0
        private const val STATE_MEDIUM = 1
        private const val STATE_LARGE = 2
        private const val START_INFLATION_THRESHOLD = 1
        private const val INFLATE_TO_LARGE_THRESHOLD = 2 * 20 // 2 seconds in ticks
        private const val DEFLATE_TO_MEDIUM_THRESHOLD = 3 * 20 // 3 seconds in ticks
        private const val DEFLATE_TO_SMALL_THRESHOLD = 5 * 20 // 5 seconds in ticks
    }
}
