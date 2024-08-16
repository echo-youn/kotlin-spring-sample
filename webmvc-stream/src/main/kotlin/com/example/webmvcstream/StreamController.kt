package com.example.webmvcstream

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.time.Duration

@RestController
class StreamController(
    private val aSyncService: ASyncService,
) {
    /**
     * 브라우저에서 사용해야 잘 보일듯
     */
    @GetMapping("/responseBodyEmitter", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun responseBodyEmitter(): ResponseBodyEmitter {
        val emitter = ResponseBodyEmitter()
        aSyncService.emit(emitter)
        return emitter
    }

    /**
     * Response header `text/event-stream`
     */
    @GetMapping("/sseEmitter")
    fun sseEmitter(): SseEmitter {
        val emitter = SseEmitter(Duration.ofSeconds(3).toMillis())
        aSyncService.emit(emitter)
        return emitter
    }

    /**
     * 브라우저에서 사용해야 잘 보일듯
     */
    @GetMapping("/streamingResponseBody")
    fun streamingResponseBody(): ResponseEntity<StreamingResponseBody> {
        val stream = StreamingResponseBody {
            it.write(listOf(1, 2, 3).toString().toByteArray())
        }
        return ResponseEntity.ok(stream)
    }
}
