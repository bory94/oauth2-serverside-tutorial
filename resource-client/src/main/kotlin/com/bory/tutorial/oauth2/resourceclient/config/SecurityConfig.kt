package com.bory.tutorial.oauth2.resourceclient.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfig {
  @Bean
  fun passwordEncoder() = BCryptPasswordEncoder()

  @Bean
  fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
      http
          .authorizeExchange()
          .anyExchange().permitAll()
          .and()
          .build()
}