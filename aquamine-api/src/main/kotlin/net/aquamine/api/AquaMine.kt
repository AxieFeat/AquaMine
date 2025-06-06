package net.aquamine.api

import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.util.FactoryProvider

internal object AquaMine {

    // Implementation note: These need to be set reflectively.
    @JvmStatic
    private var factoryProvider: FactoryProvider? = null
    @JvmStatic
    private var staticRegistryHolder: RegistryHolder? = null

    @JvmStatic
    @JvmSynthetic
    internal fun staticRegistryHolder(): RegistryHolder = staticRegistryHolder!!

    @JvmStatic
    @JvmSynthetic
    internal inline fun <reified T> factory(): T = factoryProvider!!.provide(T::class.java)
}
