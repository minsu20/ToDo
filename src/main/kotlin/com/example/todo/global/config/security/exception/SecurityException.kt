package com.example.todo.global.config.security.exception

import com.example.todo.global.exception.ApplicationException
import org.springframework.http.HttpStatus


abstract class SecurityException(
        errorCode: String,
        httpStatus: HttpStatus,
        message: String
) : ApplicationException(errorCode, httpStatus, message)