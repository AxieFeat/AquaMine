package net.aquamine.api.block.entity

import net.aquamine.annotations.Catalogue
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
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(BlockEntityType::class)
object BlockEntityTypes {

    // @formatter:off
    @JvmField
    val FURNACE: RegistryReference<BlockEntityType<Furnace>> = of("furnace")

    @JvmField
    val CHEST: RegistryReference<BlockEntityType<Chest>> = of("chest")

    @JvmField
    val TRAPPED_CHEST: RegistryReference<BlockEntityType<TrappedChest>> = of("trapped_chest")

    @JvmField
    val ENDER_CHEST: RegistryReference<BlockEntityType<EnderChest>> = of("ender_chest")

    @JvmField
    val JUKEBOX: RegistryReference<BlockEntityType<Jukebox>> = of("jukebox")

    @JvmField
    val DISPENSER: RegistryReference<BlockEntityType<Dispenser>> = of("dispenser")

    @JvmField
    val DROPPER: RegistryReference<BlockEntityType<Dropper>> = of("dropper")

    @JvmField
    val SIGN: RegistryReference<BlockEntityType<Sign>> = of("sign")

    @JvmField
    val HANGING_SIGN: RegistryReference<BlockEntityType<HangingSign>> = of("hanging_sign")

    @JvmField
    val MOB_SPAWNER: RegistryReference<BlockEntityType<Spawner>> = of("mob_spawner")

    @JvmField
    val PISTON: RegistryReference<BlockEntityType<PistonMoving>> = of("piston")

    @JvmField
    val BREWING_STAND: RegistryReference<BlockEntityType<BrewingStand>> = of("brewing_stand")

    @JvmField
    val ENCHANTING_TABLE: RegistryReference<BlockEntityType<EnchantmentTable>> =
            of("enchanting_table")

    @JvmField
    val END_PORTAL: RegistryReference<BlockEntityType<TheEndPortal>> = of("end_portal")

    @JvmField
    val BEACON: RegistryReference<BlockEntityType<Beacon>> = of("beacon")

    @JvmField
    val SKULL: RegistryReference<BlockEntityType<Skull>> = of("skull")

    @JvmField
    val DAYLIGHT_DETECTOR: RegistryReference<BlockEntityType<DaylightDetector>> =
            of("daylight_detector")

    @JvmField
    val HOPPER: RegistryReference<BlockEntityType<Hopper>> = of("hopper")

    @JvmField
    val COMPARATOR: RegistryReference<BlockEntityType<Comparator>> = of("comparator")

    @JvmField
    val BANNER: RegistryReference<BlockEntityType<Banner>> = of("banner")

    @JvmField
    val STRUCTURE_BLOCK: RegistryReference<BlockEntityType<Structure>> =
            of("structure_block")

    @JvmField
    val END_GATEWAY: RegistryReference<BlockEntityType<TheEndGateway>> = of("end_gateway")

    @JvmField
    val COMMAND_BLOCK: RegistryReference<BlockEntityType<Command>> = of("command_block")

    @JvmField
    val SHULKER_BOX: RegistryReference<BlockEntityType<ShulkerBox>> = of("shulker_box")

    @JvmField
    val BED: RegistryReference<BlockEntityType<Bed>> = of("bed")

    @JvmField
    val CONDUIT: RegistryReference<BlockEntityType<Conduit>> = of("conduit")

    @JvmField
    val BARREL: RegistryReference<BlockEntityType<Barrel>> = of("barrel")

    @JvmField
    val SMOKER: RegistryReference<BlockEntityType<Smoker>> = of("smoker")

    @JvmField
    val BLAST_FURNACE: RegistryReference<BlockEntityType<BlastFurnace>> = of("blast_furnace")

    @JvmField
    val LECTERN: RegistryReference<BlockEntityType<Lectern>> = of("lectern")

    @JvmField
    val BELL: RegistryReference<BlockEntityType<Bell>> = of("bell")

    @JvmField
    val JIGSAW: RegistryReference<BlockEntityType<Jigsaw>> = of("jigsaw")

    @JvmField
    val CAMPFIRE: RegistryReference<BlockEntityType<Campfire>> = of("campfire")

    @JvmField
    val BEEHIVE: RegistryReference<BlockEntityType<Beehive>> = of("beehive")

    @JvmField
    val SCULK_SENSOR: RegistryReference<BlockEntityType<SculkSensor>> = of("sculk_sensor")

    @JvmField
    val SCULK_CATALYST: RegistryReference<BlockEntityType<SculkCatalyst>> =
            of("sculk_catalyst")

    @JvmField
    val SCULK_SHRIEKER: RegistryReference<BlockEntityType<SculkShrieker>> =
            of("sculk_shrieker")

    @JvmField
    val CHISELED_BOOKSHELF: RegistryReference<BlockEntityType<ChiseledBookShelf>> =
            of("chiseled_bookshelf")


    // @formatter:on
    @JvmStatic
    private fun <T : BlockEntity> of(name: String): RegistryReference<BlockEntityType<T>> = RegistryReference.of(Registries.BLOCK_ENTITY_TYPE, Key.key(name))
}
