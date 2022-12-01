package com.bory.tutorial.oauth2.authorizationserver.config

import com.bory.tutorial.oauth2.authorizationserver.service.authorization.AuthorizedRequestService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import javax.servlet.http.HttpServletRequest

@Component
class AuthorizationChecker(private val authorizedRequestService: AuthorizedRequestService) {
  fun check(request: HttpServletRequest, authentication: Authentication): Boolean {
    val authorizedRequests = authorizedRequestService.findAll()

    try {
      val currentAuthority = authorizedRequests
          .filter { it.httpMethod.toString() == request.method }
          .first { AntPathMatcher().match(it.uriPattern!!, request.requestURI) }

      // TODO anonymous user를 체크하는 방법이 이것 밖에 없나? 확인 필요
      if (currentAuthority.authorities == "authenticated") {
        return authentication.principal !is String
      }

      val currentAuthorities = currentAuthority.authorities!!.split(",")
      return (currentAuthorities intersect authentication.authorities.map { it.authority }).isNotEmpty()
    } catch (e: NoSuchElementException) {
      return true
    }
  }
}
