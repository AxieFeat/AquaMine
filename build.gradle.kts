plugins {
    kotlin("jvm") version "2.1.20"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "net.aquamine"
    version = "1.0"

    repositories {
        mavenCentral()
    }

    kotlin {
        jvmToolchain(21)
    }
}