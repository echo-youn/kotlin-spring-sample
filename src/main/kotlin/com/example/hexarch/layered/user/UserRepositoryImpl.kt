package com.example.hexarch.layered.user

import com.example.hexarch.entity.QUserEntity
import com.example.hexarch.entity.UserEntity
import com.example.hexarch.layered.game.QGame
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : UserRepositoryCustom {
    override fun somethingSpecial(id: List<Long>): List<UserEntity> {
        val user = QUserEntity.userEntity
        return jpaQueryFactory
            .selectFrom(user)
            .where(user.id.`in`(id))
            .fetch()
    }

    override fun userWithGames(id: List<Long>): List<UserEntity> {
        val user = QUserEntity.userEntity
        val game = QGame.game
        return jpaQueryFactory
            .selectFrom(user).join(user.games, game).fetchJoin()
            .where(user.id.`in`(id))
            .fetch()
    }

    override fun projectionSample(id: List<Long>): List<UserDto> {
        val user = QUserEntity.userEntity
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    UserDto::class.java,
                    user.id,
                    user.username
                )
            )
            .from(user)
            .fetch()
    }
}
