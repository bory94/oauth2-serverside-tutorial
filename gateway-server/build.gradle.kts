plugins {
  id("org.springframework.boot")

  kotlin("jvm")
  kotlin("plugin.spring")
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

  implementation(kotlin("reflect"))
  implementation(kotlin("stdlib-jdk8"))

  implementation("org.springframework.boot:spring-boot-starter-webflux")

  implementation("org.springframework.cloud:spring-cloud-starter")
  implementation("org.springframework.cloud:spring-cloud-starter-gateway")
  implementation("org.springframework.cloud:spring-cloud-starter-config")

  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
  implementation("com.github.dalet-oss:mongobee:1.0.4")

  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

  developmentOnly("org.springframework.boot:spring-boot-devtools")

  testImplementation("io.projectreactor:reactor-test")
}
