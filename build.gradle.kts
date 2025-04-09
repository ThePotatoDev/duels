plugins {
    application
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}

allprojects {
    apply(plugin = "kotlin")

    group = "gg.tater"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }

    dependencies {
        api(project(":"))

        implementation("io.ktor:ktor-client-core:3.1.2")
        implementation("io.ktor:ktor-client-cio:3.1.2")
        implementation("io.ktor:ktor-client-content-negotiation:3.1.2")
        implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.2")
        implementation("io.ktor:ktor-client-logging:3.1.2")

        // Exposed and Database Drivers
        implementation("org.jetbrains.exposed:exposed-core:0.44.0")
        implementation("org.jetbrains.exposed:exposed-dao:0.44.0")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.44.0")
        implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.44.0")

        // Database Driver (PostgreSQL example)
        implementation("org.postgresql:postgresql:42.7.5")

        // Connection Pooling
        implementation("com.zaxxer:HikariCP:6.3.0")

        compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    }

    kotlin {
        jvmToolchain(21)
    }
}
