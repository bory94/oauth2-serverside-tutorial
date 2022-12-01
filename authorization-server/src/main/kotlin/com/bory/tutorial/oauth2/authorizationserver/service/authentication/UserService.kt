package com.bory.tutorial.oauth2.authorizationserver.service.authentication

import com.bory.tutorial.oauth2.common.dto.UserDto
import com.bory.tutorial.oauth2.common.exception.ResourceAlreadyExistsException
import com.bory.tutorial.oauth2.common.exception.ResourceNotFoundException
import com.bory.tutorial.oauth2.common.jpa.model.mapper.UserMapper
import com.bory.tutorial.oauth2.common.jpa.repository.authentication.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class], propagation = Propagation.REQUIRED)
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
  fun findById(seq: Long): UserDto = userRepository.findById(seq)
      .map(userMapper::toDto)
      .orElseThrow { throw ResourceNotFoundException("User by id[$seq] not exists") }

  fun findAll(pageable: Pageable): Page<UserDto> = userRepository.findAll(pageable)
      .map(userMapper::toDto)

  @Transactional(readOnly = false)
  fun insert(userDto: UserDto): UserDto {
    userRepository.findByUsername(userDto.username!!)
        .ifPresent { throw ResourceAlreadyExistsException("user by username[${userDto.username}] already exists") }

    return userMapper.toDto(
        userRepository.save(userMapper.toEntity(userDto))
    )
  }

  @Transactional(readOnly = false)
  fun update(userDto: UserDto): UserDto =
      userRepository.findByUsername(userDto.username!!)
          .map { userMapper.toEntity(userDto) }
          .map { userRepository.save(it) }
          .map(userMapper::toDto)
          .orElseThrow { throw ResourceNotFoundException("user by username[${userDto.username}] not exists") }

  @Transactional(readOnly = false)
  fun delete(seq: Long): UserDto =
      userRepository.findById(seq)
          .map { user ->
            userRepository.deleteById(seq)
            user
          }
          .map(userMapper::toDto)
          .orElseThrow { throw ResourceNotFoundException("user by id[$seq] not exists") }
}