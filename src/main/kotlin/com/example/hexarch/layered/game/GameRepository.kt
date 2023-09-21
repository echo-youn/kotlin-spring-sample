package com.example.hexarch.layered.game

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long> {
    @EntityGraph(attributePaths = ["gameUsers"])
    fun findByIdIn(ids: Collection<Long>): List<Game>
}
