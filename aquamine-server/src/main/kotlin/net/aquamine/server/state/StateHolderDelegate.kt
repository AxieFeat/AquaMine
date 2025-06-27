package net.aquamine.server.state

import net.aquamine.api.state.Property
import net.aquamine.api.state.State
import net.aquamine.api.state.StateHolder
import net.aquamine.server.state.property.AquaProperty

interface StateHolderDelegate<out S : State<S>, K : AquaState<*, K>> : StateHolder<S> {

    val stateDefinition: StateDefinition<*, K>
    override val states: List<S>
        @Suppress("UNCHECKED_CAST") get() = stateDefinition.states as List<S>
    override val availableProperties: Collection<AquaProperty<*>>
        get() = stateDefinition.properties()

    override fun getProperty(name: String): Property<*>? = stateDefinition.getProperty(name)
}
