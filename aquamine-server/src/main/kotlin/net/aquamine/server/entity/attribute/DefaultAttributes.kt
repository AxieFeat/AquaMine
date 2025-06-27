package net.aquamine.server.entity.attribute

import com.google.common.collect.ImmutableMap
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.ambient.AquaBat
import net.aquamine.server.entity.animal.AquaAxolotl
import net.aquamine.server.entity.animal.AquaBee
import net.aquamine.server.entity.animal.AquaCat
import net.aquamine.server.entity.animal.AquaChicken
import net.aquamine.server.entity.animal.AquaCow
import net.aquamine.server.entity.animal.AquaFox
import net.aquamine.server.entity.animal.AquaGoat
import net.aquamine.server.entity.animal.AquaOcelot
import net.aquamine.server.entity.animal.AquaPanda
import net.aquamine.server.entity.animal.AquaParrot
import net.aquamine.server.entity.animal.AquaPig
import net.aquamine.server.entity.animal.AquaPolarBear
import net.aquamine.server.entity.animal.AquaRabbit
import net.aquamine.server.entity.animal.AquaSheep
import net.aquamine.server.entity.animal.AquaWolf
import net.aquamine.server.entity.aquatic.AquaDolphin
import net.aquamine.server.entity.aquatic.AquaFish
import net.aquamine.server.entity.aquatic.AquaSquid
import net.aquamine.server.entity.monster.AquaBlaze
import net.aquamine.server.entity.monster.AquaCaveSpider
import net.aquamine.server.entity.monster.AquaCreeper
import net.aquamine.server.entity.monster.AquaEndermite
import net.aquamine.server.entity.monster.AquaGiant
import net.aquamine.server.entity.monster.AquaGuardian
import net.aquamine.server.entity.monster.AquaSilverfish
import net.aquamine.server.entity.monster.AquaSpider
import net.aquamine.server.entity.monster.AquaZombie
import net.aquamine.server.entity.player.AquaPlayer

object DefaultAttributes {

    private val SUPPLIERS = Builder()
        .put(AquaEntityTypes.ARMOR_STAND, AquaLivingEntity::attributes)
        .put(AquaEntityTypes.AXOLOTL, AquaAxolotl::attributes)
        .put(AquaEntityTypes.BAT, AquaBat::attributes)
        .put(AquaEntityTypes.BEE, AquaBee::attributes)
        .put(AquaEntityTypes.BLAZE, AquaBlaze::attributes)
        .put(AquaEntityTypes.CAT, AquaCat::attributes)
        .put(AquaEntityTypes.CAVE_SPIDER, AquaCaveSpider::attributes)
        .put(AquaEntityTypes.CHICKEN, AquaChicken::attributes)
        .put(AquaEntityTypes.COD, AquaFish::attributes)
        .put(AquaEntityTypes.COW, AquaCow::attributes)
        .put(AquaEntityTypes.CREEPER, AquaCreeper::attributes)
        .put(AquaEntityTypes.DOLPHIN, AquaDolphin::attributes)
        .put(AquaEntityTypes.DROWNED, AquaZombie::attributes)
        .put(AquaEntityTypes.ENDERMITE, AquaEndermite::attributes)
        .put(AquaEntityTypes.FOX, AquaFox::attributes)
        .put(AquaEntityTypes.GIANT, AquaGiant::attributes)
        .put(AquaEntityTypes.GLOW_SQUID, AquaSquid::attributes)
        .put(AquaEntityTypes.GOAT, AquaGoat::attributes)
        .put(AquaEntityTypes.GUARDIAN, AquaGuardian::attributes)
        .put(AquaEntityTypes.HUSK, AquaZombie::attributes)
        .put(AquaEntityTypes.MOOSHROOM, AquaCow::attributes)
        .put(AquaEntityTypes.OCELOT, AquaOcelot::attributes)
        .put(AquaEntityTypes.PANDA, AquaPanda::attributes)
        .put(AquaEntityTypes.PARROT, AquaParrot::attributes)
        .put(AquaEntityTypes.PIG, AquaPig::attributes)
        .put(AquaEntityTypes.PLAYER, AquaPlayer::attributes)
        .put(AquaEntityTypes.POLAR_BEAR, AquaPolarBear::attributes)
        .put(AquaEntityTypes.PUFFERFISH, AquaFish::attributes)
        .put(AquaEntityTypes.RABBIT, AquaRabbit::attributes)
        .put(AquaEntityTypes.SALMON, AquaFish::attributes)
        .put(AquaEntityTypes.SHEEP, AquaSheep::attributes)
        .put(AquaEntityTypes.SILVERFISH, AquaSilverfish::attributes)
        .put(AquaEntityTypes.SPIDER, AquaSpider::attributes)
        .put(AquaEntityTypes.SQUID, AquaSquid::attributes)
        .put(AquaEntityTypes.TROPICAL_FISH, AquaFish::attributes)
        .put(AquaEntityTypes.WOLF, AquaWolf::attributes)
        .put(AquaEntityTypes.ZOMBIE, AquaZombie::attributes)
        .build()

    @JvmStatic
    fun get(type: Key): AttributeSupplier = checkNotNull(SUPPLIERS.get(type)) { "Could not find attributes for entity type $type!" }
}

private typealias Key = AquaEntityType<AquaLivingEntity>
private typealias Builder = ImmutableMap.Builder<Key, AttributeSupplier>

private inline fun Builder.put(type: Key, supplier: () -> AttributeSupplier.Builder): Builder = put(type, supplier().build())
