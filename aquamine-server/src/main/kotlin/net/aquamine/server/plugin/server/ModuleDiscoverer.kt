package net.aquamine.server.plugin.server

import java.net.URI

interface ModuleDiscoverer {

    fun discover(): Collection<URI>
}
