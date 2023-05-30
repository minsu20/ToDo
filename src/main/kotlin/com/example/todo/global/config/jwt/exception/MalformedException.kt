package com.example.todo.global.config.jwt.exception

class MalformedException : JwtException(
        JWTExceptionList.MAL_FORMED_TOKEN.errorCode,
        JWTExceptionList.MAL_FORMED_TOKEN.httpStatus,
        JWTExceptionList.MAL_FORMED_TOKEN.message
)