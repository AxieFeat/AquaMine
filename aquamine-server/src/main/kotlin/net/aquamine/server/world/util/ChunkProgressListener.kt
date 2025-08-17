package net.aquamine.server.world.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.apache.logging.log4j.LogManager
import net.aquamine.server.util.math.Maths
import net.aquamine.server.locale.MinecraftTranslationManager

class ChunkProgressListener(radius: Int) {

    private val area = (radius * 2 + 1) * (radius * 2 + 1)
    private var count = 0
    private var startTime = 0L
    private var nextTickTime = Long.MAX_VALUE
    private var previousProgress = -1

    private fun calculateProgress(): Int = Maths.floor(count.toFloat() * FRACTION_TO_PERCENTAGE / area.toFloat())

    fun stop() {
        LOGGER.info("Time elapsed: ${System.currentTimeMillis() - startTime} ms")
        nextTickTime = Long.MAX_VALUE
    }

    fun tick() {
        nextTickTime = System.currentTimeMillis()
        startTime = nextTickTime
    }

    fun updateStatus() {
        ++count

        val progress = calculateProgress()

        if (System.currentTimeMillis() > nextTickTime) {
            nextTickTime += MILLISECONDS_PER_TICK
            if(progress != previousProgress) {
                val progressText = Component.text(Maths.clamp(progress, 0, 100))

                val message = MinecraftTranslationManager.render(Component.translatable("menu.preparingSpawn", progressText))
                LOGGER.info(PlainTextComponentSerializer.plainText().serialize(message))
            }
            previousProgress = progress
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private const val MILLISECONDS_PER_TICK = 50L
        private const val FRACTION_TO_PERCENTAGE = 100F
    }
}
