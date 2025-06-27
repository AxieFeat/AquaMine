package net.aquamine.server.entity.system

import net.aquamine.api.entity.Entity
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.util.downcastBase
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.out.play.PacketOutSetPassengers
import java.util.Collections
import java.util.stream.Stream

class EntityVehicleSystem(private val entity: AquaEntity) {

    private val passengers = ArrayList<AquaEntity>()
    private val passengersView = Collections.unmodifiableList(passengers)
    private var vehicle: AquaEntity? = null

    fun isVehicle(): Boolean = passengers.isNotEmpty()

    fun isPassenger(): Boolean = vehicle != null

    fun passengers(): List<AquaEntity> = passengersView

    fun rootVehicle(): AquaEntity {
        var root: AquaEntity = entity
        while (root.vehicleSystem.isPassenger()) {
            root = root.vehicleSystem.vehicle!!
        }
        return root
    }

    fun vehicle(): AquaEntity? = vehicle

    fun setVehicle(vehicle: Entity?) {
        this.vehicle = vehicle?.downcastBase()
    }

    private fun hasPassenger(passenger: AquaEntity): Boolean = passengers.contains(passenger)

    fun addPassenger(passenger: AquaEntity) {
        if (hasPassenger(passenger) || passenger.vehicleSystem.hasPassenger(entity)) return
        passenger.vehicleSystem.setVehicle(entity)
        passengers.add(passenger)
        updatePassengers()
    }

    fun removePassenger(passenger: AquaEntity) {
        if (passenger.vehicleSystem.vehicle === entity || !hasPassenger(passenger)) return
        passenger.vehicleSystem.setVehicle(null)
        passengers.remove(passenger)
        updatePassengers()
    }

    fun ejectPassengers() {
        passengers.forEach { it.vehicleSystem.ejectVehicle() }
    }

    fun ejectVehicle() {
        if (vehicle != null) {
            val vehicle = vehicle!!
            this.vehicle = null
            vehicle.removePassenger(entity)
        }
    }

    fun hasExactlyOnePlayerPassenger(): Boolean = getIndirectPassengersStream().filter { it is AquaPlayer }.count() == 1L

    private fun getSelfAndPassengers(): Stream<AquaEntity> = Stream.concat(Stream.of(entity), getIndirectPassengersStream())

    private fun getIndirectPassengersStream(): Stream<AquaEntity> = passengers.stream().flatMap { it.vehicleSystem.getSelfAndPassengers() }

    private fun updatePassengers() {
        PacketGrouping.sendGroupedPacket(entity.server, PacketOutSetPassengers.fromEntity(entity, passengers))
    }
}
