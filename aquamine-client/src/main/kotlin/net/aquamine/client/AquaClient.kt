package net.aquamine.client

import arc.Application
import arc.ArcObjectProvider
import arc.gl.OpenGL
import arc.graphics.RenderSystem
import arc.window.Window
import net.aquamine.client.server.IntegratedServer
import org.apache.logging.log4j.LogManager
import java.util.concurrent.CompletableFuture

class AquaClient(
    val server: CompletableFuture<IntegratedServer>
) {

    private val application: Application by lazy { Application.find() }
    private val window: Window by lazy { application.window }
    private val renderSystem: RenderSystem by lazy { application.renderSystem }

    fun initialize() {
        Thread.currentThread().name = "Client" // Because GLFW can work only in the main thread.

        LOGGER.info("Preloading arc-engine...")
        ArcObjectProvider.install()
        ArcObjectProvider.bootstrap()
        OpenGL.preload()

        LOGGER.info("Initializing client...")
        start()
    }

    fun start() {
        application.init()

        while (!window.shouldClose()) {
            renderSystem.beginFrame()

            renderSystem.endFrame()
        }

        stop()
    }

    fun stop() {
        LOGGER.info("Stopping client...")
        application.close()
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}