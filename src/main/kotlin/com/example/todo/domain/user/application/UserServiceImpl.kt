package com.example.todo.domain.user.application

import com.example.todo.domain.user.domain.User
import com.example.todo.domain.user.domain.UserRepository
import com.example.todo.domain.user.presentation.dto.req.UserRequest.LoginRequest
import com.example.todo.domain.user.presentation.dto.req.UserRequest.SignupRequest
import com.example.todo.domain.user.presentation.dto.res.UserResponse.LoginResponse
import com.example.todo.domain.user.presentation.dto.res.UserResponse.SignupResponse
import com.example.todo.global.config.jwt.TokenProvider
import com.example.todo.global.dto.TokenInfoResponse
import com.example.todo.global.exception.user.NotFoundEmailException
import com.example.todo.global.exception.user.NotFoundPasswordException
import com.example.todo.global.exception.user.OverlapUserException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val tokenProvider: TokenProvider,
        private val authenticationManagerBuilder: AuthenticationManagerBuilder
) : UserService  {

    override fun signup(signupRequest: SignupRequest): SignupResponse {
        if(userRepository.findNotDeletedByEmail(signupRequest.email).isEmpty){
            val user=User(signupRequest.email,signupRequest.password,signupRequest.nickName)
            user.encryptPassword(passwordEncoder)
            userRepository.save(user);
            return SignupResponse(user.id!!)
        }else{
            throw OverlapUserException()
        }
    }
    override fun login(loginRequest: LoginRequest): LoginResponse {
        try {
            val tokenInfoResponse = validateLogin(loginRequest)
            return LoginResponse.from(tokenInfoResponse)
        } catch (e: AuthenticationException) {
            throw NotFoundPasswordException()
        }
    }

    private fun validateLogin(loginRequest: LoginRequest): TokenInfoResponse {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        return tokenProvider.createToken(authentication)
    }

    override fun validateEmail(email: String): User {
        return userRepository.findNotDeletedByEmail(email)
                .orElseThrow { NotFoundEmailException() }
    }
}