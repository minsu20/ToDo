package com.example.todo.global.config.security

import com.example.todo.global.config.jwt.JwtAccessDeniedHandler
import com.example.todo.global.config.jwt.JwtAuthenticationEntryPoint
import com.example.todo.global.config.jwt.JwtSecurityConfig
import com.example.todo.global.config.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
        val tokenProvider: TokenProvider,
        val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
        val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**", "/img/**", "/lib/**")
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable() //CSRF 보호 비활성화
                .formLogin().disable() //폼 로그인 비활성화
                .httpBasic().disable() // HTTP 기본 인증 비활성화
                .exceptionHandling() //예외 처리 설정
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAuthenticationEntryPoint 설정
                .accessDeniedHandler(jwtAccessDeniedHandler) //권한이 없는 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAccessDeniedHandler 설정
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Spring Security에서 세션을 사용하지 않도록 설정
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v3/api-docs").permitAll()
                .anyRequest().permitAll()
                .and()
                .apply<SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>>(JwtSecurityConfig(tokenProvider))
    }
}