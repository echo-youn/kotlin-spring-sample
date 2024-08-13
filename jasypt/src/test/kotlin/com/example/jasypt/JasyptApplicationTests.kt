package com.example.jasypt

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@Import(JasyptConfig::class)
@ExtendWith(SpringExtension::class)
class JasyptApplicationTests {
    fun jasyptEncryptor(): PooledPBEStringEncryptor {
        val config = SimpleStringPBEConfig().apply {
            password =
                "strongpassword123fdsafdasasdfafsdsdafdfsasdaffsdasdafsadfsdafdsfadafstrongpassword123fdsafdasasdfafsdsdafdfsasdaffsdasdafsadfsdafdsfadaf"
            algorithm = "PBEWITHSHA256AND256BITAES-CBC-BC"
            poolSize = 1
            saltGenerator = RandomSaltGenerator()
            ivGenerator = RandomIvGenerator()
            provider = BouncyCastleProvider()
        }
        return PooledPBEStringEncryptor().apply {
            setConfig(config)
        }
    }

    @Test
    fun encrypt() {
        val jasyptEncryptor = jasyptEncryptor()
        println(jasyptEncryptor.encrypt("my secret"))
    }

    @Test
    fun decrypt() {
        val jasyptEncryptor = jasyptEncryptor()
        val enc = "+3GqbUfEbojy1JqVvub9GPk/2OmAXHl75PPtVNdoE16qLjjbLh+WIaVhcIXDbpjG"
        println(jasyptEncryptor.decrypt(enc))
    }
}
