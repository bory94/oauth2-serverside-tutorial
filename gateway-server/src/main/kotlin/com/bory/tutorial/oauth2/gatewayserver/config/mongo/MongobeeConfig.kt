package com.bory.tutorial.oauth2.gatewayserver.config.mongo

import com.github.mongobee.Mongobee
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongobeeConfig(val mongoTemplate: MongoTemplate) {

  @Value("\${spring.data.mongodb.database}")
  lateinit var databaseName: String

  @Value("\${spring.data.mongodb.host}")
  lateinit var host: String

  @Value("\${spring.data.mongodb.port}")
  lateinit var port: String

  @Value("\${spring.data.mongodb.username}")
  lateinit var username: String

  @Value("\${spring.data.mongodb.password}")
  lateinit var password: String

  @Bean
  fun mongobee(): Mongobee {
    val mongobee = Mongobee("mongodb://$username:$password@$host:$port/$databaseName?authSource=admin")
    mongobee.setChangeLogsScanPackage("com.bory.tutorial.oauth2.gatewayserver.config.mongo.changelog")
    mongobee.setMongoTemplate(mongoTemplate)
    return mongobee
  }
}
