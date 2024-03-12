package com.sandbox.kotlinsandbox.mvc.auth.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class TokenProvider(
    @Value("\${auth.secret-key}")
    private val secretKey: String,
    @Value("\${auth.expiration-hours}")
    private val expirationHours: Long,
    @Value("\${auth.issuer}")
    private val issuer: String
) {

    fun createToken(userSpecification: String) = Jwts.builder()
        .signWith(
            SecretKeySpec(
                secretKey.toByteArray(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS512.jcaName
            )
        ) // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
        .setSubject(userSpecification)   // JWT 토큰 제목
        .setIssuer(issuer)    // JWT 토큰 발급자
        .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
        .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰의 만료시간 설정
        .compact()!!    // JWT 토큰 생성

    fun validateTokenAndGetSubject(token: String): String = Jwts.parserBuilder()
        .setSigningKey(secretKey.toByteArray(StandardCharsets.UTF_8))
        .build()
        .parseClaimsJws(token)
        .body
        .subject
}