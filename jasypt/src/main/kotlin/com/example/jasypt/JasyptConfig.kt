package com.example.jasypt

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {
    // bean 이름 중요
    @Bean
    fun jasyptStringEncryptor(): StringEncryptor {
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
}
