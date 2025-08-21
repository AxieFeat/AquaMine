package net.aquamine.server.entity

import net.aquamine.api.entity.LightningBolt
import net.aquamine.api.entity.player.Player
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.api.world.chunk.BlockChangeFlags
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.rule.GameRuleKeys

class AquaLightningBolt(world: AquaWorld) : AquaEntity(world), LightningBolt {

    override val type: AquaEntityType<AquaLightningBolt>
        get() = AquaEntityTypes.LIGHTNING_BOLT

    override var isEffect: Boolean = false
    override var lifeTicks: Int = 2
    override var flashCount: Int = world.random.nextInt(3) + 1
    override var causingPlayer: Player? = null

    override fun tick() {
        super.tick()
        if (!this.isEffect && this.lifeTicks == 2) {
            val difficulty = world.difficulty

            if (difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD) {
                spawnFire(4)
            }

            powerLightningRod()
            clearCopperOnLightningStrike()

            // TODO Add calling of lighting GameEvent here.
        }
    }

    private fun spawnFire(spreadAttempts: Int) {
        if (!this.isEffect && world.gameRules().getBoolean(GameRuleKeys.DO_FIRE_TICK)) {

            val blockPosition = position.asVec3i()

            setFireBlock(blockPosition)

            // TODO Uncomment when canSurvive will be implemented
            repeat(spreadAttempts) {
//                val randomBlockPosition = blockPosition.add(
//                    this.random.nextInt(3) - 1,
//                    this.random.nextInt(3) - 1,
//                    this.random.nextInt(3) - 1
//                )
//
//                setFireBlock(randomBlockPosition)
            }
        }
    }

    private fun setFireBlock(position: Vec3i) {
        val block = world.getBlock(position)

        if(block.isAir() && AquaBlocks.FIRE.propertiesProvider.canSurvive(block, world, position)) {
            world.setBlock(position, AquaBlocks.FIRE.defaultState, BlockChangeFlags.all().raw)
        }
    }

    private fun powerLightningRod() {
        val blockPosition = position.asVec3i()
        val block = world.getBlock(blockPosition)

        // TODO Add handling of rod
    }

    private fun clearCopperOnLightningStrike() {
        // TODO Add handling of clearing copper
    }
}
