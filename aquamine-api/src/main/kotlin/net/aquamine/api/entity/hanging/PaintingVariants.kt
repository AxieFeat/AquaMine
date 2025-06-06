package net.aquamine.api.entity.hanging

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in vanilla painting variants.
 */
@Catalogue(PaintingVariant::class)
object PaintingVariants {

    // @formatter:off
    @JvmField val KEBAB: RegistryReference<PaintingVariant> = get("kebab")
    @JvmField val AZTEC: RegistryReference<PaintingVariant> = get("aztec")
    @JvmField val ALBAN: RegistryReference<PaintingVariant> = get("alban")
    @JvmField val AZTEC2: RegistryReference<PaintingVariant> = get("aztec2")
    @JvmField val BOMB: RegistryReference<PaintingVariant> = get("bomb")
    @JvmField val PLANT: RegistryReference<PaintingVariant> = get("plant")
    @JvmField val WASTELAND: RegistryReference<PaintingVariant> = get("wasteland")
    @JvmField val POOL: RegistryReference<PaintingVariant> = get("pool")
    @JvmField val COURBET: RegistryReference<PaintingVariant> = get("courbet")
    @JvmField val SEA: RegistryReference<PaintingVariant> = get("sea")
    @JvmField val SUNSET: RegistryReference<PaintingVariant> = get("sunset")
    @JvmField val CREEBET: RegistryReference<PaintingVariant> = get("creebet")
    @JvmField val WANDERER: RegistryReference<PaintingVariant> = get("wanderer")
    @JvmField val GRAHAM: RegistryReference<PaintingVariant> = get("graham")
    @JvmField val MATCH: RegistryReference<PaintingVariant> = get("match")
    @JvmField val BUST: RegistryReference<PaintingVariant> = get("bust")
    @JvmField val STAGE: RegistryReference<PaintingVariant> = get("stage")
    @JvmField val VOID: RegistryReference<PaintingVariant> = get("void")
    @JvmField val SKULL_AND_ROSES: RegistryReference<PaintingVariant> = get("skull_and_roses")
    @JvmField val WITHER: RegistryReference<PaintingVariant> = get("wither")
    @JvmField val FIGHTERS: RegistryReference<PaintingVariant> = get("fighters")
    @JvmField val POINTER: RegistryReference<PaintingVariant> = get("pointer")
    @JvmField val PIGSCENE: RegistryReference<PaintingVariant> = get("pigscene")
    @JvmField val BURNING_SKULL: RegistryReference<PaintingVariant> = get("burning_skull")
    @JvmField val SKELETON: RegistryReference<PaintingVariant> = get("skeleton")
    @JvmField val DONKEY_KONG: RegistryReference<PaintingVariant> = get("donkey_kong")

    // @formatter:on
    @JvmStatic
    private fun get(name: String): RegistryReference<PaintingVariant> = RegistryReference.of(Registries.PAINTING_VARIANT, Key.key(name))
}
