package com.bory.tutorial.oauth2.authorizationserver.config.jwt

import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.RsaSigner
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.util.JsonParserFactory
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey

class JwtOAuthHeaderAccessTokenConverter(
    private val customHeaders: Map<String, String>,
    keyPair: KeyPair) : JwtAccessTokenConverter() {
  private var signer: RsaSigner

  private val jsonParser = JsonParserFactory.create()

  init {
    super.setKeyPair(keyPair)
    this.signer = RsaSigner(keyPair.private as RSAPrivateKey)
  }

  override fun encode(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): String =
      jsonParser.formatMap(accessTokenConverter.convertAccessToken(accessToken, authentication))
          .run {
            JwtHelper.encode(this, signer, customHeaders).encoded
          }
}
