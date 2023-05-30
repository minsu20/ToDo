package com.example.todo.global.config.jwt

import com.example.todo.domain.user.domain.UserRepository
import com.example.todo.global.config.jwt.exception.*
import com.example.todo.global.config.security.service.CustomUserDetails
import com.example.todo.global.dto.TokenInfoResponse
import com.example.todo.global.exception.user.NotFoundEmailException
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors

@Component
class TokenProvider(
        private val userRepository: UserRepository,
) : InitializingBean {

    companion object {
        private const val AUTHORITIES_KEY = "auth"
        private const val USER_ID = "id"
    }

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.access-token-validity-in-seconds}")
    private var accessTokenValidityTime: Long = 0

    @Value("\${jwt.refresh-token-validity-in-seconds}")
    private var refreshTokenValidityTime: Long = 0

    private lateinit var key: Key

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    fun createToken(authentication: Authentication): TokenInfoResponse {
        val authorities = authentication.authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))

        val principal= authentication.principal as CustomUserDetails
        val id=principal.user.id

        val now = Date().time
        val accessTokenValidity = Date(now + 1000 * accessTokenValidityTime)
        val refreshTokenValidity = Date(now + 1000 * refreshTokenValidityTime)

        val accessToken = Jwts.builder()
                .setSubject(authentication.name)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(accessTokenValidity)
                .compact()

        val refreshToken = Jwts.builder()
                .setExpiration(refreshTokenValidity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()


        return TokenInfoResponse.from("Bearer", accessToken, refreshToken, refreshTokenValidityTime, id!!)
    }


    fun getAuthentication(token: String?): Authentication {
        var claims: Claims? = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        val authorities: Collection<GrantedAuthority> = claims?.get(AUTHORITIES_KEY).toString()
                .split(",")
                .map(::SimpleGrantedAuthority)
                .toList()

        val user = claims?.let {
            userRepository.findNotDeletedByEmail(it.subject)
                .orElseThrow(::NotFoundEmailException)
        }
        return UsernamePasswordAuthenticationToken(user?.let { CustomUserDetails(it) }, token, authorities)
    }


    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (e: SecurityException) {
            throw MalformedException()
        } catch (e: MalformedJwtException) {
            throw MalformedException()
        } catch (e: ExpiredJwtException) {
            throw ExpiredException()
        } catch (e: UnsupportedJwtException) {
            throw UnsupportedException()
        } catch (e: IllegalArgumentException) {
            throw IllegalException()
        } catch (e: Exception) {
            throw e
        }
    }

    fun validateRefreshToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (e: SecurityException) {
            throw MalformedException()
        } catch (e: MalformedJwtException) {
            throw MalformedException()
        } catch (e: ExpiredJwtException) {
            throw ExpiredException()
        } catch (e: UnsupportedJwtException) {
            throw UnsupportedException()
        } catch (e: IllegalArgumentException) {
            throw IllegalException()
        } catch (e: Exception) {
            throw UnknownException()
        } finally {
            false
        }
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    fun getExpiration(accessToken: String): Long {
        val expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body.expiration
        val now = Date().time
        return expiration.time - now
    }
}