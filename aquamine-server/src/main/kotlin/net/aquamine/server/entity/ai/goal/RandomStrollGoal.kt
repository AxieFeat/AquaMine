package net.aquamine.server.entity.ai.goal

import net.aquamine.api.entity.ai.goal.Goal
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.util.random.RandomSource

class RandomStrollGoal(private val entity: AquaMob, private val radius: Int) : Goal {

    private val positions = getBlocksInRange(radius)
    private val random = RandomSource.create()

    private var lastStroll = 0L

    override fun canUse(): Boolean = System.currentTimeMillis() - lastStroll >= DELAY

    override fun start() {
        var remainingAttempt = positions.size
        while (remainingAttempt-- > 0) {
            val index = random.nextInt(positions.size)
            val position = positions[index]
            val target = entity.position.add(position)
            if (entity.navigator.tryPathTo(target.asVec3d())) break
        }
    }

    override fun tick(time: Long) {
        // Nothing to do
    }

    override fun shouldStop(): Boolean = entity.navigator.hasReachedTarget()

    override fun stop() {
        lastStroll = System.currentTimeMillis()
    }

    private fun getBlocksInRange(radius: Int): List<Vec3i> {
        val result = ArrayList<Vec3i>()
        for (x in -radius..radius) {
            for (y in -radius..radius) {
                for (z in -radius..radius) {
                    result.add(Vec3i(x, y, z))
                }
            }
        }
        return result
    }

    companion object {

        private const val DELAY = 2500
    }
}
