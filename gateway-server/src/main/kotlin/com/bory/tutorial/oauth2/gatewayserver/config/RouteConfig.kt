package com.bory.tutorial.oauth2.gatewayserver.config

import com.bory.tutorial.oauth2.gatewayserver.repository.RouteLocatorRepository
import org.springframework.cloud.context.config.annotation.RefreshScope
import com.bory.tutorial.oauth2.gatewayserver.service.RouteLocatorService
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

@Configuration
class RouteConfig(val routeLocatorService: RouteLocatorService) {

  @DependsOn("mongobee")
  @Bean
  @RefreshScope
  fun resourceServerRoute(builder: RouteLocatorBuilder): RouteLocator {

    val routes = builder.routes()

    routeLocatorService.getRouteLocations().stream().forEach { routeLocation ->
      routes.route(routeLocation.routeId) { predicatedSpec ->
        predicatedSpec
          .path(routeLocation.path)
          .filters { gatewayFilterSpec ->
            gatewayFilterSpec.rewritePath(
              routeLocation.filters.rewritePath.regex,
              routeLocation.filters.rewritePath.replacement
            )
          }
          .uri(routeLocation.uri)
      }
    }

    return routes.build()
  }

}
