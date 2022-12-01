package com.bory.tutorial.oauth2.resourceclient.router

import com.bory.tutorial.oauth2.resourceclient.handler.AdminHandler
import com.bory.tutorial.oauth2.resourceclient.handler.AuthHandler
import com.bory.tutorial.oauth2.resourceclient.handler.IndexHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class ResourceClientRouter(
    private val indexHandler: IndexHandler,
    private val authHandler: AuthHandler,
    private val adminHandler: AdminHandler
) {

  @Bean
  fun apiRouter() = nest(
      path("/api"),
      router {
        GET("/", indexHandler::requestIndex)
        GET("/auth", authHandler::requestAuth)
        GET("/admin", adminHandler::requestAdmin)
      }
  )
}
