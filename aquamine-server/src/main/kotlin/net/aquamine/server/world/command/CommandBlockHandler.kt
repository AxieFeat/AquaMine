package net.aquamine.server.world.command

import com.mojang.brigadier.ResultConsumer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.util.TriState
import net.aquamine.server.AquaServer
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.AquaSender
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.rule.GameRuleKeys
import xyz.axie.nbt.ByteTag
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

abstract class CommandBlockHandler : AquaSender {

    var command: String = ""
        set(value) {
            field = value
            successCount = 0
        }
    private var lastExecution = -1L
    private var updateLastExecution = true
    private var successCount = 0
    private var trackOutput = true
    var lastOutput: Component? = null
    private val commandCallback = ResultConsumer<CommandSourceStack> { _, success, _ -> if (success) successCount++ }

    private var customName: Component = DEFAULT_NAME
    override val name: String
        get() = PlainTextComponentSerializer.plainText().serialize(customName)
    override val server: AquaServer
        get() = world().server

    protected abstract fun world(): AquaWorld

    abstract override fun createCommandSourceStack(): CommandSourceStack

    protected abstract fun onUpdated()

    fun load(data: CompoundTag) {
        command = data.getString(COMMAND_TAG)
        successCount = data.getInt(SUCCESS_COUNT_TAG)
        if (data.contains(CUSTOM_NAME_TAG, StringTag.ID)) customName = GsonComponentSerializer.gson().deserialize(data.getString(CUSTOM_NAME_TAG))
        if (data.contains(TRACK_OUTPUT_TAG, ByteTag.ID)) trackOutput = data.getBoolean(TRACK_OUTPUT_TAG)
        lastOutput = if (data.contains(LAST_OUTPUT_TAG, StringTag.ID)) {
            try {
                GsonComponentSerializer.gson().deserialize(data.getString(LAST_OUTPUT_TAG))
            } catch (exception: Exception) {
                Component.text(exception.message ?: "Error occurred while deserializing last output")
            }
        } else {
            null
        }
        if (data.contains(UPDATE_LAST_EXECUTION_TAG)) updateLastExecution = data.getBoolean(UPDATE_LAST_EXECUTION_TAG)
        lastExecution = if (updateLastExecution && data.contains(LAST_EXECUTION_TAG)) data.getLong(LAST_EXECUTION_TAG) else -1L
    }

    fun save(builder: CompoundTag.Builder): CompoundTag.Builder = builder.apply {
        putString(COMMAND_TAG, command)
        putInt(SUCCESS_COUNT_TAG, successCount)
        putString(CUSTOM_NAME_TAG, GsonComponentSerializer.gson().serialize(customName))
        putBoolean(TRACK_OUTPUT_TAG, trackOutput)
        if (lastOutput != null && trackOutput) putString(LAST_OUTPUT_TAG, GsonComponentSerializer.gson().serialize(lastOutput!!))
        putBoolean(UPDATE_LAST_EXECUTION_TAG, updateLastExecution)
        if (updateLastExecution && lastExecution > 0L) putLong(LAST_EXECUTION_TAG, lastExecution)
    }

    fun runCommand(world: AquaWorld): Boolean {
        if (world.time == lastExecution) return false
        if (command == "Searge") {
            lastOutput = Component.text("#itzlipofutzli")
            successCount = 1
            return true
        }
        successCount = 0
        if (server.config.world.allowCommandBlocks && command.isNotEmpty()) {
            lastOutput = null
            server.commandManager.dispatch(createCommandSourceStack(), command, commandCallback)
        }
        lastExecution = if (updateLastExecution) world.time else -1L
        return true
    }

    override fun sendSystemMessage(message: Component) {
        if (trackOutput) {
            lastOutput = Component.text().content("[${TIME_FORMATTER.format(LocalDateTime.now())}] ").append(message).build()
            onUpdated()
        }
    }

    override fun getPermissionValue(permission: String): TriState = TriState.TRUE

    override fun acceptsSuccess(): Boolean = world().gameRules().getBoolean(GameRuleKeys.SEND_COMMAND_FEEDBACK) && trackOutput

    override fun acceptsFailure(): Boolean = trackOutput

    override fun shouldInformAdmins(): Boolean = world().gameRules().getBoolean(GameRuleKeys.COMMAND_BLOCK_OUTPUT)

    companion object {

        private val DEFAULT_NAME = Component.text("@")
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss")

        private const val COMMAND_TAG = "Command"
        private const val SUCCESS_COUNT_TAG = "SuccessCount"
        private const val CUSTOM_NAME_TAG = "CustomName"
        private const val TRACK_OUTPUT_TAG = "TrackOutput"
        private const val LAST_OUTPUT_TAG = "LastOutput"
        private const val UPDATE_LAST_EXECUTION_TAG = "UpdateLastExecution"
        private const val LAST_EXECUTION_TAG = "LastExecution"
    }
}
