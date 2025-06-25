package net.aquamine.server.world.material

import net.aquamine.api.block.PushReaction

class Material(
    val color: MaterialColor,
    val pushReaction: PushReaction,
    val blocksMotion: Boolean,
    val flammable: Boolean,
    val liquid: Boolean,
    val solidBlocking: Boolean,
    val replaceable: Boolean,
    val solid: Boolean
) {

    override fun toString(): String = "Material(color=$color, pushReaction=$pushReaction, blocksMotion=$blocksMotion, flammable=$flammable, " +
            "liquid=$liquid, solidBlocking=$solidBlocking, replaceable=$replaceable, solid=$solid)"

    class Builder(private val color: MaterialColor) {

        private var pushReaction = PushReaction.NORMAL
        private var blocksMotion = true
        private var flammable = false
        private var liquid = false
        private var solidBlocking = true
        private var replaceable = false
        private var solid = true

        fun liquid(): Builder = apply { liquid = true }

        fun notSolid(): Builder = apply { solid = false }

        fun noCollision(): Builder = apply { blocksMotion = false }

        fun notSolidBlocking(): Builder = apply { solidBlocking = false }

        fun flammable(): Builder = apply { flammable = true }

        fun replaceable(): Builder = apply { replaceable = true }

        fun destroyOnPush(): Builder = apply { pushReaction = PushReaction.DESTROY }

        fun notPushable(): Builder = apply { pushReaction = PushReaction.BLOCK }

        fun build(): Material = Material(color, pushReaction, blocksMotion, flammable, liquid, solidBlocking, replaceable, solid)
    }

    companion object {

        @JvmStatic
        fun builder(color: MaterialColor): Builder = Builder(color)
    }
}
