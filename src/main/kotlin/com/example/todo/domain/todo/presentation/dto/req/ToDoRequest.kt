package com.example.todo.domain.todo.presentation.dto.req

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

object ToDoRequest {
    data class CreateRequest(
            @field:NotBlank(message = "투두리스트 내용을 입력해주세요")
            var content:String?=null,

            @field:NotBlank(message = "투두리스트 예정 날짜를 입력해 주세요.")
            @field:Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "날짜 형식이 맞지 않습니다. yyyy-mm-ss 형식으로 입력해주세요.")
            val whenToDo: String?=null
    )
}