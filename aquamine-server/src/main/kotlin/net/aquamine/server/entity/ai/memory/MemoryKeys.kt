package net.aquamine.server.entity.ai.memory

import net.kyori.adventure.key.Key
import net.aquamine.annotations.Catalogue
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.coordinate.GlobalPos
import net.aquamine.server.util.uuid.UUIDUtil
import org.kryptonmc.serialization.Codec
import java.util.UUID

@Catalogue(MemoryKey::class)
object MemoryKeys {

    @JvmField
    val ADMIRING_DISABLED: MemoryKey<Boolean> = register("admiring_disabled", Codec.BOOLEAN)
    @JvmField
    val ADMIRING_ITEM: MemoryKey<Boolean> = register("admiring_item", Codec.BOOLEAN)
    @JvmField
    val ANGRY_AT: MemoryKey<UUID> = register("angry_at", UUIDUtil.CODEC)
    @JvmField
    val GOLEM_DETECTED_RECENTLY: MemoryKey<Boolean> = register("golem_detected_recently", Codec.BOOLEAN)
    @JvmField
    val HAS_HUNTING_COOLDOWN: MemoryKey<Boolean> = register("has_hunting_cooldown", Codec.BOOLEAN)
    @JvmField
    val HOME: MemoryKey<GlobalPos> = register("home", GlobalPos.CODEC)
    @JvmField
    val HUNTED_RECENTLY: MemoryKey<Boolean> = register("hunted_recently", Codec.BOOLEAN)
    @JvmField
    val IS_TEMPTED: MemoryKey<Boolean> = register("is_tempted", Codec.BOOLEAN)
    @JvmField
    val JOB_SITE: MemoryKey<GlobalPos> = register("job_site", GlobalPos.CODEC)
    @JvmField
    val LAST_SLEPT: MemoryKey<Long> = register("last_slept", Codec.LONG)
    @JvmField
    val LAST_WOKEN: MemoryKey<Long> = register("last_woken", Codec.LONG)
    @JvmField
    val LAST_WORKED_AT_POI: MemoryKey<Long> = register("last_worked_at_poi", Codec.LONG)
    @JvmField
    val LONG_JUMP_COOLING_DOWN: MemoryKey<Int> = register("long_jump_cooling_down", Codec.INT)
    @JvmField
    val MEETING_POINT: MemoryKey<GlobalPos> = register("meeting_point", GlobalPos.CODEC)
    @JvmField
    val PLAY_DEAD_TICKS: MemoryKey<Int> = register("play_dead_ticks", Codec.INT)
    @JvmField
    val POTENTIAL_JOB_SITE: MemoryKey<GlobalPos> = register("potential_job_site", GlobalPos.CODEC)
    @JvmField
    val RAM_COOLDOWN_TICKS: MemoryKey<Int> = register("ram_cooldown_ticks", Codec.INT)
    @JvmField
    val TEMPTATION_COOLDOWN_TICKS: MemoryKey<Int> = register("temptation_cooldown_ticks", Codec.INT)
    @JvmField
    val UNIVERSAL_ANGER: MemoryKey<Boolean> = register("universal_anger", Codec.BOOLEAN)

    @JvmStatic
    private fun <T : Any> register(name: String, codec: Codec<T>): MemoryKey<T> =
        KryptonRegistries.register(KryptonRegistries.MEMORY_KEY, Key.key(name), MemoryKey(codec))
}
