package net.aquamine.api.event

/**
 * The root node of the server's event node tree.
 *
 * This is used to distinguish it from regular event nodes, to allow it to be
 * injected in to plugins that want to inject it in the same way as the event
 * manager could be injected.
 */
interface GlobalEventNode : EventNode<Event>
