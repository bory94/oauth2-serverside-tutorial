package com.bory.tutorial.oauth2.resourceclient.handler

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body

@Component
class AuthHandler(
    @Qualifier("oauth2WebClient") private val oauth2WebClient: WebClient,
    @Value("\${webclient.resource-server-auth-uri}") private val authUri: String
) {
  fun requestAuth(serverRequest: ServerRequest) =
      oauth2WebClient.get()
          .uri(authUri)
          .retrieve()
          .bodyToMono<String>()
          .let { ok().body(it) }
}