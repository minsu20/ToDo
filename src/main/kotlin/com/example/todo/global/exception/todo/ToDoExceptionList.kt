package com.example.todo.global.exception.todo

import org.springframework.http.HttpStatus

enum class ToDoExceptionList(
        val errorCode: String,
        val httpStatus: HttpStatus,
        val message: String
) {

    NOT_FOUND_TODO_BY_ID("T0001", HttpStatus.NOT_FOUND, "해당 id로 TODO를 찾을 수 없습니다."),
}