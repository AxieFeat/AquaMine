package net.aquamine.server.registry.loader

import com.google.common.collect.ImmutableSet
import net.aquamine.api.block.Block
import net.aquamine.api.block.entity.Banner
import net.aquamine.api.block.entity.Beacon
import net.aquamine.api.block.entity.Bed
import net.aquamine.api.block.entity.Beehive
import net.aquamine.api.block.entity.Bell
import net.aquamine.api.block.entity.BlockEntity
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.block.entity.Campfire
import net.aquamine.api.block.entity.Command
import net.aquamine.api.block.entity.Comparator
import net.aquamine.api.block.entity.Conduit
import net.aquamine.api.block.entity.DaylightDetector
import net.aquamine.api.block.entity.EnchantmentTable
import net.aquamine.api.block.entity.EnderChest
import net.aquamine.api.block.entity.Jigsaw
import net.aquamine.api.block.entity.Jukebox
import net.aquamine.api.block.entity.Lectern
import net.aquamine.api.block.entity.PistonMoving
import net.aquamine.api.block.entity.SculkCatalyst
import net.aquamine.api.block.entity.SculkSensor
import net.aquamine.api.block.entity.SculkShrieker
import net.aquamine.api.block.entity.Sign
import net.aquamine.api.block.entity.Skull
import net.aquamine.api.block.entity.Spawner
import net.aquamine.api.block.entity.Structure
import net.aquamine.api.block.entity.TheEndGateway
import net.aquamine.api.block.entity.TheEndPortal
import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.block.entity.container.Barrel
import net.aquamine.api.block.entity.container.BlastFurnace
import net.aquamine.api.block.entity.container.BrewingStand
import net.aquamine.api.block.entity.container.Chest
import net.aquamine.api.block.entity.container.Dispenser
import net.aquamine.api.block.entity.container.Dropper
import net.aquamine.api.block.entity.container.Furnace
import net.aquamine.api.block.entity.container.Hopper
import net.aquamine.api.block.entity.container.ShulkerBox
import net.aquamine.api.block.entity.container.Smoker
import net.aquamine.api.block.entity.container.TrappedChest
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.attribute.AttributeTypes
import net.aquamine.api.entity.attribute.BasicModifierOperation
import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.inventory.InventoryType
import net.aquamine.api.potion.PotionTypeCategory
import net.aquamine.api.scoreboard.ObjectiveRenderType
import net.aquamine.api.scoreboard.criteria.KeyedCriterion
import net.aquamine.api.statistic.StatisticFormatter
import net.aquamine.api.statistic.StatisticType
import net.aquamine.api.statistic.StatisticTypes
import net.aquamine.api.util.Color
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.api.world.damage.type.DamageTypes
import net.aquamine.server.adventure.AquaAdventure
import net.aquamine.server.effect.particle.AquaBlockParticleType
import net.aquamine.server.effect.particle.AquaColorParticleType
import net.aquamine.server.effect.particle.AquaDirectionalParticleType
import net.aquamine.server.effect.particle.AquaDustParticleType
import net.aquamine.server.effect.particle.AquaDustTransitionParticleType
import net.aquamine.server.effect.particle.AquaItemParticleType
import net.aquamine.server.effect.particle.AquaNoteParticleType
import net.aquamine.server.effect.particle.AquaSimpleParticleType
import net.aquamine.server.effect.particle.AquaVibrationParticleType
import net.aquamine.server.entity.AquaEntityCategory
import net.aquamine.server.entity.attribute.AttributeMap
import net.aquamine.server.entity.attribute.downcast
import net.aquamine.server.entity.hanging.AquaPaintingVariant
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.inventory.AquaInventoryType
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.potion.PotionAttributeTemplate
import net.aquamine.server.potion.PotionEffectHandler
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.statistic.AquaStatisticType
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.entity.AquaBlockEntityType
import net.aquamine.server.world.block.entity.banner.AquaBannerPatternType
import net.aquamine.server.world.damage.AquaDamageSource
import net.aquamine.server.world.damage.type.AquaDamageType
import net.aquamine.server.world.scoreboard.AquaKeyedCriterion
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.util.function.Supplier
import kotlin.math.max
import kotlin.math.min

/**
 * Contains all the built-in registry loaders for AquaMine.
 */
@Suppress("StringLiteralDuplication")
object RegistryLoaders {

