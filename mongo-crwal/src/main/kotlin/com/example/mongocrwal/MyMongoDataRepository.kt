package com.example.mongocrwal

import org.springframework.data.domain.Limit
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.repository.MongoRepository

interface MyMongoDataRepository : MongoRepository<Data, String> {
    fun findByIdIs(id: String): Data?
    fun findAllByIdContains(id: String): List<Data>
    fun findAllByMessage2IsContaining(message: String, limit: Limit): List<Data>
    fun findAllBy(criteria: TextCriteria, limit: Limit): List<Data>
}
