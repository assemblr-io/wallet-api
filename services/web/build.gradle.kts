

val kotlinx_version: String by project
val ktor_version: String by project
val kotlin_version: String by project

plugins{
    standardKotlinJvmModule()
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

repositories{
    mavenCentral()
}

dependencies{
    implementation("io.ktor:ktor-server-jetty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation(project(":services:domain"))
    implementation(project(":services:database"))
}
