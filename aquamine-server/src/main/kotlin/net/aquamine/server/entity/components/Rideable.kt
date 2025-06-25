package net.aquamine.server.entity.components

import net.aquamine.api.entity.Entity
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.Pose
import net.aquamine.server.entity.util.downcastBase
import net.aquamine.server.entity.system.EntityVehicleSystem

interface Rideable : BaseDataHolder {

    val vehicleSystem: EntityVehicleSystem

    override val passengers: List<Entity>
        get() = vehicleSystem.passengers()
    override var vehicle: Entity?
        get() = vehicleSystem.vehicle()
        set(value) = vehicleSystem.setVehicle(value)

    override fun isPassenger(): Boolean = vehicleSystem.isPassenger()

    override fun isVehicle(): Boolean = vehicleSystem.isVehicle()

    fun canAddPassenger(passenger: KryptonEntity): Boolean = vehicleSystem.passengers().isEmpty()

    fun startRiding(vehicle: KryptonEntity): Boolean = startRiding(vehicle, false)

    fun startRiding(vehicle: KryptonEntity, force: Boolean): Boolean {
        if (vehicle === vehicleSystem.vehicle()) return false
        var rootVehicle = vehicle
        while (rootVehicle.vehicleSystem.vehicle() != null) {
            if (rootVehicle.vehicleSystem.vehicle() === this) return false
            rootVehicle = rootVehicle.vehicleSystem.vehicle()!!
        }
        if (force || canRide(vehicle) && vehicle.canAddPassenger(this as KryptonEntity)) {
            if (vehicleSystem.isPassenger()) stopRiding()
            pose = Pose.STANDING
            vehicleSystem.setVehicle(vehicle)
            vehicle.vehicleSystem.addPassenger(this as KryptonEntity)
            return true
        }
        return false
    }

    fun stopRiding() {
        vehicleSystem.ejectVehicle()
    }

    fun canRide(vehicle: KryptonEntity): Boolean = !isSneaking

    override fun addPassenger(entity: Entity) {
        vehicleSystem.addPassenger(entity.downcastBase())
    }

    override fun removePassenger(entity: Entity) {
        vehicleSystem.removePassenger(entity.downcastBase())
    }

    override fun ejectPassengers() {
        vehicleSystem.ejectPassengers()
    }

    override fun ejectVehicle() {
        vehicleSystem.ejectVehicle()
    }
}
