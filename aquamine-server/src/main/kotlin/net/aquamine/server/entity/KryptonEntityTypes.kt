package net.aquamine.server.entity

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.EntityCategories
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.registry.RegistryReference
import net.aquamine.server.entity.ambient.KryptonBat
import net.aquamine.server.entity.animal.KryptonAxolotl
import net.aquamine.server.entity.animal.KryptonBee
import net.aquamine.server.entity.animal.KryptonCat
import net.aquamine.server.entity.animal.KryptonChicken
import net.aquamine.server.entity.animal.KryptonCow
import net.aquamine.server.entity.animal.KryptonFox
import net.aquamine.server.entity.animal.KryptonGoat
import net.aquamine.server.entity.animal.KryptonMooshroom
import net.aquamine.server.entity.animal.KryptonOcelot
import net.aquamine.server.entity.animal.KryptonPanda
import net.aquamine.server.entity.animal.KryptonParrot
import net.aquamine.server.entity.animal.KryptonPig
import net.aquamine.server.entity.animal.KryptonPolarBear
import net.aquamine.server.entity.animal.KryptonRabbit
import net.aquamine.server.entity.animal.KryptonSheep
import net.aquamine.server.entity.animal.KryptonTurtle
import net.aquamine.server.entity.animal.KryptonWolf
import net.aquamine.server.entity.aquatic.KryptonCod
import net.aquamine.server.entity.aquatic.KryptonDolphin
import net.aquamine.server.entity.aquatic.KryptonGlowSquid
import net.aquamine.server.entity.aquatic.KryptonPufferfish
import net.aquamine.server.entity.aquatic.KryptonSalmon
import net.aquamine.server.entity.aquatic.KryptonSquid
import net.aquamine.server.entity.aquatic.KryptonTropicalFish
import net.aquamine.server.entity.hanging.KryptonPainting
import net.aquamine.server.entity.monster.KryptonBlaze
import net.aquamine.server.entity.monster.KryptonCaveSpider
import net.aquamine.server.entity.monster.KryptonCreeper
import net.aquamine.server.entity.monster.KryptonDrowned
import net.aquamine.server.entity.monster.KryptonEndermite
import net.aquamine.server.entity.monster.KryptonGiant
import net.aquamine.server.entity.monster.KryptonGuardian
import net.aquamine.server.entity.monster.KryptonHusk
import net.aquamine.server.entity.monster.KryptonSilverfish
import net.aquamine.server.entity.monster.KryptonSpider
import net.aquamine.server.entity.monster.KryptonZombie
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.projectile.KryptonArrow
import net.aquamine.server.entity.projectile.KryptonDragonFireball
import net.aquamine.server.entity.projectile.KryptonEgg
import net.aquamine.server.entity.projectile.KryptonEnderPearl
import net.aquamine.server.entity.projectile.KryptonExperienceBottle
import net.aquamine.server.entity.projectile.KryptonFireworkRocket
import net.aquamine.server.entity.projectile.KryptonFishingHook
import net.aquamine.server.entity.projectile.KryptonLargeFireball
import net.aquamine.server.entity.projectile.KryptonLlamaSpit
import net.aquamine.server.entity.projectile.KryptonShulkerBullet
import net.aquamine.server.entity.projectile.KryptonSmallFireball
import net.aquamine.server.entity.projectile.KryptonSnowball
import net.aquamine.server.entity.projectile.KryptonSpectralArrow
import net.aquamine.server.entity.projectile.KryptonThrownPotion
import net.aquamine.server.entity.projectile.KryptonTrident
import net.aquamine.server.entity.projectile.KryptonWitherSkull
import net.aquamine.server.entity.vehicle.KryptonBoat
import net.aquamine.server.entity.vehicle.KryptonCommandBlockMinecart
import net.aquamine.server.entity.vehicle.KryptonFurnaceMinecart
import net.aquamine.server.entity.vehicle.KryptonMinecart
import net.aquamine.server.entity.vehicle.KryptonTNTMinecart
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.world.block.KryptonBlocks

@Suppress("MagicNumber", "LargeClass")
object KryptonEntityTypes {

