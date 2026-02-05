package com.echoyoun.webfluxsecurity.config

import org.slf4j.LoggerFactory
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MyPermissionEvaluator: PermissionEvaluator {
    private val logger = LoggerFactory.getLogger(this::class.java)

    val permission = "user:read:1"
    val permissionGroup = listOf("group:read:1", "cde")

    override fun hasPermission(
        authentication: Authentication,
        targetDomainObject: Any,
        permission: Any,
    ): Boolean {
        "1:read:1"

        if(authentication.name == permission) {
            return true
        }
        logger.info("authentication: $authentication")
        logger.info("targetDomainObject: $targetDomainObject")
        logger.info("permission: $permission")
        return true
    }

    override fun hasPermission(
        authentication: Authentication, // user: 1, credentials: apikey
        targetId: Serializable, // 1번
        targetType: String, // MyUserEntity
        permission: Any // "user:read, credentials:read"
    ): Boolean {
        val target = "1"
        val action = "read"
        val user = "user"

        val groups = listOf("groups1", "groups2")
        "groups1:read:1"

        if ("${authentication.name}:$targetType:$targetId" == permission) {
            return true
        }

        if (permissionGroup.contains(permission)) {
            return true
        }

        logger.info("authentication: $authentication")
        logger.info("targetId: $targetId")
        logger.info("targetType: $targetType")
        logger.info("permission: $permission")
        return true
    }
}