package net.aquamine.client.server

import net.aquamine.server.AquaServer
import net.aquamine.server.auth.GameProfileCache
import net.aquamine.server.config.AquaConfig
import net.aquamine.server.server.InitContext

class IntegratedServer(
    config: AquaConfig,
    profileCache: GameProfileCache,
    initContext: InitContext
) : AquaServer(config, profileCache, initContext) {

}