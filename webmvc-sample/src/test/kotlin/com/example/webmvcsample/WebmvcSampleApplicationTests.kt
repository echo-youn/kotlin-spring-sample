package com.example.webmvcsample

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(
    controllers = [SampleController::class]
)
class WebmvcSampleApplicationTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `default mockMVC Test`() {
        mockMvc.perform(
            get("")
        )
            .andExpect(status().isOk)
            .andExpect(handler().methodName("hello"))
            .andExpect(jsonPath("$").value("Hello World"))
            .andDo {
                println(it.response.contentAsString)
            }
    }
}
