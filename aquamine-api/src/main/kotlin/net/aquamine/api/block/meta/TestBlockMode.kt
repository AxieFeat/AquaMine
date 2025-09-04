package net.aquamine.api.block.meta

enum class TestBlockMode(id: Int, name: String) {

    START(0, "start"),
    LOG(1, "log"),
    FAIL(2, "fail"),
    ACCEPT(3, "accept");
}
