package com.bory.tutorial.oauth2.gatewayserver.config.mongo.changelog

import com.bory.tutorial.oauth2.gatewayserver.model.Filters
import com.bory.tutorial.oauth2.gatewayserver.model.RewritePath
import com.bory.tutorial.oauth2.gatewayserver.model.RouteLocation
import com.github.mongobee.changeset.ChangeLog
import com.github.mongobee.changeset.ChangeSet
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeLog
class DatabaseChangeLog {

  @ChangeSet(order = "001", id = "initRouteLocator-resource-server", author = "admin")
  fun initRouteLocator001(mongoTemplate: MongoTemplate) {
    val entity = RouteLocation(
      "", "resource-server", "http://localhost:9090", "/rs/**",
      Filters(RewritePath("/rs/(?<segment>.*)", "/\${segment}"))
    )

    mongoTemplate.save(entity, "RouteLocation")
  }

  @ChangeSet(order = "002", id = "initRouteLocator-authorization-server", author = "admin")
  fun initRouteLocator002(mongoTemplate: MongoTemplate) {
    val entity = RouteLocation(
      "", "authorization-server", "http://localhost:8080", "/as/**",
      Filters(RewritePath("/as/(?<segment>.*)", "/\${segment}"))
    )

    mongoTemplate.save(entity, "RouteLocation")
  }

}
