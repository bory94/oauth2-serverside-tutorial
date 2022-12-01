package com.bory.tutorial.oauth2.resourceclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResourceClientApplication

fun main(args: Array<String>) {
    runApplication<ResourceClientApplication>(*args)
}