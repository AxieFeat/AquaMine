package net.aquamine.server.packet

/**
 * Represents one of the four packet states in the Minecraft protocol. Each one of
 * these states has different packets and different purposes.
 */
enum class PacketState {

    /**
     * In the handshake state, the client informs the server of their intentions.
     */
    HANDSHAKE,

    /**
     * In the status state, the client requests information about the server, such
     * as the current player count, max player count, ping and MOTD.
     */
    STATUS,

    /**
     * In the login state, the client attempts to login to the server, and this
     * state establishes an encrypted connection with the client (when the server
     * is in online mode). The server will also set a compression threshold for
     * packet compression, if the threshold set in the config is > 0.
     */
    LOGIN,

    /**
     * Client acknowledged login and is now configuring the game.
     * Can also go back to configuration from play.
     */
    CONFIGURATION,

    /**
     * The vast majority of packets that exist in the protocol are in the play state.
     * This state indicates that the client is playing the game.
     */
    PLAY
}
