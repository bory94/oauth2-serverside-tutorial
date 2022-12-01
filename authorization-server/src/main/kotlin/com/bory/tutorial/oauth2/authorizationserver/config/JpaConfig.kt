package com.bory.tutorial.oauth2.authorizationserver.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(
    "com.bory.tutorial.oauth2.authorizationserver",
    "com.bory.tutorial.oauth2.common.jpa"
)
@EnableJpaRepositories(
    "com.bory.tutorial.oauth2.authorizationserver",
    "com.bory.tutorial.oauth2.common.jpa"
)
class JpaConfig