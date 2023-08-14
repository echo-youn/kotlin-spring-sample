package com.example.hexarch.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.EqualsAndHashCode
import lombok.ToString

@Entity
@ToString
@EqualsAndHashCode
// data class는 spring jpa에서 비추천함.
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var username: String
)
