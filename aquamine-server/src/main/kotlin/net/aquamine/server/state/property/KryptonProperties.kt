package net.aquamine.server.state.property

import net.aquamine.api.block.meta.AttachFace
import net.aquamine.api.block.meta.BambooLeaves
import net.aquamine.api.block.meta.BedPart
import net.aquamine.api.block.meta.BellAttachment
import net.aquamine.api.block.meta.ChestType
import net.aquamine.api.block.meta.ComparatorMode
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
import net.aquamine.api.block.meta.Tilt
import net.aquamine.api.block.meta.WallSide
import net.aquamine.api.util.Direction
import net.aquamine.server.util.enumhelper.Directions

@Suppress("StringLiteralDuplication")
object KryptonProperties {

    // ==============================
    // Boolean properties
    // ==============================

    @JvmField
    val ATTACHED: BooleanProperty = BooleanProperty("attached")
    @JvmField
    val BERRIES: BooleanProperty = BooleanProperty("berries")
    @JvmField
    val BLOOM: BooleanProperty = BooleanProperty("bloom")
    @JvmField
    val BOTTOM: BooleanProperty = BooleanProperty("bottom")
    @JvmField
    val CAN_SUMMON: BooleanProperty = BooleanProperty("can_summon")
    @JvmField
    val CHISELED_BOOKSHELF_FIRST_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_0_occupied")
    @JvmField
    val CHISELED_BOOKSHELF_SECOND_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_1_occupied")
    @JvmField
    val CHISELED_BOOKSHELF_THIRD_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_2_occupied")
    @JvmField
    val CHISELED_BOOKSHELF_FOURTH_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_3_occupied")
    @JvmField
    val CHISELED_BOOKSHELF_FIFTH_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_4_occupied")
    @JvmField
    val CHISELED_BOOKSHELF_SIXTH_SLOT_OCCUPIED: BooleanProperty = BooleanProperty("slot_5_occupied")
    @JvmField
    val CONDITIONAL: BooleanProperty = BooleanProperty("conditional")
    @JvmField
    val DISARMED: BooleanProperty = BooleanProperty("disarmed")
    @JvmField
    val DOWN: BooleanProperty = BooleanProperty("down")
    @JvmField
    val DRAG: BooleanProperty = BooleanProperty("drag")
    @JvmField
    val EAST: BooleanProperty = BooleanProperty("east")
    @JvmField
    val ENABLED: BooleanProperty = BooleanProperty("enabled")
    @JvmField
    val EXTENDED: BooleanProperty = BooleanProperty("extended")
    @JvmField
    val EYE: BooleanProperty = BooleanProperty("eye")
    @JvmField
    val FALLING: BooleanProperty = BooleanProperty("falling")
    @JvmField
    val HANGING: BooleanProperty = BooleanProperty("hanging")
    @JvmField
    val HAS_BOOK: BooleanProperty = BooleanProperty("has_book")
    @JvmField
    val HAS_FIRST_BOTTLE: BooleanProperty = BooleanProperty("has_bottle_0")
    @JvmField
    val HAS_SECOND_BOTTLE: BooleanProperty = BooleanProperty("has_bottle_1")
    @JvmField
    val HAS_THIRD_BOTTLE: BooleanProperty = BooleanProperty("has_bottle_2")
    @JvmField
    val HAS_RECORD: BooleanProperty = BooleanProperty("has_record")
    @JvmField
    val INVERTED: BooleanProperty = BooleanProperty("inverted")
    @JvmField
    val IN_WALL: BooleanProperty = BooleanProperty("in_wall")
    @JvmField
    val LIT: BooleanProperty = BooleanProperty("lit")
    @JvmField
    val LOCKED: BooleanProperty = BooleanProperty("locked")
    @JvmField
    val NORTH: BooleanProperty = BooleanProperty("north")
    @JvmField
    val OCCUPIED: BooleanProperty = BooleanProperty("occupied")
    @JvmField
    val OPEN: BooleanProperty = BooleanProperty("open")
    @JvmField
    val PERSISTENT: BooleanProperty = BooleanProperty("persistent")
    @JvmField
    val POWERED: BooleanProperty = BooleanProperty("powered")
    @JvmField
    val SHORT: BooleanProperty = BooleanProperty("short")
    @JvmField
    val SHRIEKING: BooleanProperty = BooleanProperty("shrieking")
    @JvmField
    val SIGNAL_FIRE: BooleanProperty = BooleanProperty("signal_fire")
    @JvmField
    val SNOWY: BooleanProperty = BooleanProperty("snowy")
    @JvmField
    val SOUTH: BooleanProperty = BooleanProperty("south")
    @JvmField
    val TRIGGERED: BooleanProperty = BooleanProperty("triggered")
    @JvmField
    val UNSTABLE: BooleanProperty = BooleanProperty("unstable")
    @JvmField
    val UP: BooleanProperty = BooleanProperty("up")
    @JvmField
    val VINE_END: BooleanProperty = BooleanProperty("vine_end")
    @JvmField
    val WATERLOGGED: BooleanProperty = BooleanProperty("waterlogged")
    @JvmField
    val WEST: BooleanProperty = BooleanProperty("west")

