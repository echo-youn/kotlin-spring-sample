package com.example.webmvcjpa

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "my_user")
@Entity
class MyUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val username: String,

    val password: String,

    val mobile: String,

    val roles: String,
) {
    fun toDto() = MyUserDto(
        id = this.id,
        username = this.username,
        password = this.password,
        mobile = this.mobile,
        roles = this.roles,
    )
}
