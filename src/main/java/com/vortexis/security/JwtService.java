package com.vortexis.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys; // <- Asegúrate de importar esto
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey; // <- Asegúrate de importar esto
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // IMPORTANTE: Para el algoritmo HS256, la clave debe tener al menos 256 bits (32 caracteres).
    // Si es más corta que esto, Spring Boot te lanzará otro error de "Weak Key" (Clave débil).
    private final String SECRET_KEY = "mi_clave_super_secreta_y_muy_larga_para_que_funcione_hs256";

    public String generarToken(String email, String rol) {

        // Convertimos el String de texto plano en una SecretKey válida para HMAC-SHA
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1 hora
                )
                .signWith(key) // <- Aquí le pasas directamente el objeto 'key' moderno
                .compact();
    }
}