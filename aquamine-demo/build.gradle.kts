import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("aquamine.jar")

    manifest {
        attributes["Main-Class"] = "net.aquamine.demo.MainKt"
    }
}