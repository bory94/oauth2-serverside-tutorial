package com.bory.tutorial.oauth2.gatewayserver.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "RouteLocation")
data class RouteLocation(
  @Id val id: String,
  var routeId: String,
  var uri: String,
  var path: String,
  var filters: Filters
)

class Filters(
  val rewritePath: RewritePath
)

class RewritePath(
  val regex: String,
  val replacement: String
)