package net.aquamine.server.server

import com.mojang.brigadier.exceptions.CommandSyntaxException
import org.apache.logging.log4j.LogManager
import org.jetbrains.annotations.VisibleForTesting
import net.aquamine.api.registry.Registries
import net.aquamine.api.tags.BannerPatternTags
import net.aquamine.api.tags.BiomeTags
import net.aquamine.api.tags.BlockTags
import net.aquamine.api.tags.EntityTypeTags
import net.aquamine.api.tags.FluidTags
import net.aquamine.api.tags.ItemTags
import net.aquamine.server.command.BrigadierExceptions
import net.aquamine.server.command.argument.ArgumentSerializers
import net.aquamine.server.entity.EntityFactory
import net.aquamine.server.item.ItemManager
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.tags.GameEventTags
import net.aquamine.server.util.KryptonFactoryProvider
import net.aquamine.server.util.Reflection
import net.aquamine.server.locale.MinecraftTranslationManager
import net.aquamine.server.registry.KryptonDynamicRegistries
import net.aquamine.server.util.crypto.Encryption
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.rule.GameRuleKeys
import net.aquamine.server.world.rule.WorldGameRules
import java.util.TreeSet
import java.util.function.Function

object Bootstrap {

    private val LOGGER = LogManager.getLogger()
    @Volatile
    private var bootstrapped = false
    private val kryptonClass by lazy { Class.forName("net.aquamine.api.AquaMine") }

    // Might be better if you turn away from this now, unless you're making something that is registry-based,
    // in which, good luck. It probably needs to be placed somewhere with its dependencies before it and its
    // dependents after it.
    @JvmStatic
    fun preload() {
        if (bootstrapped) return
        bootstrapped = true
        preloadTranslations()
        preloadFactories()
        preloadRegistries()
        preloadOtherClasses()
    }

    @JvmStatic
    @VisibleForTesting
    fun preloadTranslations() {
        MinecraftTranslationManager.init()
    }

    // Preload the factory stuff
    // These are some kinda nasty hacks, but the tight coupling nature of Krypton requires it
    // The only 2 other alternatives here are to do away with the static singleton and revert back, or restructure
    // the entire project to use Guice's dependency inversion (something that should be looked in to at some point)
    @JvmStatic
    @VisibleForTesting
    fun preloadFactories() {
        Reflection.modifyStaticField(kryptonClass, "factoryProvider", KryptonFactoryProvider)
        KryptonFactoryProvider.bootstrap()
    }

    // Preload all the registry classes to ensure everything is properly registered
    @JvmStatic
    @VisibleForTesting
    fun preloadRegistries() {
        Reflection.modifyStaticField(kryptonClass, "staticRegistryHolder", KryptonRegistries.StaticHolder)
        KryptonRegistries.bootstrap()
        KryptonDynamicRegistries.bootstrap()
        KryptonBlocks.initializeStates()
        Registries
        BlockTags
        EntityTypeTags
        FluidTags
        GameEventTags
        ItemTags
        BannerPatternTags
        BiomeTags
    }

    // Preload some other things that would otherwise load on first player join or some other time
    @JvmStatic
    @VisibleForTesting
    fun preloadOtherClasses() {
        Encryption
        ArgumentSerializers.bootstrap()
        EntityFactory
        ItemManager.bootstrap()
        CommandSyntaxException.BUILT_IN_EXCEPTIONS = BrigadierExceptions
    }

    @JvmStatic
    fun validate() {
        require(bootstrapped) { "Bootstrap wasn't ran!" }
        collectMissingTranslations().forEach { LOGGER.warn("Missing translation: $it") }
    }

    @JvmStatic
    private fun collectMissingTranslations(): Set<String> {
        val missing = TreeSet<String>()
        checkTranslations(KryptonRegistries.ATTRIBUTE, { it.translationKey() }, missing)
        checkTranslations(KryptonRegistries.ENTITY_TYPE, { it.translationKey() }, missing)
        checkTranslations(KryptonRegistries.ITEM, { it.translationKey() }, missing)
        checkTranslations(KryptonRegistries.BLOCK, { it.translationKey() }, missing)
        checkTranslations(KryptonRegistries.CUSTOM_STATISTIC, { "stat.${it.asString().replace(':', '.')}" }, missing)
        GameRuleKeys.visitTypes(object : WorldGameRules.TypeVisitor {
            override fun <T : WorldGameRules.Value<T>> visit(key: WorldGameRules.Key<T>, type: WorldGameRules.Type<T>) {
                if (!MinecraftTranslationManager.REGISTRY.contains(key.translationKey())) missing.add(key.id)
            }
        })
        return missing
    }

    @JvmStatic
    private fun <T> checkTranslations(values: Iterable<T>, keyGetter: Function<T, String>, missing: MutableSet<String>) {
        values.forEach {
            val key = keyGetter.apply(it)
            if (!MinecraftTranslationManager.REGISTRY.contains(key)) missing.add(key)
        }
    }
}
