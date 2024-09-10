package com.example.eventsample

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@SpringBootApplication
@EnableAsync
class EventSampleApplication {
    // Bean으로 준비 시작 이벤트 받기
    @Bean
    fun appReadyEvent(): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener<ApplicationReadyEvent> { event ->
            println("ready Event (take2): $event")
        }
    }

    // EventListener로 이벤트 받기
    @EventListener
    fun ready(event: ApplicationReadyEvent) {
        println("ready Event (take3): $event")
    }

    // 2. 앱 준비 이벤트에서 내 커스텀 이벤트 발행
    @Bean
    fun publisher(publisher: ApplicationEventPublisher): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener {
            publisher.publishEvent(MyCustomEvent())
        }
    }

    // 3. 커스텀 이벤트 수신, Async
    @Async
    @EventListener
    fun customEvent(myCustomEvent: MyCustomEvent) {
        Thread.sleep(5000)
        println("ready Event (take4): $myCustomEvent")
    }

    // 3. 커스텀 이벤트 수신
    @EventListener
    fun customEvent2(myCustomEvent: MyCustomEvent) {
        Thread.sleep(2000)
        println("ready Event (take5): $myCustomEvent")
        throw Exception("")
    }

    // 그 외 @TransactionalEventListener 등이 있음
}

fun main(args: Array<String>) {
    runApplication<EventSampleApplication>(*args)
}

// 컴포넌트, 스태틱 클래스로 모든 이벤트 받기
// applicaiton listner 구현
@Component
class MyListener : ApplicationListener<ApplicationEvent> {
    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun onApplicationEvent(event: ApplicationEvent) {
        logger.info(event.toString())
    }
}


// 2. 내 커스텀 이벤트 클래스
class MyCustomEvent

@RestController
@RequestMapping("/event")
class CustomController(
    private val dataSource: DataSource,
    private val publisher: ApplicationEventPublisher,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun sample() {
        dataSource.connection.use {
            publisher.publishEvent(MyCustomEvent())
            logger.info(it.metaData.toString())
//            it.prepareStatement("CREATE TABLE temp22(p INTEGER NOT NULL, a VARCHAR(255) NOT NULL);").executeUpdate()
            it.prepareStatement("INSERT INTO temp22(p, a) VALUES (?, ?)").apply {
                setInt(1, 1)
                setString(2, "test")
            }.executeUpdate()
            val r = it.prepareStatement("SELECT * FROM temp22").executeQuery()

            while (r.next()) {
                logger.info(r.getString(1))
                logger.info(r.getString(2))
            }
        }
    }
}
