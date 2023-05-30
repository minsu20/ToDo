package com.example.todo.global.config.security.exception

class NoAuthenticationFoundException : SecurityException(
        SecurityExceptionList.NO_AUTHENTICATION_FOUND.errorCode,
        SecurityExceptionList.NO_AUTHENTICATION_FOUND.httpStatus,
        SecurityExceptionList.NO_AUTHENTICATION_FOUND.message
)