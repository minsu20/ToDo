package com.example.todo.global.exception.todo

import com.example.todo.global.exception.ApplicationException
import org.springframework.http.HttpStatus

abstract class ToDoException(
        errorCode: String,
        httpStatus: HttpStatus,
        message: String
) : ApplicationException(errorCode, httpStatus, message)