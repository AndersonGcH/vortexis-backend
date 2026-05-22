package com.vortexis.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys; // <- Asegúrate de que se importe
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import javax.crypto.SecretKey; // <- Asegúrate de que se importe
import java.io.IOException;
import java.nio.charset.StandardCharsets; // <- Asegúrate de que se importe
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // REGLA DE ORO: Esta clave DEBE ser idéntica a la de tu JwtService y medir mínimo 32 caracteres
    private final String SECRET_KEY = "mi_clave_super_secreta_y_muy_larga_para_que_funcione_hs256";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            // 1. Transformamos el String plano en una SecretKey HMAC segura
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            // 2. Usamos los métodos modernos para leer y validar el token
            Claims claims = Jwts.parser()
                    .verifyWith(key)           // Reemplaza a setSigningKey
                    .build()
                    .parseSignedClaims(token) // Reemplaza a parseClaimsJws
                    .getPayload();            // Reemplaza a getBody()

            String email = claims.getSubject();
            String rol = claims.get("rol", String.class);

            // 3. Registramos la autenticación en Spring Security
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority("ROLE_" + rol)
                            )
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {
            // Imprime el error real en la consola de tu IDE (IntelliJ/Eclipse) para ver si algo más falla
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}