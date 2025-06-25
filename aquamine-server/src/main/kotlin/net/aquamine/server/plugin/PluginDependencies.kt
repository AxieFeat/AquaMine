package net.aquamine.server.plugin

import com.google.common.collect.Maps
import com.google.common.graph.Graph
import com.google.common.graph.GraphBuilder
import net.aquamine.api.plugin.PluginDescription
import java.util.ArrayDeque
import java.util.Deque

object PluginDependencies {

    @JvmStatic
    fun sortCandidates(candidates: List<PluginDescription>): List<PluginDescription> {
        val sortedCandidates = candidates.sortedBy { it.id }
        val graph = GraphBuilder.directed().allowsSelfLoops(false).expectedNodeCount(sortedCandidates.size).build<PluginDescription>()
        val candidateMap = Maps.uniqueIndex(sortedCandidates) { it.id }

        sortedCandidates.forEach { description ->
            graph.addNode(description)
            description.dependencies.forEach { dependency ->
                candidateMap.get(dependency.id)?.let { graph.putEdge(description, it) }
            }
        }

        val sorted = ArrayList<PluginDescription>()
        val marks = HashMap<PluginDescription, Mark>()
        graph.nodes().forEach { visitNode(graph, it, marks, sorted, ArrayDeque()) }
        return sorted
    }

    @JvmStatic
    private fun visitNode(graph: Graph<PluginDescription>, current: PluginDescription, visited: MutableMap<PluginDescription, Mark>,
                          sorted: MutableList<PluginDescription>, currentDependencyScanStack: Deque<PluginDescription>) {
        val mark = visited.getOrDefault(current, Mark.NOT_VISITED)
        if (mark == Mark.VISITED) return // Already visited this node, nothing to do
        if (mark == Mark.VISITING) {
            // A circular dependency has been detected. (Specifically, if we are visiting any dependency and a dependency we are
            // looking at depends on any dependency being visited, we have a circular dependency, thus we do not have a directed
            // acyclic graph and therefore no topological sort is possible).
            currentDependencyScanStack.addLast(current)
            error("Circular dependency detected: ${currentDependencyScanStack.joinToString(" -> ") { it.id }}")
        }

        currentDependencyScanStack.addLast(current)
        visited.put(current, Mark.VISITING)
        graph.successors(current).forEach { visitNode(graph, it, visited, sorted, currentDependencyScanStack) }
        visited.put(current, Mark.VISITED)
        currentDependencyScanStack.removeLast()
        sorted.add(current)
    }

    private enum class Mark {

        NOT_VISITED,
        VISITING,
        VISITED
    }
}
