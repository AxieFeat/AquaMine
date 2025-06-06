import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("aquamine.jar")

    manifest {
        attributes["Main-Class"] = "net.aquamine.demo.MainKt"
    }
}