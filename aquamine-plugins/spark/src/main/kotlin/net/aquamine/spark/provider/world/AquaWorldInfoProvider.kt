package net.aquamine.spark.provider.world

import me.lucko.spark.common.platform.world.WorldInfoProvider
import net.aquamine.api.Server
import net.aquamine.api.world.rule.GameRule
import net.aquamine.api.world.rule.GameRules
import java.lang.reflect.Modifier

class AquaWorldInfoProvider(
    val server: Server,
) : WorldInfoProvider {

    companion object {
        private val gameRulesByName: Map<String, GameRule<*>> = GameRules::class.java.declaredFields.asSequence()
            .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
            .filter { GameRule::class.java.isAssignableFrom(it.type) }
            .associate {
                val rule = it.get(null) as GameRule<*>

                rule.name to rule
            }
    }

    override fun pollCounts(): WorldInfoProvider.CountsResult {
        val players: Int = this.server.players.count()
        var entities = 0
        val blockEntities = 0 // TODO counter of block entities when will be implemented
        var chunks = 0

        server.worldManager.worlds.values.forEach { world ->
            entities += world.entities.count()
            chunks += world.chunks.count()
        }

        return WorldInfoProvider.CountsResult(players, entities, blockEntities, chunks)
    }

    override fun pollChunks(): WorldInfoProvider.ChunksResult<AquaChunkInfo> {
        val data = WorldInfoProvider.ChunksResult<AquaChunkInfo>()

        server.worldManager.worlds.forEach { entry ->
            val list = mutableListOf<AquaChunkInfo>()

            entry.value.chunks.forEach { chunk ->
                list.add(AquaChunkInfo(chunk))
            }

            data.put(entry.key.location.key().toString(), list)
        }

        return data
    }

    override fun pollGameRules(): WorldInfoProvider.GameRulesResult {
        val result = WorldInfoProvider.GameRulesResult()

        // Writing default values. It can be optimized by moving to lower rules loop,
        // but we prefer greater readability
        gameRulesByName.forEach { entry ->
            result.putDefault(entry.key, entry.value.defaultValue.toString())
        }

        server.worldManager.worlds.forEach { entry ->
            val worldName = entry.key.location.key().toString()
            val world = entry.value

            gameRulesByName.forEach { name, rule ->
                result.put(name, worldName, world.getGameRule(rule).toString())
            }
        }

        return result
    }

    override fun pollDataPacks(): Collection<WorldInfoProvider.DataPackInfo> {
        return emptyList()
    }

    override fun mustCallSync(): Boolean {
        return false
    }

}