package net.aquamine.server.state.property

import net.aquamine.api.block.meta.AttachFace
import net.aquamine.api.block.meta.BambooLeaves
import net.aquamine.api.block.meta.BedPart
import net.aquamine.api.block.meta.BellAttachment
import net.aquamine.api.block.meta.ChestType
import net.aquamine.api.block.meta.ComparatorMode
import net.aquamine.api.block.meta.CreakingHeartState
import net.aquamine.api.block.meta.DoorHingeSide
import net.aquamine.api.block.meta.DoubleBlockHalf
import net.aquamine.api.block.meta.DripstoneThickness
import net.aquamine.api.block.meta.Half
import net.aquamine.api.block.meta.NoteBlockInstrument
import net.aquamine.api.block.meta.Orientation
import net.aquamine.api.block.meta.PistonType
import net.aquamine.api.block.meta.RailShape
import net.aquamine.api.block.meta.RedstoneSide
import net.aquamine.api.block.meta.SculkSensorPhase
import net.aquamine.api.block.meta.SlabType
import net.aquamine.api.block.meta.StairShape
import net.aquamine.api.block.meta.StructureMode
import net.aquamine.api.block.meta.TestBlockMode
import net.aquamine.api.block.meta.Tilt
import net.aquamine.api.block.meta.TrialSpawnerState
import net.aquamine.api.block.meta.VaultState
import net.aquamine.api.block.meta.WallSide
import net.aquamine.api.util.Direction
import net.aquamine.server.util.enumhelper.Directions

@Suppress("StringLiteralDuplication")
object AquaProperties {

    // Follow BlockStateProperties order

    // Booleans
    @JvmField val ATTACHED = BooleanProperty("attached")
    @JvmField val BERRIES = BooleanProperty("berries")
    @JvmField val BLOOM = BooleanProperty("bloom")
    @JvmField val BOTTOM = BooleanProperty("bottom")
    @JvmField val CAN_SUMMON = BooleanProperty("can_summon")
    @JvmField val CONDITIONAL = BooleanProperty("conditional")
    @JvmField val DISARMED = BooleanProperty("disarmed")
    @JvmField val DRAG = BooleanProperty("drag")
    @JvmField val ENABLED = BooleanProperty("enabled")
    @JvmField val EXTENDED = BooleanProperty("extended")
    @JvmField val EYE = BooleanProperty("eye")
    @JvmField val FALLING = BooleanProperty("falling")
    @JvmField val HANGING = BooleanProperty("hanging")
    @JvmField val HAS_BOTTLE_0 = BooleanProperty("has_bottle_0")
    @JvmField val HAS_BOTTLE_1 = BooleanProperty("has_bottle_1")
    @JvmField val HAS_BOTTLE_2 = BooleanProperty("has_bottle_2")
    @JvmField val HAS_RECORD = BooleanProperty("has_record")
    @JvmField val HAS_BOOK = BooleanProperty("has_book")
    @JvmField val INVERTED = BooleanProperty("inverted")
    @JvmField val IN_WALL = BooleanProperty("in_wall")
    @JvmField val LIT = BooleanProperty("lit")
    @JvmField val LOCKED = BooleanProperty("locked")
    @JvmField val NATURAL = BooleanProperty("natural")
    @JvmField val OCCUPIED = BooleanProperty("occupied")
    @JvmField val OPEN = BooleanProperty("open")
    @JvmField val PERSISTENT = BooleanProperty("persistent")
    @JvmField val POWERED = BooleanProperty("powered")
    @JvmField val SHORT = BooleanProperty("short")
    @JvmField val SHRIEKING = BooleanProperty("shrieking")
    @JvmField val SIGNAL_FIRE = BooleanProperty("signal_fire")
    @JvmField val SNOWY = BooleanProperty("snowy")
    @JvmField val TIP = BooleanProperty("tip")
    @JvmField val TRIGGERED = BooleanProperty("triggered")
    @JvmField val UNSTABLE = BooleanProperty("unstable")
    @JvmField val WATERLOGGED = BooleanProperty("waterlogged")

    // Axis
    @JvmField val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = EnumProperty.create("axis", Direction.Axis.X, Direction.Axis.Z)
    @JvmField val AXIS: EnumProperty<Direction.Axis> = EnumProperty.create("axis")

