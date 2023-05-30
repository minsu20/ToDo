package com.example.todo.domain.user.presentation

import com.example.todo.domain.user.application.UserService
import com.example.todo.domain.user.presentation.constant.EUserResponseMessage
import com.example.todo.domain.user.presentation.dto.req.UserRequest
import com.example.todo.domain.user.presentation.dto.res.UserResponse
import com.example.todo.domain.user.presentation.dto.res.UserResponse.SignupResponse
import com.example.todo.global.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun singupUser(@Valid @RequestBody signupRequest: UserRequest.SignupRequest): ResponseEntity<ResponseDto<SignupResponse>> {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), EUserResponseMessage.SIGN_UP_SUCCESS.message, userService.signup(signupRequest)))
    }

    @PostMapping("/login")
    fun singupUser(@Valid @RequestBody loginRequest: UserRequest.LoginRequest): ResponseEntity<ResponseDto<UserResponse.LoginResponse>> {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), EUserResponseMessage.LOGIN_SUCCESS.message, userService.login(loginRequest)))
    }

}