package net.aquamine.server.registry.dynamic

import net.aquamine.api.registry.Registry

interface RegistryContainer {

    fun registries(): Collection<Registry<*>>
}
