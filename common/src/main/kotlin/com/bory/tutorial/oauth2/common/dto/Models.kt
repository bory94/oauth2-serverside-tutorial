package com.bory.tutorial.oauth2.common.dto

data class UserDto(
    var seq: Long?,
    var username: String?,
    var password: String?,
    var enabled: Boolean?
) {
  constructor() : this(-1, null, null, false)
}

data class AuthorityDto(
    var seq: Long?,
    var authority: String?,
    var username: String?
) {
  constructor() : this(-1, null, null)
}

data class ResponseMessage<T>(
    var message: String = "no-message",
    var payload: T? = null
) {
  constructor(message: String) : this(message, null)
  constructor(payload: T?) : this("OK", payload)
}