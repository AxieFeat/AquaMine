package net.aquamine.server.world.scoreboard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.aquamine.api.scoreboard.Objective
import net.aquamine.api.scoreboard.ObjectiveRenderType
import net.aquamine.api.scoreboard.Score
import net.aquamine.api.scoreboard.criteria.Criterion
import java.util.Collections

class AquaObjective(
    override val scoreboard: AquaScoreboard,
    override val name: String,
    override val criterion: Criterion,
    displayName: Component,
    renderType: ObjectiveRenderType
) : Objective {

    override var displayName: Component = displayName
        set(value) {
            field = value
            scoreboard.onObjectiveUpdated(this)
        }
    override var renderType: ObjectiveRenderType = renderType
        set(value) {
            field = value
            scoreboard.onObjectiveUpdated(this)
        }

    private val scoresByMember = HashMap<Component, AquaScore>()
    override val scores: Collection<Score>
        get() = Collections.unmodifiableCollection(scoresByMember.values)

    override fun getScore(member: Component): Score? = scoresByMember.get(member)

    override fun getOrCreateScore(member: Component): Score = scoresByMember.computeIfAbsent(member) { AquaScore(scoreboard, this, member) }

    override fun removeScore(member: Component): Boolean {
        if (!scoresByMember.containsKey(member)) return false
        scoresByMember.remove(member)
        return true
    }

    override fun removeScore(score: Score): Boolean = removeScore(score.member)

    class Builder(private val scoreboard: AquaScoreboard) : Objective.Builder, Objective.Builder.NamedStep, Objective.Builder.EndStep {

        private var name: String? = null
        private var criterion: Criterion? = null
        private var displayName: Component? = null
        private var renderType = ObjectiveRenderType.INTEGER

        override fun name(name: String): Builder = apply { this.name = name }

        override fun displayName(name: Component): Builder = apply { displayName = name }

        override fun criterion(criterion: Criterion): Builder = apply { this.criterion = criterion }

        override fun renderType(type: ObjectiveRenderType): Builder = apply { renderType = type }

        override fun buildAndRegister(): Objective = scoreboard.addObjective(name!!, criterion!!, getName(displayName, name!!), renderType)

        companion object {

            @JvmStatic
            private fun getName(displayName: Component?, name: String): Component =
                displayName ?: LegacyComponentSerializer.legacySection().deserialize(name)
        }
    }
}
