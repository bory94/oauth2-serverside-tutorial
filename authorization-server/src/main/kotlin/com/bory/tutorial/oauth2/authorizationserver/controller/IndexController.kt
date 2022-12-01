package com.bory.tutorial.oauth2.authorizationserver.controller

import com.bory.tutorial.oauth2.common.dto.ResponseMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController {
  @GetMapping
  fun index() =
      ResponseMessage<Unit>("Authorization Server version 1.0")
}