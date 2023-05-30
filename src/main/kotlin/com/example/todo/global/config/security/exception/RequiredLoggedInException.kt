package com.example.todo.global.config.security.exception

class RequiredLoggedInException : SecurityException(
        SecurityExceptionList.REQUIRED_LOGGED_IN.errorCode,
        SecurityExceptionList.REQUIRED_LOGGED_IN.httpStatus,
        SecurityExceptionList.REQUIRED_LOGGED_IN.message
)