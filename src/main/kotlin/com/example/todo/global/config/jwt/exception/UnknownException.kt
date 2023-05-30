package com.example.todo.global.config.jwt.exception

import com.example.todo.global.config.jwt.exception.JWTExceptionList.UNKNOWN_ERROR

class UnknownException  : JwtException(
        UNKNOWN_ERROR.errorCode,
        UNKNOWN_ERROR.httpStatus,
        UNKNOWN_ERROR.message
)