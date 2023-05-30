package com.example.todo.domain.user.domain

import java.util.*

interface UserCustomRepository {
    fun findNotDeletedByEmail(email: String): Optional<User>
}