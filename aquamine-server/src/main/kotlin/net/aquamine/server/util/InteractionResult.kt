package net.aquamine.server.util

enum class InteractionResult {

    SUCCESS,
    CONSUME,
    CONSUME_PARTIAL,
    FAIL,
    PASS;

    fun consumesAction(): Boolean = this == SUCCESS || this == CONSUME || this == CONSUME_PARTIAL
}
