package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.argument
import net.aquamine.api.command.literal
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.TimeArgument
import net.aquamine.server.locale.CommandMessages

object TimeCommand {

    private const val TIME = "time"

    private const val DAY_TIME = 1000
    private const val NOON_TIME = 6000
    private const val NIGHT_TIME = 13000
    private const val MIDNIGHT_TIME = 18000

    private const val TICKS_PER_DAY = 24000

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("time") {
            requiresPermission(AquaPermission.TIME)
            literal("set") {
                literal("day") { runs { setTime(it.source, DAY_TIME) } }
                literal("noon") { runs { setTime(it.source, NOON_TIME) } }
                literal("night") { runs { setTime(it.source, NIGHT_TIME) } }
                literal("midnight") { runs { setTime(it.source, MIDNIGHT_TIME) } }

                argument(TIME, TimeArgument) { runs { setTime(it.source, TimeArgument.get(it, TIME)) } }
            }
            literal("add") {
                argument(TIME, TimeArgument) {
                    runs { addTime(it.source, TimeArgument.get(it, TIME)) }
                }
            }
            literal("query") {
                literal("daytime") { runs { queryTime(it.source, it.source.world.dayTime) } }
                literal("gametime") { runs { queryTime(it.source, it.source.world.dayTime % Int.MAX_VALUE) } }
                literal("day") { runs { queryTime(it.source, it.source.world.dayTime / TICKS_PER_DAY % Int.MAX_VALUE) } }
            }
        })
    }

    @JvmStatic
    fun setTime(source: CommandSourceStack, time: Int) {
        source.world.data.dayTime = time.toLong()

        CommandMessages.TIME_SET.sendSuccess(source, time, true)
    }

    @JvmStatic
    fun addTime(source: CommandSourceStack, time: Int) {
        source.world.data.dayTime += time.toLong()

        CommandMessages.TIME_SET.sendSuccess(source, source.world.data.dayTime.toInt(), true)
    }

    @JvmStatic
    fun queryTime(source: CommandSourceStack, time: Long) {
        CommandMessages.TIME_QUERY.sendSuccess(source, time.toInt(), true)
    }
}
