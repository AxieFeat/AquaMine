@file:JvmSynthetic

package net.aquamine.api.world.biome

import net.aquamine.annotations.dsl.BiomeDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.sound.SoundEvent

/**
 * Creates a new biome by applying the given [builder] to a new builder, then
 * building the result.
 *
 * @param builder The builder.
 *
 * @return A new biome.
 */
@BiomeDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
inline fun biome(builder: Biome.Builder.() -> Unit): Biome = Biome.builder().apply(builder).build()

/**
 * Applies the given [builder] to a new climate builder and sets the
 * climate to the built instance.
 *
 * @param builder The builder.
 *
 * @return This builder.
 */
@BiomeDsl
@JvmSynthetic
@Contract("_ -> this", mutates = "this")
inline fun Biome.Builder.climate(builder: Climate.Builder.() -> Unit): Biome.Builder =
    climate(Climate.builder().apply(builder).build())

/**
 * Applies the given [builder] to a new effects settings builder and
 * sets the effect settings to the built instance.
 *
 * @param builder The builder.
 *
 * @return This builder.
 */
@BiomeDsl
@JvmSynthetic
@Contract("_ -> this", mutates = "this")
inline fun Biome.Builder.effects(builder: BiomeEffects.Builder.() -> Unit): Biome.Builder =
    effects(BiomeEffects.builder().apply(builder).build())

/**
 * Applies the given [builder] to an ambient particle settings builder,
 * builds the result, and sets the ambient particle settings to the
 * built result.
 *
 * @param type The type.
 * @param builder The builder.
 *
 * @return This builder.
 *
 * @see BiomeEffects.ambientParticleSettings
 */
@BiomeDsl
@JvmSynthetic
@Contract("_, _ -> this", mutates = "this")
inline fun BiomeEffects.Builder.particles(
    type: ParticleType,
    builder: AmbientParticleSettings.Builder.() -> Unit
): BiomeEffects.Builder =
    particles(AmbientParticleSettings.builder(type).apply(builder).build())

/**
 * Applies the given [builder] to an ambient mood settings builder,
 * builds the result, and sets the ambient mood settings to the
 * built result.
 *
 * @param sound The sound.
 * @param builder The builder.
 *
 * @return This builder.
 *
 * @see BiomeEffects.ambientMoodSettings
 */
@BiomeDsl
@JvmSynthetic
@Contract("_, _ -> this", mutates = "this")
inline fun BiomeEffects.Builder.mood(
    sound: SoundEvent,
    builder: AmbientMoodSettings.Builder.() -> Unit
): BiomeEffects.Builder =
    mood(AmbientMoodSettings.builder(sound).apply(builder).build())

/**
 * Applies the given [builder] to an ambient addition settings
 * builder, builds the result, and sets the ambient additions settings
 * to the built result.
 *
 * @param sound The sound.
 * @param builder The builder.
 *
 * @return This builder.
 *
 * @see BiomeEffects.ambientAdditionsSettings
 */
@BiomeDsl
@JvmSynthetic
@Contract("_, _ -> this", mutates = "this")
inline fun BiomeEffects.Builder.additions(
    sound: SoundEvent,
    builder: AmbientAdditionsSettings.Builder.() -> Unit
): BiomeEffects.Builder =
    additions(AmbientAdditionsSettings.builder(sound).apply(builder).build())