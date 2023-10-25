package com.example.sample

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JasyptTest {
    private val message = "password"

    private val config = SimpleStringPBEConfig().apply {
        password = "password"
        algorithm = "PBEWITHHMACSHA512ANDAES_256"
        poolSize = 1
        saltGenerator = RandomSaltGenerator()
        ivGenerator = RandomIvGenerator()
    }

    private val jasypt = PooledPBEStringEncryptor().apply {
        setConfig(config)
    }

    @Test
    fun encryptTest() {
        val encryptedMessage = jasypt.encrypt(message)
        val originalMessage = jasypt.decrypt(encryptedMessage)
        println("encryption: $encryptedMessage, original: $originalMessage")
        assert(message == originalMessage)
    }

    @Disabled
    @Test
    fun decryptTest() {
        assertDoesNotThrow {
            println("original: ${jasypt.decrypt("")}")
        }
    }
}
