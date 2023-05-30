package com.example.todo.domain.user.domain

import com.example.todo.global.config.security.constant.Role
import com.example.todo.global.entity.BaseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
        email: String,
        password: String,
        nickname: String) : BaseEntity() {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        var id: Long?=null

        @Column(nullable = false)
        var email: String=email

        @Column(nullable = false)
        var password: String=password

        @Column(nullable=false)
        var nickName: String=nickname

        @Column
        @Enumerated(EnumType.STRING)
        var role: Role?=Role.ROLE_USER

        fun encryptPassword(passwordEncoder: PasswordEncoder) {
                password = passwordEncoder.encode(password)
        }

}