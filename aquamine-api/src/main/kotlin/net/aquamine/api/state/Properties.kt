package net.aquamine.api.state

import net.aquamine.api.block.Blocks
import net.aquamine.annotations.Catalogue
import net.aquamine.api.AquaMine
import net.aquamine.api.block.meta.*
import net.aquamine.api.util.Direction

/**
 * All built-in block state properties.
 *
 * See [here](https://minecraft.fandom.com/wiki/Java_Edition_data_values#Block_states)
 * for information on what types of blocks each property is applicable to.
 */
@Catalogue(Property::class)
object Properties {

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-1.
     *
     * This is applicable to:
     * - [Bamboo][Blocks.BAMBOO] - the age of it, if 1 it appears thicker
     */
    @JvmField
    val AGE_1: Property<Int> = factory().forInt("AGE_1")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-2.
     *
     * This is applicable to
     * - [Cocoa][Blocks.COCOA] - the stage of the pod's growth, 2 is fully grown
     */
    @JvmField
    val AGE_2: Property<Int> = factory().forInt("AGE_2")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-3.
     *
     * This is applicable to:
     * - [Beetroots][Blocks.BEETROOTS] - see [here](https://minecraft.fandom.com/wiki/Block_states#Beetroots)
     * - [Frosted Ice][Blocks.FROSTED_ICE] - see [here](https://minecraft.fandom.com/wiki/Block_states#Frosted_Ice)
     * - [Nether Wart][Blocks.NETHER_WART] - see [here](https://minecraft.fandom.com/wiki/Block_states#Nether_Wart)
     * - [Sweet Berry Bush][Blocks.SWEET_BERRY_BUSH] - see
     * [here](https://minecraft.fandom.com/wiki/Block_states#Sweet_Berry_Bush)
     */
    @JvmField
    val AGE_3: Property<Int> = factory().forInt("AGE_3")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-4.
     *
     * This is applicable to:
     * - [Mangrove Propagules][Blocks.MANGROVE_PROPAGULE] - see [here](https://minecraft.fandom.com/wiki/Sapling#Block_states)
     */
    @JvmField
    val AGE_4: Property<Int> = factory().forInt("AGE_4")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-5.
     *
     * This is applicable to:
     * - [Chorus flower][Blocks.CHORUS_FLOWER] - see [here](https://minecraft.fandom.com/wiki/Block_states#Chorus_Flower)
     */
    @JvmField
    val AGE_5: Property<Int> = factory().forInt("AGE_5")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0-7.
     *
     * This is applicable to:
     * - [Melon Stem][Blocks.MELON_STEM] - see [here](https://minecraft.fandom.com/wiki/Block_states#Melon_Stem)
     * - [Pumpkin Stem][Blocks.PUMPKIN_STEM] - see [here](https://minecraft.fandom.com/wiki/Block_states#Pumpkin_Stem)
     */
    @JvmField
    val AGE_7: Property<Int> = factory().forInt("AGE_7")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0, 5.
     *
     * This is applicable to:
     * - [Cactus][Blocks.CACTUS] - see [here](https://minecraft.fandom.com/wiki/Block_states#Cactus)
     * - [Fire][Blocks.FIRE] - see [here](https://minecraft.fandom.com/wiki/Block_states#Fire)
     * - [Sugar Cane][Blocks.SUGAR_CANE] - see [here](https://minecraft.fandom.com/wiki/Block_states#Sugar_Cane)
     */
    @JvmField
    val AGE_15: Property<Int> = factory().forInt("AGE_15")

    /**
     * The current age of the block this property is applied to. This is a
     * generic property for certain types of plants. This has a range of 0, 5.
     *
     * This is applicable to:
     * - [Kelp][Blocks.KELP] - see [here](https://minecraft.fandom.com/wiki/Block_states#Kelp)
     */
    @JvmField
    val AGE_25: Property<Int> = factory().forInt("AGE_25")

    /**
     * Whether the tripwire or tripwire hook this property is applied to is
     * attached to a valid tripwire circuit.
     *
     * This is applicable to:
     * - [Tripwire][Blocks.TRIPWIRE]
     * - [Tripwire hook][Blocks.TRIPWIRE_HOOK]
     */
    @JvmField
    val ATTACHED: Property<Boolean> = factory().forBoolean("ATTACHED")

    /**
     * The face of the block which the button, grindstone, or lever this
     * property is applied to is attached to.
     *
     * This is applicable to all types of buttons, as well as the following
     * other blocks:
     * - [Grindstone][Blocks.GRINDSTONE]
     * - [Lever][Blocks.LEVER]
     */
    @JvmField
    val ATTACH_FACE: Property<AttachFace> = factory().forEnum("ATTACH_FACE")

    /**
     * The axis of orientation of the block this property is applied to.
     *
     * This is applicable to all types of logs, stripped logs, wood, stripped
     * wood, stems, stripped stems, hyphae, stripped hyphae, as well as the
     * following other blocks:
     * - [Bone Block][Blocks.BONE_BLOCK] - see [here](https://minecraft.fandom.com/wiki/Block_states#Bone_Block)
     * - [Chain][Blocks.CHAIN] - see [here](https://minecraft.fandom.com/wiki/Block_states#Chain)
     * - [Hay Bale][Blocks.HAY_BLOCK] - see [here](https://minecraft.fandom.com/wiki/Block_states#Hay_Bale)
     * - [Basalt][Blocks.BASALT]
     * - [Polished Basalt][Blocks.POLISHED_BASALT]
     * - [Quartz Pillar][Blocks.QUARTZ_PILLAR]
     * - [Purpur Pillar][Blocks.PURPUR_PILLAR]
     * - [Deepslate][Blocks.DEEPSLATE]
     */
    @JvmField
    val AXIS: Property<Direction.Axis> = factory().forEnum("AXIS")

    /**
     * The type of [Bamboo][Blocks.BAMBOO] leaves this property is applied to.
     */
    @JvmField
    val BAMBOO_LEAVES: Property<BambooLeaves> = factory().forEnum("BAMBOO_LEAVES")

    /**
     * The part of the bed this property is applied to. This is applicable to
     * all types of beds.
     */
    @JvmField
    val BED_PART: Property<BedPart> = factory().forEnum("BED_PART")

    /**
     * The attachment of the [Bell][Blocks.BELL] this property is applied to.
     */
    @JvmField
    val BELL_ATTACHMENT: Property<BellAttachment> = factory().forEnum("BELL_ATTACHMENT")

    /**
     * Whether the cave vines this property is applied to have berries. This is
     * applicable to both cave vines and their plants.
     */
    @JvmField
    val BERRIES: Property<Boolean> = factory().forBoolean("BERRIES")

    /**
     * The number of bites that have been taken from the [Cake][Blocks.CAKE]
     * this property is applied to.
     */
    @JvmField
    val BITES: Property<Int> = factory().forInt("BITES")

    /**
     * Whether the [Sculk Catalyst][Blocks.SCULK_CATALYST] this property is
     * applied to is actively spreading sculk or not.
     */
    @JvmField
    val BLOOM: Property<Boolean> = factory().forBoolean("BLOOM")

    /**
     * Whether the [Scaffolding][Blocks.SCAFFOLDING] block this property is
     * applied to is floating or is on the bottom.
     */
    @JvmField
    val BOTTOM: Property<Boolean> = factory().forBoolean("BOTTOM")

    /**
     * The amount of candles the candle block this property is applied to has.
     * This is applicable to all types of candles.
     */
    @JvmField
    val CANDLES: Property<Int> = factory().forInt("CANDLES")

    /**
     * Whether the [Sculk Shrieker][Blocks.SCULK_SHRIEKER] this property is
     * applied to can summon a warden if the warning level reaches the
     * spawning threshold or not.
     */
    @JvmField
    val CAN_SUMMON: Property<Boolean> = factory().forBoolean("CAN_SUMMON")

    /**
     * The level of the cauldron this property is applied to. This is
     * applicable to all types of cauldrons.
     */
    @JvmField
    val CAULDRON_LEVEL: Property<Int> = factory().forInt("CAULDRON_LEVEL")

    /**
     * The type of the [Chest][Blocks.CHEST], [Trapped Chest][Blocks.TRAPPED_CHEST],
     * or [Ender Chest][Blocks.ENDER_CHEST] this property is applied to.
     */
    @JvmField
    val CHEST_TYPE: Property<ChestType> = factory().forEnum("CHEST_TYPE")

    /**
     * The mode of the [Comparator][Blocks.COMPARATOR] this property is applied
     * to.
     */
    @JvmField
    val COMPARATOR_MODE: Property<ComparatorMode> = factory().forEnum("COMPARATOR_MODE")

    /**
     * The fill level of the [Composter][Blocks.COMPOSTER] this property is
     * applied to.
     */
    @JvmField
    val COMPOSTER_LEVEL: Property<Int> = factory().forInt("COMPOSTER_LEVEL")

    /**
     * Whether the command block this property is applied to is conditional or
     * not. This is applicable to all types of command blocks.
     */
    @JvmField
    val CONDITIONAL: Property<Boolean> = factory().forBoolean("CONDITIONAL")

    /**
     * The delay that the [Repeater][Blocks.REPEATER] this property is applied
     * to has.
     */
    @JvmField
    val DELAY: Property<Int> = factory().forInt("DELAY")

    /**
     * Whether the [Tripwire][Blocks.TRIPWIRE] this property is applied to is
     * disarmed.
     */
    @JvmField
    val DISARMED: Property<Boolean> = factory().forBoolean("DISARMED")

    /**
     * The distance the leaves block this property is applied to is from its
     * closest respective wood type. This is applicable to all types of
     * leaves.
     */
    @JvmField
    val DISTANCE: Property<Int> = factory().forInt("DISTANCE")

    /**
     * The hinge of a door the door this property is applied to represents.
     * This is applicable to all types of doors.
     */
    @JvmField
    val DOOR_HINGE: Property<DoorHingeSide> = factory().forEnum("DOOR_HINGE")

    /**
     * The half of a double block the block this property is applied to
     * represents.
     *
     * This is applicable to all types of doors, as well as the following
     * flowers:
     * - [Tall Grass][Blocks.TALL_GRASS]
     * - [Large Fern][Blocks.LARGE_FERN]
     * - [Small Dripleaf][Blocks.SMALL_DRIPLEAF]
     * - [Sunflower][Blocks.SUNFLOWER]
     * - [Lilac][Blocks.LILAC]
     * - [Rose Bush][Blocks.ROSE_BUSH]
     * - [Peony][Blocks.PEONY]
     * - [Tall Seagrass][Blocks.TALL_SEAGRASS]
     */
    @JvmField
    val DOUBLE_BLOCK_HALF: Property<DoubleBlockHalf> = factory().forEnum("DOUBLE_BLOCK_HALF")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends down from the center plant.
     */
    @JvmField
    val DOWN: Property<Boolean> = factory().forBoolean("DOWN")

    /**
     * Whether the [Bubble Column][Blocks.BUBBLE_COLUMN] this property is
     * applied to is upward or it is a whirlpool.
     */
    @JvmField
    val DRAG: Property<Boolean> = factory().forBoolean("DRAG")

    /**
     * The thickness of the [Pointed Dripstone][Blocks.POINTED_DRIPSTONE] this
     * property is applied to.
     */
    @JvmField
    val DRIPSTONE_THICKNESS: Property<DripstoneThickness> = factory().forEnum("DRIPSTONE_THICKNESS")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends east from the center plant.
     */
    @JvmField
    val EAST: Property<Boolean> = factory().forBoolean("EAST")

    /**
     * The connection the [Redstone Wire][Blocks.REDSTONE_WIRE] this property
     * is applied to has on the east side.
     */
    @JvmField
    val EAST_REDSTONE_SIDE: Property<RedstoneSide> = factory().forEnum("EAST_REDSTONE_SIDE")

    /**
     * The connection the wall this property is applied to has on the east
     * side. This is applicable to all types of walls.
     */
    @JvmField
    val EAST_WALL_SIDE: Property<WallSide> = factory().forEnum("EAST_WALL_SIDE")

    /**
     * The amount of eggs the [Turtle Egg][Blocks.TURTLE_EGG] this property is
     * applied to have.
     */
    @JvmField
    val EGGS: Property<Int> = factory().forInt("EGGS")

    /**
     * Whether the [Hopper][Blocks.HOPPER] this property is applied to can move
     * items to and from its inventory. When the hopper is powered by a
     * redstone current, this should be false.
     */
    @JvmField
    val ENABLED: Property<Boolean> = factory().forBoolean("ENABLED")

    /**
     * Whether the piston **base** block this property is applied to is
     * extended or not. This is applicable to both pistons and sticky pistons.
     */
    @JvmField
    val EXTENDED: Property<Boolean> = factory().forBoolean("EXTENDED")

    /**
     * Whether the [End Portal Frame][Blocks.END_PORTAL_FRAME] this property is
     * applied to has an eye in it or not.
     */
    @JvmField
    val EYE: Property<Boolean> = factory().forBoolean("EYE")

    /**
     * The direction the block this property is applied to is facing.
     *
     * This is applicable to:
     * - [Amethyst Cluster][Blocks.AMETHYST_CLUSTER]
     * - [Large Amethyst Bud][Blocks.LARGE_AMETHYST_BUD]
     * - [Medium Amethyst Bud][Blocks.MEDIUM_AMETHYST_BUD]
     * - [Small Amethyst Bud][Blocks.SMALL_AMETHYST_BUD]
     * - [Barrel][Blocks.BARREL]
     * - [End Rod][Blocks.END_ROD]
     * - [Lightning Rod][Blocks.LIGHTNING_ROD]
     * - [Observer][Blocks.OBSERVER]
     * - [Piston][Blocks.PISTON]
     * - [Sticky Piston][Blocks.STICKY_PISTON]
     * - [Piston Head][Blocks.PISTON_HEAD]
     */
    @JvmField
    val FACING: Property<Direction> = factory().forEnum("FACING")

    /**
     * Whether the flowing fluid this property is applied to is falling. This
     * is applicable to all flowing and source fluids.
     */
    @JvmField
    val FALLING: Property<Boolean> = factory().forBoolean("FALLING")

    /**
     * The half of the block that the block this property is applied to
     * represents. This is applicable to all types of stairs and trapdoors.
     */
    @JvmField
    val HALF: Property<Half> = factory().forEnum("HALF")

    /**
     * Whether the [Lantern][Blocks.LANTERN] or [Soul Lantern][Blocks.SOUL_LANTERN]
     * this property is applied to is currently hanging or not.
     */
    @JvmField
    val HANGING: Property<Boolean> = factory().forBoolean("HANGING")

    /**
     * Whether the [Lectern][Blocks.LECTERN] this property is applied to
     * currently hold a book.
     */
    @JvmField
    val HAS_BOOK: Property<Boolean> = factory().forBoolean("HAS_BOOK")

    /**
     * Whether the [Brewing Stand][Blocks.BREWING_STAND] this property is
     * applied to has a bottle brewing in the leftmost slot.
     */
    @JvmField
    val HAS_FIRST_BOTTLE: Property<Boolean> = factory().forBoolean("HAS_FIRST_BOTTLE")

    /**
     * Whether the [Brewing Stand][Blocks.BREWING_STAND] this property is
     * applied to has a bottle brewing in the central slot.
     */
    @JvmField
    val HAS_SECOND_BOTTLE: Property<Boolean> = factory().forBoolean("HAS_SECOND_BOTTLE")

    /**
     * Whether the [Brewing Stand][Blocks.BREWING_STAND] this property is
     * applied to has a bottle brewing in the rightmost slot.
     */
    @JvmField
    val HAS_THIRD_BOTTLE: Property<Boolean> = factory().forBoolean("HAS_THIRD_BOTTLE")

    /**
     * Whether the [Jukebox][Blocks.JUKEBOX] this property is applied to has a
     * record (music disc) currently playing in it.
     */
    @JvmField
    val HAS_RECORD: Property<Boolean> = factory().forBoolean("HAS_RECORD")

    /**
     * How close the [Turtle Egg][Blocks.TURTLE_EGG] this property is applied
     * to is too hatching. See [here](https://minecraft.fandom.com/wiki/Block_states#Turtle_Egg)
     * for more information.
     */
    @JvmField
    val HATCH: Property<Int> = factory().forInt("HATCH")

    /**
     * The level of honey the [Beehive][Blocks.BEEHIVE] or [Bee Nest][Blocks.BEE_NEST]
     * this property is applied to have.
     */
    @JvmField
    val HONEY_LEVEL: Property<Int> = factory().forInt("HONEY_LEVEL")

    /**
     * The direction that the [Hopper][Blocks.HOPPER] this property is applied
     * to is facing.
     */
    @JvmField
    val HOPPER_FACING: Property<Direction> = factory().forEnum("facing")

    /**
     * The axis of rotation of the [Nether Portal][Blocks.NETHER_PORTAL] this
     * property is applied to.
     * This property only permits axes on the horizontal plane.
     */
    @JvmField
    val HORIZONTAL_AXIS: Property<Direction.Axis> = factory().forEnum("HORIZONTAL_AXIS")

    /**
     * The direction that the block this property is applied to is facing.
     * This property only permits directions on the horizontal plane.
     *
     * This is applicable to all types of buttons, beds, fence gates, glazed
     * terracotta, and trapdoors, as well as the following:
     * - [Campfire][Blocks.CAMPFIRE]
     * - [Soul Campfire][Blocks.SOUL_CAMPFIRE]
     * - [Big Dripleaf][Blocks.BIG_DRIPLEAF]
     * - [Big Dripleaf Stem][Blocks.BIG_DRIPLEAF_STEM]
     * - [Small Dripleaf][Blocks.SMALL_DRIPLEAF]
     * - [Carved Pumpkin][Blocks.CARVED_PUMPKIN]
     * - [Jack o' Lantern][Blocks.JACK_O_LANTERN]
     * - [Cocoa][Blocks.COCOA]
     * - [Comparator][Blocks.COMPARATOR]
     * - [Repeater][Blocks.REPEATER]
     * - [Grindstone][Blocks.GRINDSTONE]
     * - [Lever][Blocks.LEVER]
     * - [Loom][Blocks.LOOM]
     */
    @JvmField
    val HORIZONTAL_FACING: Property<Direction> = factory().forEnum("facing")

    /**
     * The instrument that the [Note Block][Blocks.NOTE_BLOCK] this property is
     * applied to will play when attacked.
     */
    @JvmField
    val INSTRUMENT: Property<NoteBlockInstrument> = factory().forEnum("INSTRUMENT")

    /**
     * Whether the [Daylight Detector][Blocks.DAYLIGHT_DETECTOR] this property
     * is applied to is inverted, meaning it activates in the absence of light
     * rather than in the presence of light (when it is dark rather than when
     * it is light).
     */
    @JvmField
    val INVERTED: Property<Boolean> = factory().forBoolean("INVERTED")

    /**
     * Whether the fence gate this property is applied to is surrounded by
     * walls, causing the renderer to use a texture that is lowered by 3 pixels,
     * to allow for better attaching to walls.
     */
    @JvmField
    val IN_WALL: Property<Boolean> = factory().forBoolean("IN_WALL")

    /**
     * The amount of layers the [Snow][Blocks.SNOW] block this property is
     * applied to represents.
     */
    @JvmField
    val LAYERS: Property<Int> = factory().forInt("LAYERS")

    /**
     * The distance from a source block or level of light, depending on whether
     * the block is light or a liquid, that the block this property is applied
     * to represents.
     *
     * This is applicable to:
     * - [Light][Blocks.LIGHT]
     * - [Water][Blocks.WATER]
     * - [Lava][Blocks.LAVA]
     */
    @JvmField
    val LEVEL: Property<Int> = factory().forInt("LEVEL")

    /**
     * The level of liquid the flowing fluid this property is applied to
     * represents. This is applicable to all flowing and source fluids.
     */
    @JvmField
    val LIQUID_LEVEL: Property<Int> = factory().forInt("LIQUID_LEVEL")

    /**
     * Whether the block this property is applied to is lit or not.
     *
     * This is applicable to all candles and candle cakes, as well as the
     * following:
     * - [Furnace][Blocks.FURNACE]
     * - [Blast Furnace][Blocks.BLAST_FURNACE]
     * - [Smoker][Blocks.SMOKER]
     * - [Campfire][Blocks.CAMPFIRE]
     * - [Soul Campfire][Blocks.SOUL_CAMPFIRE]
     * - [Redstone Torch][Blocks.REDSTONE_TORCH]
     * - [Redstone Wall Torch][Blocks.REDSTONE_WALL_TORCH]
     */
    @JvmField
    val LIT: Property<Boolean> = factory().forBoolean("LIT")

    /**
     * Whether the [Repeater][Blocks.REPEATER] this property is applied to is
     * locked.
     */
    @JvmField
    val LOCKED: Property<Boolean> = factory().forBoolean("LOCKED")

    /**
     * The moisture level the [Farmland][Blocks.FARMLAND] this property is
     * applied to represents.
     */
    @JvmField
    val MOISTURE: Property<Int> = factory().forInt("MOISTURE")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends north from the center plant.
     */
    @JvmField
    val NORTH: Property<Boolean> = factory().forBoolean("NORTH")

    /**
     * The connection the [Redstone Wire][Blocks.REDSTONE_WIRE] this property
     * is applied to has on the north side.
     */
    @JvmField
    val NORTH_REDSTONE_SIDE: Property<RedstoneSide> = factory().forEnum("NORTH_REDSTONE_SIDE")

    /**
     * The connection the wall this property is applied to has on the north
     * side. This is applicable to all types of walls.
     */
    @JvmField
    val NORTH_WALL_SIDE: Property<WallSide> = factory().forEnum("NORTH_WALL_SIDE")

    /**
     * The note that the [Note Block][Blocks.NOTE_BLOCK] this property is
     * applied to will play when attacked.
     */
    @JvmField
    val NOTE: Property<Int> = factory().forInt("NOTE")

    /**
     * Whether the bed this property is applied to is occupied.
     */
    @JvmField
    val OCCUPIED: Property<Boolean> = factory().forBoolean("OCCUPIED")

    /**
     * Whether the block this property is applied to is open.
     *
     * This is applicable to all types of doors, fence gates, and trapdoors, as
     * well as the following:
     * - [Barrel][Blocks.BARREL]
     */
    @JvmField
    val OPEN: Property<Boolean> = factory().forBoolean("OPEN")

    /**
     * The orientation of the [Jigsaw Block][Blocks.JIGSAW] this property is
     * applied to.
     */
    @JvmField
    val ORIENTATION: Property<Orientation> = factory().forEnum("ORIENTATION")

    /**
     * Whether the leaves block this property is applied to won't decay, even
     * if it has no appropriate wood blocks nearby.
     */
    @JvmField
    val PERSISTENT: Property<Boolean> = factory().forBoolean("PERSISTENT")

    /**
     * The amount of pickles the [Sea Pickle][Blocks.SEA_PICKLE] this property
     * is applied to has.
     */
    @JvmField
    val PICKLES: Property<Int> = factory().forInt("PICKLES")

    /**
     * The type of the [Piston Head][Blocks.PISTON_HEAD] this property is
     * applied to. This is only applicable to piston heads due to pistons and
     * sticky pistons (the base blocks) being separate blocks.
     */
    @JvmField
    val PISTON_TYPE: Property<PistonType> = factory().forEnum("PISTON_TYPE")

    /**
     * The level of power that is either produced or is held by the block that
     * this property is applied to.
     *
     * This is applicable to:
     * - [Redstone Wire][Blocks.REDSTONE_WIRE] - the level of power running
     * through it
     * - [Daylight Detector][Blocks.DAYLIGHT_DETECTOR] - the level of power
     * that currently outputted by it
     * - Weighted Pressure Plates (both [light][Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE]
     * and [heavy][Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE]) - the level of
     * power outputted by it
     */
    @JvmField
    val POWER: Property<Int> = factory().forInt("POWER")

    /**
     * Whether the block this property is applied to is powered.
     *
     * This is applicable to all types of buttons, doors, fence gates, pressure
     * plates, and trapdoors, as well as the following:
     * - [Bell][Blocks.BELL]
     * - [Detector Rail][Blocks.DETECTOR_RAIL]
     * - [Lectern][Blocks.LECTERN]
     * - [Lever][Blocks.LEVER]
     * - [Lightning Rod][Blocks.LIGHTNING_ROD]
     * - [Note Block][Blocks.NOTE_BLOCK]
     * - [Observer][Blocks.OBSERVER]
     * - [Powered Rail][Blocks.POWERED_RAIL]
     * - [Tripwire][Blocks.TRIPWIRE]
     * - [Tripwire Hook][Blocks.TRIPWIRE_HOOK]
     */
    @JvmField
    val POWERED: Property<Boolean> = factory().forBoolean("POWERED")

    /**
     * The shape of the [Rail][Blocks.RAIL] this property is applied to.
     */
    @JvmField
    val RAIL_SHAPE: Property<RailShape> = factory().forEnum("RAIL_SHAPE")

    /**
     * The amount of charges the [Respawn Anchor][Blocks.RESPAWN_ANCHOR] this
     * property is applied to have.
     */
    @JvmField
    val RESPAWN_ANCHOR_CHARGES: Property<Int> = factory().forInt("RESPAWN_ANCHOR_CHARGES")

    /**
     * The rotation of the sign this property is applied to. This is applicable
     * to all types of signs.
     */
    @JvmField
    val ROTATION: Property<Int> = factory().forInt("ROTATION")

    /**
     * The horizontal distance from a center [Scaffolding][Blocks.SCAFFOLDING]
     * block the [Scaffolding][Blocks.SCAFFOLDING] block this property is
     * applied to is.
     */
    @JvmField
    val SCAFFOLD_DISTANCE: Property<Int> = factory().forInt("SCAFFOLD_DISTANCE")

    /**
     * The phase of the sculk sensor block this property is applied to
     * represents.
     */
    @JvmField
    val SCULK_SENSOR_PHASE: Property<SculkSensorPhase> = factory().forEnum("SCULK_SENSOR_PHASE")

    /**
     * Whether the [Piston Head][Blocks.PISTON_HEAD] this property is applied
     * to is shorter by 4 pixels.
     */
    @JvmField
    val SHORT: Property<Boolean> = factory().forBoolean("SHORT")

    /**
     * Whether the [Sculk Shrieker][Blocks.SCULK_SHRIEKER] this property is
     * applied to is shrieking or not, which will happen when it is stepped on.
     */
    @JvmField
    val SHRIEKING: Property<Boolean> = factory().forBoolean("SHRIEKING")

    /**
     * Whether the [Campfire][Blocks.CAMPFIRE] or [Soul Campfire][Blocks.SOUL_CAMPFIRE]
     * this property is applied to has a hay bale below it, meaning it produces
     * a signal fire.
     */
    @JvmField
    val SIGNAL_FIRE: Property<Boolean> = factory().forBoolean("SIGNAL_FIRE")

    /**
     * The type of the slab this property is applied to represents. This is
     * applicable to all types of stairs.
     */
    @JvmField
    val SLAB_TYPE: Property<SlabType> = factory().forEnum("SLAB_TYPE")

    /**
     * Whether the snowy dirt block this property is applied to is snowy.
     *
     * This is applicable to:
     * - [Podzol][Blocks.PODZOL]
     * - [Grass Block][Blocks.GRASS_BLOCK]
     * - [Mycelium][Blocks.MYCELIUM]
     */
    @JvmField
    val SNOWY: Property<Boolean> = factory().forBoolean("SNOWY")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends south from the center plant.
     */
    @JvmField
    val SOUTH: Property<Boolean> = factory().forBoolean("SOUTH")

    /**
     * The connection the [Redstone Wire][Blocks.REDSTONE_WIRE] this property
     * is applied to has on the south side.
     */
    @JvmField
    val SOUTH_REDSTONE_SIDE: Property<RedstoneSide> = factory().forEnum("SOUTH_REDSTONE_SIDE")

    /**
     * The connection the wall this property is applied to has on the south
     * side. This is applicable to all types of walls.
     */
    @JvmField
    val SOUTH_WALL_SIDE: Property<WallSide> = factory().forEnum("SOUTH_WALL_SIDE")

    /**
     * The current growth stage of the sapling this property is applied to.
     * This is applicable to all types of saplings.
     */
    @JvmField
    val STAGE: Property<Int> = factory().forInt("STAGE")

    /**
     * The shape of the stair this property is applied to represents. This is
     * applicable to all types of stairs.
     */
    @JvmField
    val STAIR_SHAPE: Property<StairShape> = factory().forEnum("STAIR_SHAPE")

    /**
     * The mode the [Structure Block][Blocks.STRUCTURE_BLOCK] this property is
     * applied to is currently in.
     */
    @JvmField
    val STRUCTURE_MODE: Property<StructureMode> = factory().forEnum("STRUCTURE_MODE")

    /**
     * The shape of the [Detector Rail][Blocks.DETECTOR_RAIL] or
     * [Powered Rail][Blocks.POWERED_RAIL] this property is applied to
     * represents.
     */
    @JvmField
    val STRAIGHT_RAIL_SHAPE: Property<RailShape> = factory().forEnum("shape")

    /**
     * The tilt of the [Big Dripleaf][Blocks.BIG_DRIPLEAF] this property is
     * applied to.
     */
    @JvmField
    val TILT: Property<Tilt> = factory().forEnum("TILT")

    /**
     * Whether the [Dispenser][Blocks.DISPENSER] or [Dropper][Blocks.DROPPER]
     * this property is applied to is activated.
     */
    @JvmField
    val TRIGGERED: Property<Boolean> = factory().forBoolean("TRIGGERED")

    /**
     * Whether the [TNT][Blocks.TNT] this property is applied to will explode
     * when hit as well as being able to be activated by a redstone signal
     * (just like the old days).
     */
    @JvmField
    val UNSTABLE: Property<Boolean> = factory().forBoolean("UNSTABLE")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends up from the center plant.
     */
    @JvmField
    val UP: Property<Boolean> = factory().forBoolean("UP")

    /**
     * The direction on the vertical plane of the
     * [Pointed Dripstone][Blocks.POINTED_DRIPSTONE] this property is applied
     * to.
     */
    @JvmField
    val VERTICAL_DIRECTION: Property<Direction> = factory().forEnum("VERTICAL_DIRECTION")

    /**
     * This property is currently unused by the vanilla Minecraft server.
     */
    @JvmField
    val VINE_END: Property<Boolean> = factory().forBoolean("VINE_END")

    /**
     * Whether the block this property is applied to has water in it, such as
     * when water is placed inside a stair.
     *
     * This is applicable to all types of corals, dead corals, coral fans,
     * amethyst clusters, rails, dripleafs (big and small), fences,
     * glass panes, chests, signs, slabs, stairs, trapdoors, walls, candles,
     * and the following:
     * - [Iron Bars][Blocks.IRON_BARS]
     * - [Pointed Dripstone][Blocks.POINTED_DRIPSTONE]
     * - [Sculk Sensor][Blocks.SCULK_SENSOR]
     * - [Campfire][Blocks.CAMPFIRE]
     * - [Soul Campfire][Blocks.SOUL_CAMPFIRE]
     * - [Chain][Blocks.CHAIN]
     * - [Conduit][Blocks.CONDUIT]
     * - [Fire][Blocks.FIRE]
     * - [Glow Lichen][Blocks.GLOW_LICHEN]
     * - [Hanging Roots][Blocks.HANGING_ROOTS]
     * - [Ladder][Blocks.LADDER]
     * - [Lantern][Blocks.LANTERN]
     * - [Soul Lantern][Blocks.SOUL_LANTERN]
     * - [Light][Blocks.LIGHT]
     * - [Lightning Rod][Blocks.LIGHTNING_ROD]
     * - [Scaffolding][Blocks.SCAFFOLDING]
     * - [Sea Pickle][Blocks.SEA_PICKLE]
     */
    @JvmField
    val WATERLOGGED: Property<Boolean> = factory().forBoolean("WATERLOGGED")

    /**
     * Whether the [Chorus Plant][Blocks.CHORUS_PLANT] this property is applied
     * to extends west from the center plant.
     */
    @JvmField
    val WEST: Property<Boolean> = factory().forBoolean("WEST")

    /**
     * The connection the [Redstone Wire][Blocks.REDSTONE_WIRE] this property
     * is applied to has on the west side.
     */
    @JvmField
    val WEST_REDSTONE_SIDE: Property<RedstoneSide> = factory().forEnum("WEST_REDSTONE_SIDE")

    /**
     * The connection the wall this property is applied to has on the west
     * side. This is applicable to all types of walls.
     */
    @JvmField
    val WEST_WALL_SIDE: Property<WallSide> = factory().forEnum("WEST_WALL_SIDE")

    private fun factory(): Property.Factory = AquaMine.factory()
}