    // ==============================
    // Integer properties
    // ==============================

    @JvmField
    val AGE_1: IntProperty = IntProperty("age", 0, 1)
    @JvmField
    val AGE_2: IntProperty = IntProperty("age", 0, 2)
    @JvmField
    val AGE_3: IntProperty = IntProperty("age", 0, 3)
    @JvmField
    val AGE_4: IntProperty = IntProperty("age", 0, 4)
    @JvmField
    val AGE_5: IntProperty = IntProperty("age", 0, 5)
    @JvmField
    val AGE_7: IntProperty = IntProperty("age", 0, 7)
    @JvmField
    val AGE_15: IntProperty = IntProperty("age", 0, 15)
    @JvmField
    val AGE_25: IntProperty = IntProperty("age", 0, 25)
    @JvmField
    val BITES: IntProperty = IntProperty("bites", 0, 6)
    @JvmField
    val CANDLES: IntProperty = IntProperty("candles", 1, 4)
    @JvmField
    val CAULDRON_LEVEL: IntProperty = IntProperty("level", 1, 3)
    @JvmField
    val COMPOSTER_LEVEL: IntProperty = IntProperty("level", 0, 8)
    @JvmField
    val DELAY: IntProperty = IntProperty("delay", 1, 4)
    @JvmField
    val DISTANCE: IntProperty = IntProperty("distance", 1, 7)
    @JvmField
    val EGGS: IntProperty = IntProperty("eggs", 1, 4)
    @JvmField
    val HATCH: IntProperty = IntProperty("hatch", 0, 2)
    @JvmField
    val HONEY_LEVEL: IntProperty = IntProperty("honey_level", 0, 5)
    @JvmField
    val LAYERS: IntProperty = IntProperty("layers", 1, 8)
    @JvmField
    val LEVEL: IntProperty = IntProperty("level", 0, 15)
    @JvmField
    val LIQUID_LEVEL: IntProperty = IntProperty("level", 1, 8)
    @JvmField
    val MOISTURE: IntProperty = IntProperty("moisture", 0, 7)
    @JvmField
    val NOTE: IntProperty = IntProperty("note", 0, 24)
    @JvmField
    val PICKLES: IntProperty = IntProperty("pickles", 1, 4)
    @JvmField
    val POWER: IntProperty = IntProperty("power", 0, 15)
    @JvmField
    val RESPAWN_ANCHOR_CHARGES: IntProperty = IntProperty("charges", 0, 4)
    @JvmField
    val ROTATION: IntProperty = IntProperty("rotation", 0, 15)
    @JvmField
    val SCAFFOLD_DISTANCE: IntProperty = IntProperty("distance", 0, 7)
    @JvmField
    val STAGE: IntProperty = IntProperty("stage", 0, 1)

    // ==============================
    // Enum properties
    // ==============================

