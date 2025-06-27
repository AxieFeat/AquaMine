package net.aquamine.server.entity

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.EntityCategories
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.registry.RegistryReference
import net.aquamine.server.entity.ambient.AquaBat
import net.aquamine.server.entity.animal.AquaAxolotl
import net.aquamine.server.entity.animal.AquaBee
import net.aquamine.server.entity.animal.AquaCat
import net.aquamine.server.entity.animal.AquaChicken
import net.aquamine.server.entity.animal.AquaCow
import net.aquamine.server.entity.animal.AquaFox
import net.aquamine.server.entity.animal.AquaGoat
import net.aquamine.server.entity.animal.AquaMooshroom
import net.aquamine.server.entity.animal.AquaOcelot
import net.aquamine.server.entity.animal.AquaPanda
import net.aquamine.server.entity.animal.AquaParrot
import net.aquamine.server.entity.animal.AquaPig
import net.aquamine.server.entity.animal.AquaPolarBear
import net.aquamine.server.entity.animal.AquaRabbit
import net.aquamine.server.entity.animal.AquaSheep
import net.aquamine.server.entity.animal.AquaTurtle
import net.aquamine.server.entity.animal.AquaWolf
import net.aquamine.server.entity.aquatic.AquaCod
import net.aquamine.server.entity.aquatic.AquaDolphin
import net.aquamine.server.entity.aquatic.AquaGlowSquid
import net.aquamine.server.entity.aquatic.AquaPufferfish
import net.aquamine.server.entity.aquatic.AquaSalmon
import net.aquamine.server.entity.aquatic.AquaSquid
import net.aquamine.server.entity.aquatic.AquaTropicalFish
import net.aquamine.server.entity.hanging.AquaPainting
import net.aquamine.server.entity.monster.AquaBlaze
import net.aquamine.server.entity.monster.AquaCaveSpider
import net.aquamine.server.entity.monster.AquaCreeper
import net.aquamine.server.entity.monster.AquaDrowned
import net.aquamine.server.entity.monster.AquaEndermite
import net.aquamine.server.entity.monster.AquaGiant
import net.aquamine.server.entity.monster.AquaGuardian
import net.aquamine.server.entity.monster.AquaHusk
import net.aquamine.server.entity.monster.AquaSilverfish
import net.aquamine.server.entity.monster.AquaSpider
import net.aquamine.server.entity.monster.AquaZombie
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.projectile.AquaArrow
import net.aquamine.server.entity.projectile.AquaDragonFireball
import net.aquamine.server.entity.projectile.AquaEgg
import net.aquamine.server.entity.projectile.AquaEnderPearl
import net.aquamine.server.entity.projectile.AquaExperienceBottle
import net.aquamine.server.entity.projectile.AquaFireworkRocket
import net.aquamine.server.entity.projectile.AquaFishingHook
import net.aquamine.server.entity.projectile.AquaLargeFireball
import net.aquamine.server.entity.projectile.AquaLlamaSpit
import net.aquamine.server.entity.projectile.AquaShulkerBullet
import net.aquamine.server.entity.projectile.AquaSmallFireball
import net.aquamine.server.entity.projectile.AquaSnowball
import net.aquamine.server.entity.projectile.AquaSpectralArrow
import net.aquamine.server.entity.projectile.AquaThrownPotion
import net.aquamine.server.entity.projectile.AquaTrident
import net.aquamine.server.entity.projectile.AquaWitherSkull
import net.aquamine.server.entity.vehicle.AquaBoat
import net.aquamine.server.entity.vehicle.AquaCommandBlockMinecart
import net.aquamine.server.entity.vehicle.AquaFurnaceMinecart
import net.aquamine.server.entity.vehicle.AquaMinecart
import net.aquamine.server.entity.vehicle.AquaTNTMinecart
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.world.block.AquaBlocks

@Suppress("MagicNumber", "LargeClass")
object AquaEntityTypes {

    private const val MAGIC_HORSE_WIDTH = 1.3964844F
    private const val HUMAN_HEIGHT = 1.95F