    // Directional booleans
    @JvmField val UP = BooleanProperty("up")
    @JvmField val DOWN = BooleanProperty("down")
    @JvmField val NORTH = BooleanProperty("north")
    @JvmField val EAST = BooleanProperty("east")
    @JvmField val SOUTH = BooleanProperty("south")
    @JvmField val WEST = BooleanProperty("west")

    // Directions
    @JvmField val FACING: DirectionProperty = DirectionProperty.create("facing")
    @JvmField val FACING_HOPPER: DirectionProperty = DirectionProperty.create("facing") { it != Direction.UP }
    @JvmField val HORIZONTAL_FACING: DirectionProperty = DirectionProperty.create("facing", Directions.Plane.HORIZONTAL)

    // Counts
    @JvmField val FLOWER_AMOUNT = IntProperty("flower_amount", 1, 4)
    @JvmField val SEGMENT_AMOUNT = IntProperty("segment_amount", 1, 4)

    // Enums
    @JvmField val ORIENTATION: EnumProperty<Orientation> = EnumProperty.create("orientation")
    @JvmField val ATTACH_FACE: EnumProperty<AttachFace> = EnumProperty.create("face")
    @JvmField val BELL_ATTACHMENT: EnumProperty<BellAttachment> = EnumProperty.create("attachment")
    @JvmField val EAST_WALL: EnumProperty<WallSide> = EnumProperty.create("east")
    @JvmField val NORTH_WALL: EnumProperty<WallSide> = EnumProperty.create("north")
    @JvmField val SOUTH_WALL: EnumProperty<WallSide> = EnumProperty.create("south")
    @JvmField val WEST_WALL: EnumProperty<WallSide> = EnumProperty.create("west")
    @JvmField val EAST_REDSTONE: EnumProperty<RedstoneSide> = EnumProperty.create("east")
    @JvmField val NORTH_REDSTONE: EnumProperty<RedstoneSide> = EnumProperty.create("north")
    @JvmField val SOUTH_REDSTONE: EnumProperty<RedstoneSide> = EnumProperty.create("south")
    @JvmField val WEST_REDSTONE: EnumProperty<RedstoneSide> = EnumProperty.create("west")
    @JvmField val DOUBLE_BLOCK_HALF: EnumProperty<DoubleBlockHalf> = EnumProperty.create("half")
    @JvmField val HALF: EnumProperty<Half> = EnumProperty.create("half")
    @JvmField val RAIL_SHAPE: EnumProperty<RailShape> = EnumProperty.create("shape")
    @JvmField val RAIL_SHAPE_STRAIGHT: EnumProperty<RailShape> = EnumProperty.create(
        "shape",
        RailShape.NORTH_SOUTH,
        RailShape.EAST_WEST,
        RailShape.ASCENDING_NORTH,
        RailShape.ASCENDING_SOUTH,
        RailShape.ASCENDING_EAST,
        RailShape.ASCENDING_WEST
    )

    // Ages (ints)
    @JvmField val AGE_1 = IntProperty("age", 0, 1)
    @JvmField val AGE_2 = IntProperty("age", 0, 2)
    @JvmField val AGE_3 = IntProperty("age", 0, 3)
    @JvmField val AGE_4 = IntProperty("age", 0, 4)
    @JvmField val AGE_5 = IntProperty("age", 0, 5)
    @JvmField val AGE_7 = IntProperty("age", 0, 7)
    @JvmField val AGE_15 = IntProperty("age", 0, 15)
    @JvmField val AGE_25 = IntProperty("age", 0, 25)

    // Other ints
    @JvmField val BITES = IntProperty("bites", 0, 6)
    @JvmField val CANDLES = IntProperty("candles", 1, 4)
    @JvmField val DELAY = IntProperty("delay", 1, 4)
    @JvmField val DISTANCE = IntProperty("distance", 1, 7)
    @JvmField val EGGS = IntProperty("eggs", 1, 4)
    @JvmField val HATCH = IntProperty("hatch", 0, 2)
    @JvmField val LAYERS = IntProperty("layers", 1, 8)
    @JvmField val LEVEL_CAULDRON = IntProperty("level", 1, 3)
    @JvmField val LEVEL_COMPOSTER = IntProperty("level", 0, 8)
    @JvmField val LEVEL_FLOWING = IntProperty("level", 1, 8)
    @JvmField val LEVEL_HONEY = IntProperty("honey_level", 0, 5)
    @JvmField val LEVEL = IntProperty("level", 0, 15)
    @JvmField val MOISTURE = IntProperty("moisture", 0, 7)
    @JvmField val NOTE = IntProperty("note", 0, 24)
    @JvmField val PICKLES = IntProperty("pickles", 1, 4)
    @JvmField val POWER = IntProperty("power", 0, 15)
    @JvmField val STAGE = IntProperty("stage", 0, 1)
    @JvmField val STABILITY_DISTANCE = IntProperty("distance", 0, 7)
    @JvmField val RESPAWN_ANCHOR_CHARGES = IntProperty("charges", 0, 4)
    @JvmField val DRIED_GHAST_HYDRATION_LEVELS = IntProperty("hydration", 0, 3)
    @JvmField val ROTATION_16 = IntProperty("rotation", 0, 15)

