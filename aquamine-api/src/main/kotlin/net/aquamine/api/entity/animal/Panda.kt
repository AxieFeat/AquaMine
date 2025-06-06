package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.PandaGene

/**
 * A panda.
 */
interface Panda : Animal {

    /**
     * The main gene that this panda has.
     *
     * Panda genetics are very similar to real-world biology, except that, in
     * Minecraft, things are a lot more simple, as the pandas only have two
     * genes.
     *
     * There are dominant and recessive alleles, and dominant alleles will
     * always be expressed over recessive ones. If the panda has two recessive
     * alleles, the recessive trait will be expressed. The one special rule is
     * that if both genes are dominant, the main gene will always be expressed
     * over the hidden gene.
     */
    var knownGene: PandaGene

    /**
     * The hidden gene that this panda has.
     *
     * See [knownGene] for an explanation on panda genetics.
     */
    var hiddenGene: PandaGene

    /**
     * If this panda is currently unhappy.
     */
    val isUnhappy: Boolean

    /**
     * If this panda is currently sneezing.
     *
     * Baby pandas have a 1/6000, or 0.000166666%, chance to sneeze on any
     * tick. This is higher for weak baby pandas, at 1/500, or 0.002%.
     * When a baby panda sneezes, all adult pandas within a 10-block radius
     * will jump, and the baby panda has a 1/700 chance to drop a slimeball.
     */
    var isSneezing: Boolean

    /**
     * If this panda is currently eating.
     */
    var isEating: Boolean

    /**
     * If this panda is currently rolling.
     */
    var isRolling: Boolean

    /**
     * If this panda is currently sitting.
     */
    var isSitting: Boolean

    /**
     * If this panda is currently lying on its back.
     */
    var isLyingOnBack: Boolean

    /**
     * If this panda is scared.
     */
    val isScared: Boolean

    /**
     * The remaining time, in ticks, this panda will be unhappy for.
     */
    var unhappyTime: Int

    /**
     * The remaining time, in ticks, this panda will be unhappy for.
     */
    var sneezingTime: Int

    /**
     * The remaining time, in ticks, this panda will be unhappy for.
     */
    var eatingTime: Int
}
