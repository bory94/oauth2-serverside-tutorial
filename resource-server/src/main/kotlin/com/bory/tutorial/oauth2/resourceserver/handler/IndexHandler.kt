package com.bory.tutorial.oauth2.resourceserver.handler

import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.kotlin.core.publisher.toMono

@Component
class IndexHandler {
  fun handleWhoAmI(request: ServerRequest) =
      ReactiveSecurityContextHolder.getContext()
          .flatMap { context ->
            ok().body(context.toMono())
          }

  fun handleIndex(request: ServerRequest) =
      ok().body("hello world".toMono())
}