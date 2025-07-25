package net.aquamine.api.world.rule

import net.aquamine.annotations.Catalogue
import net.aquamine.api.entity.Mob
import net.aquamine.api.entity.player.Player

/**
 * All the built-in game rules.
 */
@Catalogue(GameRule::class)
object GameRules {

    /**
     * If advancements should be announced to the server.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val ANNOUNCE_ADVANCEMENTS: GameRule<Boolean> = GameRule.of("announceAdvancements")

    /**
     * If false, blocks will always drop from explosions. If true, whether a
     * block drops from an explosion is determined randomly depending on how
     * far the block is from the center of the explosion.
     *
     * This rule is for block explosions, i.e., those created from right
     * clicking a bed in a dimension where beds are not allowed.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val BLOCK_EXPLOSION_DROP_DECAY: GameRule<Boolean> = GameRule.of("blockExplosionDropDecay")

    /**
     * Whether command blocks should notify admins when they perform commands.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val COMMAND_BLOCK_OUTPUT: GameRule<Boolean> = GameRule.of("commandBlockOutput")

    /**
     * Whether the server should skip checking player speed when the player is
     * wearing elytra.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val DISABLE_ELYTRA_MOVEMENT_CHECK: GameRule<Boolean> = GameRule.of("disableElytraMovementCheck")

    /**
     * Whether raids are disabled.
     *
     * If the value of this game rule is `true`, all raids will stop.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val DISABLE_RAIDS: GameRule<Boolean> = GameRule.of("disableRaids")

    /**
     * Whether blocks should have drops.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_BLOCK_DROPS: GameRule<Boolean> = GameRule.of("doTileDrops")

    /**
     * Whether the day-night cycle and moon phases progress.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_DAYLIGHT_CYCLE: GameRule<Boolean> = GameRule.of("doDaylightCycle")

    /**
     * Whether entities that are not mobs should have drops.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_ENTITY_DROPS: GameRule<Boolean> = GameRule.of("doEntityDrops")

    /**
     * Whether fire should spread and naturally extinguish.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_FIRE_TICK: GameRule<Boolean> = GameRule.of("doFireTick")

    /**
     * Whether phantoms can spawn in the nighttime.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_INSOMNIA: GameRule<Boolean> = GameRule.of("doInsomnia")

    /**
     * Whether [Player]s should respawn immediately without showing the death screen.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val DO_IMMEDIATE_RESPAWN: GameRule<Boolean> = GameRule.of("doImmediateRespawn")

    /**
     * Whether [Player]s can only craft recipes they have unlocked.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val DO_LIMITED_CRAFTING: GameRule<Boolean> = GameRule.of("doLimitedCrafting")

    /**
     * Whether [Mob]s should drop items.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_MOB_LOOT: GameRule<Boolean> = GameRule.of("doMobLoot")

    /**
     * Whether [Mob]s should naturally spawn.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_MOB_SPAWNING: GameRule<Boolean> = GameRule.of("doMobSpawning")

    /**
     * Whether patrollers will go out on patrol (typically in a raid).
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_PATROL_SPAWNING: GameRule<Boolean> = GameRule.of("doPatrolSpawning")

    /**
     * Whether wandering traders will naturally spawn.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_TRADER_SPAWNING: GameRule<Boolean> = GameRule.of("doTraderSpawning")

    /**
     * Whether wardens will spawn when triggered by sculk shriekers.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_WARDEN_SPAWNING: GameRule<Boolean> = GameRule.of("doWardenSpawning")

    /**
     * Whether the weather will change.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DO_WEATHER_CYCLE: GameRule<Boolean> = GameRule.of("doWeatherCycle")

    /**
     * Whether entities should take drowning damage.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val DROWNING_DAMAGE: GameRule<Boolean> = GameRule.of("drowningDamage")

    /**
     * Whether entities should take fall damage.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val FALL_DAMAGE: GameRule<Boolean> = GameRule.of("fallDamage")

    /**
     * Whether entities should take fire damage.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val FIRE_DAMAGE: GameRule<Boolean> = GameRule.of("fireDamage")

    /**
     * Makes angered neutral mobs stop being angry when the targeted player
     * dies nearby.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val FORGIVE_DEAD_PLAYERS: GameRule<Boolean> = GameRule.of("forgiveDeadPlayers")

    /**
     * Whether entities should take freezing damage.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val FREEZE_DAMAGE: GameRule<Boolean> = GameRule.of("freezeDamage")

    /**
     * Whether sound events that can be heard by all players regardless of
     * location, such as the end portal lighting sound, are allowed to play.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val GLOBAL_SOUND_EVENTS: GameRule<Boolean> = GameRule.of("globalSoundEvents")

    /**
     * Whether [Player]s should keep items in their inventory after death.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val KEEP_INVENTORY: GameRule<Boolean> = GameRule.of("keepInventory")

    /**
     * Whether new sources of lava are allowed to form.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val LAVA_SOURCE_CONVERSION: GameRule<Boolean> = GameRule.of("lavaSourceConversion")

    /**
     * Whether to log admin commands to server log.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val LOG_ADMIN_COMMANDS: GameRule<Boolean> = GameRule.of("logAdminCommands")

    /**
     * The total number of chain commands that can run during a single tick.
     *
     * This is a numerical game rule, with a default value of `65536`.
     */
    @JvmField
    val MAX_COMMAND_CHAIN_LENGTH: GameRule<Int> = GameRule.of("maxCommandChainLength")

