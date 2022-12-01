package com.bory.tutorial.oauth2.authorizationserver.repository.oauth2

import com.bory.tutorial.oauth2.authorizationserver.model.OauthClientDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OauthClientDetailsRepository : JpaRepository<OauthClientDetails, String>