package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import net.aquamine.api.command.argument
import net.aquamine.api.command.literal
import net.aquamine.api.command.literalCommand
import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.potionEffect
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.PotionEffectArgumentType
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.potion.downcast

// TODO Refactor, remove using of PotionEffectArgumentType and fix "infinity" literal.
object EffectCommand {

    private const val TARGETS = "targets"
    private const val EFFECT = "effect"
    private const val SECONDS = "seconds"
    private const val AMPLIFIER = "amplifier"
    private const val HIDE_PARTICLES = "hideParticles"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("effect") {
            requiresPermission(AquaPermission.EFFECT)

            literal("clear") {
                argument(TARGETS, EntityArgumentType.players()) {
                    runs { removeEffects(it.source, EntityArgumentType.getPlayers(it, TARGETS)) }

                    argument(EFFECT, PotionEffectArgumentType) {
                        runs {
                            removeEffects(
                            it.source,
                            EntityArgumentType.getPlayers(it, TARGETS),
                            PotionEffectArgumentType.get(it, EFFECT)
                        ) }
                    }
                }
            }
            literal("give") {
                argument(TARGETS, EntityArgumentType.players()) {
                    argument(EFFECT, PotionEffectArgumentType) {

                        runs {
                            giveEffect(
                                it.source,
                                EntityArgumentType.getPlayers(it, TARGETS),
                                potionEffect {
                                    type(PotionEffectArgumentType.get(it, EFFECT))
                                    duration(30 * 20)
                                }
                            )
                        }

                        argument(SECONDS, IntegerArgumentType.integer(1, 1000000)) {

                            runs {
                                giveEffect(
                                    it.source,
                                    EntityArgumentType.getPlayers(it, TARGETS),
                                    potionEffect {
                                        type(PotionEffectArgumentType.get(it, EFFECT))
                                        duration(IntegerArgumentType.getInteger(it, SECONDS) * 20)
                                    }
                                )
                            }

                            argument(AMPLIFIER, IntegerArgumentType.integer(1, 255)) {

                                runs {
                                    giveEffect(
                                        it.source,
                                        EntityArgumentType.getPlayers(it, TARGETS),
                                        potionEffect {
                                            type(PotionEffectArgumentType.get(it, EFFECT))
                                            amplifier(IntegerArgumentType.getInteger(it, AMPLIFIER).toByte())
                                            duration(IntegerArgumentType.getInteger(it, SECONDS) * 20)
                                        }
                                    )
                                }

                                argument(HIDE_PARTICLES, BoolArgumentType.bool()) {
                                    runs {
                                        giveEffect(
                                            it.source,
                                            EntityArgumentType.getPlayers(it, TARGETS),
                                            potionEffect {
                                                type(PotionEffectArgumentType.get(it, EFFECT))
                                                amplifier(IntegerArgumentType.getInteger(it, AMPLIFIER).toByte())
                                                duration(IntegerArgumentType.getInteger(it, SECONDS) * 20)
                                                particles(BoolArgumentType.getBool(it, HIDE_PARTICLES))
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        literal("infinite") {

                            runs {
                                giveEffect(
                                    it.source,
                                    EntityArgumentType.getPlayers(it, TARGETS),
                                    potionEffect {
                                        type(PotionEffectArgumentType.get(it, EFFECT))
                                        duration(-1)
                                    }
                                )
                            }

                            argument(AMPLIFIER, IntegerArgumentType.integer(1, 255)) {

                                runs {
                                    giveEffect(
                                        it.source,
                                        EntityArgumentType.getPlayers(it, TARGETS),
                                        potionEffect {
                                            type(PotionEffectArgumentType.get(it, EFFECT))
                                            amplifier(IntegerArgumentType.getInteger(it, AMPLIFIER).toByte())
                                            duration(-1)
                                        }
                                    )
                                }

                                argument(HIDE_PARTICLES, BoolArgumentType.bool()) {
                                    runs {
                                        giveEffect(
                                            it.source,
                                            EntityArgumentType.getPlayers(it, TARGETS),
                                            potionEffect {
                                                type(PotionEffectArgumentType.get(it, EFFECT))
                                                amplifier(IntegerArgumentType.getInteger(it, AMPLIFIER).toByte())
                                                duration(-1)
                                                particles(BoolArgumentType.getBool(it, HIDE_PARTICLES))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    @JvmStatic
    private fun removeEffects(source: CommandSourceStack, targets: Collection<AquaPlayer>, effect: AquaPotionType? = null) {
        when {
            effect == null -> when {
                targets.count() == 1 ->
                    CommandMessages.EFFECT_CLEAR_EVERYTHING_SINGLE.sendSuccess(source, targets.first().profile, true)

                else ->
                    CommandMessages.EFFECT_CLEAR_EVERYTHING_MULTIPLE.sendSuccess(source, targets.count(), true)
            }.also { targets.forEach(AquaLivingEntity::clearPotionEffects) }

            else -> when {
                targets.count() == 1 ->
                    CommandMessages.EFFECT_CLEAR_SPECIFIC_SINGLE.sendSuccess(source, effect, targets.first().profile, true)

                else ->
                    CommandMessages.EFFECT_CLEAR_SPECIFIC_MULTIPLE.sendSuccess(source, effect, targets.count(), true)
            }.also { targets.forEach { it.removePotionEffect(effect) } }
        }
    }

    @JvmStatic
    private fun giveEffect(source: CommandSourceStack, targets: Collection<AquaPlayer>, effect: PotionEffect) {
        val effect = effect.downcast()

        when {
            targets.count() == 1 ->
                CommandMessages.EFFECT_GIVE_SINGLE.sendSuccess(source, effect.type, targets.first().profile, true)


            else ->
                CommandMessages.EFFECT_GIVE_MULTIPLE.sendSuccess(source, effect.type, targets.count(), true)
        }

        targets.forEach { it.addPotionEffect(effect) }
    }
}