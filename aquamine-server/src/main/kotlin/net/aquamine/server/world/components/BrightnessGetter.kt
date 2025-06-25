package net.aquamine.server.world.components

import net.aquamine.api.util.Vec3i

// TODO: Revise when lighting engine is implemented
interface BrightnessGetter : BlockGetter {

    fun skyDarken(): Int

    fun getSkyBrightness(x: Int, y: Int, z: Int): Int = 0

    fun getSkyBrightness(pos: Vec3i): Int = getSkyBrightness(pos.x, pos.y, pos.z)

    fun getBlockBrightness(x: Int, y: Int, z: Int): Int = 0

    fun getBlockBrightness(pos: Vec3i): Int = getBlockBrightness(pos.x, pos.y, pos.z)

    fun canSeeSky(x: Int, y: Int, z: Int): Boolean = getSkyBrightness(x, y, z) >= maximumLightLevel()

    fun canSeeSky(pos: Vec3i): Boolean = canSeeSky(pos.x, pos.y, pos.z)
}
