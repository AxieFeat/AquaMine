@file:JvmSynthetic
package net.aquamine.api.effect.particle

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.effect.particle.builder.BaseParticleEffectBuilder
import net.aquamine.api.registry.RegistryReference

/**
 * Creates a new particle effect with the given [type] and the result of
 * applying the given [builder].
 *
 * @param B The builder type.
 * @param T The particle type.
 * @param type The type.
 * @param builder The builder to apply.
 *
 * @return A new particle effect.
 */
@ParticleDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun <B : Base<B>, T : Scoped<B>> particleEffect(type: T, builder: B.() -> Unit): ParticleEffect =
    type.builder().apply(builder).build()

/**
 * Creates a new particle effect with the given [type] and the result of
 * applying the given [builder].
 *
 * @param B The builder type.
 * @param T The particle type.
 * @param type The type.
 * @param builder The builder to apply.
 *
 * @return A new particle effect.
 */
@ParticleDsl
@JvmSynthetic
@Contract("_, _ -> new", pure = true)
inline fun <B : Base<B>, T : Scoped<B>> particleEffect(type: RegistryReference<T>, builder: B.() -> Unit): ParticleEffect =
    type.get().builder().apply(builder).build()

private typealias Base<B> = BaseParticleEffectBuilder<B>
private typealias Scoped<B> = ScopedParticleType<B>
