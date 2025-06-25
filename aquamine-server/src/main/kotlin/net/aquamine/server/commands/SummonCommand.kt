package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.kyori.adventure.key.Key
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.api.util.Position
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.SuggestionProviders
import net.aquamine.server.command.arguments.NBTCompoundArgument
import net.aquamine.server.command.arguments.SummonEntityArgument
import net.aquamine.server.command.arguments.VectorArgument
import net.aquamine.server.entity.EntityFactory
import net.aquamine.server.command.arguments.CommandExceptions
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.util.Worlds
import xyz.axie.nbt.CompoundTag

object SummonCommand {

    private val ERROR_FAILED = CommandExceptions.simple("commands.summon.failed")
    private val ERROR_INVALID_POSITION = CommandExceptions.simple("commands.summon.invalidPosition")

    private const val ENTITY = "entity"
    private const val POSITION = "position"
    private const val NBT = "nbt"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("summon") {
            requiresPermission(KryptonPermission.SUMMON)
            argument(ENTITY, SummonEntityArgument) {
                suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                runs { spawnEntity(it.source, SummonEntityArgument.get(it, ENTITY), it.source.position, CompoundTag.EMPTY) }
                argument(POSITION, VectorArgument.normal()) {
                    runs { spawnEntity(it.source, SummonEntityArgument.get(it, ENTITY), VectorArgument.get(it, POSITION), CompoundTag.EMPTY) }
                    argument(NBT, NBTCompoundArgument) {
                        runs {
                            spawnEntity(it.source, SummonEntityArgument.get(it, ENTITY), VectorArgument.get(it, POSITION), it.getArgument(NBT))
                        }
                    }
                }
            }
        })
    }

    @JvmStatic
    private fun spawnEntity(source: CommandSourceStack, type: Key, position: Position, nbt: CompoundTag) {
        if (!Worlds.isInSpawnableBounds(position)) throw ERROR_INVALID_POSITION.create()
        val entity = EntityFactory.create(source.world, type.asString(), nbt)?.apply { this.position = position } ?: throw ERROR_FAILED.create()
        source.world.spawnEntity(entity)
        CommandMessages.SUMMON.sendSuccess(source, entity.displayName, true)
    }
}
