import org.gradle.kotlin.dsl.credentials

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AquaMine"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://libraries.minecraft.net")
        maven("https://maven.pkg.github.com/AxieFeat/Arc") {
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
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
        gradlePluginPortal()
        maven("https://repo.spongepowered.org/repository/maven-public/")
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
project("client")

project("plugins")

plugin("tablist")
plugin("spark")

fun project(projectName: String) {
    include("${rootProject.name.lowercase()}-$projectName")
}

fun plugin(pluginName: String) {
    include("${rootProject.name.lowercase()}-plugins:$pluginName")
}