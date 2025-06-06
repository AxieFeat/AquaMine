sourceSets.main {
    kotlin.srcDir("src/generated/kotlin")
}

dependencies {
    api(projects.aquamineAnnotations)

    api(libs.adventure.api)
    api(libs.brigadier)
    api(libs.guice)
}