package com.example.todo.domain.todo.domain

import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.GetResponse
import java.util.*

interface ToDoCustomRepository {
    fun findNotDeletedById(id: Long): Optional<ToDo>
    fun getAscByWriterId(id: Long): List<GetResponse>
    fun getDescByWriterId(id: Long): List<GetResponse>
}