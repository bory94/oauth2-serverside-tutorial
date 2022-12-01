package com.bory.tutorial.oauth2.gatewayserver.repository

import com.bory.tutorial.oauth2.gatewayserver.model.RouteLocation
import org.springframework.data.mongodb.repository.MongoRepository

interface RouteLocatorRepository : MongoRepository<RouteLocation, String>