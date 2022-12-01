package com.bory.tutorial.oauth2.authorizationserver.config

import com.bory.tutorial.oauth2.authorizationserver.config.jwt.JwtOAuthHeaderAccessTokenConverter
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
@Import(SecurityConfig::class)
class AuthorizationServerConfig(
    private val dataSource: DataSource,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder) : AuthorizationServerConfigurerAdapter() {

  companion object {
    const val OAUTH_KEY_ID = "jwt-oauth-key-id"
    const val OAUTH_KEY_FILENAME = "my-oauth-jwt.jks"
    const val OAUTH_KEY_PASSWORD = "password"
    const val OAUTH_KEY_ALIAS = "my-oauth-jwt"
  }

  @Bean
  fun keyPair(): KeyPair =
      KeyStoreKeyFactory(ClassPathResource(OAUTH_KEY_FILENAME), OAUTH_KEY_PASSWORD.toCharArray())
          .getKeyPair(OAUTH_KEY_ALIAS)

  @Bean
  fun accessTokenConverter() =
      JwtOAuthHeaderAccessTokenConverter(mapOf("kid" to OAUTH_KEY_ID), keyPair())

  @Bean
  fun tokenStore() = JwtTokenStore(accessTokenConverter())

  @Bean
  fun tokenServices(): DefaultTokenServices = DefaultTokenServices().apply {
    setTokenStore(tokenStore())
  }

  @Bean
  fun jwkSet() = JWKSet(
      RSAKey.Builder(keyPair().public as RSAPublicKey)
          .keyUse(KeyUse.SIGNATURE)
          .algorithm(JWSAlgorithm.RS256)
          .keyID(OAUTH_KEY_ID)
          .build()
  )

  override fun configure(security: AuthorizationServerSecurityConfigurer) {
    security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients()
  }

  override fun configure(clients: ClientDetailsServiceConfigurer) {
    clients.jdbc(dataSource)
        .passwordEncoder(passwordEncoder)
  }

  override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
    endpoints.tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter())
        .authenticationManager(authenticationManager)
  }
}