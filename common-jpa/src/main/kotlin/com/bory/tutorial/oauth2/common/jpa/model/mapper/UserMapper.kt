package com.bory.tutorial.oauth2.common.jpa.model.mapper

import com.bory.tutorial.oauth2.common.dto.UserDto
import com.bory.tutorial.oauth2.common.jpa.model.User
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface UserMapper {
  fun toDto(user: User): UserDto
  fun toEntity(userDto: UserDto): User
}