    // More enums
    @JvmField val BED_PART: EnumProperty<BedPart> = EnumProperty.create("part")
    @JvmField val CHEST_TYPE: EnumProperty<ChestType> = EnumProperty.create("type")
    @JvmField val MODE_COMPARATOR: EnumProperty<ComparatorMode> = EnumProperty.create("mode")
    @JvmField val DOOR_HINGE: EnumProperty<DoorHingeSide> = EnumProperty.create("hinge")
    @JvmField val NOTEBLOCK_INSTRUMENT: EnumProperty<NoteBlockInstrument> = EnumProperty.create("instrument")
    @JvmField val PISTON_TYPE: EnumProperty<PistonType> = EnumProperty.create("type")
    @JvmField val SLAB_TYPE: EnumProperty<SlabType> = EnumProperty.create("type")
    @JvmField val STAIRS_SHAPE: EnumProperty<StairShape> = EnumProperty.create("shape")
    @JvmField val STRUCTUREBLOCK_MODE: EnumProperty<StructureMode> = EnumProperty.create("mode")
    @JvmField val BAMBOO_LEAVES: EnumProperty<BambooLeaves> = EnumProperty.create("leaves")
    @JvmField val TILT: EnumProperty<Tilt> = EnumProperty.create("tilt")
    @JvmField val VERTICAL_DIRECTION: DirectionProperty = DirectionProperty.create("vertical_direction", Direction.UP, Direction.DOWN)
    @JvmField val DRIPSTONE_THICKNESS: EnumProperty<DripstoneThickness> = EnumProperty.create("thickness")
    @JvmField val SCULK_SENSOR_PHASE: EnumProperty<SculkSensorPhase> = EnumProperty.create("sculk_sensor_phase")

    // Chiseled Bookshelf slots
    @JvmField val CHISELED_BOOKSHELF_SLOT_0_OCCUPIED = BooleanProperty("slot_0_occupied")
    @JvmField val CHISELED_BOOKSHELF_SLOT_1_OCCUPIED = BooleanProperty("slot_1_occupied")
    @JvmField val CHISELED_BOOKSHELF_SLOT_2_OCCUPIED = BooleanProperty("slot_2_occupied")
    @JvmField val CHISELED_BOOKSHELF_SLOT_3_OCCUPIED = BooleanProperty("slot_3_occupied")
    @JvmField val CHISELED_BOOKSHELF_SLOT_4_OCCUPIED = BooleanProperty("slot_4_occupied")
    @JvmField val CHISELED_BOOKSHELF_SLOT_5_OCCUPIED = BooleanProperty("slot_5_occupied")

    // Brushing
    @JvmField val DUSTED = IntProperty("dusted", 0, 3)

    // Cracked/crafting flags
    @JvmField val CRACKED = BooleanProperty("cracked")
    @JvmField val CRAFTING = BooleanProperty("crafting")

    // New block entity state enums and flags
    @JvmField val TRIAL_SPAWNER_STATE: EnumProperty<TrialSpawnerState> = EnumProperty.create("trial_spawner_state")
    @JvmField val VAULT_STATE: EnumProperty<VaultState> = EnumProperty.create("vault_state")
    @JvmField val CREAKING_HEART_STATE: EnumProperty<CreakingHeartState> = EnumProperty.create("creaking_heart_state")
    @JvmField val OMINOUS = BooleanProperty("ominous")
    @JvmField val TEST_BLOCK_MODE: EnumProperty<TestBlockMode> = EnumProperty.create("mode")
    @JvmField val MAP = BooleanProperty("map")
}
