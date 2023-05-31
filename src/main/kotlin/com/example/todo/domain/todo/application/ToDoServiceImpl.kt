package com.example.todo.domain.todo.application

import com.example.todo.domain.todo.domain.ToDo
import com.example.todo.domain.todo.domain.ToDoRepository
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.UpdateRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.ChangeStatusResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.UpdateResponse
import com.example.todo.global.config.security.util.SecurityUtils
import com.example.todo.global.exception.todo.NotFoundToDoByIdException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ToDoServiceImpl(
        private val toDoRepository: ToDoRepository)
    : ToDoService {
    override fun createTodo(createRequest: ToDoRequest.CreateRequest): CreateResponse {
        val user = SecurityUtils.getLoggedInUser()
        val todo = ToDo(createRequest.content!!, LocalDate.parse(createRequest.whenToDo), user, user.nickName)
        toDoRepository.save(todo)
        return CreateResponse(todo.id!!)
    }

    override fun updateTodo(id: Long, updateRequest: UpdateRequest): UpdateResponse {
        val todo = validateTodo(id)
        updateRequest.content?.let { todo.content = it }
        updateRequest.whenToDo?.let { todo.whenToDo = LocalDate.parse(it) }
        toDoRepository.save(todo)
        return UpdateResponse(todo.id!!)
    }

    override fun changeStatus(id: Long): ChangeStatusResponse {
        val todo = validateTodo(id)
        todo.changeStatus()
        toDoRepository.save(todo)
        return ChangeStatusResponse(todo.id!!)
    }

    override fun getAsc(): List<ToDoResponse.GetResponse> {
        val loggedInUserId = SecurityUtils.getLoggedInUser().id
        return toDoRepository.getAscByWriterId(loggedInUserId!!)
    }

    override fun getDesc(): List<ToDoResponse.GetResponse> {
        val loggedInUserId = SecurityUtils.getLoggedInUser().id
        return toDoRepository.getDescByWriterId(loggedInUserId!!)
    }


    override fun validateTodo(id: Long): ToDo {
        return toDoRepository.findNotDeletedById(id).orElseThrow { NotFoundToDoByIdException() };
    }
}