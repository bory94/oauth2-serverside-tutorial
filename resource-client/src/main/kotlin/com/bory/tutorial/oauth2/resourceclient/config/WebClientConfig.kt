package com.bory.tutorial.oauth2.resourceclient.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun oauth2WebClient(clientRegistrationRepository: ReactiveClientRegistrationRepository) =
            WebClient.builder().filter(
                    ServerOAuth2AuthorizedClientExchangeFilterFunction(
                            clientRegistrationRepository,
                            UnAuthenticatedServerOAuth2AuthorizedClientRepository()
                    ).run {
                        setDefaultClientRegistrationId("first-service")
                        this
                    }
            ).build()
}
