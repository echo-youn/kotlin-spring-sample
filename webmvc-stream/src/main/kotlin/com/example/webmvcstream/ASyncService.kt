package com.example.webmvcstream

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter

@Service
class ASyncService {
    @Async
    fun emit(emitter: ResponseBodyEmitter) {
        val l = mutableListOf<Int>()
        repeat(1000) {
            l.add(it)
            emitter.send(l)
            println(it)
            Thread.sleep(10)
        }
        emitter.complete()
    }
}
