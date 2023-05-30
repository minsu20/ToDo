package com.example.todo.global.dto

import java.time.LocalDateTime


class ErrorResponse(val errorCode: String, val message: String?) {
    val timeStamp: LocalDateTime = LocalDateTime.now().withNano(0)
}
