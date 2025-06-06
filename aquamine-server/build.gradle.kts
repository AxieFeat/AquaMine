plugins {
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    api(projects.aquamineApi)

    implementation("io.klogging:klogging-jvm:0.10.1")
}

setupDetekt()