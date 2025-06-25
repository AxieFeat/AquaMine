package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Tamable
import net.aquamine.api.entity.player.Player
import net.aquamine.api.scoreboard.Team
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.TamableSerializer
import net.aquamine.server.world.KryptonWorld

abstract class KryptonTamable(world: KryptonWorld) : KryptonAnimal(world), Tamable {

    override val serializer: EntitySerializer<out KryptonTamable>
        get() = TamableSerializer

    final override var isOrderedToSit: Boolean = false
    final override var isSitting: Boolean
        get() = data.getFlag(MetadataKeys.Tamable.FLAGS, FLAG_SITTING)
        set(value) = data.setFlag(MetadataKeys.Tamable.FLAGS, FLAG_SITTING, value)
    override var isTamed: Boolean
        get() = data.getFlag(MetadataKeys.Tamable.FLAGS, FLAG_TAMED)
        set(value) = data.setFlag(MetadataKeys.Tamable.FLAGS, FLAG_TAMED, value)
    final override val owner: KryptonPlayer?
        get() = data.get(MetadataKeys.Tamable.OWNER)?.let(world.entityManager::getByUUID) as? KryptonPlayer
    final override val team: Team?
        get() = if (isTamed) owner?.team else super.team

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Tamable.FLAGS, 0)
        data.define(MetadataKeys.Tamable.OWNER, null)
    }

    final override fun tame(tamer: Player) {
        isTamed = true
        data.set(MetadataKeys.Tamable.OWNER, tamer.uuid)
        // TODO: Trigger tame animal advancement criteria
    }

    companion object {

        private const val FLAG_SITTING = 0
        private const val FLAG_TAMED = 2
    }
}
