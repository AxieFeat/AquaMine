package net.aquamine.server.entity

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.EntityType
import net.aquamine.api.entity.EntityTypes
import net.aquamine.api.registry.RegistryReference
import net.aquamine.server.entity.ambient.KryptonBat
import net.aquamine.server.entity.animal.KryptonAxolotl
import net.aquamine.server.entity.animal.KryptonBee
import net.aquamine.server.entity.animal.KryptonChicken
import net.aquamine.server.entity.animal.KryptonFox
import net.aquamine.server.entity.animal.KryptonOcelot
import net.aquamine.server.entity.animal.KryptonPanda
import net.aquamine.server.entity.animal.KryptonPig
import net.aquamine.server.entity.animal.KryptonPolarBear
import net.aquamine.server.entity.animal.KryptonCat
import net.aquamine.server.entity.animal.KryptonCow
import net.aquamine.server.entity.animal.KryptonGoat
import net.aquamine.server.entity.animal.KryptonMooshroom
import net.aquamine.server.entity.animal.KryptonParrot
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
import net.aquamine.server.world.KryptonWorld
import xyz.axie.nbt.CompoundTag
import java.util.function.Function

/**
 * This exists primarily because not all entities are implemented yet, and so this logic,
 * which would normally be in KryptonEntityType using a factory, is here instead.
 *
 * This is used for instantiating new entities from type and NBT.
 */
object EntityFactory {

    private val LOGGER = LogManager.getLogger()
    private val TYPE_MAP = mapOf(
        entry(EntityTypes.AREA_EFFECT_CLOUD, ::KryptonAreaEffectCloud),
        entry(EntityTypes.ARMOR_STAND, ::KryptonArmorStand),
        entry(EntityTypes.ARROW, ::KryptonArrow),
        entry(EntityTypes.AXOLOTL, ::KryptonAxolotl),
        entry(EntityTypes.BAT, ::KryptonBat),
        entry(EntityTypes.BEE, ::KryptonBee),
        entry(EntityTypes.BLAZE, ::KryptonBlaze),
        entry(EntityTypes.BOAT, ::KryptonBoat),
        entry(EntityTypes.CAT, ::KryptonCat),
        entry(EntityTypes.CAVE_SPIDER, ::KryptonCaveSpider),
        entry(EntityTypes.CHICKEN, ::KryptonChicken),
        entry(EntityTypes.COD, ::KryptonCod),
        entry(EntityTypes.COMMAND_BLOCK_MINECART, ::KryptonCommandBlockMinecart),
        entry(EntityTypes.COW, ::KryptonCow),
        entry(EntityTypes.CREEPER, ::KryptonCreeper),
        entry(EntityTypes.DOLPHIN, ::KryptonDolphin),
        entry(EntityTypes.DRAGON_FIREBALL, ::KryptonDragonFireball),
        entry(EntityTypes.DROWNED, ::KryptonDrowned),
        entry(EntityTypes.EGG, ::KryptonEgg),
        entry(EntityTypes.ENDER_PEARL, ::KryptonEnderPearl),
        entry(EntityTypes.ENDERMITE, ::KryptonEndermite),
        entry(EntityTypes.EXPERIENCE_BOTTLE, ::KryptonExperienceBottle),
        entry(EntityTypes.EXPERIENCE_ORB, ::KryptonExperienceOrb),
        entry(EntityTypes.FIREWORK_ROCKET, ::KryptonFireworkRocket),
        entry(EntityTypes.FIREBALL, ::KryptonLargeFireball),
        entry(EntityTypes.FISHING_HOOK, ::KryptonFishingHook),
        entry(EntityTypes.FOX, ::KryptonFox),
        entry(EntityTypes.FURNACE_MINECART, ::KryptonFurnaceMinecart),
        entry(EntityTypes.GIANT, ::KryptonGiant),
        entry(EntityTypes.GLOW_SQUID, ::KryptonGlowSquid),
        entry(EntityTypes.GOAT, ::KryptonGoat),
        entry(EntityTypes.GUARDIAN, ::KryptonGuardian),
        entry(EntityTypes.HUSK, ::KryptonHusk),
        entry(EntityTypes.LLAMA_SPIT, ::KryptonLlamaSpit),
        entry(EntityTypes.MINECART, ::KryptonMinecart),
        entry(EntityTypes.MOOSHROOM, ::KryptonMooshroom),
        entry(EntityTypes.OCELOT, ::KryptonOcelot),
        entry(EntityTypes.PAINTING, ::KryptonPainting),
        entry(EntityTypes.PANDA, ::KryptonPanda),
        entry(EntityTypes.PARROT, ::KryptonParrot),
        entry(EntityTypes.PIG, ::KryptonPig),
        EntityTypes.PLAYER to null,
        entry(EntityTypes.POLAR_BEAR, ::KryptonPolarBear),
        entry(EntityTypes.POTION, ::KryptonThrownPotion),
        entry(EntityTypes.PUFFERFISH, ::KryptonPufferfish),
        entry(EntityTypes.RABBIT, ::KryptonRabbit),
        entry(EntityTypes.SALMON, ::KryptonSalmon),
        entry(EntityTypes.SHEEP, ::KryptonSheep),
        entry(EntityTypes.SHULKER_BULLET, ::KryptonShulkerBullet),
        entry(EntityTypes.SILVERFISH, ::KryptonSilverfish),
        entry(EntityTypes.SMALL_FIREBALL, ::KryptonSmallFireball),
        entry(EntityTypes.SNOWBALL, ::KryptonSnowball),
        entry(EntityTypes.SPECTRAL_ARROW, ::KryptonSpectralArrow),
        entry(EntityTypes.SPIDER, ::KryptonSpider),
        entry(EntityTypes.SQUID, ::KryptonSquid),
        entry(EntityTypes.TNT_MINECART, ::KryptonTNTMinecart),
        entry(EntityTypes.TRIDENT, ::KryptonTrident),
        entry(EntityTypes.TROPICAL_FISH, ::KryptonTropicalFish),
        entry(EntityTypes.TURTLE, ::KryptonTurtle),
        entry(EntityTypes.WITHER_SKULL, ::KryptonWitherSkull),
        entry(EntityTypes.WOLF, ::KryptonWolf),
        entry(EntityTypes.ZOMBIE, ::KryptonZombie)
    )

