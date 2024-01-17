package com.example.hexarch.layered.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class ApplicationLifeCycle : InitializingBean, DisposableBean {
    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun afterPropertiesSet() {
        logger.info("application init")
    }

    override fun destroy() {
        logger.info("application destroyed")
    }
}
