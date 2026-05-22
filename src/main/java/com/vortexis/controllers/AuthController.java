package com.vortexis.controllers;

import com.vortexis.dto.LoginRequestDTO;
import com.vortexis.dto.LoginResponseDTO;
import com.vortexis.entities.Usuario;
import com.vortexis.repositories.UsuarioRepository;
import com.vortexis.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        Usuario usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        )) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(
                usuario.getEmail(),
                usuario.getRol().name()
        );

        return new LoginResponseDTO(token);
    }
}
