package br.com.phamtecnologia.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtBearerComponent {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String createToken(String user, String role) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600 * 1000);

        String token = Jwts.builder()
                .setSubject(user)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return token;
    }

}
