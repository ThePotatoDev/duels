plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "duels"
include("services")
include("services:players-svc")
findProject(":services:players-svc")?.name = "players-svc"
include("servers")
include("servers:lobby")
findProject(":servers:lobby")?.name = "lobby"
include("servers:duel")
findProject(":servers:duel")?.name = "duel"
include("plugins")
include("plugins:core")
findProject(":plugins:core")?.name = "core"
include("servers:proxy")
findProject(":servers:proxy")?.name = "proxy"
