package net.aquamine.api.entity.player

import net.kyori.adventure.text.Component
import net.aquamine.api.effect.particle.ParticleEffect
import net.aquamine.api.entity.Equipable
import net.aquamine.api.entity.LivingEntity
import net.aquamine.api.event.player.action.PlayerStartGlidingEvent
import net.aquamine.api.event.player.action.PlayerStopGlidingEvent
import net.aquamine.api.inventory.Inventory
import net.aquamine.api.inventory.PlayerInventory
import net.aquamine.api.plugin.PluginMessageRecipient
import net.aquamine.api.resource.ResourcePack
import net.aquamine.api.util.Direction
import net.aquamine.api.statistic.StatisticsTracker
import net.aquamine.api.scoreboard.Scoreboard
import net.aquamine.api.user.BaseUser
import net.aquamine.api.util.Vec3d
import net.aquamine.api.world.GameMode
import java.net.SocketAddress

/**
 * A player that is connected to the server and playing the game.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Player : LivingEntity, Equipable, PluginMessageRecipient, BaseUser {

    /**
     * The address that the player is currently connected from.
     */
    val address: SocketAddress

    /**
     * If this player can fly.
     */
    @get:JvmName("canFly")
    var canFly: Boolean

    /**
     * If this player can build (place/break blocks).
     */
    @get:JvmName("canBuild")
    var canBuild: Boolean

    /**
     * If this player can place and break blocks with no delay.
     */
    @get:JvmName("canInstantlyBuild")
    var canInstantlyBuild: Boolean

    /**
     * The current speed at which this player can walk at.
     */
    var walkingSpeed: Float

    /**
     * The current speed at which this player can fly at.
     */
    var flyingSpeed: Float

    /**
     * If this player is currently flying.
     */
    var isFlying: Boolean

    /**
     * The settings for the player.
     */
    val settings: PlayerSettings

    /**
     * This player's current game mode.
     */
    var gameMode: GameMode

    /**
     * The direction this player is currently facing.
     */
    val facing: Direction

    /**
     * The scoreboard currently being shown to this player.
     */
    val scoreboard: Scoreboard

    /**
     * The tab list for this player.
     */
    val tabList: TabList

    /**
     * The inventory of this player.
     *
     * This holds information on all the items that are currently held by
     * this player.
     */
    val inventory: PlayerInventory

    /**
     * The inventory that this player currently has open.
     */
    var openInventory: Inventory?

    /**
     * The food level of this player.
     */
    var foodLevel: Int

    /**
     * The food exhaustion level of this player.
     */
    var foodExhaustionLevel: Float

    /**
     * The food saturation level of this player.
     */
    var foodSaturationLevel: Float

    /**
     * The statistics tracker for this player.
     */
    val statisticsTracker: StatisticsTracker

    /**
     * The cooldown tracker for this player.
     */
    val itemCooldownTracker: CooldownTracker

    /**
     * The current ping of this player.
     */
    val ping: Int

    /**
     * Spawns particles for this player relative to a location.
     *
     * @param effect The particle effect used to describe the particles'
     * appearance.
     * @param location The central location to spawn the particles at.
     */
    fun spawnParticles(effect: ParticleEffect, location: Vec3d)

    /**
     * Sends the given resource [pack] to this player.
     *
     * @param pack The resource pack.
     */
    fun sendResourcePack(pack: ResourcePack)

    /**
     * Shows the given [scoreboard] to this player, hiding the old scoreboard
     * this player was viewing from them.
     *
     * @param scoreboard The scoreboard to show.
     */
    fun showScoreboard(scoreboard: Scoreboard)

    /**
     * Resets the scoreboard shown to the player back to the default one.
     */
    fun resetScoreboard()

    /**
     * Makes this player start gliding (elytra flying).
     *
     * This may fire the [PlayerStartGlidingEvent], which may cause this
     * action to get denied, meaning the player will keep their previous
     * gliding status.
     *
     * @return Whether the request to start gliding was approved, or it was
     * denied by the result of calling the event.
     */
    fun startGliding(): Boolean

    /**
     * Makes this player stop gliding (elytra flying).
     *
     * This may fire the [PlayerStopGlidingEvent], which may cause this action
     * to get denied, meaning the player will keep their previous gliding
     * status.
     *
     * @return Whether the request to stop gliding was approved, or it was
     * denied by the result of calling the event.
     */
    fun stopGliding(): Boolean

    /**
     * Kicks the player with the given [text] shown.
     */
    fun disconnect(text: Component)
}
