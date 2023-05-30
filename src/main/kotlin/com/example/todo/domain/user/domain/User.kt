package com.example.todo.domain.user.domain

import com.example.todo.global.config.security.constant.Role
import com.example.todo.global.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        var id: Long? = null,

        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var password: String,

        @Column
        var nickName: String,

        @Column
        @Enumerated(EnumType.STRING)
        var role: Role,


) : BaseTimeEntity()