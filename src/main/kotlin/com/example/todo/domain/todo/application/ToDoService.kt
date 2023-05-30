package com.example.todo.domain.todo.application

import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.CreateRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse

interface ToDoService {

    fun createTodo(createRequest: CreateRequest) : CreateResponse
}