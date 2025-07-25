plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

publishing {
    publications {
        // TODO
        create<MavenPublication>("maven") {
            groupId = "net.aquamine"
            artifactId = project.name
            version = "1.0"

            from(components["kotlin"])
        }
    }
}