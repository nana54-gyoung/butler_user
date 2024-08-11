package com.butler.user.common.util

import com.butler.user.api.ui.vo.TokenVO
import com.butler.user.model.entity.MemberEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*


class JwtUtil {
    companion object {
        private val SECRET_KEY =
            "eyJhbGciOiJIUzUxMiJ9eyJtZW1iZXJJbmZvIjp7Im1ibm8iOjIsInVzZXJJZCI6ImFiZDJldm9sIiwicGFzc3dvcmQiOiIxMjM0NTYiLCJuYW1lIjoi6rSA7JqwIiwicmVnTm8iOiI2ODExMDgtMTU4MjgxNiJ9fQRf9XsREiE0d9kIOUMYlWv1smL698JN5CXhDCUW2ebrYIcUtz"

        fun createToken(member: MemberEntity): TokenVO {
            val claims = Jwts.claims()
            claims["role"] = member.role
            claims["mbno"] = member.mbno

            val now = Date()
            val utcExpirationDate = Date(now.time + 1000L * 60 * 60)

            return TokenVO(
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(utcExpirationDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact()
            )
        }

        fun extractMbno(token: String): Int {
            return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .body
                .get("mbno").toString().toInt()
        }

        fun extractAuthorities(token: String): List<String> {
            val res = Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .body
                .get("role").toString()
            return listOf(res)
        }

    }
}