package com.bory.tutorial.oauth2.common.jpa.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var seq: Long?,
    var username: String?,
    var password: String?,
    var enabled: Boolean?
) {
  constructor() : this(-1, null, null, false)
}

@Entity
data class Authority(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var seq: Long?,
    var authority: String?,
    var username: String?
) {
  constructor() : this(-1, null, null)
}
