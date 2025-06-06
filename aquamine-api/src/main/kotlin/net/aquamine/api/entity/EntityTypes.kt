package net.aquamine.api.entity

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.entity.ambient.Bat
import net.aquamine.api.entity.animal.Axolotl
import net.aquamine.api.entity.animal.Bee
import net.aquamine.api.entity.animal.Chicken
import net.aquamine.api.entity.animal.Cat
import net.aquamine.api.entity.animal.Fox
import net.aquamine.api.entity.animal.Ocelot
import net.aquamine.api.entity.animal.Cow
import net.aquamine.api.entity.animal.Goat
import net.aquamine.api.entity.animal.Mooshroom
import net.aquamine.api.entity.animal.Panda
import net.aquamine.api.entity.animal.Parrot
import net.aquamine.api.entity.animal.Pig
import net.aquamine.api.entity.animal.PolarBear
import net.aquamine.api.entity.animal.Rabbit
import net.aquamine.api.entity.animal.Sheep
import net.aquamine.api.entity.animal.Turtle
import net.aquamine.api.entity.animal.Wolf
import net.aquamine.api.entity.aquatic.Cod
import net.aquamine.api.entity.aquatic.Dolphin
import net.aquamine.api.entity.aquatic.GlowSquid
import net.aquamine.api.entity.aquatic.Pufferfish
import net.aquamine.api.entity.aquatic.Salmon
import net.aquamine.api.entity.aquatic.Squid
import net.aquamine.api.entity.aquatic.TropicalFish
import net.aquamine.api.entity.hanging.Painting
import net.aquamine.api.entity.monster.Blaze
import net.aquamine.api.entity.monster.CaveSpider
import net.aquamine.api.entity.monster.Creeper
import net.aquamine.api.entity.monster.Drowned
import net.aquamine.api.entity.monster.Endermite
import net.aquamine.api.entity.monster.Giant
import net.aquamine.api.entity.monster.Guardian
import net.aquamine.api.entity.monster.Husk
import net.aquamine.api.entity.monster.Silverfish
import net.aquamine.api.entity.monster.Spider
import net.aquamine.api.entity.monster.Zombie
import net.aquamine.api.entity.player.Player
import net.aquamine.api.entity.projectile.Arrow
import net.aquamine.api.entity.projectile.DragonFireball
import net.aquamine.api.entity.projectile.Egg
import net.aquamine.api.entity.projectile.EnderPearl
import net.aquamine.api.entity.projectile.ExperienceBottle
import net.aquamine.api.entity.projectile.FireworkRocket
import net.aquamine.api.entity.projectile.FishingHook
import net.aquamine.api.entity.projectile.LargeFireball
import net.aquamine.api.entity.projectile.LlamaSpit
import net.aquamine.api.entity.projectile.ShulkerBullet
import net.aquamine.api.entity.projectile.SmallFireball
import net.aquamine.api.entity.projectile.Snowball
import net.aquamine.api.entity.projectile.SpectralArrow
import net.aquamine.api.entity.projectile.ThrownPotion
import net.aquamine.api.entity.projectile.Trident
import net.aquamine.api.entity.projectile.WitherSkull
import net.aquamine.api.entity.vehicle.Boat
import net.aquamine.api.entity.vehicle.CommandBlockMinecart
import net.aquamine.api.entity.vehicle.FurnaceMinecart
import net.aquamine.api.entity.vehicle.Minecart
import net.aquamine.api.entity.vehicle.TNTMinecart
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the types of entities in the game.
 */
@Catalogue(EntityType::class)
object EntityTypes {

