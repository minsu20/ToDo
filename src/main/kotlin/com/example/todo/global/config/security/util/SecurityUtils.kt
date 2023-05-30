package com.example.todo.global.config.security.util

import com.example.todo.domain.user.domain.User
import com.example.todo.global.config.security.service.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import java.util.*


class SecurityUtils {
    companion object {
        @Transactional
        fun getLoggedInUser(): User {
            return try {
                (SecurityContextHolder.getContext().authentication.principal as CustomUserDetails).user
            } catch (e: NullPointerException) {
                throw RequiredLoggedInException()
            }
        }
    }
}