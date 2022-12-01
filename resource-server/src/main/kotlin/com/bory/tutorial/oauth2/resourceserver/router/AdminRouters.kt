package com.bory.tutorial.oauth2.resourceserver.router

import com.bory.tutorial.oauth2.resourceserver.handler.AdminHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class AdminRouters(private val adminHandler: AdminHandler) {
  @Bean
  fun adminRouter() = nest(
      path("/v1/admin"),
      router {
        GET("/", adminHandler::handleAdmin)
      }
  )
}