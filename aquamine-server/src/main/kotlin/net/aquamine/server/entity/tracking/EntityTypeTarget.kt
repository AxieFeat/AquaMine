package net.aquamine.server.entity.tracking

import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.util.ImmutableLists

/**
 * A target for entities. This is used to separate entities in to groups in tracking, so
 * they can be retrieved as a group, which is faster than manually filtering a list
 * every time.
 */
interface EntityTypeTarget<out E : KryptonEntity> {

    val type: Class<out E>
    val ordinal: Int

    companion object {

        @JvmField
        val ENTITIES: EntityTypeTarget<KryptonEntity> = create(KryptonEntity::class.java)
        @JvmField
        val PLAYERS: EntityTypeTarget<KryptonPlayer> = create(KryptonPlayer::class.java)

        @JvmField
        val VALUES: List<EntityTypeTarget<KryptonEntity>> = ImmutableLists.of(ENTITIES, PLAYERS)

        private fun <E : KryptonEntity> create(type: Class<E>): EntityTypeTarget<E> {
            val ordinal = DefaultEntityTracker.TARGET_COUNTER.getAndIncrement()
            return object : EntityTypeTarget<E> {

                override val type = type
                override val ordinal = ordinal
            }
        }
    }
}
