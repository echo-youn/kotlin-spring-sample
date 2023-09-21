package com.example.hexarch.layered.game

import com.example.hexarch.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val gameName: String,

    @ManyToMany
    @JoinTable(
        name = "user_game_relation",
        joinColumns = [JoinColumn(name = "uid", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "gid", referencedColumnName = "id")]
    )
    val gameUsers: List<UserEntity>
)