    @JvmStatic
    fun bannerPatternType(): RegistryLoaderProvider<BannerPatternType> = loader {
        add(Key.key("base")) { AquaBannerPatternType(it, "b") }
        add(Key.key("square_bottom_left")) { AquaBannerPatternType(it, "bl") }
        add(Key.key("square_bottom_right")) { AquaBannerPatternType(it, "br") }
        add(Key.key("square_top_left")) { AquaBannerPatternType(it, "tl") }
        add(Key.key("square_top_right")) { AquaBannerPatternType(it, "tr") }
        add(Key.key("stripe_bottom")) { AquaBannerPatternType(it, "bs") }
        add(Key.key("stripe_top")) { AquaBannerPatternType(it, "ts") }
        add(Key.key("stripe_left")) { AquaBannerPatternType(it, "ls") }
        add(Key.key("stripe_right")) { AquaBannerPatternType(it, "rs") }
        add(Key.key("stripe_center")) { AquaBannerPatternType(it, "cs") }
        add(Key.key("stripe_middle")) { AquaBannerPatternType(it, "ms") }
        add(Key.key("stripe_downright")) { AquaBannerPatternType(it, "drs") }
        add(Key.key("stripe_downleft")) { AquaBannerPatternType(it, "dls") }
        add(Key.key("small_stripes")) { AquaBannerPatternType(it, "ss") }
        add(Key.key("cross")) { AquaBannerPatternType(it, "cr") }
        add(Key.key("straight_cross")) { AquaBannerPatternType(it, "sc") }
        add(Key.key("triangle_bottom")) { AquaBannerPatternType(it, "bt") }
        add(Key.key("triangle_top")) { AquaBannerPatternType(it, "tt") }
        add(Key.key("triangles_bottom")) { AquaBannerPatternType(it, "bts") }
        add(Key.key("triangles_top")) { AquaBannerPatternType(it, "tts") }
        add(Key.key("diagonal_left")) { AquaBannerPatternType(it, "ld") }
        add(Key.key("diagonal_up_right")) { AquaBannerPatternType(it, "rd") }
        add(Key.key("diagonal_up_left")) { AquaBannerPatternType(it, "lud") }
        add(Key.key("diagonal_right")) { AquaBannerPatternType(it, "rud") }
        add(Key.key("circle")) { AquaBannerPatternType(it, "mc") }
        add(Key.key("rhombus")) { AquaBannerPatternType(it, "mr") }
        add(Key.key("half_vertical")) { AquaBannerPatternType(it, "vh") }
        add(Key.key("half_horizontal")) { AquaBannerPatternType(it, "hh") }
        add(Key.key("half_vertical_right")) { AquaBannerPatternType(it, "vhr") }
        add(Key.key("half_horizontal_bottom")) { AquaBannerPatternType(it, "hhb") }
        add(Key.key("border")) { AquaBannerPatternType(it, "bo") }
        add(Key.key("curly_border")) { AquaBannerPatternType(it, "cbo") }
        add(Key.key("gradient")) { AquaBannerPatternType(it, "gra") }
        add(Key.key("gradient_up")) { AquaBannerPatternType(it, "gru") }
        add(Key.key("bricks")) { AquaBannerPatternType(it, "bri") }
        add(Key.key("globe")) { AquaBannerPatternType(it, "glb") }
        add(Key.key("creeper")) { AquaBannerPatternType(it, "cre") }
        add(Key.key("skull")) { AquaBannerPatternType(it, "sku") }
        add(Key.key("flower")) { AquaBannerPatternType(it, "flo") }
        add(Key.key("mojang")) { AquaBannerPatternType(it, "moj") }
        add(Key.key("piglin")) { AquaBannerPatternType(it, "pig") }
    }

