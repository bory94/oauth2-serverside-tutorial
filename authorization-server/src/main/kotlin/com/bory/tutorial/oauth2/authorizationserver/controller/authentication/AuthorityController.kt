package com.bory.tutorial.oauth2.authorizationserver.controller.authentication

import com.bory.tutorial.oauth2.authorizationserver.service.authentication.AuthorityService
import com.bory.tutorial.oauth2.common.dto.AuthorityDto
import com.bory.tutorial.oauth2.common.dto.ResponseMessage
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth/authority")
class AuthorityController(private val authorityService: AuthorityService) {
  @GetMapping("/{seq}")
  fun findById(@PathVariable seq: Long) = ResponseMessage(authorityService.findById(seq))

  @GetMapping
  fun findAll(
      @PageableDefault(size = 10, sort = ["seq"], direction = Sort.Direction.DESC) pageable: Pageable
  ) = ResponseMessage(authorityService.findAll(pageable))

  @PostMapping
  fun insert(@RequestBody authorityDto: AuthorityDto) = ResponseMessage(authorityService.insert(authorityDto)
  )

  @PutMapping
  fun update(@RequestBody authorityDto: AuthorityDto) = ResponseMessage(authorityService.update(authorityDto))

  @DeleteMapping("/{seq}")
  fun delete(@PathVariable seq: Long) = ResponseMessage(authorityService.delete(seq))
}