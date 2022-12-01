package com.bory.tutorial.oauth2.authorizationserver.model.mapper

import com.bory.tutorial.oauth2.authorizationserver.model.AuthorizedRequest
import com.bory.tutorial.oauth2.authorizationserver.model.AuthorizedRequestDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface AuthorizedRequestMapper {
  fun toDto(authorizedRequest: AuthorizedRequest): AuthorizedRequestDto
  fun toEntity(authorizedRequestDto: AuthorizedRequestDto): AuthorizedRequest
}