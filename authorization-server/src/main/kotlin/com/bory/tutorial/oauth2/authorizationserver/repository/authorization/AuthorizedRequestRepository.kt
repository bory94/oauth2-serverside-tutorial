package com.bory.tutorial.oauth2.authorizationserver.repository.authorization

import com.bory.tutorial.oauth2.authorizationserver.model.AuthorizedRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorizedRequestRepository : JpaRepository<AuthorizedRequest, Long>