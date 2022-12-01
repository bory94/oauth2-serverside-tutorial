package com.bory.tutorial.oauth2.authorizationserver.service.authentication

import com.bory.tutorial.oauth2.common.jpa.repository.authentication.AuthorityRepository
import com.bory.tutorial.oauth2.common.jpa.repository.authentication.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthUserDetailsService(
    private val userRepository: UserRepository,
    private val authorityRepository: AuthorityRepository
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails =
      userRepository.findByUsername(username)
          .map { user ->
            object : UserDetails {
              override fun getUsername(): String = user.username!!
              override fun getPassword(): String = user.password!!
              override fun isEnabled(): Boolean = user.enabled!!
              override fun isCredentialsNonExpired(): Boolean = true
              override fun isAccountNonExpired(): Boolean = true
              override fun isAccountNonLocked(): Boolean = true

              override fun getAuthorities() = loadAuthorityByUsername(user.username!!)
            }
          }
          .orElseThrow { throw UsernameNotFoundException("User[$username] not exists.") }

  private fun loadAuthorityByUsername(username: String) =
      authorityRepository.findByUsername(username).map { auth -> SimpleGrantedAuthority(auth.authority) }
}