package com.bory.tutorial.oauth2.resourceserver.router

import com.bory.tutorial.oauth2.resourceserver.handler.IndexHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class IndexRouters(private val indexHandler: IndexHandler) {
  @Bean
  fun indexRouter() = router {
    GET("/", indexHandler::handleIndex)
    GET("/whoami", indexHandler::handleWhoAmI)
  }
}