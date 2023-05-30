package com.example.todo.domain.user.application

import com.example.todo.domain.user.domain.User
import com.example.todo.domain.user.presentation.dto.req.UserRequest.SignupRequest
import com.example.todo.domain.user.presentation.dto.res.UserResponse.SignupResponse

interface UserService {
    fun signup(signupRequest: SignupRequest): SignupResponse
    fun validateEmail(email: String): User
}