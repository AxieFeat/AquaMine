plugins {
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
}

sourceSets.main {
    kotlin.srcDir("src/generated/kotlin")
}

dependencies {
    api(projects.aquamineAnnotations)

    api(libs.adventure.api)
    api(libs.brigadier)
    api(libs.guice)

    ksp(projects.aquamineAnnotationProcessor)
}

setupDetekt()
setupPublishing()