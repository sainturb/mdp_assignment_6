package miu.edu.foodieapp.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Calendar
import java.util.Date

class Token() {

    fun getToken(email: String, payload: String): String {
        val key = Keys.hmacShaKeyFor(ConstantsShared.secret.toByteArray())

        val jws = Jwts.builder()
            .setExpiration(getExpirationDate())
            .setIssuer(ConstantsShared.jwtIssuer)
            .setAudience(ConstantsShared.jwtAudience)
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(ConstantsShared.jwtSubject)
//            .setPayload(payload)
            .claim("email", email)
            .compact();
        return jws
    }

    private fun getExpirationDate(): Date {
        val cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 60)
        return cal.time
    }

}