    // TODO: Make each of these be of their respective entity types when they exist
    // @formatter:off
    @JvmField val ALLAY: RegistryReference<EntityType<Entity>> = get("allay")
    @JvmField val AREA_EFFECT_CLOUD: RegistryReference<EntityType<AreaEffectCloud>> = get("area_effect_cloud")
    @JvmField val ARMOR_STAND: RegistryReference<EntityType<ArmorStand>> = get("armor_stand")
    @JvmField val ARROW: RegistryReference<EntityType<Arrow>> = get("arrow")
    @JvmField val AXOLOTL: RegistryReference<EntityType<Axolotl>> = get("axolotl")
    @JvmField val BAT: RegistryReference<EntityType<Bat>> = get("bat")
    @JvmField val BEE: RegistryReference<EntityType<Bee>> = get("bee")
    @JvmField val BLAZE: RegistryReference<EntityType<Blaze>> = get("blaze")
    @JvmField val BOAT: RegistryReference<EntityType<Boat>> = get("boat")
    @JvmField val CHEST_BOAT: RegistryReference<EntityType<Entity>> = get("chest_boat")
    @JvmField val CAT: RegistryReference<EntityType<Cat>> = get("cat")
    @JvmField val CAMEL: RegistryReference<EntityType<Entity>> = get("camel")
    @JvmField val CAVE_SPIDER: RegistryReference<EntityType<CaveSpider>> = get("cave_spider")
    @JvmField val CHICKEN: RegistryReference<EntityType<Chicken>> = get("chicken")
    @JvmField val COD: RegistryReference<EntityType<Cod>> = get("cod")
    @JvmField val COW: RegistryReference<EntityType<Cow>> = get("cow")
    @JvmField val CREEPER: RegistryReference<EntityType<Creeper>> = get("creeper")
    @JvmField val DOLPHIN: RegistryReference<EntityType<Dolphin>> = get("dolphin")
    @JvmField val DONKEY: RegistryReference<EntityType<Entity>> = get("donkey")
    @JvmField val DRAGON_FIREBALL: RegistryReference<EntityType<DragonFireball>> = get("dragon_fireball")
    @JvmField val DROWNED: RegistryReference<EntityType<Drowned>> = get("drowned")
    @JvmField val ELDER_GUARDIAN: RegistryReference<EntityType<Entity>> = get("elder_guardian")
    @JvmField val END_CRYSTAL: RegistryReference<EntityType<Entity>> = get("end_crystal")
    @JvmField val ENDER_DRAGON: RegistryReference<EntityType<Entity>> = get("ender_dragon")
    @JvmField val ENDERMAN: RegistryReference<EntityType<Entity>> = get("enderman")
    @JvmField val ENDERMITE: RegistryReference<EntityType<Endermite>> = get("endermite")
    @JvmField val EVOKER: RegistryReference<EntityType<Entity>> = get("evoker")
    @JvmField val EVOKER_FANGS: RegistryReference<EntityType<Entity>> = get("evoker_fangs")
    @JvmField val EXPERIENCE_ORB: RegistryReference<EntityType<ExperienceOrb>> = get("experience_orb")
    @JvmField val EYE_OF_ENDER: RegistryReference<EntityType<Entity>> = get("eye_of_ender")
    @JvmField val FALLING_BLOCK: RegistryReference<EntityType<Entity>> = get("falling_block")
    @JvmField val FIREWORK_ROCKET: RegistryReference<EntityType<FireworkRocket>> = get("firework_rocket")
    @JvmField val FOX: RegistryReference<EntityType<Fox>> = get("fox")
    @JvmField val FROG: RegistryReference<EntityType<Entity>> = get("frog")
    @JvmField val GHAST: RegistryReference<EntityType<Entity>> = get("ghast")
    @JvmField val GIANT: RegistryReference<EntityType<Giant>> = get("giant")
    @JvmField val GLOW_ITEM_FRAME: RegistryReference<EntityType<Entity>> = get("glow_item_frame")
    @JvmField val GLOW_SQUID: RegistryReference<EntityType<GlowSquid>> = get("glow_squid")
    @JvmField val GOAT: RegistryReference<EntityType<Goat>> = get("goat")
    @JvmField val GUARDIAN: RegistryReference<EntityType<Guardian>> = get("guardian")
    @JvmField val HOGLIN: RegistryReference<EntityType<Entity>> = get("hoglin")
    @JvmField val HORSE: RegistryReference<EntityType<Entity>> = get("horse")
    @JvmField val HUSK: RegistryReference<EntityType<Husk>> = get("husk")
    @JvmField val ILLUSIONER: RegistryReference<EntityType<Entity>> = get("illusioner")
    @JvmField val IRON_GOLEM: RegistryReference<EntityType<Entity>> = get("iron_golem")
    @JvmField val ITEM: RegistryReference<EntityType<Entity>> = get("item")
    @JvmField val ITEM_FRAME: RegistryReference<EntityType<Entity>> = get("item_frame")
    @JvmField val FIREBALL: RegistryReference<EntityType<LargeFireball>> = get("fireball")
    @JvmField val LEASH_KNOT: RegistryReference<EntityType<Entity>> = get("leash_knot")
    @JvmField val LIGHTNING_BOLT: RegistryReference<EntityType<Entity>> = get("lightning_bolt")
    @JvmField val LLAMA: RegistryReference<EntityType<Entity>> = get("llama")
    @JvmField val LLAMA_SPIT: RegistryReference<EntityType<LlamaSpit>> = get("llama_spit")
    @JvmField val MAGMA_CUBE: RegistryReference<EntityType<Entity>> = get("magma_cube")
    @JvmField val MARKER: RegistryReference<EntityType<Entity>> = get("marker")
    @JvmField val MINECART: RegistryReference<EntityType<Minecart>> = get("minecart")
    @JvmField val CHEST_MINECART: RegistryReference<EntityType<Entity>> = get("chest_minecart")
    @JvmField val COMMAND_BLOCK_MINECART: RegistryReference<EntityType<CommandBlockMinecart>> = get("command_block_minecart")
    @JvmField val FURNACE_MINECART: RegistryReference<EntityType<FurnaceMinecart>> = get("furnace_minecart")
    @JvmField val HOPPER_MINECART: RegistryReference<EntityType<Entity>> = get("hopper_minecart")
    @JvmField val SPAWNER_MINECART: RegistryReference<EntityType<Entity>> = get("spawner_minecart")
    @JvmField val TNT_MINECART: RegistryReference<EntityType<TNTMinecart>> = get("tnt_minecart")
    @JvmField val MULE: RegistryReference<EntityType<Entity>> = get("mule")
    @JvmField val MOOSHROOM: RegistryReference<EntityType<Mooshroom>> = get("mooshroom")
    @JvmField val OCELOT: RegistryReference<EntityType<Ocelot>> = get("ocelot")
    @JvmField val PAINTING: RegistryReference<EntityType<Painting>> = get("painting")
    @JvmField val PANDA: RegistryReference<EntityType<Panda>> = get("panda")
    @JvmField val PARROT: RegistryReference<EntityType<Parrot>> = get("parrot")
    @JvmField val PHANTOM: RegistryReference<EntityType<Entity>> = get("phantom")
    @JvmField val PIG: RegistryReference<EntityType<Pig>> = get("pig")
    @JvmField val PIGLIN: RegistryReference<EntityType<Entity>> = get("piglin")
    @JvmField val PIGLIN_BRUTE: RegistryReference<EntityType<Entity>> = get("piglin_brute")
    @JvmField val PILLAGER: RegistryReference<EntityType<Entity>> = get("pillager")
    @JvmField val POLAR_BEAR: RegistryReference<EntityType<PolarBear>> = get("polar_bear")
    @JvmField val PRIMED_TNT: RegistryReference<EntityType<Entity>> = get("tnt")
    @JvmField val PUFFERFISH: RegistryReference<EntityType<Pufferfish>> = get("pufferfish")
    @JvmField val RABBIT: RegistryReference<EntityType<Rabbit>> = get("rabbit")
    @JvmField val RAVAGER: RegistryReference<EntityType<Entity>> = get("ravager")
    @JvmField val SALMON: RegistryReference<EntityType<Salmon>> = get("salmon")
    @JvmField val SHEEP: RegistryReference<EntityType<Sheep>> = get("sheep")
    @JvmField val SHULKER: RegistryReference<EntityType<Entity>> = get("shulker")
    @JvmField val SHULKER_BULLET: RegistryReference<EntityType<ShulkerBullet>> = get("shulker_bullet")
    @JvmField val SILVERFISH: RegistryReference<EntityType<Silverfish>> = get("silverfish")
    @JvmField val SKELETON: RegistryReference<EntityType<Entity>> = get("skeleton")
    @JvmField val SKELETON_HORSE: RegistryReference<EntityType<Entity>> = get("skeleton_horse")
    @JvmField val SLIME: RegistryReference<EntityType<Entity>> = get("slime")
    @JvmField val SMALL_FIREBALL: RegistryReference<EntityType<SmallFireball>> = get("small_fireball")
    @JvmField val SNOW_GOLEM: RegistryReference<EntityType<Entity>> = get("snow_golem")
    @JvmField val SNOWBALL: RegistryReference<EntityType<Snowball>> = get("snowball")
    @JvmField val SPECTRAL_ARROW: RegistryReference<EntityType<SpectralArrow>> = get("spectral_arrow")
    @JvmField val SPIDER: RegistryReference<EntityType<Spider>> = get("spider")
    @JvmField val SQUID: RegistryReference<EntityType<Squid>> = get("squid")
    @JvmField val STRAY: RegistryReference<EntityType<Entity>> = get("stray")
    @JvmField val STRIDER: RegistryReference<EntityType<Entity>> = get("strider")
    @JvmField val TADPOLE: RegistryReference<EntityType<Entity>> = get("tadpole")
    @JvmField val EGG: RegistryReference<EntityType<Egg>> = get("egg")
    @JvmField val ENDER_PEARL: RegistryReference<EntityType<EnderPearl>> = get("ender_pearl")
    @JvmField val EXPERIENCE_BOTTLE: RegistryReference<EntityType<ExperienceBottle>> = get("experience_bottle")
    @JvmField val POTION: RegistryReference<EntityType<ThrownPotion>> = get("potion")
    @JvmField val TRIDENT: RegistryReference<EntityType<Trident>> = get("trident")
    @JvmField val TRADER_LLAMA: RegistryReference<EntityType<Entity>> = get("trader_llama")
    @JvmField val TROPICAL_FISH: RegistryReference<EntityType<TropicalFish>> = get("tropical_fish")
    @JvmField val TURTLE: RegistryReference<EntityType<Turtle>> = get("turtle")
    @JvmField val VEX: RegistryReference<EntityType<Entity>> = get("vex")
    @JvmField val VILLAGER: RegistryReference<EntityType<Entity>> = get("villager")
    @JvmField val VINDICATOR: RegistryReference<EntityType<Entity>> = get("vindicator")
    @JvmField val WANDERING_TRADER: RegistryReference<EntityType<Entity>> = get("wandering_trader")
    @JvmField val WARDEN: RegistryReference<EntityType<Entity>> = get("warden")
    @JvmField val WITCH: RegistryReference<EntityType<Entity>> = get("witch")
    @JvmField val WITHER: RegistryReference<EntityType<Entity>> = get("wither")
    @JvmField val WITHER_SKELETON: RegistryReference<EntityType<Entity>> = get("wither_skeleton")
    @JvmField val WITHER_SKULL: RegistryReference<EntityType<WitherSkull>> = get("wither_skull")
    @JvmField val WOLF: RegistryReference<EntityType<Wolf>> = get("wolf")
    @JvmField val ZOGLIN: RegistryReference<EntityType<Entity>> = get("zoglin")
    @JvmField val ZOMBIE: RegistryReference<EntityType<Zombie>> = get("zombie")
    @JvmField val ZOMBIE_HORSE: RegistryReference<EntityType<Entity>> = get("zombie_horse")
    @JvmField val ZOMBIE_VILLAGER: RegistryReference<EntityType<Entity>> = get("zombie_villager")
    @JvmField val ZOMBIFIED_PIGLIN: RegistryReference<EntityType<Entity>> = get("zombified_piglin")
    @JvmField val PLAYER: RegistryReference<EntityType<Player>> = get("player")
    @JvmField val FISHING_HOOK: RegistryReference<EntityType<FishingHook>> = get("fishing_bobber")
    // @formatter:on

    @JvmStatic
    private fun <T : Entity> get(name: String): RegistryReference<EntityType<T>> = RegistryReference.of(Registries.ENTITY_TYPE, Key.key(name))
}
