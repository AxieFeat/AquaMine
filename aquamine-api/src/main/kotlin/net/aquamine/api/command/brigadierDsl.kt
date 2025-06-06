package net.aquamine.api.command

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import net.aquamine.annotations.dsl.BrigadierDsl
import org.jetbrains.annotations.Contract

/**
 * Creates a new Brigadier literal argument builder with the given [name],
 * applying the given [builder] to it and returning the result.
 *
 * @param S The command source type.
 * @param name The name of the literal.
 * @param builder The builder to apply.
 *
 * @return A new literal argument builder.
 */
@Contract("_, _ -> new", pure = true)
@JvmSynthetic
@BrigadierDsl
inline fun <S> literalCommand(name: String, builder: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> {
    return LiteralArgumentBuilder.literal<S>(name).apply(builder)
}

/**
 * Adds a new literal command node with the given name to this literal,
 * applying the given [builder] to the node and returning this builder as the
 * result.
 *
 * @param S The command source type.
 * @param name The name of the literal.
 * @param builder The builder to apply.
 *
 * @return This builder.
 */
@Contract("_, _ -> this", mutates = "this")
@JvmSynthetic
@BrigadierDsl
inline fun <S> LiteralArgumentBuilder<S>.literal(name: String, builder: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> {
    return then(LiteralArgumentBuilder.literal<S>(name).apply(builder))
}

/**
 * Adds a new argument command node with the given [name] and [type] to this
 * literal, applying the given [builder] to the node and returning this
 * builder as the result.
 *
 * @param S The command source type.
 * @param T The argument type.
 * @param name The name of the argument.
 * @param type The argument type for parsing.
 * @param builder The builder to apply.
 *
 * @return This builder.
 */
@Contract("_, _, _ -> this", mutates = "this")
@JvmSynthetic
@BrigadierDsl
inline fun <S, T> LiteralArgumentBuilder<S>.argument(
    name: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<S, T>.() -> Unit
): LiteralArgumentBuilder<S> {
    return then(RequiredArgumentBuilder.argument<S, T>(name, type).apply(builder))
}

/**
 * Adds a new argument command node with the given [name] and [type] to this
 * argument, applying the given [builder] to the node and returning this
 * builder as the result.
 *
 * @param S The command source type.
 * @param T The argument type of this argument.
 * @param T1 The argument type of the new argument.
 * @param name The name of the argument.
 * @param type The argument type for parsing.
 * @param builder The builder to apply.
 *
 * @return This builder.
 */
@Contract("_, _, _ -> this", mutates = "this")
@JvmSynthetic
@BrigadierDsl
inline fun <S, T, T1> RequiredArgumentBuilder<S, T>.argument(
    name: String,
    type: ArgumentType<T1>,
    builder: RequiredArgumentBuilder<S, T1>.() -> Unit
): RequiredArgumentBuilder<S, T> {
    return then(RequiredArgumentBuilder.argument<S, T1>(name, type).apply(builder))
}
