package net.aquamine.api.block.meta

enum class VaultState(name: String) {

    INACTIVE("inactive"),
    ACTIVE("active"),
    UNLOCKING("unlocking"),
    EJECTING("ejecting");
}
