package com.bory.tutorial.oauth2.authorizationserver.config

import com.bory.tutorial.oauth2.authorizationserver.service.authentication.AuthUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authUserDetailsService: AuthUserDetailsService
) : WebSecurityConfigurerAdapter() {

  @Bean
  fun passwordEncoder() = BCryptPasswordEncoder()

  override fun configure(http: HttpSecurity) {
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/").permitAll()
        .antMatchers("/oauth/**", "/.well-known/jwks.json").permitAll()
        .antMatchers("/v*/**").access("@authorizationChecker.check(httpServletRequest, authentication)")
        .anyRequest().authenticated()
        .and()
        .formLogin(withDefaults())
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(authUserDetailsService)
  }

  @Bean
  override fun authenticationManagerBean(): AuthenticationManager {
    return super.authenticationManagerBean()
  }

}