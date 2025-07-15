package net.aquamine.server.util

import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Function

object Futures {

    @JvmStatic
    fun <V> sequenceFailFast(futures: List<CompletableFuture<out V>>): CompletableFuture<List<V>> {
        val result = CompletableFuture<List<V>>()
        return fallibleSequence(futures) { result.completeExceptionally(it) }.applyToEither(result, Function.identity())
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <V> fallibleSequence(futures: List<CompletableFuture<out V>>, consumer: Consumer<Throwable>): CompletableFuture<List<V>> {
        val result = ArrayList<V?>(futures.size)
        val resultFutures = arrayOfNulls<CompletableFuture<*>>(futures.size)
        futures.forEach {
            val size = result.size
            result.add(null)
            resultFutures[size] = it.whenComplete { value, exception ->
                if (exception != null) consumer.accept(exception) else result[size] = value
            }
        }
        return NoSpread.completableFutureAllOf(resultFutures).thenApply { result as ArrayList<V> }
    }
}
