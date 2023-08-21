val h2_version: String by project

val postgres_version: String by project
val ktor_version: String by project
val kotlinx_version: String by project

plugins{
    standardKotlinJvmModule()
    id("io.ktor.plugin") version "2.3.1"
}

dependencies {
    implementation(project(":services:domain"))
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.h2database:h2:$h2_version")
  //  testImplementation(project(":services:web"))
}

application {
    mainClass.set("ApplicationKt")
}
