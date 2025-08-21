plugins {
    id("com.gradleup.shadow")
}

repositories {
    mavenCentral()
    maven("https://repo.lucko.me/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(projects.aquamineApi)
    compileOnly(libs.log4j.api)
    compileOnly(libs.gson)
    compileOnly(libs.configurate.hocon)
    compileOnly(libs.bundles.adventure)

    implementation("me.lucko:bytesocks-java-client:1.0-SNAPSHOT")
    implementation("me.lucko:spark-common:1.10.134-SNAPSHOT") {
        exclude("me.lucko", "bytesocks-java-client")
    }
}
