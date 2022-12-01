package com.bory.tutorial.oauth2.resourceserver.router

import com.bory.tutorial.oauth2.resourceserver.handler.AuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class AuthRouters(private val authHandler: AuthHandler) {
  @Bean
  fun authRouter() = nest(
      path("/v1/auth"),
      router {
        GET("/", authHandler::handleAuth)
      }
  )
}