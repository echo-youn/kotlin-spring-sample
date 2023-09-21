package com.example.hexarch.layered.game

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(
    private val gameService: GameService
) {
    @GetMapping("games")
    fun findGames(
        @RequestParam
        ids: Set<Long>
    ): List<Game> {
        return gameService.findGames(ids)
    }
}
