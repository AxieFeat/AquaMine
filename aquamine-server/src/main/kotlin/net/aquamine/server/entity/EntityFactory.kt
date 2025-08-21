package net.aquamine.server.entity

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.EntityType
import net.aquamine.api.entity.EntityTypes
import net.aquamine.api.registry.RegistryReference
import net.aquamine.server.entity.ambient.AquaBat
import net.aquamine.server.entity.animal.AquaAxolotl
import net.aquamine.server.entity.animal.AquaBee
import net.aquamine.server.entity.animal.AquaChicken
import net.aquamine.server.entity.animal.AquaFox
import net.aquamine.server.entity.animal.AquaOcelot
import net.aquamine.server.entity.animal.AquaPanda
import net.aquamine.server.entity.animal.AquaPig
import net.aquamine.server.entity.animal.AquaPolarBear
import net.aquamine.server.entity.animal.AquaCat
import net.aquamine.server.entity.animal.AquaCow
import net.aquamine.server.entity.animal.AquaGoat
import net.aquamine.server.entity.animal.AquaMooshroom
import net.aquamine.server.entity.animal.AquaParrot
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
import net.aquamine.server.world.AquaWorld
import xyz.axie.nbt.CompoundTag
import java.util.function.Function

/**
 * This exists primarily because not all entities are implemented yet, and so this logic,
 * which would normally be in AquaEntityType using a factory, is here instead.
 *
 * This is used for instantiating new entities from type and NBT.
 */
object EntityFactory {

    private val LOGGER = LogManager.getLogger()
    private val TYPE_MAP = mapOf(
        entry(EntityTypes.AREA_EFFECT_CLOUD, ::AquaAreaEffectCloud),
        entry(EntityTypes.ARMOR_STAND, ::AquaArmorStand),
        entry(EntityTypes.ARROW, ::AquaArrow),
        entry(EntityTypes.AXOLOTL, ::AquaAxolotl),
        entry(EntityTypes.BAT, ::AquaBat),
        entry(EntityTypes.BEE, ::AquaBee),
        entry(EntityTypes.BLAZE, ::AquaBlaze),
        entry(EntityTypes.BOAT, ::AquaBoat),
        entry(EntityTypes.CAT, ::AquaCat),
        entry(EntityTypes.CAVE_SPIDER, ::AquaCaveSpider),
        entry(EntityTypes.CHICKEN, ::AquaChicken),
        entry(EntityTypes.COD, ::AquaCod),
        entry(EntityTypes.COMMAND_BLOCK_MINECART, ::AquaCommandBlockMinecart),
        entry(EntityTypes.COW, ::AquaCow),
        entry(EntityTypes.CREEPER, ::AquaCreeper),
        entry(EntityTypes.DOLPHIN, ::AquaDolphin),
        entry(EntityTypes.DRAGON_FIREBALL, ::AquaDragonFireball),
        entry(EntityTypes.DROWNED, ::AquaDrowned),
        entry(EntityTypes.EGG, ::AquaEgg),
        entry(EntityTypes.ENDER_PEARL, ::AquaEnderPearl),
        entry(EntityTypes.ENDERMITE, ::AquaEndermite),
        entry(EntityTypes.EXPERIENCE_BOTTLE, ::AquaExperienceBottle),
        entry(EntityTypes.EXPERIENCE_ORB, ::AquaExperienceOrb),
        entry(EntityTypes.FIREWORK_ROCKET, ::AquaFireworkRocket),
        entry(EntityTypes.FIREBALL, ::AquaLargeFireball),
        entry(EntityTypes.FISHING_HOOK, ::AquaFishingHook),
        entry(EntityTypes.FOX, ::AquaFox),
        entry(EntityTypes.FURNACE_MINECART, ::AquaFurnaceMinecart),
        entry(EntityTypes.GIANT, ::AquaGiant),
        entry(EntityTypes.GLOW_SQUID, ::AquaGlowSquid),
        entry(EntityTypes.GOAT, ::AquaGoat),
        entry(EntityTypes.GUARDIAN, ::AquaGuardian),
        entry(EntityTypes.HUSK, ::AquaHusk),
        entry(EntityTypes.LLAMA_SPIT, ::AquaLlamaSpit),
        entry(EntityTypes.MINECART, ::AquaMinecart),
        entry(EntityTypes.MOOSHROOM, ::AquaMooshroom),
        entry(EntityTypes.OCELOT, ::AquaOcelot),
        entry(EntityTypes.PAINTING, ::AquaPainting),
        entry(EntityTypes.PANDA, ::AquaPanda),
        entry(EntityTypes.PARROT, ::AquaParrot),
        entry(EntityTypes.PIG, ::AquaPig),
        EntityTypes.PLAYER to null,
        entry(EntityTypes.POLAR_BEAR, ::AquaPolarBear),
        entry(EntityTypes.POTION, ::AquaThrownPotion),
        entry(EntityTypes.PUFFERFISH, ::AquaPufferfish),
        entry(EntityTypes.RABBIT, ::AquaRabbit),
        entry(EntityTypes.SALMON, ::AquaSalmon),
        entry(EntityTypes.SHEEP, ::AquaSheep),
        entry(EntityTypes.SHULKER_BULLET, ::AquaShulkerBullet),
        entry(EntityTypes.SILVERFISH, ::AquaSilverfish),
        entry(EntityTypes.SMALL_FIREBALL, ::AquaSmallFireball),
        entry(EntityTypes.SNOWBALL, ::AquaSnowball),
        entry(EntityTypes.SPECTRAL_ARROW, ::AquaSpectralArrow),
        entry(EntityTypes.SPIDER, ::AquaSpider),
        entry(EntityTypes.SQUID, ::AquaSquid),
        entry(EntityTypes.TNT_MINECART, ::AquaTNTMinecart),
        entry(EntityTypes.TRIDENT, ::AquaTrident),
        entry(EntityTypes.TROPICAL_FISH, ::AquaTropicalFish),
        entry(EntityTypes.TURTLE, ::AquaTurtle),
        entry(EntityTypes.WITHER_SKULL, ::AquaWitherSkull),
        entry(EntityTypes.WOLF, ::AquaWolf),
        entry(EntityTypes.ZOMBIE, ::AquaZombie),
        entry(EntityTypes.LIGHTNING_BOLT, ::AquaLightningBolt)
    )

    /**
     * This does nothing more than lookup the entity's factory in the map using the type
     * and instantiate the entity with the world. It will return an entity with all of its
     * data set to the default values on initialisation.
     */
    @JvmStatic
    fun create(type: EntityType<Entity>, world: AquaWorld): AquaEntity? = TYPE_MAP[type]?.apply(world)

    /**
     * This is used to create an entity from an NBT tag.
     *
     * It will try to resolve the entity's type from the given id, warning and returning null if
     * it can't resolve the type, and then will use that to create the entity, and further, load
     * its data from the nbt tag, if it is non-null.
     */
    @JvmStatic
    fun create(world: AquaWorld, id: String, nbt: CompoundTag?): AquaEntity? {
        return try {
            val type = AquaRegistries.ENTITY_TYPE.get(Key.key(id))
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
    private inline fun entry(type: RegistryReference<out EntityType<Entity>>, crossinline constructor: (AquaWorld) -> AquaEntity): Entry =
        Entry(type.get(), Function { constructor(it) })
}

private typealias Entry = Pair<EntityType<Entity>, Function<AquaWorld, AquaEntity>?>
