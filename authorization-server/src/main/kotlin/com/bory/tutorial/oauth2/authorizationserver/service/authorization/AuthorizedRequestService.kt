package com.bory.tutorial.oauth2.authorizationserver.service.authorization

import com.bory.tutorial.oauth2.authorizationserver.model.AuthorizedRequestDto
import com.bory.tutorial.oauth2.authorizationserver.model.mapper.AuthorizedRequestMapper
import com.bory.tutorial.oauth2.authorizationserver.repository.authorization.AuthorizedRequestRepository
import com.bory.tutorial.oauth2.common.exception.ResourceNotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class], propagation = Propagation.REQUIRED)
class AuthorizedRequestService(
    private val repository: AuthorizedRequestRepository,
    private val mapper: AuthorizedRequestMapper
) {

  fun findById(seq: Long): AuthorizedRequestDto = repository.findById(seq)
      .map(mapper::toDto)
      .orElseThrow { throw ResourceNotFoundException("authorizedRequest by seq[$seq] not exists") }

  fun findAll(): List<AuthorizedRequestDto> = repository.findAll(Sort.by(Sort.Direction.ASC, "sort"))
      .map(mapper::toDto)

  @Transactional(readOnly = false)
  fun save(dto: AuthorizedRequestDto): AuthorizedRequestDto =
      mapper.toEntity(dto)
          .let(repository::save)
          .let(mapper::toDto)

  @Transactional(readOnly = false)
  fun delete(seq: Long): AuthorizedRequestDto =
      repository.findById(seq)
          .map {
            repository.delete(it)
            it
          }
          .map(mapper::toDto)
          .orElseThrow { ResourceNotFoundException("authorizedRequest by seq[$seq] not exists") }
}