package net.aquamine.server.entity.serializer.player

import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.attribute.AttributeTypes
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.player.RespawnData
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.LivingEntitySerializer
import net.aquamine.server.util.enumhelper.GameModes
import net.aquamine.server.util.nbt.hasNumber
import net.aquamine.server.util.nbt.putDataVersion
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import java.time.Instant

object PlayerSerializer : EntitySerializer<AquaPlayer> {

    private val LOGGER = LogManager.getLogger()

    // Vanilla tags
    private const val GAME_TYPE_TAG = "playerGameType"
    private const val PREVIOUS_GAME_TYPE_TAG = "previousPlayerGameType"
    private const val INVENTORY_TAG = "Inventory"
    private const val SELECTED_SLOT_TAG = "SelectedItemSlot"
    private const val SCORE_TAG = "Score"
    private const val LEFT_SHOULDER_TAG = "ShoulderEntityLeft"
    private const val RIGHT_SHOULDER_TAG = "ShoulderEntityRight"
    private const val DIMENSION_TAG = "Dimension"
    private const val ROOT_VEHICLE_TAG = "RootVehicle"
    private const val ATTACH_TAG = "Attach"
    private const val ENTITY_TAG = "Entity"

    // Custom aquamine-only tags
    private const val AQUAMINE_TAG = "aquamine"
    private const val FIRST_JOINED_TAG = "firstJoined"
    private const val LAST_JOINED_TAG = "lastJoined"

    override fun load(entity: AquaPlayer, data: CompoundTag) {
        LivingEntitySerializer.load(entity, data)
        val gameMode = if (data.hasNumber(GAME_TYPE_TAG)) GameModes.fromIdOrDefault(data.getInt(GAME_TYPE_TAG)) else null
        val oldGameMode = if (data.hasNumber(PREVIOUS_GAME_TYPE_TAG)) GameModes.fromId(data.getInt(PREVIOUS_GAME_TYPE_TAG)) else null
        entity.gameModeSystem.setGameMode(selectGameMode(entity, gameMode), oldGameMode)

        entity.inventory.load(data.getList(INVENTORY_TAG, CompoundTag.ID))
        entity.inventory.heldSlot = data.getInt(SELECTED_SLOT_TAG)
        entity.data.set(MetadataKeys.Player.SCORE, data.getInt(SCORE_TAG))
        entity.hungerSystem.load(data)

        entity.abilities.load(data)
        entity.getAttribute(AttributeTypes.MOVEMENT_SPEED)!!.baseValue = entity.walkingSpeed.toDouble()

        // NBT data for entities sitting on the player's shoulders, e.g. parrots
        if (data.contains(LEFT_SHOULDER_TAG, CompoundTag.ID)) {
            entity.data.set(MetadataKeys.Player.LEFT_SHOULDER, data.getCompound(LEFT_SHOULDER_TAG))
        }
        if (data.contains(RIGHT_SHOULDER_TAG, CompoundTag.ID)) {
            entity.data.set(MetadataKeys.Player.RIGHT_SHOULDER, data.getCompound(RIGHT_SHOULDER_TAG))
        }

        // Respawn data
        entity.respawnData = RespawnData.load(data, LOGGER)

        if (data.contains(AQUAMINE_TAG, CompoundTag.ID)) {
            entity.hasJoinedBefore = true
            val aquaData = data.getCompound(AQUAMINE_TAG)
            entity.firstJoined = Instant.ofEpochMilli(aquaData.getLong(FIRST_JOINED_TAG))
            entity.lastJoined = Instant.ofEpochMilli(aquaData.getLong(LAST_JOINED_TAG))
        }
    }

    private fun selectGameMode(player: AquaPlayer, loadedMode: GameMode?): GameMode {
        val config = player.server.config.world
        if (config.forceDefaultGameMode || loadedMode == null) return config.defaultGameMode
        return loadedMode
    }

    override fun save(entity: AquaPlayer): CompoundTag.Builder = LivingEntitySerializer.save(entity).apply {
        putInt(GAME_TYPE_TAG, entity.gameMode.ordinal)
        entity.gameModeSystem.previousGameMode()?.let { putInt(PREVIOUS_GAME_TYPE_TAG, it.ordinal) }

        putDataVersion()
        put(INVENTORY_TAG, entity.inventory.save())
        putInt(SELECTED_SLOT_TAG, entity.inventory.heldSlot)
        putInt(SCORE_TAG, entity.data.get(MetadataKeys.Player.SCORE))
        entity.hungerSystem.save(this)
        entity.abilities.save(this)

        val leftShoulder = entity.data.get(MetadataKeys.Player.LEFT_SHOULDER)
        val rightShoulder = entity.data.get(MetadataKeys.Player.RIGHT_SHOULDER)
        if (!leftShoulder.isEmpty()) put(LEFT_SHOULDER_TAG, leftShoulder)
        if (!rightShoulder.isEmpty()) put(RIGHT_SHOULDER_TAG, rightShoulder)

        putString(DIMENSION_TAG, entity.world.dimension.location.asString())
        entity.respawnData?.save(this, LOGGER)

        val rootVehicle = entity.vehicleSystem.rootVehicle()
        val vehicle = entity.vehicle
        if (vehicle != null && rootVehicle !== entity && rootVehicle.vehicleSystem.hasExactlyOnePlayerPassenger()) {
            compound(ROOT_VEHICLE_TAG) {
                putUUID(ATTACH_TAG, vehicle.uuid)
                put(ENTITY_TAG, rootVehicle.saveWithPassengers().build())
            }
        }

        compound(AQUAMINE_TAG) {
            putLong(FIRST_JOINED_TAG, entity.firstJoined.toEpochMilli())
            putLong(LAST_JOINED_TAG, entity.lastJoined.toEpochMilli())
        }
    }
}
