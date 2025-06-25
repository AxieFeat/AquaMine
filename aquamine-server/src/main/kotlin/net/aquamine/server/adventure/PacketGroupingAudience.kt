package net.aquamine.server.adventure

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.ForwardingAudience
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.chat.ChatType
import net.kyori.adventure.chat.SignedMessage
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.effect.sound.KryptonSoundEvent
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.network.chat.MessageSignature
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.PacketOutSetActionBarText
import net.aquamine.server.packet.out.play.PacketOutClearTitles
import net.aquamine.server.packet.out.play.PacketOutEntitySoundEffect
import net.aquamine.server.packet.out.play.PacketOutDeleteChat
import net.aquamine.server.packet.out.play.PacketOutDisguisedChat
import net.aquamine.server.packet.out.play.PacketOutSetTabListHeaderAndFooter
import net.aquamine.server.packet.out.play.PacketOutSoundEffect
import net.aquamine.server.packet.out.play.PacketOutStopSound
import net.aquamine.server.packet.out.play.PacketOutSetSubtitleText
import net.aquamine.server.packet.out.play.PacketOutSetTitleText
import net.aquamine.server.packet.out.play.PacketOutSetTitleAnimationTimes
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.holder.Holder
import kotlin.random.Random

fun interface PacketGroupingAudience : ForwardingAudience {

    fun players(): Collection<KryptonPlayer>

    fun generateSoundSeed(): Long {
        return Random.nextLong()
    }

    private fun sendGroupedPacket(packet: Packet) {
        PacketGrouping.sendGroupedPacket(players(), packet)
    }

    override fun deleteMessage(signature: SignedMessage.Signature) {
        sendGroupedPacket(PacketOutDeleteChat(MessageSignature.Packed(MessageSignature(signature.bytes()))))
    }

    override fun sendMessage(signedMessage: SignedMessage, boundChatType: ChatType.Bound) {
        val message = signedMessage.unsignedContent() ?: Component.text(signedMessage.message())
        if (signedMessage.isSystem) {
            sendMessage(message, boundChatType)
            return
        }
        // TODO: Add support for signed player messages
    }

    override fun sendMessage(message: Component) {
        sendGroupedPacket(PacketOutSystemChat(message, false))
    }

    override fun sendMessage(message: Component, boundChatType: ChatType.Bound) {
        val type = RichChatType.Bound.from(boundChatType).toNetwork()
        PacketGrouping.sendGroupedPacket(players(), PacketOutDisguisedChat(message, type)) { it.acceptsChatMessages() }
    }

    override fun sendActionBar(message: Component) {
        sendGroupedPacket(PacketOutSetActionBarText(message))
    }

    override fun sendPlayerListHeader(header: Component) {
        sendPlayerListHeaderAndFooter(header, Component.empty())
    }

    override fun sendPlayerListFooter(footer: Component) {
        sendPlayerListHeaderAndFooter(Component.empty(), footer)
    }

    override fun sendPlayerListHeaderAndFooter(header: Component, footer: Component) {
        sendGroupedPacket(PacketOutSetTabListHeaderAndFooter(header, footer))
    }

    override fun <T : Any> sendTitlePart(part: TitlePart<T>, value: T) {
        if (part === TitlePart.TITLE) sendGroupedPacket(PacketOutSetTitleText(value as Component))
        if (part === TitlePart.SUBTITLE) sendGroupedPacket(PacketOutSetSubtitleText(value as Component))
        if (part === TitlePart.TIMES) sendGroupedPacket(PacketOutSetTitleAnimationTimes.fromTimes(value as Title.Times))
        throw IllegalArgumentException("Unknown TitlePart")
    }

    override fun clearTitle() {
        sendGroupedPacket(PacketOutClearTitles(false))
    }

    override fun resetTitle() {
        sendGroupedPacket(PacketOutClearTitles(true))
    }

    override fun showBossBar(bar: BossBar) {
        BossBarManager.addBar(bar, this)
    }

    override fun hideBossBar(bar: BossBar) {
        BossBarManager.removeBar(bar, this)
    }

    override fun playSound(sound: Sound, x: Double, y: Double, z: Double) {
        val seed = sound.seed().orElseGet { generateSoundSeed() }
        val event = getSoundEventHolder(sound)
        val packet = PacketOutSoundEffect.create(event, sound.source(), x, y, z, sound.volume(), sound.pitch(), seed)
        sendGroupedPacket(packet)
    }

    override fun playSound(sound: Sound, emitter: Sound.Emitter) {
        if (emitter !== Sound.Emitter.self()) {
            val entity = if (emitter is KryptonEntity) emitter else error("Sound emitter must be an entity or self(), was $emitter!")
            val seed = sound.seed().orElseGet { generateSoundSeed() }
            val event = getSoundEventHolder(sound)
            sendGroupedPacket(PacketOutEntitySoundEffect(event, sound.source(), entity.id, sound.volume(), sound.pitch(), seed))
            return
        }
        // If we're playing on self, we need to delegate to each audience member
        super.playSound(sound, emitter)
    }

    private fun getSoundEventHolder(sound: Sound): Holder<SoundEvent> {
        val name = sound.name()
        val event = KryptonRegistries.SOUND_EVENT.get(name)
        return if (event != null) KryptonRegistries.SOUND_EVENT.wrapAsHolder(event) else Holder.Direct(KryptonSoundEvent.createVariableRange(name))
    }

    override fun stopSound(stop: SoundStop) {
        sendGroupedPacket(PacketOutStopSound.create(stop))
    }

    override fun openBook(book: Book) {
        val item = AquaAdventure.toItemStack(book)
        players().forEach { it.openBook(item) }
    }

    override fun audiences(): Iterable<Audience> = players()
}
