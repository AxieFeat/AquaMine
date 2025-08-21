package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.api.command.literal
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.TimeArgument
import net.aquamine.server.locale.CommandMessages

object WeatherCommand {

    private const val DURATION = "duration"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("weather") {
            requiresPermission(AquaPermission.WEATHER)
            literal("clear") {
                runs { clearWeather(it.source) }
                argument(DURATION, TimeArgument) {
                    runs { clearWeather(it.source, TimeArgument.get(it, DURATION)) }
                }
            }
            literal("rain") {
                runs { rainWeather(it.source) }
                argument(DURATION, TimeArgument) {
                    runs { rainWeather(it.source, TimeArgument.get(it, DURATION)) }
                }
            }
            literal("thunder") {
                runs { thunderWeather(it.source) }
                argument(DURATION, TimeArgument) {
                    runs { thunderWeather(it.source, TimeArgument.get(it, DURATION)) }
                }
            }
        })
    }

    @JvmStatic
    private fun clearWeather(source: CommandSourceStack, duration: Int? = null) {
        val world = source.world
        val data = world.data

        data.isThundering = false
        data.isRaining = false
        data.rainTime = 0
        data.thunderTime = 0
        data.clearWeatherTime = duration ?: world.random.nextInt(12000, 180000)

        source.sendSuccess(CommandMessages.WEATHER_CLEAR, true)
    }

    @JvmStatic
    private fun rainWeather(source: CommandSourceStack, duration: Int? = null) {
        val world = source.world
        val data = world.data

        val weatherTime = duration ?: world.random.nextInt(12000, 24000)

        data.isThundering = false
        data.isRaining = true
        data.rainTime = weatherTime
        data.thunderTime = weatherTime
        data.clearWeatherTime = 0

        source.sendSuccess(CommandMessages.WEATHER_RAIN, true)
    }

    @JvmStatic
    private fun thunderWeather(source: CommandSourceStack, duration: Int? = null) {
        val world = source.world
        val data = world.data

        val weatherTime = duration ?: world.random.nextInt(3600, 5600)

        data.isThundering = true
        data.isRaining = true
        data.rainTime = weatherTime
        data.thunderTime = weatherTime
        data.clearWeatherTime = 0

        source.sendSuccess(CommandMessages.WEATHER_THUNDER, true)
    }
}
