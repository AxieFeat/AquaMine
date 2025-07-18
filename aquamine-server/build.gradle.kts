import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.withType

plugins {
    id("io.gitlab.arturbosch.detekt")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    api(projects.aquamineApi)
    api(projects.aquaminePluginAnnotationProcessor)

    api(libs.kotlinGuice)
    api(libs.fastutil)
    api(libs.nbt)
    api(libs.caffeine)
    api(libs.jline)
    api(libs.tca)
    api(libs.hydrazine)
    api(libs.flareFastutil)
    api(libs.jctools)
    api(libs.commonsLang)
    api(libs.reflections)
    api(libs.clikt)

    api(libs.configurate.core)
    api(libs.configurate.extraKotlin)
    api(libs.configurate.gson)
    api(libs.configurate.hocon)
    api(libs.configurate.yaml)

    api(libs.kotlinx.coroutines)
    api(libs.kotlinx.collections)

    api(libs.log4j.api)
    api(libs.log4j.core)

    api(libs.serialization)
    api(libs.serialization.gson)
    api(libs.serialization.nbt)

    api(libs.adventure.serializer.gson)
    api(libs.adventure.serializer.plain)
    api(libs.adventure.serializer.legacy)
    api(libs.adventure.serializer.configurate)
    api(libs.adventure.minimessage)
}

tasks.withType<ShadowJar> {
    archiveFileName.set("aquamine.jar")

    manifest {
        attributes["Main-Class"] = "net.aquamine.server.AquaMineKt"
        attributes["Multi-Release"] = true
    }
}

setupDetekt()