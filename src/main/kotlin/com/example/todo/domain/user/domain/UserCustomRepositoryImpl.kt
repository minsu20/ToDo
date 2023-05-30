package com.example.todo.domain.user.domain

import com.example.todo.domain.user.domain.QUser.user
import com.querydsl.jpa.impl.JPAQueryFactory
import java.util.*

class UserCustomRepositoryImpl (private val queryFactory: JPAQueryFactory) : UserCustomRepository {

    override fun findNotDeletedByEmail(email: String): Optional<User> {
        return Optional.ofNullable(
                queryFactory.selectFrom(user)
                        .where(user.email.eq(email), user.isDeleted.eq(false))
                        .fetchFirst()
        )
    }
}