package com.bory.tutorial.oauth2.common.jpa.model.mapper

import com.bory.tutorial.oauth2.common.dto.AuthorityDto
import com.bory.tutorial.oauth2.common.jpa.model.Authority
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface AuthorityMapper {
  fun toDto(authority: Authority): AuthorityDto
  fun toEntity(authorityDto: AuthorityDto): Authority
}