    /**
     * The maximum number of other pushable entities a mob or player can push,
     * before taking 3 suffocation damage per half-second.
     *
     * Damage affects
     * [survival mode][net.aquamine.api.world.GameMode.SURVIVAL] or
     * [adventure mode][net.aquamine.api.world.GameMode.ADVENTURE]
     * players, and all mobs but bats.
     * Pushable entities include non-spectator-mode players, any mob except
     * bats, as well as minecarts and boats.
     *
     * Setting to `0` disables the rule.
     *
     * This is a numerical game rule, with a default value of `24`.
     */
    @JvmField
    val MAX_ENTITY_CRAMMING: GameRule<Int> = GameRule.of("maxEntityCramming")

    /**
     * If false, blocks will always drop from explosions. If true, whether a
     * block drops from an explosion is determined randomly depending on how
     * far the block is from the center of the explosion.
     *
     * This rule is for mob explosions, i.e., those from a creeper.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val MOB_EXPLOSION_DROP_DECAY: GameRule<Boolean> = GameRule.of("mobExplosionDropDecay")

    /**
     * Whether [Mob]s should be able to change blocks and pick up items.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val MOB_GRIEFING: GameRule<Boolean> = GameRule.of("mobGriefing")

    /**
     * Whether [Player]s can regenerate health naturally if their hunger is
     * full enough (doesn't affect external healing, such as golden apples,
     * the Regeneration effect, etc.).
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val NATURAL_REGENERATION: GameRule<Boolean> = GameRule.of("naturalRegeneration")

    /**
     * The percentage of players that must be sleeping to skip the night.
     *
     * This is a numerical game rule, with a default value of `100`.
     */
    @JvmField
    val PLAYER_SLEEPING_PERCENTAGE: GameRule<Int> = GameRule.of("playersSleepingPercentage")

    /**
     * How often a random block tick occurs (such as plant growth, leaf decay,
     * etc.) per chunk section per game tick.
     *
     * 0 will disable random ticks, higher numbers will increase random ticks
     *
     * This is a numerical game rule, with a default value of `3`.
     */
    @JvmField
    val RANDOM_TICK_SPEED: GameRule<Int> = GameRule.of("randomTickSpeed")

    /**
     * Whether the debug screen shows all or reduced information.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val REDUCED_DEBUG_INFO: GameRule<Boolean> = GameRule.of("reducedDebugInfo")

    /**
     * Whether the feedback from commands executed by a [Player] should show up
     * in chat.
     *
     * This game rule affects the default behavior of whether command blocks
     * store their output
     * text.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val SEND_COMMAND_FEEDBACK: GameRule<Boolean> = GameRule.of("sendCommandFeedback")

    /**
     * Whether a message appears in chat when a [Player] dies.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val SHOW_DEATH_MESSAGES: GameRule<Boolean> = GameRule.of("showDeathMessages")

    /**
     * The number of blocks outward from the world spawn coordinates that a
     * player will spawn in when first joining a server or when dying without
     * a spawn point.
     *
     * This is a numerical game rule, with a default value of `10`.
     */
    @JvmField
    val SPAWN_RADIUS: GameRule<Int> = GameRule.of("spawnRadius")

    /**
     * Whether players in
     * [spectator mode][net.aquamine.api.world.GameMode.SPECTATOR] can
     * generate chunks.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val SPECTATORS_GENERATE_CHUNKS: GameRule<Boolean> = GameRule.of("spectatorsGenerateChunks")

    /**
     * If false, blocks will always drop from explosions. If true, whether a
     * block drops from an explosion is determined randomly depending on how
     * far the block is from the center of the explosion.
     *
     * This rule is for TNT explosions.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val TNT_EXPLOSION_DROP_DECAY: GameRule<Boolean> = GameRule.of("tntExplosionDropDecay")

    /**
     * Makes angered neutral mobs attack any nearby player, not just the player
     * that angered them.
     *
     * This is a boolean game rule, with a default value of `false`.
     */
    @JvmField
    val UNIVERSAL_ANGER: GameRule<Boolean> = GameRule.of("universalAnger")

    /**
     * Whether new sources of water are allowed to form.
     *
     * This is a boolean game rule, with a default value of `true`.
     */
    @JvmField
    val WATER_SOURCE_CONVERSION: GameRule<Boolean> = GameRule.of("waterSourceConversion")
}
