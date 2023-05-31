package com.example.todo.domain.todo.application

import com.example.todo.domain.todo.domain.ToDo
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.CreateRequest
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.UpdateRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.ChangeStatusResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.GetResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.UpdateResponse

interface ToDoService {
    fun createTodo(createRequest: CreateRequest): CreateResponse
    fun updateTodo(id: Long, updateRequest: UpdateRequest): UpdateResponse
    fun changeStatus(id: Long): ChangeStatusResponse
    fun getAsc(): List<GetResponse>
    fun getDesc(): List<GetResponse>
    fun validateTodo(id: Long): ToDo
}