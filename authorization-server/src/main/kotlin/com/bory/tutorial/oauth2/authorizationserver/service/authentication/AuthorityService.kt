package com.bory.tutorial.oauth2.authorizationserver.service.authentication

import com.bory.tutorial.oauth2.common.dto.AuthorityDto
import com.bory.tutorial.oauth2.common.exception.ResourceAlreadyExistsException
import com.bory.tutorial.oauth2.common.exception.ResourceNotFoundException
import com.bory.tutorial.oauth2.common.jpa.model.mapper.AuthorityMapper
import com.bory.tutorial.oauth2.common.jpa.repository.authentication.AuthorityRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class], propagation = Propagation.REQUIRED)
class AuthorityService(private val authorityRepository: AuthorityRepository,
                       private val authorityMapper: AuthorityMapper
) {

  fun findById(seq: Long): AuthorityDto = authorityRepository.findById(seq)
      .map(authorityMapper::toDto)
      .orElseThrow { throw ResourceNotFoundException("Authority by id[$seq] not exists") }

  fun findAll(pageable: Pageable): Page<AuthorityDto> = authorityRepository.findAll(pageable)
      .map(authorityMapper::toDto)

  fun findByUsername(username: String, pageable: Pageable): Page<AuthorityDto> = authorityRepository.findByUsername(username, pageable)
      .map(authorityMapper::toDto)

  @Transactional(readOnly = false)
  fun insert(authorityDto: AuthorityDto): AuthorityDto {
    authorityRepository.findByUsernameAndAuthority(authorityDto.username!!, authorityDto.authority!!)
        .ifPresent { throw ResourceAlreadyExistsException("authority by username and authority[${authorityDto.username}, ${authorityDto.authority}] already exists") }

    return authorityMapper.toDto(
        authorityRepository.save(authorityMapper.toEntity(authorityDto))
    )
  }

  @Transactional(readOnly = false)
  fun update(authorityDto: AuthorityDto): AuthorityDto =
      authorityRepository.findByUsernameAndAuthority(authorityDto.username!!, authorityDto.authority!!)
          .map { authorityMapper.toEntity(authorityDto) }
          .map { authorityRepository.save(it) }
          .map(authorityMapper::toDto)
          .orElseThrow { throw ResourceNotFoundException("authority by username and authority[${authorityDto.username}, ${authorityDto.authority}] not exists") }


  @Transactional(readOnly = false)
  fun delete(seq: Long): AuthorityDto =
      authorityRepository.findById(seq)
          .map { auth ->
            authorityRepository.deleteById(seq)
            auth
          }
          .map(authorityMapper::toDto)
          .orElseThrow { throw ResourceNotFoundException("authority by id[$seq] not exists") }
}