    private const val MAGIC_HORSE_WIDTH = 1.3964844F
    private const val HUMAN_HEIGHT = 1.95F

    // TODO: Make each of these be of their respective entity types when they exist
    @JvmField
    val ALLAY: KryptonEntityType<KryptonEntity> = register("allay", EntityCategories.CREATURE) {
        size(0.35F, 0.6F)
        clientTrackingRange(8)
        updateInterval(2)
    }
    @JvmField
    val AREA_EFFECT_CLOUD: KryptonEntityType<KryptonAreaEffectCloud> = register("area_effect_cloud", EntityCategories.MISC) {
        fireImmune()
        size(6F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val ARMOR_STAND: KryptonEntityType<KryptonArmorStand> = register("armor_stand", EntityCategories.MISC) {
        size(0.5F, 1.975F)
        clientTrackingRange(10)
    }
    @JvmField
    val ARROW: KryptonEntityType<KryptonArrow> = register("arrow", EntityCategories.MISC) {
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val AXOLOTL: KryptonEntityType<KryptonAxolotl> = register("axolotl", EntityCategories.UNDERGROUND_WATER_CREATURE) {
        size(0.75F, 0.42F)
        clientTrackingRange(10)
    }
    @JvmField
    val BAT: KryptonEntityType<KryptonBat> = register("bat", EntityCategories.AMBIENT) {
        size(0.5F, 0.9F)
        clientTrackingRange(5)
    }
    @JvmField
    val BEE: KryptonEntityType<KryptonBee> = register("bee", EntityCategories.CREATURE) {
        size(0.7F, 0.6F)
        clientTrackingRange(8)
    }
    @JvmField
    val BLAZE: KryptonEntityType<KryptonBlaze> = register("blaze", EntityCategories.MONSTER) {
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val BOAT: KryptonEntityType<KryptonBoat> = register("boat", EntityCategories.MISC) {
        size(1.375F, 0.5625F)
        clientTrackingRange(10)
    }
    @JvmField
    val CHEST_BOAT: KryptonEntityType<KryptonEntity> = register("chest_boat", EntityCategories.MISC) {
        size(1.375F, 0.5625F)
        clientTrackingRange(10)
    }
    @JvmField
    val CAT: KryptonEntityType<KryptonCat> = register("cat", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(8)
    }
    @JvmField
    val CAMEL: KryptonEntityType<KryptonEntity> = register("camel", EntityCategories.CREATURE) {
        size(1.7F, 2.375F)
        clientTrackingRange(10)
    }
    @JvmField
    val CAVE_SPIDER: KryptonEntityType<KryptonCaveSpider> = register("cave_spider", EntityCategories.MONSTER) {
        size(0.7F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val CHICKEN: KryptonEntityType<KryptonChicken> = register("chicken", EntityCategories.CREATURE) {
        size(0.4F, 0.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val COD: KryptonEntityType<KryptonCod> = register("cod", EntityCategories.WATER_AMBIENT) {
        size(0.5F, 0.3F)
        clientTrackingRange(4)
    }
    @JvmField
    val COW: KryptonEntityType<KryptonCow> = register("cow", EntityCategories.CREATURE) {
        size(0.9F, 1.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val CREEPER: KryptonEntityType<KryptonCreeper> = register("creeper", EntityCategories.MONSTER) {
        size(0.6F, 1.7F)
        clientTrackingRange(8)
    }
    @JvmField
    val DOLPHIN: KryptonEntityType<KryptonDolphin> = register("dolphin", EntityCategories.WATER_CREATURE) { size(0.9F, 0.6F) }
    @JvmField
    val DONKEY: KryptonEntityType<KryptonEntity> = register("donkey", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.5F)
        clientTrackingRange(10)
    }
    @JvmField
    val DRAGON_FIREBALL: KryptonEntityType<KryptonDragonFireball> = register("dragon_fireball", EntityCategories.MISC) {
        size(1F, 1F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val DROWNED: KryptonEntityType<KryptonDrowned> = register("drowned", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ELDER_GUARDIAN: KryptonEntityType<KryptonEntity> = register("elder_guardian", EntityCategories.MONSTER) {
        size(1.9975F, 1.9975F)
        clientTrackingRange(10)
    }
    @JvmField
    val END_CRYSTAL: KryptonEntityType<KryptonEntity> = register("end_crystal", EntityCategories.MISC) {
        size(2F, 2F)
        clientTrackingRange(16)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val ENDER_DRAGON: KryptonEntityType<KryptonEntity> = register("ender_dragon", EntityCategories.MONSTER) {
        fireImmune()
        size(16F, 8F)
        clientTrackingRange(10)
    }
    @JvmField
    val ENDERMAN: KryptonEntityType<KryptonEntity> = register("enderman", EntityCategories.MONSTER) {
        size(0.6F, 2.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val ENDERMITE: KryptonEntityType<KryptonEndermite> = register("endermite", EntityCategories.MONSTER) {
        size(0.4F, 0.3F)
        clientTrackingRange(8)
    }
    @JvmField
    val EVOKER: KryptonEntityType<KryptonEntity> = register("evoker", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val EVOKER_FANGS: KryptonEntityType<KryptonEntity> = register("evoker_fangs", EntityCategories.MISC) {
        size(0.5F, 0.8F)
        clientTrackingRange(6)
        updateInterval(2)
    }
    @JvmField
    val EXPERIENCE_ORB: KryptonEntityType<KryptonExperienceOrb> = register("experience_orb", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(6)
        updateInterval(20)
    }
    @JvmField
    val EYE_OF_ENDER: KryptonEntityType<KryptonEntity> = register("eye_of_ender", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(4)
    }
    @JvmField
    val FALLING_BLOCK: KryptonEntityType<KryptonEntity> = register("falling_block", EntityCategories.MISC) {
        size(0.98F, 0.98F)
        clientTrackingRange(10)
        updateInterval(20)
    }
    @JvmField
    val FIREWORK_ROCKET: KryptonEntityType<KryptonFireworkRocket> = register("firework_rocket", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val FOX: KryptonEntityType<KryptonFox> = register("fox", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(8)
        immuneTo(KryptonBlocks.SWEET_BERRY_BUSH)
    }
    @JvmField
    val FROG: KryptonEntityType<KryptonEntity> = register("frog", EntityCategories.CREATURE) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
    }
    @JvmField
    val GHAST: KryptonEntityType<KryptonEntity> = register("ghast", EntityCategories.MONSTER) {
        size(4F, 4F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val GIANT: KryptonEntityType<KryptonGiant> = register("giant", EntityCategories.MONSTER) {
        size(3.6F, 12F)
        clientTrackingRange(10)
    }
    @JvmField
    val GLOW_ITEM_FRAME: KryptonEntityType<KryptonEntity> = register("glow_item_frame", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val GLOW_SQUID: KryptonEntityType<KryptonGlowSquid> = register("glow_squid", EntityCategories.UNDERGROUND_WATER_CREATURE) {
        size(0.8F, 0.8F)
        clientTrackingRange(10)
    }
    @JvmField
    val GOAT: KryptonEntityType<KryptonGoat> = register("goat", EntityCategories.CREATURE) {
        size(0.9F, 1.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val GUARDIAN: KryptonEntityType<KryptonGuardian> = register("guardian", EntityCategories.MONSTER) {
        size(0.85F, 0.85F)
        clientTrackingRange(8)
    }
    @JvmField
    val HOGLIN: KryptonEntityType<KryptonEntity> = register("hoglin", EntityCategories.MONSTER) {
        size(MAGIC_HORSE_WIDTH, 1.4F)
        clientTrackingRange(8)
    }
    @JvmField
    val HORSE: KryptonEntityType<KryptonEntity> = register("horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val HUSK: KryptonEntityType<KryptonHusk> = register("husk", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ILLUSIONER: KryptonEntityType<KryptonEntity> = register("illusioner", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val IRON_GOLEM: KryptonEntityType<KryptonEntity> = register("iron_golem", EntityCategories.MISC) {
        size(1.4F, 2.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val ITEM: KryptonEntityType<KryptonEntity> = register("item", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(6)
        updateInterval(20)
    }
    @JvmField
    val ITEM_FRAME: KryptonEntityType<KryptonEntity> = register("item_frame", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val FIREBALL: KryptonEntityType<KryptonLargeFireball> = register("fireball", EntityCategories.MISC) {
        size(1F, 1F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val LEASH_KNOT: KryptonEntityType<KryptonEntity> = register("leash_knot", EntityCategories.MISC) {
        size(0.375F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val LIGHTNING_BOLT: KryptonEntityType<KryptonEntity> = register("lightning_bolt", EntityCategories.MISC) {
        size(0F, 0F)
        clientTrackingRange(16)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val LLAMA: KryptonEntityType<KryptonEntity> = register("llama", EntityCategories.CREATURE) {
        size(0.9F, 1.87F)
        clientTrackingRange(10)
    }
    @JvmField
    val LLAMA_SPIT: KryptonEntityType<KryptonLlamaSpit> = register("llama_spit", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val MAGMA_CUBE: KryptonEntityType<KryptonEntity> = register("magma_cube", EntityCategories.MONSTER) {
        size(2.04F, 2.04F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val MARKER: KryptonEntityType<KryptonEntity> = register("marker", EntityCategories.MISC) {
        size(0F, 0F)
        clientTrackingRange(0)
    }
    @JvmField
    val MINECART: KryptonEntityType<KryptonMinecart> = minecart("minecart")
    @JvmField
    val CHEST_MINECART: KryptonEntityType<KryptonEntity> = minecart("chest_minecart")
    @JvmField
    val COMMAND_BLOCK_MINECART: KryptonEntityType<KryptonCommandBlockMinecart> = minecart("command_block_minecart")
    @JvmField
    val FURNACE_MINECART: KryptonEntityType<KryptonFurnaceMinecart> = minecart("furnace_minecart")
    @JvmField
    val HOPPER_MINECART: KryptonEntityType<KryptonEntity> = minecart("hopper_minecart")
    @JvmField
    val SPAWNER_MINECART: KryptonEntityType<KryptonEntity> = minecart("spawner_minecart")
    @JvmField
    val TNT_MINECART: KryptonEntityType<KryptonTNTMinecart> = minecart("tnt_minecart")
    @JvmField
    val MULE: KryptonEntityType<KryptonEntity> = register("mule", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(8)
    }
    @JvmField
    val MOOSHROOM: KryptonEntityType<KryptonMooshroom> = register("mooshroom", EntityCategories.CREATURE) {
        size(0.9F, 1.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val OCELOT: KryptonEntityType<KryptonOcelot> = register("ocelot", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val PAINTING: KryptonEntityType<KryptonPainting> = register("painting", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val PANDA: KryptonEntityType<KryptonPanda> = register("panda", EntityCategories.CREATURE) {
        size(1.3F, 1.25F)
        clientTrackingRange(10)
    }
    @JvmField
    val PARROT: KryptonEntityType<KryptonParrot> = register("parrot", EntityCategories.CREATURE) {
        size(0.5F, 0.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val PHANTOM: KryptonEntityType<KryptonEntity> = register("phantom", EntityCategories.MONSTER) {
        size(0.9F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val PIG: KryptonEntityType<KryptonPig> = register("pig", EntityCategories.CREATURE) {
        size(0.9F, 0.9F)
        clientTrackingRange(10)
    }
    @JvmField
    val PIGLIN: KryptonEntityType<KryptonEntity> = register("piglin", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val PIGLIN_BRUTE: KryptonEntityType<KryptonEntity> = register("piglin_brute", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val PILLAGER: KryptonEntityType<KryptonEntity> = register("pillager", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val POLAR_BEAR: KryptonEntityType<KryptonPolarBear> = register("polar_bear", EntityCategories.CREATURE) {
        size(1.4F, 1.4F)
        clientTrackingRange(10)
        immuneTo(KryptonBlocks.POWDER_SNOW)
    }
    @JvmField
    val PRIMED_TNT: KryptonEntityType<KryptonEntity> = register("tnt", EntityCategories.MISC) {
        size(0.98F, 0.98F)
        fireImmune()
        clientTrackingRange(10)
        updateInterval(10)
    }
    @JvmField
    val PUFFERFISH: KryptonEntityType<KryptonPufferfish> = register("pufferfish", EntityCategories.WATER_AMBIENT) {
        size(0.7F, 0.7F)
        clientTrackingRange(4)
    }
    @JvmField
    val RABBIT: KryptonEntityType<KryptonRabbit> = register("rabbit", EntityCategories.CREATURE) {
        size(0.4F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val RAVAGER: KryptonEntityType<KryptonEntity> = register("ravager", EntityCategories.MONSTER) {
        size(1.95F, 2.2F)
        clientTrackingRange(10)
    }
    @JvmField
    val SALMON: KryptonEntityType<KryptonSalmon> = register("salmon", EntityCategories.WATER_AMBIENT) {
        size(0.7F, 0.4F)
        clientTrackingRange(4)
    }
    @JvmField
    val SHEEP: KryptonEntityType<KryptonSheep> = register("sheep", EntityCategories.CREATURE) {
        size(0.9F, 1.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val SHULKER: KryptonEntityType<KryptonEntity> = register("shulker", EntityCategories.MONSTER) {
        size(1F, 1F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val SHULKER_BULLET: KryptonEntityType<KryptonShulkerBullet> = register("shulker_bullet", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(8)
    }
    @JvmField
    val SILVERFISH: KryptonEntityType<KryptonSilverfish> = register("silverfish", EntityCategories.MONSTER) {
        size(0.4F, 0.3F)
        clientTrackingRange(8)
    }
    @JvmField
    val SKELETON: KryptonEntityType<KryptonEntity> = register("skeleton", EntityCategories.MONSTER) {
        size(0.6F, 1.99F)
        clientTrackingRange(8)
    }
    @JvmField
    val SKELETON_HORSE: KryptonEntityType<KryptonEntity> = register("skeleton_horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val SLIME: KryptonEntityType<KryptonEntity> = register("slime", EntityCategories.MONSTER) {
        size(2.04F, 2.04F)
        clientTrackingRange(10)
    }
    @JvmField
    val SMALL_FIREBALL: KryptonEntityType<KryptonSmallFireball> = register("small_fireball", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val SNOW_GOLEM: KryptonEntityType<KryptonEntity> = register("snow_golem", EntityCategories.MISC) {
        size(0.7F, 1.9F)
        clientTrackingRange(8)
        immuneTo(KryptonBlocks.POWDER_SNOW)
    }
    @JvmField
    val SNOWBALL: KryptonEntityType<KryptonSnowball> = register("snowball", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val SPECTRAL_ARROW: KryptonEntityType<KryptonSpectralArrow> = register("spectral_arrow", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val SPIDER: KryptonEntityType<KryptonSpider> = register("spider", EntityCategories.MONSTER) {
        size(1.4F, 0.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val SQUID: KryptonEntityType<KryptonSquid> = register("squid", EntityCategories.WATER_CREATURE) {
        size(0.8F, 0.8F)
        clientTrackingRange(8)
    }
    @JvmField
    val STRAY: KryptonEntityType<KryptonEntity> = register("stray", EntityCategories.MONSTER) {
        size(0.6F, 1.99F)
        clientTrackingRange(8)
        immuneTo(KryptonBlocks.POWDER_SNOW)
    }
    @JvmField
    val STRIDER: KryptonEntityType<KryptonEntity> = register("strider", EntityCategories.CREATURE) {
        size(0.9F, 1.7F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val TADPOLE: KryptonEntityType<KryptonEntity> = register("tadpole", EntityCategories.CREATURE) {
        size(0.4F, 0.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val EGG: KryptonEntityType<KryptonEgg> = thrownItem("egg")
    @JvmField
    val ENDER_PEARL: KryptonEntityType<KryptonEnderPearl> = thrownItem("ender_pearl")
    @JvmField
    val EXPERIENCE_BOTTLE: KryptonEntityType<KryptonExperienceBottle> = thrownItem("experience_bottle")
    @JvmField
    val POTION: KryptonEntityType<KryptonThrownPotion> = thrownItem("potion")
    @JvmField
    val TRIDENT: KryptonEntityType<KryptonTrident> = register("trident", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val TRADER_LLAMA: KryptonEntityType<KryptonEntity> = register("trader_llama", EntityCategories.CREATURE) {
        size(0.9F, 1.87F)
        clientTrackingRange(10)
    }
    @JvmField
    val TROPICAL_FISH: KryptonEntityType<KryptonTropicalFish> = register("tropical_fish", EntityCategories.WATER_AMBIENT) {
        size(0.5F, 0.4F)
        clientTrackingRange(4)
    }
    @JvmField
    val TURTLE: KryptonEntityType<KryptonTurtle> = register("turtle", EntityCategories.CREATURE) {
        size(1.2F, 0.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val VEX: KryptonEntityType<KryptonEntity> = register("vex", EntityCategories.MONSTER) {
        size(0.4F, 0.8F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val VILLAGER: KryptonEntityType<KryptonEntity> = register("villager", EntityCategories.MISC) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(10)
    }
    @JvmField
    val VINDICATOR: KryptonEntityType<KryptonEntity> = register("vindicator", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val WANDERING_TRADER: KryptonEntityType<KryptonEntity> = register("wandering_trader", EntityCategories.CREATURE) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(10)
    }
    @JvmField
    val WARDEN: KryptonEntityType<KryptonEntity> = register("warden", EntityCategories.MONSTER) {
        size(0.9F, 2.9F)
        clientTrackingRange(16)
        fireImmune()
    }
    @JvmField
    val WITCH: KryptonEntityType<KryptonEntity> = register("witch", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val WITHER: KryptonEntityType<KryptonEntity> = register("wither", EntityCategories.MONSTER) {
        size(0.9F, 3.5F)
        fireImmune()
        clientTrackingRange(10)
        immuneTo(KryptonBlocks.WITHER_ROSE)
    }
    @JvmField
    val WITHER_SKELETON: KryptonEntityType<KryptonEntity> = register("wither_skeleton", EntityCategories.MONSTER) {
        size(0.7F, 2.4F)
        fireImmune()
        clientTrackingRange(8)
        immuneTo(KryptonBlocks.WITHER_ROSE)
    }
    @JvmField
    val WITHER_SKULL: KryptonEntityType<KryptonWitherSkull> = register("wither_skull", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val WOLF: KryptonEntityType<KryptonWolf> = register("wolf", EntityCategories.CREATURE) {
        size(0.6F, 0.85F)
        clientTrackingRange(10)
    }
    @JvmField
    val ZOGLIN: KryptonEntityType<KryptonEntity> = register("zoglin", EntityCategories.MONSTER) {
        size(MAGIC_HORSE_WIDTH, 1.4F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIE: KryptonEntityType<KryptonZombie> = register("zombie", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIE_HORSE: KryptonEntityType<KryptonEntity> = register("zombie_horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val ZOMBIE_VILLAGER: KryptonEntityType<KryptonEntity> = register("zombie_villager", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIFIED_PIGLIN: KryptonEntityType<KryptonEntity> = register("zombified_piglin", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val PLAYER: KryptonEntityType<KryptonPlayer> = register("player", EntityCategories.MISC) {
        notSummonable()
        clientTrackingRange(32)
        updateInterval(2)
    }
    @JvmField
    val FISHING_HOOK: KryptonEntityType<KryptonFishingHook> = register("fishing_bobber", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        notSummonable()
        clientTrackingRange(4)
        updateInterval(5)
    }

    @JvmStatic
    private fun <T : KryptonEntity> minecart(name: String): KryptonEntityType<T> = register(name, EntityCategories.MISC) {
        size(0.98F, 0.7F)
        clientTrackingRange(8)
    }

    @JvmStatic
    private fun <T : KryptonEntity> thrownItem(name: String): KryptonEntityType<T> = register(name, EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }

    @JvmStatic
    private inline fun <T : KryptonEntity> register(
        name: String,
        category: RegistryReference<EntityCategory>,
        builder: KryptonEntityType.Builder<T>.() -> Unit = {}
    ): KryptonEntityType<T> = register(name, KryptonEntityType.Builder<T>(category.get()).apply(builder).build())

    @JvmStatic
    private fun <T : KryptonEntity> register(name: String, type: KryptonEntityType<T>): KryptonEntityType<T> =
        KryptonRegistries.register(KryptonRegistries.ENTITY_TYPE, Key.key(name), type)
}
