package com.example.hexarch.entity

import com.example.hexarch.layered.game.Game
import com.example.hexarch.layered.user.UserDto
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
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

    var username: String,

    @JsonIgnore
    @ManyToMany(mappedBy = "gameUsers")
    val games: List<Game> = listOf()
) {
    fun toDto(): UserDto = UserDto(
        id,
        username,
        games
    )
}
