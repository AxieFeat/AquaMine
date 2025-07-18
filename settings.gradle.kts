enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AquaMine"

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")
        maven("https://libraries.minecraft.net")
    }
    versionCatalogs {
        create("global") {
            from(files("gradle/global.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("build-logic")

    repositories {
        maven("https://repo.spongepowered.org/repository/maven-public/")
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

project("api")
project("server")
project("annotations")
project("annotation-processor")
project("plugin-annotation-processor")
project("generator")
project("demo")

project("plugins")

plugin("tablist")
plugin("spark")

fun project(projectName: String) {
    include("${rootProject.name.lowercase()}-$projectName")
}

fun plugin(pluginName: String) {
    include("${rootProject.name.lowercase()}-plugins:$pluginName")
}