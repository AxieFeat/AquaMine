package net.aquamine.server.world.components

import net.aquamine.api.util.Vec3i
import net.aquamine.server.coordinate.SectionPos

interface HeightAccessor {

    fun height(): Int

    fun minimumBuildHeight(): Int

    fun maximumBuildHeight(): Int = minimumBuildHeight() + height()

    fun minimumSection(): Int = SectionPos.blockToSection(minimumBuildHeight())

    fun maximumSection(): Int = SectionPos.blockToSection(maximumBuildHeight() - 1) + 1

    fun sectionCount(): Int = maximumSection() - minimumSection()

    fun getSectionIndex(y: Int): Int = getSectionIndexFromSectionY(SectionPos.blockToSection(y))

    fun getSectionIndexFromSectionY(y: Int): Int = y - minimumSection()

    fun getSectionYFromSectionIndex(index: Int): Int = index + minimumSection()

    fun isOutsideBuildHeight(pos: Vec3i): Boolean = isOutsideBuildHeight(pos.y)

    fun isOutsideBuildHeight(y: Int): Boolean = y < minimumBuildHeight() || y >= maximumBuildHeight()
}
