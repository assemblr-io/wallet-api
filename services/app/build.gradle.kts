val postgres_version: String by project
val ktor_version: String by project
val kotlinx_version: String by project

plugins{
    application
    standardKotlinJvmModule()
}

dependencies {
    implementation(project(":services:domain"))
    implementation(project(":services:database"))
    implementation(project(":services:web"))
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.8")
}

application {
    mainClass.set("vgw.wallets.lnd.ApplicationKt")
}
