package net.aquamine.server.world.rule

import net.aquamine.api.world.rule.GameRule
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.out.play.GameEventTypes
import net.aquamine.server.packet.out.play.PacketOutEntityEvent
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.world.rule.WorldGameRules.BooleanValue
import net.aquamine.server.world.rule.WorldGameRules.Category
import net.aquamine.server.world.rule.WorldGameRules.IntegerValue
import net.aquamine.server.world.rule.WorldGameRules.Key
import net.aquamine.server.world.rule.WorldGameRules.Type
import net.aquamine.server.world.rule.WorldGameRules.TypeVisitor
import net.aquamine.server.world.rule.WorldGameRules.Value
import java.util.TreeMap

object GameRuleKeys {

    private val KEYS_BY_NAME = HashMap<String, Key<*>>()
    private val GAME_RULE_TYPES = TreeMap<Key<*>, Type<*>>(Comparator.comparing { it.id })
    @JvmField
    val ANNOUNCE_ADVANCEMENTS: Key<BooleanValue> = register("announceAdvancements", Category.CHAT, BooleanValue.create(true))
    @JvmField
    val BLOCK_EXPLOSION_DROP_DECAY: Key<BooleanValue> = register("blockExplosionDropDecay", Category.DROPS, BooleanValue.create(true))
    @JvmField
    val COMMAND_BLOCK_OUTPUT: Key<BooleanValue> = register("commandBlockOutput", Category.CHAT, BooleanValue.create(true))
    @JvmField
    val DISABLE_ELYTRA_MOVEMENT_CHECK: Key<BooleanValue> = register("disableElytraMovementCheck", Category.PLAYER, BooleanValue.create(false))
    @JvmField
    val DISABLE_RAIDS: Key<BooleanValue> = register("disableRaids", Category.MOBS, BooleanValue.create(false))
    @JvmField
    val DO_BLOCK_DROPS: Key<BooleanValue> = register("doTileDrops", Category.DROPS, BooleanValue.create(true))
    @JvmField
    val DO_DAYLIGHT_CYCLE: Key<BooleanValue> = register("doDaylightCycle", Category.UPDATES, BooleanValue.create(true))
    @JvmField
    val DO_ENTITY_DROPS: Key<BooleanValue> = register("doEntityDrops", Category.DROPS, BooleanValue.create(true))
    @JvmField
    val DO_FIRE_TICK: Key<BooleanValue> = register("doFireTick", Category.UPDATES, BooleanValue.create(true))
    @JvmField
    val DO_INSOMNIA: Key<BooleanValue> = register("doInsomnia", Category.SPAWNING, BooleanValue.create(true))
    @JvmField
    val DO_IMMEDIATE_RESPAWN: Key<BooleanValue> = register("doImmediateRespawn", Category.PLAYER, BooleanValue.create(false) { server, value ->
        val setting = if (value.get()) 1F else 0F
        PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(GameEventTypes.ENABLE_RESPAWN_SCREEN, setting))
    })
    @JvmField
    val DO_LIMITED_CRAFTING: Key<BooleanValue> = register("doLimitedCrafting", Category.PLAYER, BooleanValue.create(false))
    @JvmField
    val DO_MOB_LOOT: Key<BooleanValue> = register("doMobLoot", Category.DROPS, BooleanValue.create(true))
    @JvmField
    val DO_MOB_SPAWNING: Key<BooleanValue> = register("doMobSpawning", Category.SPAWNING, BooleanValue.create(true))
    @JvmField
    val DO_PATROL_SPAWNING: Key<BooleanValue> = register("doPatrolSpawning", Category.SPAWNING, BooleanValue.create(true))
    @JvmField
    val DO_TRADER_SPAWNING: Key<BooleanValue> = register("doTraderSpawning", Category.SPAWNING, BooleanValue.create(true))
    @JvmField
    val DO_WARDEN_SPAWNING: Key<BooleanValue> = register("doWardenSpawning", Category.SPAWNING, BooleanValue.create(true))
    @JvmField
    val DO_WEATHER_CYCLE: Key<BooleanValue> = register("doWeatherCycle", Category.UPDATES, BooleanValue.create(true))
    @JvmField
    val DROWNING_DAMAGE: Key<BooleanValue> = register("drowningDamage", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val FALL_DAMAGE: Key<BooleanValue> = register("fallDamage", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val FIRE_DAMAGE: Key<BooleanValue> = register("fireDamage", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val FORGIVE_DEAD_PLAYERS: Key<BooleanValue> = register("forgiveDeadPlayers", Category.MOBS, BooleanValue.create(true))
    @JvmField
    val FREEZE_DAMAGE: Key<BooleanValue> = register("freezeDamage", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val GLOBAL_SOUND_EVENTS: Key<BooleanValue> = register("globalSoundEvents", Category.MISC, BooleanValue.create(true))
    @JvmField
    val KEEP_INVENTORY: Key<BooleanValue> = register("keepInventory", Category.PLAYER, BooleanValue.create(false))
    @JvmField
    val LAVA_SOURCE_CONVERSION: Key<BooleanValue> = register("lavaSourceConversion", Category.UPDATES, BooleanValue.create(false))
    @JvmField
    val LOG_ADMIN_COMMANDS: Key<BooleanValue> = register("logAdminCommands", Category.CHAT, BooleanValue.create(true))
    @JvmField
    val MAX_COMMAND_CHAIN_LENGTH: Key<IntegerValue> = register("maxCommandChainLength", Category.MISC, IntegerValue.create(65536))
    @JvmField
    val MAX_ENTITY_CRAMMING: Key<IntegerValue> = register("maxEntityCramming", Category.MOBS, IntegerValue.create(24))
    @JvmField
    val MOB_EXPLOSION_DROP_DECAY: Key<BooleanValue> = register("mobExplosionDropDecay", Category.DROPS, BooleanValue.create(true))
    @JvmField
    val MOB_GRIEFING: Key<BooleanValue> = register("mobGriefing", Category.MOBS, BooleanValue.create(true))
    @JvmField
    val NATURAL_REGENERATION: Key<BooleanValue> = register("naturalRegeneration", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val PLAYERS_SLEEPING_PERCENTAGE: Key<IntegerValue> = register("playersSleepingPercentage", Category.PLAYER, IntegerValue.create(100))
    @JvmField
    val RANDOM_TICK_SPEED: Key<IntegerValue> = register("randomTickSpeed", Category.UPDATES, IntegerValue.create(3))
    @JvmField
    val REDUCED_DEBUG_INFO: Key<BooleanValue> = register("reducedDebugInfo", Category.MISC, BooleanValue.create(false) { server, value ->
        val event: Byte = if (value.get()) 22 else 23
        server.playerManager.players().forEach { it.connection.send(PacketOutEntityEvent(it.id, event)) }
    })
    @JvmField
    val SEND_COMMAND_FEEDBACK: Key<BooleanValue> = register("sendCommandFeedback", Category.CHAT, BooleanValue.create(true))
    @JvmField
    val SNOW_ACCUMULATION_HEIGHT: Key<IntegerValue> = register("snowAccumulationHeight", Category.UPDATES, IntegerValue.create(1))
    @JvmField
    val SHOW_DEATH_MESSAGES: Key<BooleanValue> = register("showDeathMessages", Category.CHAT, BooleanValue.create(true))
    @JvmField
    val SPAWN_RADIUS: Key<IntegerValue> = register("spawnRadius", Category.PLAYER, IntegerValue.create(10))
    @JvmField
    val SPECTATORS_GENERATE_CHUNKS: Key<BooleanValue> = register("spectatorsGenerateChunks", Category.PLAYER, BooleanValue.create(true))
    @JvmField
    val TNT_EXPLOSION_DROP_DECAY: Key<BooleanValue> = register("tntExplosionDropDecay", Category.DROPS, BooleanValue.create(false))
    @JvmField
    val UNIVERSAL_ANGER: Key<BooleanValue> = register("universalAnger", Category.MOBS, BooleanValue.create(false))
    @JvmField
    val WATER_SOURCE_CONVERSION: Key<BooleanValue> = register("waterSourceConversion", Category.UPDATES, BooleanValue.create(true))

    @JvmStatic
    fun types(): Map<Key<*>, Type<*>> = GAME_RULE_TYPES

    @JvmStatic
    fun getKey(id: String): Key<*> = KEYS_BY_NAME[id] ?: error("Cannot find game rule $id!")

    @JvmStatic
    fun visitTypes(visitor: TypeVisitor) {
        GAME_RULE_TYPES.forEach { doCallVisitor(visitor, it.key, it.value) }
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T : Value<T>> doCallVisitor(visitor: TypeVisitor, key: Key<T>, type: Type<*>) {
        val temp = type as Type<T>
        visitor.visit(key, temp)
        temp.callVisitor(visitor, key)
    }

    @JvmStatic
    private fun <T : Value<T>> register(id: String, category: Category, type: Type<T>): Key<T> {
        val key = Key<T>(id, category)
        KEYS_BY_NAME[id] = key
        check(GAME_RULE_TYPES.put(key, type) == null) { "Duplicate game rule registration for $id!" }
        return key
    }

    object Factory : GameRule.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <V> of(name: String): GameRule<V> = KEYS_BY_NAME[name] as GameRule<V>
    }
}
