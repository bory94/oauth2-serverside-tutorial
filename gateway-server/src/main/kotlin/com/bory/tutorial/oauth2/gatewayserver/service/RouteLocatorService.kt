package com.bory.tutorial.oauth2.gatewayserver.service

import com.bory.tutorial.oauth2.gatewayserver.model.RouteLocation
import com.bory.tutorial.oauth2.gatewayserver.repository.RouteLocatorRepository
import org.springframework.stereotype.Service

@Service
class RouteLocatorService(val routeLocatorRepository: RouteLocatorRepository) {
    fun getRouteLocations(): List<RouteLocation> = routeLocatorRepository.findAll()
}