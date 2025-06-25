package net.aquamine.server.entity.components

import net.aquamine.api.entity.Bucketable
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.item.meta.AbstractItemMeta
import net.aquamine.server.item.meta.KryptonItemMeta
import xyz.axie.nbt.CompoundTag

interface BucketStorable : Bucketable {

    fun loadFromBucket(tag: CompoundTag)

    fun setSpawnedFromBucket(value: Boolean)

    companion object {

        fun loadDefaultsFromBucket(mob: KryptonMob, tag: CompoundTag) {
            if (tag.contains("NoAI")) mob.hasAI = !tag.getBoolean("NoAI")
            if (tag.contains("Silent")) mob.isSilent = tag.getBoolean("Silent")
            if (tag.contains("NoGravity")) mob.hasGravity = !tag.getBoolean("NoGravity")
            if (tag.contains("Glowing")) mob.isGlowing = tag.getBoolean("Glowing")
            if (tag.contains("Invulnerable")) mob.isInvulnerable = tag.getBoolean("Invulnerable")
            if (tag.contains("Health", 99)) mob.health = tag.getFloat("Health")
        }

        @Suppress("ExpressionBodySyntax") // There's commented out code here
        fun saveDefaultsToBucket(mob: KryptonMob): AbstractItemMeta<*> {
            // FIXME
            /*
            val nbt = item.meta.nbt
            if (mob.customName != null)
            if (mob.customName != null) item.meta[MetaKeys.NAME] = mob.customName!!
            if (!mob.hasAI) nbt.putBoolean("NoAI", !mob.hasAI)
            if (mob.isSilent) nbt.putBoolean("Silent", mob.isSilent)
            if (!mob.hasGravity) nbt.putBoolean("NoGravity", !mob.hasGravity)
            if (mob.isGlowing) nbt.putBoolean("Glowing", mob.isGlowing)
            if (mob.isInvulnerable) nbt.putBoolean("Invulnerable", mob.isInvulnerable)
            nbt.putFloat("Health", mob.health)
             */
            return KryptonItemMeta.DEFAULT
        }
    }
}
