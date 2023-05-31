package com.example.todo.domain.todo.presentation

import com.example.todo.domain.todo.presentation.constant.ETodoResponseMessage
import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.CreateRequest
import com.example.todo.domain.todo.presentation.dto.res.ToDoResponse.CreateResponse
import com.example.todo.global.dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import com.example.todo.domain.todo.application.ToDoService
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class ToDoController(private val toDoService: ToDoService) {
    @PostMapping
    fun signup(@Valid @RequestBody createRequest: CreateRequest): ResponseEntity<ResponseDto<CreateResponse>> {
        val createResponse=toDoService.createTodo(createRequest)
        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createResponse.id)
                .toUri()
        return ResponseEntity.created(location).body(ResponseDto.create(ETodoResponseMessage.CREATE_TODO_SUCCESS.message, createResponse))
    }

    //TODO 투두리스트 수정
    //TODO 투두리스트 상태변화 (TODO->DONE, DONE->TODO)
    //TODO 투두리스트 전체조회
    //TODO 투두리스트 일정 예정 날짜순으로 정렬 (오름차순, 내림차순)
}