package net.aquamine.server.server

import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonReader
import net.kyori.adventure.key.InvalidKeyException
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.api.registry.Registry
import net.aquamine.api.statistic.Statistic
import net.aquamine.api.statistic.StatisticType
import net.aquamine.server.AquaPlatform
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.registry.AquaRegistries
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class StatisticsSerializer(private val folder: Path) {

    fun loadAll(player: AquaPlayer) {
        val file = folder.resolve("${player.uuid}.json")
        loadAllInFile(player, file)
    }

    private fun loadAllInFile(player: AquaPlayer, file: Path) {
        if (!Files.isRegularFile(file)) return
        val reader = Files.newInputStream(file, StandardOpenOption.READ).reader()

        try {
            val json = JsonReader(reader).apply { isLenient = false }.use(Streams::parse)
            if (json.isJsonNull) {
                LOGGER.error("Unable to parse statistics data from $file to JSON!")
                return
            }

            json as JsonObject
            if (!json.has(DATA_VERSION_KEY) || !json.get(DATA_VERSION_KEY).isJsonPrimitive) {
                json.addProperty(DATA_VERSION_KEY, OLD_VERSION)
            }

            if (json.get(STATS_KEY).asJsonObject.size() == 0) return

            val stats = json.get(STATS_KEY)?.asJsonObject ?: JsonObject()
            loadStatistics(player, file, stats)
        } catch (exception: IOException) {
            LOGGER.error("Failed to load statistics data from $file!", exception)
        } catch (exception: JsonParseException) {
            LOGGER.error("Failed to parse statistics data from $file to JSON!", exception)
        }
    }

    private fun loadStatistics(player: AquaPlayer, file: Path, stats: JsonObject) {
        stats.keySet().forEach { key ->
            if (!stats.get(key).isJsonObject) return@forEach
            val type = AquaRegistries.STATISTIC_TYPE.get(Key.key(key))
            if (type == null) {
                LOGGER.warn("Invalid statistic type found in $file! Could not recognise $key!")
                return@forEach
            }
            val values = stats.get(key).asJsonObject
            values.keySet().forEach { valueKey -> loadStatisticValue(player, file, values, valueKey, type) }
        }
    }

    private fun loadStatisticValue(player: AquaPlayer, file: Path, values: JsonObject, valueKey: String, type: StatisticType<*>) {
        val value = values.get(valueKey)
        if (!value.isJsonPrimitive) {
            LOGGER.warn("Invalid statistic found in $file! Could not recognise value ${values.get(valueKey)} for key $valueKey")
            return
        }
        val statistic = getStatistic(type, valueKey)
        if (statistic != null) {
            player.statisticsTracker.statistics.put(statistic, value.asInt)
        } else {
            LOGGER.warn("Invalid statistic found in $file! Could not recognise key $valueKey!")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun saveAll(player: AquaPlayer) {
        val tracker = player.statisticsTracker
        val file = folder.resolve("${player.uuid}.json")

        try {
            val map = HashMap<StatisticType<*>, JsonObject>()
            tracker.statistics.object2IntEntrySet().forEach {
                val registry = it.key.type.registry as Registry<Any>
                map.computeIfAbsent(it.key.type) { JsonObject() }.addProperty(registry.getKey(it.key.value!!)!!.toString(), it.intValue)
            }

            val statsJson = JsonObject()
            map.forEach { statsJson.add(it.key.key().asString(), it.value) }

            val json = JsonObject().apply {
                add(STATS_KEY, statsJson)
                addProperty(DATA_VERSION_KEY, AquaPlatform.worldVersion)
            }.toString()
            Files.newOutputStream(file).writer().use { it.write(json) }
        } catch (exception: IOException) {
            LOGGER.error("Failed to save statistics file $file!", exception)
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private const val OLD_VERSION = 1343

        private const val STATS_KEY = "stats"
        private const val DATA_VERSION_KEY = "DataVersion"

        @JvmStatic
        private fun <T> getStatistic(type: StatisticType<T>, name: String): Statistic<T>? {
            val key = try {
                Key.key(name)
            } catch (_: InvalidKeyException) {
                return null
            }
            return type.registry.get(key)?.let(type::getStatistic)
        }
    }
}
