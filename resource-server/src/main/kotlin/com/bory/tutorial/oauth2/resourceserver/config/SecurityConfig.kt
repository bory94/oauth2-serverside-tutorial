package com.bory.tutorial.oauth2.resourceserver.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer.withDefaults
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
          .pathMatchers("/v1/auth/**").authenticated()
          .pathMatchers("/v1/admin/**").hasAuthority("SCOPE_ADMIN")
          .anyExchange().permitAll()
          .and()
          .oauth2ResourceServer()
          .jwt(withDefaults())
          .and()
          .build()
}
