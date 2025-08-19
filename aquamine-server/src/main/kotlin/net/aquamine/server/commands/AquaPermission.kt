package net.aquamine.server.commands

/**
 * All of the built-in permissions used by AquaMine's internals.
 */
enum class AquaPermission(val node: String) {

    BAN("aquamine.command.ban"),
    BAN_IP("aquamine.command.banip"),
    CLEAR("aquamine.command.clear"),
    DIFFICULTY("aquamine.command.difficulty"),
    GAME_MODE("aquamine.command.gamemode"),
    GAME_RULE("aquamine.command.gamerule"),
    GIVE("aquamine.command.give"),
    KICK("aquamine.command.kick"),
    LIST("aquamine.command.list"),
    ME("aquamine.command.me"),
    MESSAGE("aquamine.command.message"),
    PARDON("aquamine.command.pardon"),
    PARDON_IP("aquamine.command.pardonip"),
    RESTART("aquamine.command.restart"),
    SAY("aquamine.command.say"),
    SEED("aquamine.command.seed"),
    STOP("aquamine.command.stop"),
    SUMMON("aquamine.command.summon"),
    TELEPORT("aquamine.command.teleport"),
    TITLE("aquamine.command.title"),
    WHITELIST("aquamine.command.whitelist"),
    USE_GAME_MASTER_BLOCKS("aquamine.feature.game-master-blocks"),
    ENTITY_QUERY("aquamine.data.query.entity"),
    BYPASS_SPAWN_PROTECTION("aquamine.bypass-spawn-protection"),
    BROADCAST_ADMIN("aquamine.broadcast_admin"),
    EFFECT("aquamine.effect");
}
