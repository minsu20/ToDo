package com.example.todo.global.config.jwt.exception

import com.example.todo.global.exception.ApplicationException
import org.springframework.http.HttpStatus

abstract class JwtException(
        errorCode: String,
        httpStatus: HttpStatus,
        message: String
) : ApplicationException(errorCode, httpStatus, message)
