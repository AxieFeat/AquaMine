package net.aquamine.server.pack.resources

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Supplier
import net.aquamine.server.pack.PackResources
import net.aquamine.server.pack.resources.reload.ReloadInstance
import net.aquamine.server.pack.resources.reload.PreparableReloadListener
import net.aquamine.server.pack.resources.reload.SimpleReloadInstance
import net.aquamine.server.pack.PackType
import net.aquamine.server.util.ImmutableLists
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Predicate

class ReloadableResourceManager(private val type: PackType) : ResourceManager, AutoCloseable {

    private var resources: CloseableResourceManager = MultiPackResourceManager(type, ImmutableLists.of())
    private val listeners = ArrayList<PreparableReloadListener>()

    override fun close() {
        resources.close()
    }

    fun registerListener(listener: PreparableReloadListener) {
        listeners.add(listener)
    }

    fun createReload(background: Executor, main: Executor, waitingFor: CompletableFuture<Unit>, packs: List<PackResources>): ReloadInstance {
        LOGGER.info(Supplier { "Reloading resource manager: ${packs.joinToString(", ") { it.packId() }}" })
        resources.close()
        resources = MultiPackResourceManager(type, packs)
        return SimpleReloadInstance.create(resources, listeners, background, main, waitingFor)
    }

    override fun getResource(location: Key): Resource? = resources.getResource(location)

    override fun listResources(path: String, predicate: Predicate<Key>): Map<Key, Resource> = resources.listResources(path, predicate)

    override fun listResourceStacks(path: String, predicate: Predicate<Key>): Map<Key, List<Resource>> =
        resources.listResourceStacks(path, predicate)

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
