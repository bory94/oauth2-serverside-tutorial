package com.bory.tutorial.oauth2.common.jpa.repository.authentication

import com.bory.tutorial.oauth2.common.jpa.model.Authority
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AuthorityRepository : JpaRepository<Authority, Long> {
  fun findByUsername(username: String): List<Authority>

  fun findByUsername(username: String, pageable: Pageable): Page<Authority>

  fun findByUsernameAndAuthority(username: String, authority: String): Optional<Authority>
}