package com.example.todo.global.exception.user

import com.example.todo.global.exception.ApplicationException
import org.springframework.http.HttpStatus

abstract class UserException(
        errorCode: String,
        httpStatus: HttpStatus,
        message: String
) : ApplicationException(errorCode, httpStatus, message)