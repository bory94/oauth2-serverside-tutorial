package com.bory.tutorial.oauth2.authorizationserver.controller.authorization

import com.bory.tutorial.oauth2.authorizationserver.model.AuthorizedRequestDto
import com.bory.tutorial.oauth2.authorizationserver.service.authorization.AuthorizedRequestService
import com.bory.tutorial.oauth2.common.dto.ResponseMessage
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth/authorized-request")
class AuthorizedRequestController(private val authorizedRequestService: AuthorizedRequestService) {
  @GetMapping("/{seq}")
  fun findById(@PathVariable seq: Long) =
      ResponseMessage(authorizedRequestService.findById(seq))

  @GetMapping
  fun findAll() =
      ResponseMessage(authorizedRequestService.findAll())

  @PostMapping
  fun save(@RequestBody dto: AuthorizedRequestDto) =
      ResponseMessage(authorizedRequestService.save(dto))

  @DeleteMapping("{seq}")
  fun delete(@PathVariable seq: Long) =
      ResponseMessage(authorizedRequestService.delete(seq))

}
