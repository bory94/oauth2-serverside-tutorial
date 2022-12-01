package com.bory.tutorial.oauth2.authorizationserver.controller.authentication

import com.bory.tutorial.oauth2.authorizationserver.service.authentication.UserService
import com.bory.tutorial.oauth2.common.dto.ResponseMessage
import com.bory.tutorial.oauth2.common.dto.UserDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth/user")
class UserController(private val userService: UserService) {
  @GetMapping("/{seq}")
  fun findById(@PathVariable seq: Long) =
      ResponseMessage(userService.findById(seq))

  @GetMapping
  fun findAll(
      @PageableDefault(size = 20, sort = ["seq"], direction = Sort.Direction.DESC) pageable: Pageable
  ) = ResponseMessage(userService.findAll(pageable))

  @PostMapping
  fun insert(@RequestBody userDto: UserDto) = ResponseMessage(userService.insert(userDto))

  @PutMapping
  fun update(@RequestBody userDto: UserDto) = ResponseMessage(userService.update(userDto))

  @DeleteMapping("/{seq}")
  fun delete(@PathVariable seq: Long) = ResponseMessage(userService.delete(seq))
}