package net.aquamine.api.scoreboard.criteria

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in criteria for scoreboards.
 */
@Catalogue(KeyedCriterion::class)
object Criteria {

    // @formatter:off
    @JvmField val DUMMY: RegistryReference<KeyedCriterion> = of("dummy")
    @JvmField val TRIGGER: RegistryReference<KeyedCriterion> = of("trigger")
    @JvmField val DEATH_COUNT: RegistryReference<KeyedCriterion> = of("death_count")
    @JvmField val PLAYER_KILL_COUNT: RegistryReference<KeyedCriterion> = of("player_kill_count")
    @JvmField val TOTAL_KILL_COUNT: RegistryReference<KeyedCriterion> = of("total_kill_count")
    @JvmField val HEALTH: RegistryReference<KeyedCriterion> = of("health")
    @JvmField val FOOD: RegistryReference<KeyedCriterion> = of("food")
    @JvmField val AIR: RegistryReference<KeyedCriterion> = of("air")
    @JvmField val ARMOR: RegistryReference<KeyedCriterion> = of("armor")
    @JvmField val EXPERIENCE: RegistryReference<KeyedCriterion> = of("experience")
    @JvmField val LEVEL: RegistryReference<KeyedCriterion> = of("level")
    @JvmField val TEAM_KILL_BLACK: RegistryReference<KeyedCriterion> = of("team_kill_black")
    @JvmField val TEAM_KILL_DARK_BLUE: RegistryReference<KeyedCriterion> = of("team_kill_dark_blue")
    @JvmField val TEAM_KILL_DARK_GREEN: RegistryReference<KeyedCriterion> = of("team_kill_dark_green")
    @JvmField val TEAM_KILL_DARK_AQUA: RegistryReference<KeyedCriterion> = of("team_kill_dark_aqua")
    @JvmField val TEAM_KILL_DARK_RED: RegistryReference<KeyedCriterion> = of("team_kill_dark_red")
    @JvmField val TEAM_KILL_DARK_PURPLE: RegistryReference<KeyedCriterion> = of("team_kill_dark_purple")
    @JvmField val TEAM_KILL_GOLD: RegistryReference<KeyedCriterion> = of("team_kill_gold")
    @JvmField val TEAM_KILL_GRAY: RegistryReference<KeyedCriterion> = of("team_kill_gray")
    @JvmField val TEAM_KILL_DARK_GRAY: RegistryReference<KeyedCriterion> = of("team_kill_dark_gray")
    @JvmField val TEAM_KILL_BLUE: RegistryReference<KeyedCriterion> = of("team_kill_blue")
    @JvmField val TEAM_KILL_GREEN: RegistryReference<KeyedCriterion> = of("team_kill_green")
    @JvmField val TEAM_KILL_AQUA: RegistryReference<KeyedCriterion> = of("team_kill_aqua")
    @JvmField val TEAM_KILL_RED: RegistryReference<KeyedCriterion> = of("team_kill_red")
    @JvmField val TEAM_KILL_LIGHT_PURPLE: RegistryReference<KeyedCriterion> = of("team_kill_light_purple")
    @JvmField val TEAM_KILL_YELLOW: RegistryReference<KeyedCriterion> = of("team_kill_yellow")
    @JvmField val TEAM_KILL_WHITE: RegistryReference<KeyedCriterion> = of("team_kill_white")
    @JvmField val KILLED_BY_TEAM_BLACK: RegistryReference<KeyedCriterion> = of("killed_by_team_black")
    @JvmField val KILLED_BY_TEAM_DARK_BLUE: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_blue")
    @JvmField val KILLED_BY_TEAM_DARK_GREEN: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_green")
    @JvmField val KILLED_BY_TEAM_DARK_AQUA: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_aqua")
    @JvmField val KILLED_BY_TEAM_DARK_RED: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_red")
    @JvmField val KILLED_BY_TEAM_DARK_PURPLE: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_purple")
    @JvmField val KILLED_BY_TEAM_GOLD: RegistryReference<KeyedCriterion> = of("killed_by_team_gold")
    @JvmField val KILLED_BY_TEAM_GRAY: RegistryReference<KeyedCriterion> = of("killed_by_team_gray")
    @JvmField val KILLED_BY_TEAM_DARK_GRAY: RegistryReference<KeyedCriterion> = of("killed_by_team_dark_gray")
    @JvmField val KILLED_BY_TEAM_BLUE: RegistryReference<KeyedCriterion> = of("killed_by_team_blue")
    @JvmField val KILLED_BY_TEAM_GREEN: RegistryReference<KeyedCriterion> = of("killed_by_team_green")
    @JvmField val KILLED_BY_TEAM_AQUA: RegistryReference<KeyedCriterion> = of("killed_by_team_aqua")
    @JvmField val KILLED_BY_TEAM_RED: RegistryReference<KeyedCriterion> = of("killed_by_team_red")
    @JvmField val KILLED_BY_TEAM_LIGHT_PURPLE: RegistryReference<KeyedCriterion> = of("killed_by_team_light_purple")
    @JvmField val KILLED_BY_TEAM_YELLOW: RegistryReference<KeyedCriterion> = of("killed_by_team_yellow")
    @JvmField val KILLED_BY_TEAM_WHITE: RegistryReference<KeyedCriterion> = of("killed_by_team_white")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<KeyedCriterion> = RegistryReference.of(Registries.CRITERIA, Key.key("aquamine", name))
}
