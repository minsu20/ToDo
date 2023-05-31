package com.example.todo.domain.todo.domain

import com.example.todo.domain.todo.domain.QToDo.toDo
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.GetResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import java.util.*

class ToDoCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ToDoCustomRepository {
    override fun findNotDeletedById(id: Long): Optional<ToDo> {
        return Optional.ofNullable(
                queryFactory.selectFrom(toDo)
                        .where(toDo.id.eq(id), toDo.isDeleted.eq(false))
                        .fetchFirst()
        )
    }

    override fun getAscByWriterId(id: Long): List<GetResponse> {
        return queryFactory
                .select(Projections.constructor(GetResponse::class.java,
                        toDo.id, toDo.content, toDo.whenToDo, toDo.status, toDo.userNickName))
                .from(toDo)
                .where(toDo.user.id.eq(id), toDo.isDeleted.eq(false))
                .orderBy(toDo.whenToDo.asc())
                .fetch()
    }

    override fun getDescByWriterId(id: Long): List<GetResponse> {
        return queryFactory
                .select(Projections.constructor(GetResponse::class.java,
                        toDo.id, toDo.content, toDo.whenToDo, toDo.status, toDo.userNickName))
                .from(toDo)
                .where(toDo.user.id.eq(id), toDo.isDeleted.eq(false))
                .orderBy(toDo.whenToDo.desc())
                .fetch()
    }
}