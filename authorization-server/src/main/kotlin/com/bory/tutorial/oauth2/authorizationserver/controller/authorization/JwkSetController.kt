package com.bory.tutorial.oauth2.authorizationserver.controller.authorization

import com.nimbusds.jose.jwk.JWKSet
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JwkSetController(private val jwkSet: JWKSet) {
  @GetMapping("/.well-known/jwks.json")
  fun keys() = jwkSet.toJSONObject()!!
}