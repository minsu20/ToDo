package com.example.todo.global.config.jwt.exception

import com.example.todo.global.config.jwt.exception.JWTExceptionList.MAL_FORMED_TOKEN

class MalformedException : JwtException(
        MAL_FORMED_TOKEN.errorCode,
        MAL_FORMED_TOKEN.httpStatus,
        MAL_FORMED_TOKEN.message
)