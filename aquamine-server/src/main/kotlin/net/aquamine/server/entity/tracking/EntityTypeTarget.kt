package net.aquamine.server.entity.tracking

import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.util.ImmutableLists

/**
 * A target for entities. This is used to separate entities in to groups in tracking, so
 * they can be retrieved as a group, which is faster than manually filtering a list
 * every time.
 */
interface EntityTypeTarget<out E : AquaEntity> {

    val type: Class<out E>
    val ordinal: Int

    companion object {

        @JvmField
        val ENTITIES: EntityTypeTarget<AquaEntity> = create(AquaEntity::class.java)
        @JvmField
        val PLAYERS: EntityTypeTarget<AquaPlayer> = create(AquaPlayer::class.java)

        @JvmField
        val VALUES: List<EntityTypeTarget<AquaEntity>> = ImmutableLists.of(ENTITIES, PLAYERS)

        private fun <E : AquaEntity> create(type: Class<E>): EntityTypeTarget<E> {
            val ordinal = DefaultEntityTracker.TARGET_COUNTER.getAndIncrement()
            return object : EntityTypeTarget<E> {

                override val type = type
                override val ordinal = ordinal
            }
        }
    }
}