    @JvmField
    val ATTACH_FACE: EnumProperty<AttachFace> = EnumProperty.create("face")
    @JvmField
    val AXIS: EnumProperty<Direction.Axis> = EnumProperty.create("axis")
    @JvmField
    val BAMBOO_LEAVES: EnumProperty<BambooLeaves> = EnumProperty.create("leaves")
    @JvmField
    val BED_PART: EnumProperty<BedPart> = EnumProperty.create("part")
    @JvmField
    val BELL_ATTACHMENT: EnumProperty<BellAttachment> = EnumProperty.create("attachment")
    @JvmField
    val CHEST_TYPE: EnumProperty<ChestType> = EnumProperty.create("type")
    @JvmField
    val COMPARATOR_MODE: EnumProperty<ComparatorMode> = EnumProperty.create("mode")
    @JvmField
    val DOOR_HINGE: EnumProperty<DoorHingeSide> = EnumProperty.create("hinge")
    @JvmField
    val DOUBLE_BLOCK_HALF: EnumProperty<DoubleBlockHalf> = EnumProperty.create("half")
    @JvmField
    val DRIPSTONE_THICKNESS: EnumProperty<DripstoneThickness> = EnumProperty.create("thickness")
    @JvmField
    val EAST_REDSTONE_SIDE: EnumProperty<RedstoneSide> = EnumProperty.create("east")
    @JvmField
    val EAST_WALL_SIDE: EnumProperty<WallSide> = EnumProperty.create("east")
    @JvmField
    val HALF: EnumProperty<Half> = EnumProperty.create("half")
    @JvmField
    val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = EnumProperty.create("axis", Direction.Axis.X, Direction.Axis.Z)
    @JvmField
    val INSTRUMENT: EnumProperty<NoteBlockInstrument> = EnumProperty.create("instrument")
    @JvmField
    val NORTH_REDSTONE_SIDE: EnumProperty<RedstoneSide> = EnumProperty.create("north")
    @JvmField
    val NORTH_WALL_SIDE: EnumProperty<WallSide> = EnumProperty.create("north")
    @JvmField
    val ORIENTATION: EnumProperty<Orientation> = EnumProperty.create("orientation")
    @JvmField
    val PISTON_TYPE: EnumProperty<PistonType> = EnumProperty.create("type")
    @JvmField
    val RAIL_SHAPE: EnumProperty<RailShape> = EnumProperty.create("shape")
    @JvmField
    val SCULK_SENSOR_PHASE: EnumProperty<SculkSensorPhase> = EnumProperty.create("sculk_sensor_phase")
    @JvmField
    val SLAB_TYPE: EnumProperty<SlabType> = EnumProperty.create("type")
    @JvmField
    val SOUTH_REDSTONE_SIDE: EnumProperty<RedstoneSide> = EnumProperty.create("south")
    @JvmField
    val SOUTH_WALL_SIDE: EnumProperty<WallSide> = EnumProperty.create("south")
    @JvmField
    val STAIR_SHAPE: EnumProperty<StairShape> = EnumProperty.create("shape")
    @JvmField
    val STRUCTURE_MODE: EnumProperty<StructureMode> = EnumProperty.create("mode")
    @JvmField
    val STRAIGHT_RAIL_SHAPE: EnumProperty<RailShape> = EnumProperty.create("shape") {
        it != RailShape.NORTH_EAST && it != RailShape.NORTH_WEST && it != RailShape.SOUTH_EAST && it != RailShape.SOUTH_WEST
    }
    @JvmField
    val TILT: EnumProperty<Tilt> = EnumProperty.create("tilt")
    @JvmField
    val WEST_REDSTONE_SIDE: EnumProperty<RedstoneSide> = EnumProperty.create("west")
    @JvmField
    val WEST_WALL_SIDE: EnumProperty<WallSide> = EnumProperty.create("west")

    // ==============================
    // Direction properties
    // ==============================

    @JvmField
    val FACING: DirectionProperty = DirectionProperty.create("facing")
    @JvmField
    val HOPPER_FACING: DirectionProperty = DirectionProperty.create("facing") { it != Direction.UP }
    @JvmField
    val HORIZONTAL_FACING: DirectionProperty = DirectionProperty.create("facing", Directions.Plane.HORIZONTAL)
    @JvmField
    val VERTICAL_DIRECTION: DirectionProperty = DirectionProperty.create("vertical_direction", Direction.UP, Direction.DOWN)
}
