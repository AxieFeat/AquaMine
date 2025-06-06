import org.spongepowered.gradle.vanilla.repository.MinecraftPlatform

plugins {
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
}

dependencies {
    implementation(libs.kotlinpoet)
}

minecraft {
    version(global.versions.minecraft.get())
    platform(MinecraftPlatform.SERVER)
}