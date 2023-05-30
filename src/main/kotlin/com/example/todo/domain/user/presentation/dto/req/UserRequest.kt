package com.example.todo.domain.user.presentation.dto.req

import javax.validation.constraints.NotBlank

object UserRequest {
    data class SignupRequest(
            @field:NotBlank(message = "이메일을 입력해주세요")
            var email: String?=null,

            @field:NotBlank(message = "닉네임을 입력해주세요")
            var nickName: String?=null,

            @field:NotBlank(message = "비밀번호를 입력해주세요")
            var password: String?=null
    )

    data class LoginRequest(
            @field:NotBlank(message = "이메일을 입력해주세요")
            var email: String?=null,

            @field:NotBlank(message = "비밀번호를 입력해주세요")
            var password: String?=null
    )
}