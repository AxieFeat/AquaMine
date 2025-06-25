package net.aquamine.server.util

import net.kyori.adventure.key.Key
import net.aquamine.server.util.serialization.Codecs
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder
import java.util.Optional
import java.util.function.Predicate
import java.util.regex.Pattern

class KeyPattern(private val namespacePattern: Pattern?, private val valuePattern: Pattern?) {

    private val namespacePredicate = namespacePattern?.asPredicate() ?: Predicate { true }
    private val valuePredicate = valuePattern?.asPredicate() ?: Predicate { true }
    private val locationPredicate = Predicate<Key> { namespacePredicate.test(it.namespace()) && valuePredicate.test(it.value()) }

    fun namespacePredicate(): Predicate<String> = namespacePredicate

    fun valuePredicate(): Predicate<String> = valuePredicate

    fun locationPredicate(): Predicate<Key> = locationPredicate

    companion object {

        @JvmField
        val CODEC: Codec<KeyPattern> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codecs.PATTERN.optionalFieldOf("namespace").getting { Optional.ofNullable(it.namespacePattern) },
                Codecs.PATTERN.optionalFieldOf("path").getting { Optional.ofNullable(it.valuePattern) }
            ).apply(instance) { namespace, value -> KeyPattern(namespace.orElse(null), value.orElse(null)) }
        }
    }
}
