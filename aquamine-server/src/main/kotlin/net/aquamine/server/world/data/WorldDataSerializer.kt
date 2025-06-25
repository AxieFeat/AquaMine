package net.aquamine.server.world.data

interface WorldDataSerializer {

    fun load(name: String): WorldData?

    fun save(name: String, data: WorldData)
}
