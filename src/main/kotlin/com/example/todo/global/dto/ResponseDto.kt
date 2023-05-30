package com.example.todo.global.dto

data class ResponseDto<T>(
        val message: String,
        val data: T? = null
) {
    companion object {
        fun <T> create(message: String, data: T? = null): ResponseDto<T> =
                ResponseDto(message, data)
    }
}
