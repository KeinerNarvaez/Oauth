package com.example.Auth2.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import org.springframework.stereotype.Component;
import javax.json.Json;
import javax.json.JsonObject;


@Component  // Asegúrate de que esta anotación esté aquí
public class JwtUtil {
    String SECRET_KEY = "K5n7jDd@#3vLx!2PqRs9zTgUvW0xYbM1"; // Puedes cargar esto desde application.properties
    // Generar el token JWT
    public JsonObject generateTokenAsJson(String email, String name) {
        String jwt = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim("name", name)
                .compact();
    
        return Json.createObjectBuilder()
                .add("jwt", jwt)
                .build();
    }
    

    // Extraer el nombre de usuario (correo) del token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Verificar si el token ha expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
