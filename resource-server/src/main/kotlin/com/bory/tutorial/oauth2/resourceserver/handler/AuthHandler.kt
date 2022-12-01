package com.bory.tutorial.oauth2.resourceserver.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.kotlin.core.publisher.toMono

@Component
class AuthHandler {
  fun handleAuth(serverRequest: ServerRequest) =
      ok().body("auth world".toMono())
}