    // TODO: Make each of these be of their respective entity types when they exist
    @JvmField
    val ALLAY: AquaEntityType<AquaEntity> = register("allay", EntityCategories.CREATURE) {
        size(0.35F, 0.6F)
        clientTrackingRange(8)
        updateInterval(2)
    }
    @JvmField
    val AREA_EFFECT_CLOUD: AquaEntityType<AquaAreaEffectCloud> = register("area_effect_cloud", EntityCategories.MISC) {
        fireImmune()
        size(6F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val ARMOR_STAND: AquaEntityType<AquaArmorStand> = register("armor_stand", EntityCategories.MISC) {
        size(0.5F, 1.975F)
        clientTrackingRange(10)
    }
    @JvmField
    val ARROW: AquaEntityType<AquaArrow> = register("arrow", EntityCategories.MISC) {
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val AXOLOTL: AquaEntityType<AquaAxolotl> = register("axolotl", EntityCategories.UNDERGROUND_WATER_CREATURE) {
        size(0.75F, 0.42F)
        clientTrackingRange(10)
    }
    @JvmField
    val BAT: AquaEntityType<AquaBat> = register("bat", EntityCategories.AMBIENT) {
        size(0.5F, 0.9F)
        clientTrackingRange(5)
    }
    @JvmField
    val BEE: AquaEntityType<AquaBee> = register("bee", EntityCategories.CREATURE) {
        size(0.7F, 0.6F)
        clientTrackingRange(8)
    }
    @JvmField
    val BLAZE: AquaEntityType<AquaBlaze> = register("blaze", EntityCategories.MONSTER) {
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val BOAT: AquaEntityType<AquaBoat> = register("boat", EntityCategories.MISC) {
        size(1.375F, 0.5625F)
        clientTrackingRange(10)
    }
    @JvmField
    val CHEST_BOAT: AquaEntityType<AquaEntity> = register("chest_boat", EntityCategories.MISC) {
        size(1.375F, 0.5625F)
        clientTrackingRange(10)
    }
    @JvmField
    val CAT: AquaEntityType<AquaCat> = register("cat", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(8)
    }
    @JvmField
    val CAMEL: AquaEntityType<AquaEntity> = register("camel", EntityCategories.CREATURE) {
        size(1.7F, 2.375F)
        clientTrackingRange(10)
    }
    @JvmField
    val CAVE_SPIDER: AquaEntityType<AquaCaveSpider> = register("cave_spider", EntityCategories.MONSTER) {
        size(0.7F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val CHICKEN: AquaEntityType<AquaChicken> = register("chicken", EntityCategories.CREATURE) {
        size(0.4F, 0.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val COD: AquaEntityType<AquaCod> = register("cod", EntityCategories.WATER_AMBIENT) {
        size(0.5F, 0.3F)
        clientTrackingRange(4)
    }
    @JvmField
    val COW: AquaEntityType<AquaCow> = register("cow", EntityCategories.CREATURE) {
        size(0.9F, 1.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val CREEPER: AquaEntityType<AquaCreeper> = register("creeper", EntityCategories.MONSTER) {
        size(0.6F, 1.7F)
        clientTrackingRange(8)
    }
    @JvmField
    val DOLPHIN: AquaEntityType<AquaDolphin> = register("dolphin", EntityCategories.WATER_CREATURE) { size(0.9F, 0.6F) }
    @JvmField
    val DONKEY: AquaEntityType<AquaEntity> = register("donkey", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.5F)
        clientTrackingRange(10)
    }
    @JvmField
    val DRAGON_FIREBALL: AquaEntityType<AquaDragonFireball> = register("dragon_fireball", EntityCategories.MISC) {
        size(1F, 1F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val DROWNED: AquaEntityType<AquaDrowned> = register("drowned", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ELDER_GUARDIAN: AquaEntityType<AquaEntity> = register("elder_guardian", EntityCategories.MONSTER) {
        size(1.9975F, 1.9975F)
        clientTrackingRange(10)
    }
    @JvmField
    val END_CRYSTAL: AquaEntityType<AquaEntity> = register("end_crystal", EntityCategories.MISC) {
        size(2F, 2F)
        clientTrackingRange(16)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val ENDER_DRAGON: AquaEntityType<AquaEntity> = register("ender_dragon", EntityCategories.MONSTER) {
        fireImmune()
        size(16F, 8F)
        clientTrackingRange(10)
    }
    @JvmField
    val ENDERMAN: AquaEntityType<AquaEntity> = register("enderman", EntityCategories.MONSTER) {
        size(0.6F, 2.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val ENDERMITE: AquaEntityType<AquaEndermite> = register("endermite", EntityCategories.MONSTER) {
        size(0.4F, 0.3F)
        clientTrackingRange(8)
    }
    @JvmField
    val EVOKER: AquaEntityType<AquaEntity> = register("evoker", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val EVOKER_FANGS: AquaEntityType<AquaEntity> = register("evoker_fangs", EntityCategories.MISC) {
        size(0.5F, 0.8F)
        clientTrackingRange(6)
        updateInterval(2)
    }
    @JvmField
    val EXPERIENCE_ORB: AquaEntityType<AquaExperienceOrb> = register("experience_orb", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(6)
        updateInterval(20)
    }
    @JvmField
    val EYE_OF_ENDER: AquaEntityType<AquaEntity> = register("eye_of_ender", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(4)
    }
    @JvmField
    val FALLING_BLOCK: AquaEntityType<AquaEntity> = register("falling_block", EntityCategories.MISC) {
        size(0.98F, 0.98F)
        clientTrackingRange(10)
        updateInterval(20)
    }
    @JvmField
    val FIREWORK_ROCKET: AquaEntityType<AquaFireworkRocket> = register("firework_rocket", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val FOX: AquaEntityType<AquaFox> = register("fox", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(8)
        immuneTo(AquaBlocks.SWEET_BERRY_BUSH)
    }
    @JvmField
    val FROG: AquaEntityType<AquaEntity> = register("frog", EntityCategories.CREATURE) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
    }
    @JvmField
    val GHAST: AquaEntityType<AquaEntity> = register("ghast", EntityCategories.MONSTER) {
        size(4F, 4F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val GIANT: AquaEntityType<AquaGiant> = register("giant", EntityCategories.MONSTER) {
        size(3.6F, 12F)
        clientTrackingRange(10)
    }
    @JvmField
    val GLOW_ITEM_FRAME: AquaEntityType<AquaEntity> = register("glow_item_frame", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val GLOW_SQUID: AquaEntityType<AquaGlowSquid> = register("glow_squid", EntityCategories.UNDERGROUND_WATER_CREATURE) {
        size(0.8F, 0.8F)
        clientTrackingRange(10)
    }
    @JvmField
    val GOAT: AquaEntityType<AquaGoat> = register("goat", EntityCategories.CREATURE) {
        size(0.9F, 1.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val GUARDIAN: AquaEntityType<AquaGuardian> = register("guardian", EntityCategories.MONSTER) {
        size(0.85F, 0.85F)
        clientTrackingRange(8)
    }
    @JvmField
    val HOGLIN: AquaEntityType<AquaEntity> = register("hoglin", EntityCategories.MONSTER) {
        size(MAGIC_HORSE_WIDTH, 1.4F)
        clientTrackingRange(8)
    }
    @JvmField
    val HORSE: AquaEntityType<AquaEntity> = register("horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val HUSK: AquaEntityType<AquaHusk> = register("husk", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ILLUSIONER: AquaEntityType<AquaEntity> = register("illusioner", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val IRON_GOLEM: AquaEntityType<AquaEntity> = register("iron_golem", EntityCategories.MISC) {
        size(1.4F, 2.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val ITEM: AquaEntityType<AquaEntity> = register("item", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(6)
        updateInterval(20)
    }
    @JvmField
    val ITEM_FRAME: AquaEntityType<AquaEntity> = register("item_frame", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val FIREBALL: AquaEntityType<AquaLargeFireball> = register("fireball", EntityCategories.MISC) {
        size(1F, 1F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val LEASH_KNOT: AquaEntityType<AquaEntity> = register("leash_knot", EntityCategories.MISC) {
        size(0.375F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val LIGHTNING_BOLT: AquaEntityType<AquaEntity> = register("lightning_bolt", EntityCategories.MISC) {
        size(0F, 0F)
        clientTrackingRange(16)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val LLAMA: AquaEntityType<AquaEntity> = register("llama", EntityCategories.CREATURE) {
        size(0.9F, 1.87F)
        clientTrackingRange(10)
    }
    @JvmField
    val LLAMA_SPIT: AquaEntityType<AquaLlamaSpit> = register("llama_spit", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val MAGMA_CUBE: AquaEntityType<AquaEntity> = register("magma_cube", EntityCategories.MONSTER) {
        size(2.04F, 2.04F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val MARKER: AquaEntityType<AquaEntity> = register("marker", EntityCategories.MISC) {
        size(0F, 0F)
        clientTrackingRange(0)
    }
    @JvmField
    val MINECART: AquaEntityType<AquaMinecart> = minecart("minecart")
    @JvmField
    val CHEST_MINECART: AquaEntityType<AquaEntity> = minecart("chest_minecart")
    @JvmField
    val COMMAND_BLOCK_MINECART: AquaEntityType<AquaCommandBlockMinecart> = minecart("command_block_minecart")
    @JvmField
    val FURNACE_MINECART: AquaEntityType<AquaFurnaceMinecart> = minecart("furnace_minecart")
    @JvmField
    val HOPPER_MINECART: AquaEntityType<AquaEntity> = minecart("hopper_minecart")
    @JvmField
    val SPAWNER_MINECART: AquaEntityType<AquaEntity> = minecart("spawner_minecart")
    @JvmField
    val TNT_MINECART: AquaEntityType<AquaTNTMinecart> = minecart("tnt_minecart")
    @JvmField
    val MULE: AquaEntityType<AquaEntity> = register("mule", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(8)
    }
    @JvmField
    val MOOSHROOM: AquaEntityType<AquaMooshroom> = register("mooshroom", EntityCategories.CREATURE) {
        size(0.9F, 1.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val OCELOT: AquaEntityType<AquaOcelot> = register("ocelot", EntityCategories.CREATURE) {
        size(0.6F, 0.7F)
        clientTrackingRange(10)
    }
    @JvmField
    val PAINTING: AquaEntityType<AquaPainting> = register("painting", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(10)
        updateInterval(Int.MAX_VALUE)
    }
    @JvmField
    val PANDA: AquaEntityType<AquaPanda> = register("panda", EntityCategories.CREATURE) {
        size(1.3F, 1.25F)
        clientTrackingRange(10)
    }
    @JvmField
    val PARROT: AquaEntityType<AquaParrot> = register("parrot", EntityCategories.CREATURE) {
        size(0.5F, 0.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val PHANTOM: AquaEntityType<AquaEntity> = register("phantom", EntityCategories.MONSTER) {
        size(0.9F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val PIG: AquaEntityType<AquaPig> = register("pig", EntityCategories.CREATURE) {
        size(0.9F, 0.9F)
        clientTrackingRange(10)
    }
    @JvmField
    val PIGLIN: AquaEntityType<AquaEntity> = register("piglin", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val PIGLIN_BRUTE: AquaEntityType<AquaEntity> = register("piglin_brute", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val PILLAGER: AquaEntityType<AquaEntity> = register("pillager", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val POLAR_BEAR: AquaEntityType<AquaPolarBear> = register("polar_bear", EntityCategories.CREATURE) {
        size(1.4F, 1.4F)
        clientTrackingRange(10)
        immuneTo(AquaBlocks.POWDER_SNOW)
    }
    @JvmField
    val PRIMED_TNT: AquaEntityType<AquaEntity> = register("tnt", EntityCategories.MISC) {
        size(0.98F, 0.98F)
        fireImmune()
        clientTrackingRange(10)
        updateInterval(10)
    }
    @JvmField
    val PUFFERFISH: AquaEntityType<AquaPufferfish> = register("pufferfish", EntityCategories.WATER_AMBIENT) {
        size(0.7F, 0.7F)
        clientTrackingRange(4)
    }
    @JvmField
    val RABBIT: AquaEntityType<AquaRabbit> = register("rabbit", EntityCategories.CREATURE) {
        size(0.4F, 0.5F)
        clientTrackingRange(8)
    }
    @JvmField
    val RAVAGER: AquaEntityType<AquaEntity> = register("ravager", EntityCategories.MONSTER) {
        size(1.95F, 2.2F)
        clientTrackingRange(10)
    }
    @JvmField
    val SALMON: AquaEntityType<AquaSalmon> = register("salmon", EntityCategories.WATER_AMBIENT) {
        size(0.7F, 0.4F)
        clientTrackingRange(4)
    }
    @JvmField
    val SHEEP: AquaEntityType<AquaSheep> = register("sheep", EntityCategories.CREATURE) {
        size(0.9F, 1.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val SHULKER: AquaEntityType<AquaEntity> = register("shulker", EntityCategories.MONSTER) {
        size(1F, 1F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val SHULKER_BULLET: AquaEntityType<AquaShulkerBullet> = register("shulker_bullet", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(8)
    }
    @JvmField
    val SILVERFISH: AquaEntityType<AquaSilverfish> = register("silverfish", EntityCategories.MONSTER) {
        size(0.4F, 0.3F)
        clientTrackingRange(8)
    }
    @JvmField
    val SKELETON: AquaEntityType<AquaEntity> = register("skeleton", EntityCategories.MONSTER) {
        size(0.6F, 1.99F)
        clientTrackingRange(8)
    }
    @JvmField
    val SKELETON_HORSE: AquaEntityType<AquaEntity> = register("skeleton_horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val SLIME: AquaEntityType<AquaEntity> = register("slime", EntityCategories.MONSTER) {
        size(2.04F, 2.04F)
        clientTrackingRange(10)
    }
    @JvmField
    val SMALL_FIREBALL: AquaEntityType<AquaSmallFireball> = register("small_fireball", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val SNOW_GOLEM: AquaEntityType<AquaEntity> = register("snow_golem", EntityCategories.MISC) {
        size(0.7F, 1.9F)
        clientTrackingRange(8)
        immuneTo(AquaBlocks.POWDER_SNOW)
    }
    @JvmField
    val SNOWBALL: AquaEntityType<AquaSnowball> = register("snowball", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val SPECTRAL_ARROW: AquaEntityType<AquaSpectralArrow> = register("spectral_arrow", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val SPIDER: AquaEntityType<AquaSpider> = register("spider", EntityCategories.MONSTER) {
        size(1.4F, 0.9F)
        clientTrackingRange(8)
    }
    @JvmField
    val SQUID: AquaEntityType<AquaSquid> = register("squid", EntityCategories.WATER_CREATURE) {
        size(0.8F, 0.8F)
        clientTrackingRange(8)
    }
    @JvmField
    val STRAY: AquaEntityType<AquaEntity> = register("stray", EntityCategories.MONSTER) {
        size(0.6F, 1.99F)
        clientTrackingRange(8)
        immuneTo(AquaBlocks.POWDER_SNOW)
    }
    @JvmField
    val STRIDER: AquaEntityType<AquaEntity> = register("strider", EntityCategories.CREATURE) {
        size(0.9F, 1.7F)
        fireImmune()
        clientTrackingRange(10)
    }
    @JvmField
    val TADPOLE: AquaEntityType<AquaEntity> = register("tadpole", EntityCategories.CREATURE) {
        size(0.4F, 0.3F)
        clientTrackingRange(10)
    }
    @JvmField
    val EGG: AquaEntityType<AquaEgg> = thrownItem("egg")
    @JvmField
    val ENDER_PEARL: AquaEntityType<AquaEnderPearl> = thrownItem("ender_pearl")
    @JvmField
    val EXPERIENCE_BOTTLE: AquaEntityType<AquaExperienceBottle> = thrownItem("experience_bottle")
    @JvmField
    val POTION: AquaEntityType<AquaThrownPotion> = thrownItem("potion")
    @JvmField
    val TRIDENT: AquaEntityType<AquaTrident> = register("trident", EntityCategories.MISC) {
        size(0.5F, 0.5F)
        clientTrackingRange(4)
        updateInterval(20)
    }
    @JvmField
    val TRADER_LLAMA: AquaEntityType<AquaEntity> = register("trader_llama", EntityCategories.CREATURE) {
        size(0.9F, 1.87F)
        clientTrackingRange(10)
    }
    @JvmField
    val TROPICAL_FISH: AquaEntityType<AquaTropicalFish> = register("tropical_fish", EntityCategories.WATER_AMBIENT) {
        size(0.5F, 0.4F)
        clientTrackingRange(4)
    }
    @JvmField
    val TURTLE: AquaEntityType<AquaTurtle> = register("turtle", EntityCategories.CREATURE) {
        size(1.2F, 0.4F)
        clientTrackingRange(10)
    }
    @JvmField
    val VEX: AquaEntityType<AquaEntity> = register("vex", EntityCategories.MONSTER) {
        size(0.4F, 0.8F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val VILLAGER: AquaEntityType<AquaEntity> = register("villager", EntityCategories.MISC) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(10)
    }
    @JvmField
    val VINDICATOR: AquaEntityType<AquaEntity> = register("vindicator", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val WANDERING_TRADER: AquaEntityType<AquaEntity> = register("wandering_trader", EntityCategories.CREATURE) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(10)
    }
    @JvmField
    val WARDEN: AquaEntityType<AquaEntity> = register("warden", EntityCategories.MONSTER) {
        size(0.9F, 2.9F)
        clientTrackingRange(16)
        fireImmune()
    }
    @JvmField
    val WITCH: AquaEntityType<AquaEntity> = register("witch", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val WITHER: AquaEntityType<AquaEntity> = register("wither", EntityCategories.MONSTER) {
        size(0.9F, 3.5F)
        fireImmune()
        clientTrackingRange(10)
        immuneTo(AquaBlocks.WITHER_ROSE)
    }
    @JvmField
    val WITHER_SKELETON: AquaEntityType<AquaEntity> = register("wither_skeleton", EntityCategories.MONSTER) {
        size(0.7F, 2.4F)
        fireImmune()
        clientTrackingRange(8)
        immuneTo(AquaBlocks.WITHER_ROSE)
    }
    @JvmField
    val WITHER_SKULL: AquaEntityType<AquaWitherSkull> = register("wither_skull", EntityCategories.MISC) {
        size(0.3125F, 0.3125F)
        clientTrackingRange(4)
        updateInterval(10)
    }
    @JvmField
    val WOLF: AquaEntityType<AquaWolf> = register("wolf", EntityCategories.CREATURE) {
        size(0.6F, 0.85F)
        clientTrackingRange(10)
    }
    @JvmField
    val ZOGLIN: AquaEntityType<AquaEntity> = register("zoglin", EntityCategories.MONSTER) {
        size(MAGIC_HORSE_WIDTH, 1.4F)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIE: AquaEntityType<AquaZombie> = register("zombie", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIE_HORSE: AquaEntityType<AquaEntity> = register("zombie_horse", EntityCategories.CREATURE) {
        size(MAGIC_HORSE_WIDTH, 1.6F)
        clientTrackingRange(10)
    }
    @JvmField
    val ZOMBIE_VILLAGER: AquaEntityType<AquaEntity> = register("zombie_villager", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        clientTrackingRange(8)
    }
    @JvmField
    val ZOMBIFIED_PIGLIN: AquaEntityType<AquaEntity> = register("zombified_piglin", EntityCategories.MONSTER) {
        size(0.6F, HUMAN_HEIGHT)
        fireImmune()
        clientTrackingRange(8)
    }
    @JvmField
    val PLAYER: AquaEntityType<AquaPlayer> = register("player", EntityCategories.MISC) {
        notSummonable()
        clientTrackingRange(32)
        updateInterval(2)
    }
    @JvmField
    val FISHING_HOOK: AquaEntityType<AquaFishingHook> = register("fishing_bobber", EntityCategories.MISC) {
        size(0.25F, 0.25F)
        notSummonable()
        clientTrackingRange(4)
        updateInterval(5)
    }

    @JvmStatic
    private fun <T : AquaEntity> minecart(name: String): AquaEntityType<T> = register(name, EntityCategories.MISC) {
        size(0.98F, 0.7F)
        clientTrackingRange(8)
    }

    @JvmStatic
    private fun <T : AquaEntity> thrownItem(name: String): AquaEntityType<T> = register(name, EntityCategories.MISC) {
        size(0.25F, 0.25F)
        clientTrackingRange(4)
        updateInterval(10)
    }

    @JvmStatic
    private inline fun <T : AquaEntity> register(
        name: String,
        category: RegistryReference<EntityCategory>,
        builder: AquaEntityType.Builder<T>.() -> Unit = {}
    ): AquaEntityType<T> = register(name, AquaEntityType.Builder<T>(category.get()).apply(builder).build())

    @JvmStatic
    private fun <T : AquaEntity> register(name: String, type: AquaEntityType<T>): AquaEntityType<T> =
        AquaRegistries.register(AquaRegistries.ENTITY_TYPE, Key.key(name), type)
}
