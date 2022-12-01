import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false

plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    implementation(project(":common"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.mapstruct:mapstruct:1.3.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.3.1.Final")
}
