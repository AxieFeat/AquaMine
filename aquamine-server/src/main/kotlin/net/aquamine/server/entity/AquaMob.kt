package net.aquamine.server.entity

import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.MainHand
import net.aquamine.api.entity.Mob
import net.aquamine.server.entity.ai.goal.AquaGoalSelector
import net.aquamine.server.entity.ai.pathfinding.AquaNavigator
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import net.aquamine.server.entity.util.EquipmentSlots
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.util.collection.FixedList
import net.aquamine.server.world.AquaWorld

@Suppress("LeakingThis")
abstract class AquaMob(world: AquaWorld) : AquaLivingEntity(world), Mob {

    override val serializer: EntitySerializer<out AquaMob>
        get() = MobSerializer

    internal val handItems = FixedList(2, AquaItemStack.EMPTY)
    internal val armorItems = FixedList(4, AquaItemStack.EMPTY)
    internal val handDropChances = FloatArray(2) { DEFAULT_DROP_CHANCE }
    internal val armorDropChances = FloatArray(4) { DEFAULT_DROP_CHANCE }

    final override var canPickUpLoot: Boolean = false
    final override var isPersistent: Boolean = false
    private var target: AquaLivingEntity? = null

    override val goalSelector: AquaGoalSelector = AquaGoalSelector()
    override val navigator: AquaNavigator = AquaNavigator(this)

    final override var hasAI: Boolean
        get() = !data.getFlag(MetadataKeys.Mob.FLAGS, FLAG_NO_AI)
        set(value) = data.setFlag(MetadataKeys.Mob.FLAGS, FLAG_NO_AI, !value)
    final override var mainHand: MainHand
        get() = if (data.getFlag(MetadataKeys.Mob.FLAGS, FLAG_LEFT_HANDED)) MainHand.LEFT else MainHand.RIGHT
        set(value) = data.setFlag(MetadataKeys.Mob.FLAGS, FLAG_LEFT_HANDED, value == MainHand.LEFT)
    final override var isAggressive: Boolean
        get() = data.getFlag(MetadataKeys.Mob.FLAGS, FLAG_AGGRESSIVE)
        set(value) = data.setFlag(MetadataKeys.Mob.FLAGS, FLAG_AGGRESSIVE, value)

    init {
        registerGoals()
    }

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Mob.FLAGS, 0)
    }

    override fun getEquipment(slot: EquipmentSlot): AquaItemStack = when (slot.type) {
        EquipmentSlot.Type.HAND -> handItems.get(EquipmentSlots.index(slot))
        EquipmentSlot.Type.ARMOR -> armorItems.get(EquipmentSlots.index(slot))
    }

    override fun setEquipment(slot: EquipmentSlot, item: AquaItemStack) {
        when (slot.type) {
            EquipmentSlot.Type.HAND -> handItems.set(EquipmentSlots.index(slot), item)
            EquipmentSlot.Type.ARMOR -> armorItems.set(EquipmentSlots.index(slot), item)
        }
    }

    // TODO: Separate mob interactions
    //protected open fun mobInteract(player: KryptonPlayer, hand: Hand): InteractionResult = InteractionResult.PASS

    /*
    final override fun interact(player: KryptonPlayer, hand: Hand): InteractionResult {
        if (!isAlive) return InteractionResult.PASS
        var result = handleImportantInteractions(player, hand)
        if (result.consumesAction) return result
        result = mobInteract(player, hand)
        if (result.consumesAction) return result
        return super.interact(player, hand)
    }

    private fun handleImportantInteractions(player: KryptonPlayer, hand: Hand): InteractionResult {
        val heldItem = player.heldItem(hand)
        // TODO: Handle mob leashing
        if (heldItem.type === ItemTypes.NAME_TAG) {
            val result = heldItem.type.handler().interactEntity(heldItem, player, this, hand)
            if (result.consumesAction) return result
        }
        // TODO: Handle spawn egg
        return InteractionResult.PASS
    }
    */

    protected open fun registerGoals() {
        // No goals to register by default
    }

    override fun tick(time: Long) {
        super.tick(time)
        if (!isRemoved() && hasAI) doAiTick(time)
    }

    private fun doAiTick(time: Long) {
        goalSelector.tick(time)
        navigator.tick()
        aiTick()
    }

    protected open fun aiTick() {
        // Do nothing by default - subtypes can override this to add custom AI logic
    }

    fun target(): AquaLivingEntity? = target

    open fun setTarget(target: AquaLivingEntity?) {
        this.target = target
    }

    companion object {

        private const val FLAG_NO_AI = 0
        private const val FLAG_LEFT_HANDED = 1
        private const val FLAG_AGGRESSIVE = 2

        private const val DEFAULT_DROP_CHANCE = 0.085F

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaLivingEntity.attributes()
            .add(AquaAttributeTypes.FOLLOW_RANGE, 16.0)
            .add(AquaAttributeTypes.ATTACK_KNOCKBACK)
    }
}
