package com.example.todo.global.exception

import org.springframework.http.HttpStatus

abstract class ApplicationException protected constructor(
        val errorCode: String,
        val httpStatus: HttpStatus,
        message: String) : RuntimeException(message)