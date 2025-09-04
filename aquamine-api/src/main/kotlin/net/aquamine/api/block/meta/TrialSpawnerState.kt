package net.aquamine.api.block.meta

enum class TrialSpawnerState(name: String) {

    INACTIVE("inactive"),
    WAITING_FOR_PLAYERS("waiting_for_players"),
    ACTIVE("active"),
    WAITING_FOR_REWARD_EJECTION("waiting_for_reward_ejection"),
    EJECTING_REWARD("ejecting_reward"),
    COOLDOWN("cooldown");
}