    /**
     * This does nothing more than lookup the entity's factory in the map using the type
     * and instantiate the entity with the world. It will return an entity with all of its
     * data set to the default values on initialisation.
     */
    @JvmStatic
    fun create(type: EntityType<Entity>, world: KryptonWorld): KryptonEntity? = TYPE_MAP.get(type)?.apply(world)

    /**
     * This is used to create an entity from an NBT tag.
     *
     * It will try to resolve the entity's type from the given id, warning and returning null if
     * it can't resolve the type, and then will use that to create the entity, and further, load
     * its data from the nbt tag, if it is non-null.
     */
    @JvmStatic
    fun create(world: KryptonWorld, id: String, nbt: CompoundTag?): KryptonEntity? {
        return try {
            val type = KryptonRegistries.ENTITY_TYPE.get(Key.key(id))
            val entity = create(type, world)
            if (entity == null) {
                LOGGER.warn("No entity found with ID $id!")
                return null
            }
            if (nbt != null) entity.load(nbt)
            return entity
        } catch (exception: RuntimeException) {
            LOGGER.warn("Exception loading entity", exception)
            null
        }
    }

    @JvmStatic
    private inline fun entry(type: RegistryReference<out EntityType<Entity>>, crossinline constructor: (KryptonWorld) -> KryptonEntity): Entry =
        Entry(type.get(), Function { constructor(it) })
}

private typealias Entry = Pair<EntityType<Entity>, Function<KryptonWorld, KryptonEntity>?>
