package com.bory.tutorial.oauth2.authorizationserver.exception

import com.bory.tutorial.oauth2.common.dto.ResponseMessage
import com.bory.tutorial.oauth2.common.exception.ResourceAlreadyExistsException
import com.bory.tutorial.oauth2.common.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException::class)
  fun handleNotFound() =
      ResponseMessage<Unit>("Not Found")

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(ResourceAlreadyExistsException::class)
  fun handleNotAcceptable() =
      ResponseMessage<Unit>("Not Acceptable")
}