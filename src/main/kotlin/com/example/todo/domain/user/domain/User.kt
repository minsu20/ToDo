package com.example.todo.domain.user.domain

import com.example.todo.global.config.security.constant.Role
import com.example.todo.global.entity.BaseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
        initialEmail: String,
        initialPassword: String,
        initialNickname: String,
        initialRole: Role = Role.ROLE_USER) : BaseEntity() {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        val id: Long?=null

        @Column(nullable = false)
        val email: String=initialEmail

        @Column(nullable = false)
        var password: String=initialPassword

        @Column(nullable=false)
        val nickName: String=initialNickname

        @Column
        @Enumerated(EnumType.STRING)
        val role: Role?=Role.ROLE_USER

        fun encryptPassword(passwordEncoder: PasswordEncoder) {
                password = passwordEncoder.encode(password)
        }

}