    @JvmStatic
    fun blockEntityType(): RegistryLoaderProvider<BlockEntityType<*>> = loader {
        add<Furnace>("furnace", AquaBlocks.FURNACE)
        add<Chest>("chest", AquaBlocks.CHEST)
        add<TrappedChest>("trapped_chest", AquaBlocks.TRAPPED_CHEST)
        add<EnderChest>("ender_chest", AquaBlocks.ENDER_CHEST)
        add<Jukebox>("jukebox", AquaBlocks.JUKEBOX)
        add<Dispenser>("dispenser", AquaBlocks.DISPENSER)
        add<Dropper>("dropper", AquaBlocks.DROPPER)
        add<Sign>("sign", AquaBlocks.OAK_SIGN, AquaBlocks.SPRUCE_SIGN, AquaBlocks.BIRCH_SIGN, AquaBlocks.ACACIA_SIGN,
            AquaBlocks.JUNGLE_SIGN, AquaBlocks.DARK_OAK_SIGN, AquaBlocks.OAK_WALL_SIGN, AquaBlocks.SPRUCE_WALL_SIGN,
            AquaBlocks.BIRCH_WALL_SIGN, AquaBlocks.ACACIA_WALL_SIGN, AquaBlocks.JUNGLE_WALL_SIGN, AquaBlocks.DARK_OAK_WALL_SIGN,
            AquaBlocks.CRIMSON_SIGN, AquaBlocks.CRIMSON_WALL_SIGN, AquaBlocks.WARPED_SIGN, AquaBlocks.WARPED_WALL_SIGN,
            AquaBlocks.MANGROVE_SIGN, AquaBlocks.MANGROVE_WALL_SIGN)
        add<Spawner>("mob_spawner", AquaBlocks.SPAWNER)
        add<PistonMoving>("piston", AquaBlocks.MOVING_PISTON)
        add<BrewingStand>("brewing_stand", AquaBlocks.BREWING_STAND)
        add<EnchantmentTable>("enchanting_table", AquaBlocks.ENCHANTING_TABLE)
        add<TheEndPortal>("end_portal", AquaBlocks.END_PORTAL)
        add<Beacon>("beacon", AquaBlocks.BEACON)
        add<Skull>("skull", AquaBlocks.SKELETON_SKULL, AquaBlocks.SKELETON_WALL_SKULL, AquaBlocks.CREEPER_HEAD,
            AquaBlocks.CREEPER_WALL_HEAD, AquaBlocks.DRAGON_HEAD, AquaBlocks.DRAGON_WALL_HEAD, AquaBlocks.ZOMBIE_HEAD,
            AquaBlocks.ZOMBIE_WALL_HEAD, AquaBlocks.WITHER_SKELETON_SKULL, AquaBlocks.WITHER_SKELETON_WALL_SKULL, AquaBlocks.PLAYER_HEAD,
            AquaBlocks.PLAYER_WALL_HEAD)
        add<DaylightDetector>("daylight_detector", AquaBlocks.DAYLIGHT_DETECTOR)
        add<Hopper>("hopper", AquaBlocks.HOPPER)
        add<Comparator>("comparator", AquaBlocks.COMPARATOR)
        add<Banner>("banner", AquaBlocks.WHITE_BANNER, AquaBlocks.ORANGE_BANNER, AquaBlocks.MAGENTA_BANNER, AquaBlocks.LIGHT_BLUE_BANNER,
            AquaBlocks.YELLOW_BANNER, AquaBlocks.LIME_BANNER, AquaBlocks.PINK_BANNER, AquaBlocks.GRAY_BANNER,
            AquaBlocks.LIGHT_GRAY_BANNER, AquaBlocks.CYAN_BANNER, AquaBlocks.PURPLE_BANNER, AquaBlocks.BLUE_BANNER,
            AquaBlocks.BROWN_BANNER, AquaBlocks.GREEN_BANNER, AquaBlocks.RED_BANNER, AquaBlocks.BLACK_BANNER,
            AquaBlocks.WHITE_WALL_BANNER, AquaBlocks.ORANGE_WALL_BANNER, AquaBlocks.MAGENTA_WALL_BANNER,
            AquaBlocks.LIGHT_BLUE_WALL_BANNER, AquaBlocks.YELLOW_WALL_BANNER, AquaBlocks.LIME_WALL_BANNER, AquaBlocks.PINK_WALL_BANNER,
            AquaBlocks.GRAY_WALL_BANNER, AquaBlocks.LIGHT_GRAY_WALL_BANNER, AquaBlocks.CYAN_WALL_BANNER, AquaBlocks.PURPLE_WALL_BANNER,
            AquaBlocks.BLUE_WALL_BANNER, AquaBlocks.BROWN_WALL_BANNER, AquaBlocks.GREEN_WALL_BANNER, AquaBlocks.RED_WALL_BANNER,
            AquaBlocks.BLACK_WALL_BANNER)
        add<Structure>("structure_block", AquaBlocks.STRUCTURE_BLOCK)
        add<TheEndGateway>("end_gateway", AquaBlocks.END_GATEWAY)
        add<Command>("command_block", AquaBlocks.COMMAND_BLOCK, AquaBlocks.CHAIN_COMMAND_BLOCK, AquaBlocks.REPEATING_COMMAND_BLOCK)
        add<ShulkerBox>("shulker_box", AquaBlocks.SHULKER_BOX, AquaBlocks.BLACK_SHULKER_BOX, AquaBlocks.BLUE_SHULKER_BOX,
            AquaBlocks.BROWN_SHULKER_BOX, AquaBlocks.CYAN_SHULKER_BOX, AquaBlocks.GRAY_SHULKER_BOX, AquaBlocks.GREEN_SHULKER_BOX,
            AquaBlocks.LIGHT_BLUE_SHULKER_BOX, AquaBlocks.LIGHT_GRAY_SHULKER_BOX, AquaBlocks.LIME_SHULKER_BOX,
            AquaBlocks.MAGENTA_SHULKER_BOX, AquaBlocks.ORANGE_SHULKER_BOX, AquaBlocks.PINK_SHULKER_BOX, AquaBlocks.PURPLE_SHULKER_BOX,
            AquaBlocks.RED_SHULKER_BOX, AquaBlocks.WHITE_SHULKER_BOX, AquaBlocks.YELLOW_SHULKER_BOX)
        add<Bed>("bed", AquaBlocks.RED_BED, AquaBlocks.BLACK_BED, AquaBlocks.BLUE_BED, AquaBlocks.BROWN_BED, AquaBlocks.CYAN_BED,
            AquaBlocks.GRAY_BED, AquaBlocks.GREEN_BED, AquaBlocks.LIGHT_BLUE_BED, AquaBlocks.LIGHT_GRAY_BED, AquaBlocks.LIME_BED,
            AquaBlocks.MAGENTA_BED, AquaBlocks.ORANGE_BED, AquaBlocks.PINK_BED, AquaBlocks.PURPLE_BED, AquaBlocks.WHITE_BED,
            AquaBlocks.YELLOW_BED)
        add<Conduit>("conduit", AquaBlocks.CONDUIT)
        add<Barrel>("barrel", AquaBlocks.BARREL)
        add<Smoker>("smoker", AquaBlocks.SMOKER)
        add<BlastFurnace>("blast_furnace", AquaBlocks.BLAST_FURNACE)
        add<Lectern>("lectern", AquaBlocks.LECTERN)
        add<Bell>("bell", AquaBlocks.BELL)
        add<Jigsaw>("jigsaw", AquaBlocks.JIGSAW)
        add<Campfire>("campfire", AquaBlocks.CAMPFIRE, AquaBlocks.SOUL_CAMPFIRE)
        add<Beehive>("beehive", AquaBlocks.BEE_NEST, AquaBlocks.BEEHIVE)
        add<SculkSensor>("sculk_sensor", AquaBlocks.SCULK_SENSOR)
        add<SculkCatalyst>("sculk_catalyst", AquaBlocks.SCULK_CATALYST)
        add<SculkShrieker>("sculk_shrieker", AquaBlocks.SCULK_SHRIEKER)
    }

    @JvmStatic
    fun criterion(): RegistryLoaderProvider<KeyedCriterion> = loader {
        add(Key.key("aquamine", "dummy")) { AquaKeyedCriterion(it, "dummy", false, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "trigger")) { AquaKeyedCriterion(it, "trigger", false, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "death_count")) { AquaKeyedCriterion(it, "deathCount", false, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "player_kill_count")) { AquaKeyedCriterion(it, "playerKillCount", false, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "total_kill_count")) { AquaKeyedCriterion(it, "totalKillCount", false, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "health")) { AquaKeyedCriterion(it, "health", true, ObjectiveRenderType.HEARTS) }
        add(Key.key("aquamine", "food")) { AquaKeyedCriterion(it, "food", true, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "air")) { AquaKeyedCriterion(it, "air", true, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "armor")) { AquaKeyedCriterion(it, "armor", true, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "experience")) { AquaKeyedCriterion(it, "xp", true, ObjectiveRenderType.INTEGER) }
        add(Key.key("aquamine", "level")) { AquaKeyedCriterion(it, "level", true, ObjectiveRenderType.INTEGER) }
        AquaAdventure.colors().forEach { color ->
            val name = NamedTextColor.NAMES.key(color)
            add(Key.key("aquamine", "team_kill_$name")) { AquaKeyedCriterion(it, "teamkill.$name", false, ObjectiveRenderType.INTEGER) }
            add(Key.key("aquamine", "killed_by_team_$name")) { AquaKeyedCriterion(it, "killedByTeam.$name", false, ObjectiveRenderType.INTEGER) }
        }
    }

