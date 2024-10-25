package com.example.mongocrwal

import com.mongodb.client.model.TextSearchOptions
import com.mongodb.client.model.search.TextSearchOperator
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Limit
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
@EnableScheduling
class MongoCrwalApplication

fun main(args: Array<String>) {
    runApplication<MongoCrwalApplication>(*args)
}

@Service
class MyService(
    private val mongoDataRepository: MyMongoDataRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    @Scheduled(fixedDelay = 1)
    fun greet() {
        val d = Data(
            UUID.randomUUID().toString(),
            "woooow",
            "hello World!! ${Math.random() * 1000 % 100}",
            LocalDateTime.now(),
        )
        mongoDataRepository.save(d)
    }

    @Scheduled(fixedDelay = 100)
    fun hi() {
        val a = (Math.random() * 10 % 10).toInt()
        // regex
        val r = mongoDataRepository.findAllByMessage2IsContaining(a.toString(), Limit.of(2))

        // full text search
        val q = TextCriteria.forDefaultLanguage().matchingAny(a.toString())
        val r2 = mongoDataRepository.findAllBy(q, Limit.of(2))

        logger.info(r.toString())
        logger.info(r2.toString())
    }
}

@Document("newData")
data class Data(
    @Id
    val id: String,
    @Indexed
    val message: String?,
    @Indexed(sparse = true)
    @TextIndexed
    val message2: String?,
    val createDateTime: LocalDateTime = LocalDateTime.now(),
    // 몽고디비 TTL 백그라운드는 1분에 한번씩 작동함
    // 삭제되는데까지 1초 이상의 시간이 걸릴수 있음
    // spring.data.mongodb.auto-index-creation 옵션이 켜져있어야 적용됨
    @Indexed(name = "ttl_index", expireAfterSeconds = 5)
    val expiredAt: Instant = Instant.now(),
)
