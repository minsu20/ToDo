package com.example.todo.domain.todo.application

import com.example.todo.domain.todo.domain.ToDo
import com.example.todo.domain.todo.domain.ToDoRepository
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse
import com.example.todo.global.config.security.util.SecurityUtils
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ToDoServiceImpl(
        private val toDoRepository: ToDoRepository)
    : ToDoService {
    override fun createTodo(createRequest: ToDoRequest.CreateRequest): CreateResponse {
        val user=SecurityUtils.getLoggedInUser();
        val todo=ToDo(createRequest.content!!, LocalDate.parse(createRequest.whenToDo), user)
        toDoRepository.save(todo)
        return CreateResponse(todo.id!!)
    }
}