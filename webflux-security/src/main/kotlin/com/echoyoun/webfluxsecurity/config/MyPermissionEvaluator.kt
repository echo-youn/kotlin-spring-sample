package com.echoyoun.webfluxsecurity.config

import org.slf4j.LoggerFactory
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MyPermissionEvaluator: PermissionEvaluator {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun hasPermission(
        authentication: Authentication,
        targetDomainObject: Any,
        permission: Any,
    ): Boolean {
        logger.info("authentication: $authentication")
        logger.info("targetDomainObject: $targetDomainObject")
        logger.info("permission: $permission")
        return true
    }

    override fun hasPermission(
        authentication: Authentication,
        targetId: Serializable,
        targetType: String,
        permission: Any
    ): Boolean {
        logger.info("authentication: $authentication")
        logger.info("targetId: $targetId")
        logger.info("targetType: $targetType")
        logger.info("permission: $permission")
        return true
    }
}