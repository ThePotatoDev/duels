subprojects {
    dependencies {
        implementation("io.ktor:ktor-server-core:3.1.2")
        implementation("io.ktor:ktor-server-netty:3.1.2")
        implementation("io.ktor:ktor-server-content-negotiation:3.1.2")
        implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.2")
        implementation("io.github.cdimascio:dotenv-java:3.0.0")
    }
}