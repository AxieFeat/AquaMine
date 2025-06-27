package net.aquamine.server.event

import net.aquamine.api.event.Event
import net.aquamine.api.event.EventListener
import net.aquamine.api.event.type.DeniableEvent
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import java.util.function.Predicate

interface AquaEventListener {

    class Builder<T : Event>(private val eventType: Class<T>) : EventListener.Builder<T> {

        private val filters = ArrayList<Predicate<T>>()
        private var ignoreDenied = true
        private var expireCount = 0
        private var expireWhen: Predicate<T>? = null
        private var handler: Consumer<T>? = null

        override fun addFilter(filter: Predicate<T>): EventListener.Builder<T> = apply { filters.add(filter) }

        override fun ignoreDenied(ignore: Boolean): EventListener.Builder<T> = apply { ignoreDenied = ignore }

        override fun expireCount(count: Int): EventListener.Builder<T> = apply { expireCount = count }

        override fun expireWhen(condition: Predicate<T>): EventListener.Builder<T> = apply { expireWhen = condition }

        override fun handler(handler: Consumer<T>): EventListener.Builder<T> = apply { this.handler = handler }

        override fun build(): EventListener<T> {
            val expirationCount = AtomicInteger(expireCount)
            val hasExpirationCount = expirationCount.get() > 0
            val expireWhen = expireWhen
            val filters = ArrayList(filters)
            val handler = handler
            return object : EventListener<T> {
                override val eventType: Class<T>
                    get() = this@Builder.eventType

                override fun run(event: T): EventListener.Result {
                    // Deniable events
                    if (ignoreDenied && event is DeniableEvent && !event.isAllowed()) return EventListener.Result.INVALID
                    // Expiration predicate
                    if (expireWhen != null && expireWhen.test(event)) return EventListener.Result.EXPIRED
                    // Filtering
                    if (filters.isNotEmpty()) {
                        filters.forEach { filter -> if (!filter.test(event)) return EventListener.Result.INVALID }
                    }
                    // Handler
                    handler?.accept(event)
                    // Expiration count
                    if (hasExpirationCount && expirationCount.decrementAndGet() == 0) return EventListener.Result.EXPIRED
                    return EventListener.Result.SUCCESS
                }
            }
        }
    }

    object Factory : EventListener.Factory {

        override fun <T : Event> builder(eventType: Class<T>): EventListener.Builder<T> = Builder(eventType)
    }
}
