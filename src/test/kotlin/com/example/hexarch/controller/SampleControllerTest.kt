package com.example.hexarch.controller

import com.example.hexarch.layered.game.GameController
import com.example.hexarch.layered.game.GameService
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.reflect.jvm.javaMethod

@WebMvcTest(
    controllers = [
        GameController::class
    ]
)
// security test 할때 필수 @WithMockUser
class SampleControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
    // @SpykBean Spy Bean 생성
    @MockkBean(relaxed = true)
    lateinit var gameService: GameService

    @Test
    fun test() {
        val result = mockMvc
            .perform(
                get("/games").queryParam("ids", "1,2,3,4")
            )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").exists())
            .andExpect(content().string("[]"))
            .andExpect(
                handler().method(
                    GameController::findGames.javaMethod!!
                )
            )
            .andReturn()
            .response.contentAsString

        println("result: $result")

        verify(exactly = 1) {
            gameService.findGames(setOf(1, 2, 3, 4))
        }
    }
}
