package net.aquamine.server.pack.resources

import net.kyori.adventure.key.Key
import java.util.function.Predicate

interface ResourceManager : ResourceProvider {

    fun listResources(path: String, predicate: Predicate<Key>): Map<Key, Resource>

    fun listResourceStacks(path: String, predicate: Predicate<Key>): Map<Key, List<Resource>>
}
