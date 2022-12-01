package com.bory.tutorial.oauth2.authorizationserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
      "com.bory.tutorial.oauth2.common",
      "com.bory.tutorial.oauth2.authorizationserver"
    ]
)
class AuthorizationServerApplication

fun main(args: Array<String>) {
  runApplication<AuthorizationServerApplication>(*args)
}
