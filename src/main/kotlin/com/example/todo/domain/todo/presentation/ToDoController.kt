package com.example.todo.domain.todo.presentation

import com.example.todo.domain.todo.application.ToDoService
import com.example.todo.domain.todo.presentation.constant.ETodoResponseMessage
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.CreateRequest
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.UpdateRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.ChangeStatusResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.GetResponse
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.UpdateResponse
import com.example.todo.global.dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class ToDoController(private val toDoService: ToDoService) {
    @PostMapping
    fun create(@Valid @RequestBody createRequest: CreateRequest): ResponseEntity<ResponseDto<CreateResponse>> {
        val createResponse = toDoService.createTodo(createRequest)
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createResponse.id)
                .toUri()
        return ResponseEntity.created(location).body(ResponseDto.create(ETodoResponseMessage.CREATE_TODO_SUCCESS.message, createResponse))
    }

    @PatchMapping("/{id}")
    fun update(@Valid @PathVariable id: Long, @RequestBody updateRequest: UpdateRequest): ResponseEntity<ResponseDto<UpdateResponse>> {
        return ResponseEntity.ok(ResponseDto.create(ETodoResponseMessage.UPDATE_TODO_SUCCESS.message, toDoService.updateTodo(id, updateRequest)))
    }

    @PatchMapping("/{id}/status")
    fun changeStatus(@PathVariable id: Long): ResponseEntity<ResponseDto<ChangeStatusResponse>> {
        return ResponseEntity.ok(ResponseDto.create(ETodoResponseMessage.CHANGE_TODO_STATUS_SUCCESS.message, toDoService.changeStatus(id)))
    }

    @GetMapping("/asc")
    fun getAsc(): ResponseEntity<ResponseDto<List<GetResponse>>> {
        return ResponseEntity.ok(ResponseDto.create(ETodoResponseMessage.GET_TODO_ASC_SUCCESS.message, toDoService.getAsc()))
    }

    @GetMapping("/desc")
    fun getDesc(): ResponseEntity<ResponseDto<List<GetResponse>>> {
        return ResponseEntity.ok(ResponseDto.create(ETodoResponseMessage.GET_TODO_DESC_SUCCESS.message, toDoService.getDesc()))
    }
}