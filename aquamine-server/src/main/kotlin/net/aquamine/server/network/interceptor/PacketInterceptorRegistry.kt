package net.aquamine.server.network.interceptor

object PacketInterceptorRegistry {

    private val interceptors = ArrayList<PacketInterceptor>()

    fun register(interceptor: PacketInterceptor) {
        interceptors.add(interceptor)
    }

    fun unregister(interceptor: PacketInterceptor) {
        interceptors.remove(interceptor)
    }

    fun all(): List<PacketInterceptor> = interceptors
}