    @JvmStatic
    fun customStatistic(): RegistryLoaderProvider<Key> = loader {
        add("leave_game", StatisticFormatter.DEFAULT)
        add("play_time", StatisticFormatter.TIME)
        add("total_world_time", StatisticFormatter.TIME)
        add("time_since_death", StatisticFormatter.TIME)
        add("time_since_rest", StatisticFormatter.TIME)
        add("sneak_time", StatisticFormatter.TIME)
        add("walk_one_cm", StatisticFormatter.DISTANCE)
        add("crouch_one_cm", StatisticFormatter.DISTANCE)
        add("sprint_one_cm", StatisticFormatter.DISTANCE)
        add("walk_on_water_one_cm", StatisticFormatter.DISTANCE)
        add("fall_one_cm", StatisticFormatter.DISTANCE)
        add("climb_one_cm", StatisticFormatter.DISTANCE)
        add("fly_one_cm", StatisticFormatter.DISTANCE)
        add("walk_under_water_one_cm", StatisticFormatter.DISTANCE)
        add("minecart_one_cm", StatisticFormatter.DISTANCE)
        add("boat_one_cm", StatisticFormatter.DISTANCE)
        add("pig_one_cm", StatisticFormatter.DISTANCE)
        add("horse_one_cm", StatisticFormatter.DISTANCE)
        add("aviate_one_cm", StatisticFormatter.DISTANCE)
        add("swim_one_cm", StatisticFormatter.DISTANCE)
        add("strider_one_cm", StatisticFormatter.DISTANCE)
        add("jump", StatisticFormatter.DEFAULT)
        add("drop", StatisticFormatter.DEFAULT)
        add("damage_dealt", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_dealt_absorbed", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_dealt_resisted", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_taken", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_blocked_by_shield", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_absorbed", StatisticFormatter.DIVIDE_BY_TEN)
        add("damage_resisted", StatisticFormatter.DIVIDE_BY_TEN)
        add("deaths", StatisticFormatter.DEFAULT)
        add("mob_kills", StatisticFormatter.DEFAULT)
        add("animals_bred", StatisticFormatter.DEFAULT)
        add("player_kills", StatisticFormatter.DEFAULT)
        add("fish_caught", StatisticFormatter.DEFAULT)
        add("talked_to_villager", StatisticFormatter.DEFAULT)
        add("traded_with_villager", StatisticFormatter.DEFAULT)
        add("eat_cake_slice", StatisticFormatter.DEFAULT)
        add("fill_cauldron", StatisticFormatter.DEFAULT)
        add("use_cauldron", StatisticFormatter.DEFAULT)
        add("clean_armor", StatisticFormatter.DEFAULT)
        add("clean_banner", StatisticFormatter.DEFAULT)
        add("clean_shulker_box", StatisticFormatter.DEFAULT)
        add("interact_with_brewingstand", StatisticFormatter.DEFAULT)
        add("interact_with_beacon", StatisticFormatter.DEFAULT)
        add("inspect_dropper", StatisticFormatter.DEFAULT)
        add("inspect_hopper", StatisticFormatter.DEFAULT)
        add("inspect_dispenser", StatisticFormatter.DEFAULT)
        add("play_noteblock", StatisticFormatter.DEFAULT)
        add("tune_noteblock", StatisticFormatter.DEFAULT)
        add("pot_flower", StatisticFormatter.DEFAULT)
        add("trigger_trapped_chest", StatisticFormatter.DEFAULT)
        add("open_enderchest", StatisticFormatter.DEFAULT)
        add("enchant_item", StatisticFormatter.DEFAULT)
        add("play_record", StatisticFormatter.DEFAULT)
        add("interact_with_furnace", StatisticFormatter.DEFAULT)
        add("interact_with_crafting_table", StatisticFormatter.DEFAULT)
        add("open_chest", StatisticFormatter.DEFAULT)
        add("sleep_in_bed", StatisticFormatter.DEFAULT)
        add("open_shulker_box", StatisticFormatter.DEFAULT)
        add("open_barrel", StatisticFormatter.DEFAULT)
        add("interact_with_blast_furnace", StatisticFormatter.DEFAULT)
        add("interact_with_smoker", StatisticFormatter.DEFAULT)
        add("interact_with_lectern", StatisticFormatter.DEFAULT)
        add("interact_with_campfire", StatisticFormatter.DEFAULT)
        add("interact_with_cartography_table", StatisticFormatter.DEFAULT)
        add("interact_with_loom", StatisticFormatter.DEFAULT)
        add("interact_with_stonecutter", StatisticFormatter.DEFAULT)
        add("bell_ring", StatisticFormatter.DEFAULT)
        add("raid_trigger", StatisticFormatter.DEFAULT)
        add("raid_win", StatisticFormatter.DEFAULT)
        add("interact_with_anvil", StatisticFormatter.DEFAULT)
        add("interact_with_grindstone", StatisticFormatter.DEFAULT)
        add("target_hit", StatisticFormatter.DEFAULT)
        add("interact_with_smithing_table", StatisticFormatter.DEFAULT)
    }

    @JvmStatic
    fun damageType(): RegistryLoaderProvider<DamageType> = loader {
        put("in_fire", "inFire") { bypassArmor().fire() }
        put("lightning_bolt", "lightningBolt")
        put("on_fire", "onFire") { bypassArmor().fire() }
        put("lava", "lava") { fire() }
        put("hot_floor", "hotFloor") { fire() }
        put("suffocation", "inWall") { bypassArmor() }
        put("cramming", "cramming") { bypassArmor() }
        put("drowning", "drown") { bypassArmor() }
        put("starving", "starve") { bypassArmor().bypassMagic() }
        put("cactus", "cactus")
        put("falling", "fall") { bypassArmor().fall() }
        put("fly_into_wall", "flyIntoWall") { bypassArmor() }
        put("void", "outOfWorld") { bypassArmor().bypassInvulnerability() }
        put("generic", "generic") { bypassArmor() }
        put("magic", "magic") { bypassArmor().magic() }
        put("wither", "wither") { bypassArmor() }
        put("anvil", "anvil") { damagesHelmet() }
        put("falling_block", "fallingBlock") { damagesHelmet() }
        put("dragon_breath", "dragonBreath") { bypassArmor() }
        put("dry_out", "dryout")
        put("sweet_berry_bush", "sweetBerryBush")
        put("freezing", "freeze") { bypassArmor() }
        put("falling_stalactite", "fallingStalactite") { damagesHelmet() }
        put("stalagmite", "stalagmite") { fall() }
        put("sting", "sting")
        put("generic_mob_attack", "mob")
        put("passive_mob_attack", "mob") { noAggravatesTarget() }
        put("indirect_mob_attack", "mob") { projectile() }
        put("player_attack", "player")
        put("arrow", "arrow") { projectile() }
        put("trident", "trident") { projectile() }
        put("fireworks", "fireworks") { explosion() }
        put("fireball", "fireball") { fire().projectile() }
        put("fireball_on_fire", "onFire") { fire().projectile() }
        put("wither_skull", "witherSkull") { projectile() }
        put("thrown_projectile", "thrown") { projectile() }
        put("indirect_magic", "indirectMagic") { bypassArmor().magic() }
        put("thorns", "thorns") { thorns().magic() }
        put("explosion", "explosion") { scalesWithDifficulty().explosion() }
        put("player_explosion", "explosion.player") { scalesWithDifficulty().explosion() }
        put("sonic_boom", "sonic_boom") { bypassArmor().bypassEnchantments().magic() }
        put("bad_respawn_point", "badRespawnPoint") { scalesWithDifficulty().explosion() }
    }

    @JvmStatic
    fun entityCategory(): RegistryLoaderProvider<EntityCategory> = loader {
        val noDespawn = 32
        val despawn = 128
        add(Key.key("monster")) { AquaEntityCategory(it, 70, false, false, despawn, noDespawn) }
        add(Key.key("creature")) { AquaEntityCategory(it, 10, true, true, despawn, noDespawn) }
        add(Key.key("ambient")) { AquaEntityCategory(it, 15, true, false, despawn, noDespawn) }
        add(Key.key("underground_water_creature")) { AquaEntityCategory(it, 5, true, false, despawn, noDespawn) }
        add(Key.key("water_creature")) { AquaEntityCategory(it, 5, true, false, despawn, noDespawn) }
        add(Key.key("water_ambient")) { AquaEntityCategory(it, 20, true, false, 64, noDespawn) }
        add(Key.key("misc")) { AquaEntityCategory(it, -1, true, true, despawn, noDespawn) }
    }

    @JvmStatic
    fun inventoryType(): RegistryLoaderProvider<InventoryType> = loader {
        val chestTitle = Component.translatable("container.chest")
        add(Key.key("chest_one_row")) { AquaInventoryType(it, 9, chestTitle) }
        add(Key.key("chest_two_rows")) { AquaInventoryType(it, 9 * 2, chestTitle) }
        add(Key.key("chest_three_rows")) { AquaInventoryType(it, 9 * 3, chestTitle) }
        val doubleChestTitle = Component.translatable("container.chestDouble")
        add(Key.key("chest_four_rows")) { AquaInventoryType(it, 9 * 4, doubleChestTitle) }
        add(Key.key("chest_five_rows")) { AquaInventoryType(it, 9 * 5, doubleChestTitle) }
        add(Key.key("chest_six_rows")) { AquaInventoryType(it, 9 * 6, doubleChestTitle) }
        add(Key.key("generic_3x3")) { AquaInventoryType(it, 3 * 3, Component.translatable("container.dispenser")) }
        add(Key.key("anvil")) { AquaInventoryType(it, 3, Component.translatable("container.repair")) }
        add(Key.key("beacon")) { AquaInventoryType(it, 1, Component.translatable("container.beacon")) }
        add(Key.key("blast_furnace")) { AquaInventoryType(it, 3, Component.translatable("container.blast_furnace")) }
        add(Key.key("brewing_stand")) { AquaInventoryType(it, 5, Component.translatable("container.brewing")) }
        add(Key.key("cartography_table")) { AquaInventoryType(it, 3, Component.translatable("container.cartography_table")) }
        add(Key.key("crafting")) { AquaInventoryType(it, 5, Component.translatable("container.crafting")) }
        add(Key.key("enchantment")) { AquaInventoryType(it, 2, Component.translatable("container.enchant")) }
        add(Key.key("furnace")) { AquaInventoryType(it, 3, Component.translatable("container.furnace")) }
        add(Key.key("grindstone")) { AquaInventoryType(it, 3, Component.translatable("container.grindstone_title")) }
        add(Key.key("hopper")) { AquaInventoryType(it, 5, Component.translatable("container.hopper")) }
        add(Key.key("lectern")) { AquaInventoryType(it, 1, Component.translatable("container.lectern")) }
        add(Key.key("loom")) { AquaInventoryType(it, 4, Component.translatable("container.loom")) }
        add(Key.key("merchant")) { AquaInventoryType(it, 3, Component.translatable("merchant.trades")) }
        add(Key.key("shulker_box")) { AquaInventoryType(it, 9 * 3, Component.translatable("container.shulkerBox")) }
        add(Key.key("smoker")) { AquaInventoryType(it, 3, Component.translatable("container.smoker")) }
        add(Key.key("smithing")) { AquaInventoryType(it, 3, Component.translatable("container.upgrade")) }
        add(Key.key("stonecutter")) { AquaInventoryType(it, 2, Component.translatable("container.stonecutter")) }
    }

    @JvmStatic
    fun paintingVariant(): RegistryLoaderProvider<PaintingVariant> = loader {
        add(Key.key("kebab")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("aztec")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("alban")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("aztec2")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("bomb")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("plant")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("wasteland")) { AquaPaintingVariant(it, 16, 16) }
        add(Key.key("pool")) { AquaPaintingVariant(it, 32, 16) }
        add(Key.key("courbet")) { AquaPaintingVariant(it, 32, 16) }
        add(Key.key("sea")) { AquaPaintingVariant(it, 32, 16) }
        add(Key.key("sunset")) { AquaPaintingVariant(it, 32, 16) }
        add(Key.key("creebet")) { AquaPaintingVariant(it, 32, 16) }
        add(Key.key("wanderer")) { AquaPaintingVariant(it, 16, 32) }
        add(Key.key("graham")) { AquaPaintingVariant(it, 16, 32) }
        add(Key.key("match")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("bust")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("stage")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("void")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("skull_and_roses")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("wither")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("fighters")) { AquaPaintingVariant(it, 64, 32) }
        add(Key.key("pointer")) { AquaPaintingVariant(it, 64, 64) }
        add(Key.key("pigscene")) { AquaPaintingVariant(it, 64, 64) }
        add(Key.key("burning_skull")) { AquaPaintingVariant(it, 64, 64) }
        add(Key.key("skeleton")) { AquaPaintingVariant(it, 64, 48) }
        add(Key.key("earth")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("wind")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("water")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("fire")) { AquaPaintingVariant(it, 32, 32) }
        add(Key.key("donkey_kong")) { AquaPaintingVariant(it, 64, 48) }
    }

    @JvmStatic
    fun particleType(): RegistryLoaderProvider<ParticleType> = loader {
        add(Key.key("ambient_entity_effect"), ::AquaSimpleParticleType)
        add(Key.key("angry_villager"), ::AquaSimpleParticleType)
        add(Key.key("block"), ::AquaBlockParticleType)
        add(Key.key("block_marker"), ::AquaBlockParticleType)
        add(Key.key("bubble"), ::AquaDirectionalParticleType)
        add(Key.key("cloud"), ::AquaDirectionalParticleType)
        add(Key.key("crit"), ::AquaDirectionalParticleType)
        add(Key.key("damage_indicator"), ::AquaDirectionalParticleType)
        add(Key.key("dragon_breath"), ::AquaDirectionalParticleType)
        add(Key.key("dripping_lava"), ::AquaSimpleParticleType)
        add(Key.key("falling_lava"), ::AquaSimpleParticleType)
        add(Key.key("landing_lava"), ::AquaSimpleParticleType)
        add(Key.key("dripping_water"), ::AquaSimpleParticleType)
        add(Key.key("falling_water"), ::AquaSimpleParticleType)
        add(Key.key("dust"), ::AquaDustParticleType)
        add(Key.key("dust_color_transition"), ::AquaDustTransitionParticleType)
        add(Key.key("effect"), ::AquaSimpleParticleType)
        add(Key.key("elder_guardian"), ::AquaSimpleParticleType)
        add(Key.key("enchanted_hit"), ::AquaDirectionalParticleType)
        add(Key.key("enchant"), ::AquaDirectionalParticleType)
        add(Key.key("end_rod"), ::AquaDirectionalParticleType)
        add(Key.key("entity_effect"), ::AquaColorParticleType)
        add(Key.key("explosion_emitter"), ::AquaSimpleParticleType)
        add(Key.key("explosion"), ::AquaSimpleParticleType)
        add(Key.key("falling_dust"), ::AquaBlockParticleType)
        add(Key.key("firework"), ::AquaDirectionalParticleType)
        add(Key.key("fishing"), ::AquaDirectionalParticleType)
        add(Key.key("flame"), ::AquaDirectionalParticleType)
        add(Key.key("soul_fire_flame"), ::AquaDirectionalParticleType)
        add(Key.key("soul"), ::AquaDirectionalParticleType)
        add(Key.key("flash"), ::AquaSimpleParticleType)
        add(Key.key("happy_villager"), ::AquaSimpleParticleType)
        add(Key.key("composter"), ::AquaSimpleParticleType)
        add(Key.key("heart"), ::AquaSimpleParticleType)
        add(Key.key("instant_effect"), ::AquaSimpleParticleType)
        add(Key.key("item"), ::AquaItemParticleType)
        add(Key.key("vibration"), ::AquaVibrationParticleType)
        add(Key.key("item_slime"), ::AquaSimpleParticleType)
        add(Key.key("item_snowball"), ::AquaSimpleParticleType)
        add(Key.key("large_smoke"), ::AquaDirectionalParticleType)
        add(Key.key("lava"), ::AquaSimpleParticleType)
        add(Key.key("mycelium"), ::AquaSimpleParticleType)
        add(Key.key("note"), ::AquaNoteParticleType)
        add(Key.key("poof"), ::AquaDirectionalParticleType)
        add(Key.key("portal"), ::AquaDirectionalParticleType)
        add(Key.key("rain"), ::AquaSimpleParticleType)
        add(Key.key("smoke"), ::AquaDirectionalParticleType)
        add(Key.key("sneeze"), ::AquaDirectionalParticleType)
        add(Key.key("spit"), ::AquaDirectionalParticleType)
        add(Key.key("squid_ink"), ::AquaDirectionalParticleType)
        add(Key.key("sweep_attack"), ::AquaSimpleParticleType)
        add(Key.key("totem_of_undying"), ::AquaDirectionalParticleType)
        add(Key.key("underwater"), ::AquaSimpleParticleType)
        add(Key.key("splash"), ::AquaSimpleParticleType)
        add(Key.key("witch"), ::AquaSimpleParticleType)
        add(Key.key("bubble_pop"), ::AquaDirectionalParticleType)
        add(Key.key("current_down"), ::AquaSimpleParticleType)
        add(Key.key("bubble_column_up"), ::AquaDirectionalParticleType)
        add(Key.key("nautilus"), ::AquaDirectionalParticleType)
        add(Key.key("dolphin"), ::AquaSimpleParticleType)
        add(Key.key("campfire_cosy_smoke"), ::AquaDirectionalParticleType)
        add(Key.key("campfire_signal_smoke"), ::AquaDirectionalParticleType)
        add(Key.key("dripping_honey"), ::AquaSimpleParticleType)
        add(Key.key("falling_honey"), ::AquaSimpleParticleType)
        add(Key.key("landing_honey"), ::AquaSimpleParticleType)
        add(Key.key("falling_nectar"), ::AquaSimpleParticleType)
        add(Key.key("falling_spore_blossom"), ::AquaSimpleParticleType)
        add(Key.key("ash"), ::AquaSimpleParticleType)
        add(Key.key("crimson_spore"), ::AquaSimpleParticleType)
        add(Key.key("warped_spore"), ::AquaSimpleParticleType)
        add(Key.key("spore_blossom_air"), ::AquaSimpleParticleType)
        add(Key.key("dripping_obsidian_tear"), ::AquaSimpleParticleType)
        add(Key.key("falling_obsidian_tear"), ::AquaSimpleParticleType)
        add(Key.key("landing_obsidian_tear"), ::AquaSimpleParticleType)
        add(Key.key("reverse_portal"), ::AquaDirectionalParticleType)
        add(Key.key("white_ash"), ::AquaSimpleParticleType)
        add(Key.key("small_flame"), ::AquaSimpleParticleType)
        add(Key.key("snowflake"), ::AquaSimpleParticleType)
        add(Key.key("dripping_dripstone_lava"), ::AquaSimpleParticleType)
        add(Key.key("falling_dripstone_lava"), ::AquaSimpleParticleType)
        add(Key.key("dripping_dripstone_water"), ::AquaSimpleParticleType)
        add(Key.key("falling_dripstone_water"), ::AquaSimpleParticleType)
        add(Key.key("glow_squid_ink"), ::AquaSimpleParticleType)
        add(Key.key("glow"), ::AquaSimpleParticleType)
        add(Key.key("wax_on"), ::AquaSimpleParticleType)
        add(Key.key("wax_off"), ::AquaSimpleParticleType)
        add(Key.key("electric_spark"), ::AquaSimpleParticleType)
        add(Key.key("scrape"), ::AquaSimpleParticleType)
    }

    @JvmStatic
    fun statisticType(): RegistryLoaderProvider<StatisticType<*>> = loader {
        add(Key.key("mined")) { AquaStatisticType(it, AquaRegistries.BLOCK) }
        add(Key.key("crafted")) { AquaStatisticType(it, AquaRegistries.ITEM) }
        add(Key.key("used")) { AquaStatisticType(it, AquaRegistries.ITEM) }
        add(Key.key("broken")) { AquaStatisticType(it, AquaRegistries.ITEM) }
        add(Key.key("picked_up")) { AquaStatisticType(it, AquaRegistries.ITEM) }
        add(Key.key("dropped")) { AquaStatisticType(it, AquaRegistries.ITEM) }
        add(Key.key("killed")) { AquaStatisticType(it, AquaRegistries.ENTITY_TYPE) }
        add(Key.key("killed_by")) { AquaStatisticType(it, AquaRegistries.ENTITY_TYPE) }
        add(Key.key("custom")) { AquaStatisticType(it, AquaRegistries.CUSTOM_STATISTIC) }
    }

    @Suppress("LongMethod")
    @JvmStatic
    fun potionType(): RegistryLoaderProvider<AquaPotionType> = loader {
        val magicDamageSource = AquaDamageSource(DamageTypes.MAGIC.get())

        put("speed", PotionTypeCategory.BENEFICIAL, 8171462) { key ->
            val template = PotionAttributeTemplate(key, 0.2, BasicModifierOperation.MULTIPLY_TOTAL)
            val attribute = AttributeTypes.MOVEMENT_SPEED.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("slowness", PotionTypeCategory.HARMFUL, 5926017) { key ->
            val template = PotionAttributeTemplate(key, -0.15, BasicModifierOperation.MULTIPLY_TOTAL)
            val attribute = AttributeTypes.MOVEMENT_SPEED.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("haste", PotionTypeCategory.BENEFICIAL, 14270531) { key ->
            val template = PotionAttributeTemplate(key, 0.1, BasicModifierOperation.MULTIPLY_TOTAL)
            val attribute = AttributeTypes.ATTACK_SPEED.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("mining_fatigue", PotionTypeCategory.HARMFUL, 4866583) { key ->
            val template = PotionAttributeTemplate(key, -0.1, BasicModifierOperation.MULTIPLY_TOTAL)
            val attribute = AttributeTypes.ATTACK_SPEED.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("strength", PotionTypeCategory.BENEFICIAL, 9643043) { key ->
            val template = PotionAttributeTemplate(key, 3.0, BasicModifierOperation.ADDITION)
            val attribute = AttributeTypes.ATTACK_DAMAGE.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("instant_health", PotionTypeCategory.BENEFICIAL, 16262179) { key ->
            onApply { entity, effect ->
                // TODO Inversion for zombie and etc.
                entity.health += max(4 shl effect.amplifier.toInt(), 0).toFloat()
            }
        }
        put("instant_damage", PotionTypeCategory.HARMFUL, 4393481) { key ->
            onApply { entity, effect ->
                // TODO Inversion for zombie and etc.
                entity.damage(magicDamageSource, (6 shl effect.amplifier.toInt()).toFloat())
            }
        }
        put("jump_boost", PotionTypeCategory.BENEFICIAL, 2293580) { key ->
            // TODO Safe fall distance attribute here?
        }
        put("nausea", PotionTypeCategory.HARMFUL, 5578058)
        put("regeneration", PotionTypeCategory.BENEFICIAL, 13458603) { key ->
            fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
                val interval = 50 ushr amplifier.toInt()
                return interval <= 0 || duration % interval == 0
            }

            onTick { entity, effect, ticksToEnd ->
                if(shouldApplyEffectTick(ticksToEnd, effect.amplifier) && entity.health < entity.maxHealth) {
                    entity.health += 1
                }
            }
        }

        // TODO Not forget implement checking for this effects in damage calculation:
        put("resistance", PotionTypeCategory.BENEFICIAL, 10044730)
        put("fire_resistance", PotionTypeCategory.BENEFICIAL, 14981690)

        // TODO Not forget implement checking for this effect in water logic
        put("water_breathing", PotionTypeCategory.BENEFICIAL, 3035801)

        put("invisibility", PotionTypeCategory.BENEFICIAL, 8356754) // Clientside effect
        put("blindness", PotionTypeCategory.HARMFUL, 2039587) // Clientside effect
        put("night_vision", PotionTypeCategory.BENEFICIAL, 2039713) // Clientside effect

        put("hunger", PotionTypeCategory.HARMFUL, 5797459) { key ->
            onTick { entity, effect, ticksToEnd ->
                // Only player has food level.
                if(entity !is AquaPlayer) return@onTick

                entity.foodExhaustionLevel = min(entity.hungerSystem.exhaustionLevel + (0.005f * effect.amplifier), 40f)
            }
        }
        put("weakness", PotionTypeCategory.HARMFUL, 4738376) { key ->
            val template = PotionAttributeTemplate(key, -4.0, BasicModifierOperation.ADDITION)
            val attribute = AttributeTypes.ATTACK_DAMAGE.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("poison", PotionTypeCategory.HARMFUL, 5149489) { key ->
            fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
                val interval = 25 ushr amplifier.toInt()
                return interval <= 0 || duration % interval == 0
            }

            onTick { entity, effect, ticksToEnd ->
                if(shouldApplyEffectTick(ticksToEnd, effect.amplifier) && entity.health > 1) {
                    entity.damage(magicDamageSource, 1f)
                }
            }
        }
        put("wither", PotionTypeCategory.HARMFUL, 3484199) { key ->
            val witherDamageSource = AquaDamageSource(DamageTypes.WITHER.get())

            fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
                val interval = 40 ushr amplifier.toInt()
                return interval <= 0 || duration % interval == 0
            }

            onTick { entity, effect, ticksToEnd ->
                if(shouldApplyEffectTick(ticksToEnd, effect.amplifier)) {
                    entity.damage(witherDamageSource, 1f)
                }
            }
        }
        put("health_boost", PotionTypeCategory.BENEFICIAL, 16284963) { key ->
            val template = PotionAttributeTemplate(key, 4.0, BasicModifierOperation.ADDITION)
            val attribute = AttributeTypes.MAX_HEALTH.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("absorption", PotionTypeCategory.BENEFICIAL, 2445989) { key ->
            // TODO How it works in vanilla?
        }
        put("saturation", PotionTypeCategory.BENEFICIAL, 16262179) { key ->
            onApply { entity, effect ->
                // Only player has food level.
                if(entity !is AquaPlayer) return@onApply

                entity.foodLevel += effect.amplifier
            }
        }
        put("glowing", PotionTypeCategory.NEUTRAL, 9740385) { key ->
            onApply { entity, effect -> entity.isGlowing = true }
            onEnd { entity, effect -> entity.isGlowing = false }
        }
        put("levitation", PotionTypeCategory.HARMFUL, 13565951) // Clientside effect
        put("luck", PotionTypeCategory.BENEFICIAL, 3381504) { key ->
            val template = PotionAttributeTemplate(key, 1.0, BasicModifierOperation.ADDITION)
            val attribute = AttributeTypes.LUCK.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("unluck", PotionTypeCategory.HARMFUL, 12624973) { key ->
            val template = PotionAttributeTemplate(key, -1.0, BasicModifierOperation.ADDITION)
            val attribute = AttributeTypes.LUCK.get()

            onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
            onEnd { entity, effect -> entity.attributes.reset(attribute, template) }
        }
        put("slow_falling", PotionTypeCategory.BENEFICIAL, 16773073) // Clientside effect
        put("conduit_power", PotionTypeCategory.BENEFICIAL, 1950417) // TODO Handle this effect in water system
        put("dolphins_grace", PotionTypeCategory.BENEFICIAL, 8954814) // Clientside effect
        put("bad_omen", PotionTypeCategory.NEUTRAL, 745784) // TODO Handle this effect when villages are implemented
        put("hero_of_the_village", PotionTypeCategory.BENEFICIAL, 4521796) // TODO Handle this effect when villagers are implemented
        put("darkness", PotionTypeCategory.HARMFUL, 2696993) // Clientside effect
    }

    /**
     * This method exists to allow creating loaders in a more DSL-like style, which is much cleaner.
     */
    @JvmStatic
    private inline fun <T> loader(crossinline init: RegistryLoader<T>.() -> Unit): RegistryLoaderProvider<T> =
        Supplier { RegistryLoader<T>().apply(init) }
}

private typealias RegistryLoaderProvider<T> = Supplier<RegistryLoader<T>>

private inline fun RegistryLoader<DamageType>.put(key: String, translationKey: String, builder: AquaDamageType.Builder.() -> Unit = {}) {
    add(Key.key(key)) { AquaDamageType.Builder(it, translationKey).apply(builder).build() }
}

private inline fun RegistryLoader<AquaPotionType>.put(
    key: String,
    category: PotionTypeCategory,
    color: Int,
    handler: PotionEffectHandler.(key: String) -> Unit = {}
) {
    add(Key.key(key)) {
        AquaPotionType(
            it,
            "effect.minecraft.$key",
            category,
            Color(color),
            PotionEffectHandler().also { handler(it, key) }
        )
    }
}

private inline fun AttributeMap.modify(attribute: AttributeType, modifier: AttributeModifier) {
    val attribute = this.getAttribute(attribute.downcast())
    attribute?.removeModifier(modifier.uuid)
    attribute?.addModifier(modifier)
}

private inline fun AttributeMap.reset(attribute: AttributeType, template: PotionAttributeTemplate) {
    this.getAttribute(attribute.downcast())?.removeModifier(template.uuid)
}

private fun RegistryLoader<Key>.add(name: String, formatter: StatisticFormatter) {
    add(Key.key(name)) {
        StatisticTypes.CUSTOM.get().getStatistic(it, formatter)
        it
    }
}

private fun <T : BlockEntity> RegistryLoader<BlockEntityType<*>>.add(name: String, vararg blocks: Block) {
    add(Key.key(name)) { AquaBlockEntityType<T>(it, ImmutableSet.copyOf(blocks)) }
}
