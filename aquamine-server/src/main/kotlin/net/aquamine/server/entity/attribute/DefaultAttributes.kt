package net.aquamine.server.entity.attribute

import com.google.common.collect.ImmutableMap
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.ambient.KryptonBat
import net.aquamine.server.entity.animal.KryptonAxolotl
import net.aquamine.server.entity.animal.KryptonBee
import net.aquamine.server.entity.animal.KryptonCat
import net.aquamine.server.entity.animal.KryptonChicken
import net.aquamine.server.entity.animal.KryptonCow
import net.aquamine.server.entity.animal.KryptonFox
import net.aquamine.server.entity.animal.KryptonGoat
import net.aquamine.server.entity.animal.KryptonOcelot
import net.aquamine.server.entity.animal.KryptonPanda
import net.aquamine.server.entity.animal.KryptonParrot
import net.aquamine.server.entity.animal.KryptonPig
import net.aquamine.server.entity.animal.KryptonPolarBear
import net.aquamine.server.entity.animal.KryptonRabbit
import net.aquamine.server.entity.animal.KryptonSheep
import net.aquamine.server.entity.animal.KryptonWolf
import net.aquamine.server.entity.aquatic.KryptonDolphin
import net.aquamine.server.entity.aquatic.KryptonFish
import net.aquamine.server.entity.aquatic.KryptonSquid
import net.aquamine.server.entity.monster.KryptonBlaze
import net.aquamine.server.entity.monster.KryptonCaveSpider
import net.aquamine.server.entity.monster.KryptonCreeper
import net.aquamine.server.entity.monster.KryptonEndermite
import net.aquamine.server.entity.monster.KryptonGiant
import net.aquamine.server.entity.monster.KryptonGuardian
import net.aquamine.server.entity.monster.KryptonSilverfish
import net.aquamine.server.entity.monster.KryptonSpider
import net.aquamine.server.entity.monster.KryptonZombie
import net.aquamine.server.entity.player.KryptonPlayer

object DefaultAttributes {

    private val SUPPLIERS = Builder()
        .put(KryptonEntityTypes.ARMOR_STAND, KryptonLivingEntity::attributes)
        .put(KryptonEntityTypes.AXOLOTL, KryptonAxolotl::attributes)
        .put(KryptonEntityTypes.BAT, KryptonBat::attributes)
        .put(KryptonEntityTypes.BEE, KryptonBee::attributes)
        .put(KryptonEntityTypes.BLAZE, KryptonBlaze::attributes)
        .put(KryptonEntityTypes.CAT, KryptonCat::attributes)
        .put(KryptonEntityTypes.CAVE_SPIDER, KryptonCaveSpider::attributes)
        .put(KryptonEntityTypes.CHICKEN, KryptonChicken::attributes)
        .put(KryptonEntityTypes.COD, KryptonFish::attributes)
        .put(KryptonEntityTypes.COW, KryptonCow::attributes)
        .put(KryptonEntityTypes.CREEPER, KryptonCreeper::attributes)
        .put(KryptonEntityTypes.DOLPHIN, KryptonDolphin::attributes)
        .put(KryptonEntityTypes.DROWNED, KryptonZombie::attributes)
        .put(KryptonEntityTypes.ENDERMITE, KryptonEndermite::attributes)
        .put(KryptonEntityTypes.FOX, KryptonFox::attributes)
        .put(KryptonEntityTypes.GIANT, KryptonGiant::attributes)
        .put(KryptonEntityTypes.GLOW_SQUID, KryptonSquid::attributes)
        .put(KryptonEntityTypes.GOAT, KryptonGoat::attributes)
        .put(KryptonEntityTypes.GUARDIAN, KryptonGuardian::attributes)
        .put(KryptonEntityTypes.HUSK, KryptonZombie::attributes)
        .put(KryptonEntityTypes.MOOSHROOM, KryptonCow::attributes)
        .put(KryptonEntityTypes.OCELOT, KryptonOcelot::attributes)
        .put(KryptonEntityTypes.PANDA, KryptonPanda::attributes)
        .put(KryptonEntityTypes.PARROT, KryptonParrot::attributes)
        .put(KryptonEntityTypes.PIG, KryptonPig::attributes)
        .put(KryptonEntityTypes.PLAYER, KryptonPlayer::attributes)
        .put(KryptonEntityTypes.POLAR_BEAR, KryptonPolarBear::attributes)
        .put(KryptonEntityTypes.PUFFERFISH, KryptonFish::attributes)
        .put(KryptonEntityTypes.RABBIT, KryptonRabbit::attributes)
        .put(KryptonEntityTypes.SALMON, KryptonFish::attributes)
        .put(KryptonEntityTypes.SHEEP, KryptonSheep::attributes)
        .put(KryptonEntityTypes.SILVERFISH, KryptonSilverfish::attributes)
        .put(KryptonEntityTypes.SPIDER, KryptonSpider::attributes)
        .put(KryptonEntityTypes.SQUID, KryptonSquid::attributes)
        .put(KryptonEntityTypes.TROPICAL_FISH, KryptonFish::attributes)
        .put(KryptonEntityTypes.WOLF, KryptonWolf::attributes)
        .put(KryptonEntityTypes.ZOMBIE, KryptonZombie::attributes)
        .build()

    @JvmStatic
    fun get(type: Key): AttributeSupplier = checkNotNull(SUPPLIERS.get(type)) { "Could not find attributes for entity type $type!" }
}

private typealias Key = KryptonEntityType<KryptonLivingEntity>
private typealias Builder = ImmutableMap.Builder<Key, AttributeSupplier>

private inline fun Builder.put(type: Key, supplier: () -> AttributeSupplier.Builder): Builder = put(type, supplier().build())
