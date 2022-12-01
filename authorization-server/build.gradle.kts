plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
}

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR4")
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":common-jpa"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.2.6.RELEASE")
    implementation("com.nimbusds:nimbus-jose-jwt:8.15")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.3.0")

    implementation("org.mapstruct:mapstruct:1.3.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.3.1.Final")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
