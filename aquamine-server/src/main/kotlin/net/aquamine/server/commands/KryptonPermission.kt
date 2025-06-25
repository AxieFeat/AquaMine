package net.aquamine.server.commands

/**
 * All of the built-in permissions used by Krypton's internals.
 */
enum class KryptonPermission(val node: String) {

    BAN("krypton.command.ban"),
    BAN_IP("krypton.command.banip"),
    CLEAR("krypton.command.clear"),
    DIFFICULTY("krypton.command.difficulty"),
    GAME_MODE("krypton.command.gamemode"),
    GAME_RULE("krypton.command.gamerule"),
    GIVE("krypton.command.give"),
    KICK("krypton.command.kick"),
    LIST("krypton.command.list"),
    ME("krypton.command.me"),
    MESSAGE("krypton.command.message"),
    PARDON("krypton.command.pardon"),
    PARDON_IP("krypton.command.pardonip"),
    RESTART("krypton.command.restart"),
    SAY("krypton.command.say"),
    SEED("krypton.command.seed"),
    STOP("krypton.command.stop"),
    SUMMON("krypton.command.summon"),
    TELEPORT("krypton.command.teleport"),
    TITLE("krypton.command.title"),
    WHITELIST("krypton.command.whitelist"),
    USE_GAME_MASTER_BLOCKS("krypton.feature.game-master-blocks"),
    ENTITY_QUERY("krypton.data.query.entity"),
    BYPASS_SPAWN_PROTECTION("krypton.bypass-spawn-protection"),
    BROADCAST_ADMIN("krypton.broadcast_admin");
}
