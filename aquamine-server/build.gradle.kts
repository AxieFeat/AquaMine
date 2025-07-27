import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.withType

plugins {
    id("io.gitlab.arturbosch.detekt")
    id("com.gradleup.shadow")
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

    api(libs.bundles.configurate)

    api(libs.kotlinx.coroutines)
    api(libs.kotlinx.collections)

    api(libs.log4j.api)
    api(libs.log4j.core)

    api(libs.bundles.serialization)

    api(libs.bundles.adventure)
}

tasks.withType<ShadowJar> {
    archiveFileName.set("aquamine.jar")

    manifest {
        attributes["Main-Class"] = "net.aquamine.server.AquaMineKt"
        attributes["Multi-Release"] = true
    }
}

setupDetekt()