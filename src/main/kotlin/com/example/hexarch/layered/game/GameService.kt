package com.example.hexarch.layered.game

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    private val gameRepository: GameRepository
) {
    @Transactional(readOnly = true)
    fun findGames(ids: Set<Long>): List<Game> {
        return gameRepository.findByIdIn(ids)
    }
}
