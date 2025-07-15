package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Panda
import net.aquamine.api.entity.animal.type.PandaGene
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.PandaSerializer
import net.aquamine.server.world.AquaWorld

class AquaPanda(world: AquaWorld) : AquaAnimal(world), Panda {

    override val type: AquaEntityType<AquaPanda>
        get() = AquaEntityTypes.PANDA
    override val serializer: EntitySerializer<AquaPanda>
        get() = PandaSerializer

    override var knownGene: PandaGene
        get() = GENES.getOrNull(data.get(MetadataKeys.Panda.MAIN_GENE).toInt()) ?: PandaGene.NORMAL
        set(value) = data.set(MetadataKeys.Panda.MAIN_GENE, value.ordinal.toByte())
    override var hiddenGene: PandaGene
        get() = GENES.getOrNull(data.get(MetadataKeys.Panda.HIDDEN_GENE).toInt()) ?: PandaGene.NORMAL
        set(value) = data.set(MetadataKeys.Panda.HIDDEN_GENE, value.ordinal.toByte())
    override val isUnhappy: Boolean
        get() = unhappyTime > 0
    override var isEating: Boolean
        get() = eatingTime > 0
        set(value) {
            eatingTime = if (value) 1 else 0
        }
    override var isSneezing: Boolean
        get() = data.getFlag(MetadataKeys.Panda.FLAGS, FLAG_SNEEZING)
        set(value) {
            data.setFlag(MetadataKeys.Panda.FLAGS, FLAG_SNEEZING, value)
            if (!value) sneezingTime = 0
        }
    override var isRolling: Boolean
        get() = data.getFlag(MetadataKeys.Panda.FLAGS, FLAG_ROLLING)
        set(value) = data.setFlag(MetadataKeys.Panda.FLAGS, FLAG_ROLLING, value)
    override var isSitting: Boolean
        get() = data.getFlag(MetadataKeys.Panda.FLAGS, FLAG_SITTING)
        set(value) = data.setFlag(MetadataKeys.Panda.FLAGS, FLAG_SITTING, value)
    override var isLyingOnBack: Boolean
        get() = data.getFlag(MetadataKeys.Panda.FLAGS, FLAG_LYING_ON_BACK)
        set(value) = data.setFlag(MetadataKeys.Panda.FLAGS, FLAG_LYING_ON_BACK, value)
    override var unhappyTime: Int
        get() = data.get(MetadataKeys.Panda.UNHAPPY_TIMER)
        set(value) = data.set(MetadataKeys.Panda.UNHAPPY_TIMER, value)
    override var sneezingTime: Int
        get() = data.get(MetadataKeys.Panda.SNEEZE_TIMER)
        set(value) = data.set(MetadataKeys.Panda.SNEEZE_TIMER, value)
    override var eatingTime: Int
        get() = data.get(MetadataKeys.Panda.EATING_TIMER)
        set(value) = data.set(MetadataKeys.Panda.EATING_TIMER, value)
    override val isScared: Boolean
        get() = variant() == PandaGene.WORRIED && world.isThundering()

    init {
        if (!isBaby) canPickUpLoot = false
    }

    private fun variant(): PandaGene = variantFromGenes(knownGene, hiddenGene)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Panda.UNHAPPY_TIMER, 0)
        data.define(MetadataKeys.Panda.SNEEZE_TIMER, 0)
        data.define(MetadataKeys.Panda.EATING_TIMER, 0)
        data.define(MetadataKeys.Panda.MAIN_GENE, 0)
        data.define(MetadataKeys.Panda.HIDDEN_GENE, 0)
        data.define(MetadataKeys.Panda.FLAGS, 0)
    }

    override fun isFood(item: ItemStack): Boolean = item.type === ItemTypes.BAMBOO

    companion object {

        private const val FLAG_SNEEZING = 1
        private const val FLAG_ROLLING = 2
        private const val FLAG_SITTING = 3
        private const val FLAG_LYING_ON_BACK = 4
        private val GENES = PandaGene.entries.toTypedArray()

        private const val DEFAULT_MOVEMENT_SPEED = 0.15
        private const val DEFAULT_ATTACK_DAMAGE = 6.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)

        @JvmStatic
        private fun variantFromGenes(main: PandaGene, hidden: PandaGene): PandaGene {
            if (main.isRecessive) {
                if (main == hidden) return main
                return PandaGene.NORMAL
            }
            return main
        }
    }
}
