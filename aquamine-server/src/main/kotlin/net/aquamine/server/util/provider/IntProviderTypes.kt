package net.aquamine.server.util.provider

import net.kyori.adventure.key.Key
import net.aquamine.server.registry.KryptonRegistries
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.DataResult
import org.kryptonmc.util.Either
import java.util.function.Function

object IntProviderTypes {

    // We put the XMAPs here because otherwise we get a compilation error. See https://youtrack.jetbrains.com/issue/KT-53478
    @JvmField
    val CONSTANT: IntProviderType<ConstantInt> = register("constant", ConstantInt.CODEC
        .xmap({ either -> either.map({ ConstantInt.of(it) }, Function.identity()) }, { Either.left(it.value) }))
    @JvmField
    val UNIFORM: IntProviderType<UniformInt> = register("uniform", UniformInt.CODEC.comapFlatMap({
        if (it.maximumValue >= it.minimumValue) return@comapFlatMap DataResult.success(it)
        DataResult.error("Maximum must be >= minimum! Maximum: ${it.maximumValue}, minimum: ${it.minimumValue}")
    }, Function.identity()))

    @JvmStatic
    private fun <P : IntProvider> register(name: String, codec: Codec<P>): IntProviderType<P> =
        KryptonRegistries.register(KryptonRegistries.INT_PROVIDER_TYPE, Key.key(name), IntProviderType { codec })
}
