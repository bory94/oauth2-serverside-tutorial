package com.bory.tutorial.oauth2.authorizationserver.model

import org.springframework.http.HttpMethod
import javax.persistence.*

@Entity
data class AuthorizedRequest(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var seq: Long?,
    @Enumerated(EnumType.STRING)
    var httpMethod: HttpMethod? = HttpMethod.GET,
    var uriPattern: String?,
    var authorities: String?,
    val sort: Int? = 0
) {
  constructor() : this(-1, HttpMethod.GET, null, null, 0)
}

data class AuthorizedRequestDto(
    var seq: Long?,
    var httpMethod: HttpMethod? = HttpMethod.GET,
    var uriPattern: String?,
    var authorities: String?,
    val sort: Int? = 0
) {
  constructor() : this(-1, HttpMethod.GET, null, null, 0)
}

@Entity
data class OauthClientDetails(
    @Id
    var clientId: String?,
    var resourceIds: String?,
    var clientSecret: String?,
    var scope: String?,
    var authorizedGrantTypes: String?,
    var webServerRedirectUri: String?,
    var authorities: String?,
    var accessTokenValidity: Int?,
    var refreshTokenValidity: Int?,
    var additionalInformation: String?,
    var autoapprove: String?
) {
  constructor() : this(null, null, null, null,
      null, null, null, -1,
      -1, null, null)
}
