package com.bory.tutorial.oauth2.common.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

class ResourceAlreadyExistsException(message: String) : RuntimeException(message)