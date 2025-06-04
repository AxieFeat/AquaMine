plugins {
    kotlin("jvm") version "2.0.21"
    `java-library`
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.java-library")

    group = "net.aquamine"
    version = "1.0"

    repositories {
        mavenCentral()
    }

    kotlin {
        jvmToolchain(21)
    }
}