package com.bory.tutorial.oauth2.common.jpa.repository.authentication

import com.bory.tutorial.oauth2.common.jpa.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
  fun findByUsername(username: String): Optional<User>
}