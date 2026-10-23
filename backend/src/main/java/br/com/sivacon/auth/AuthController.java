package br.com.sivacon.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) { this.service = service; }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        try {
            var result = service.login(request.email(), request.password());
            return new AuthResponse(result.token(), result.expiresAt(), result.email(),
                    result.displayName(), result.role());
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos.");
        }
    }

    public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}
    public record AuthResponse(String token, java.time.Instant expiresAt, String email,
                               String displayName, String role) {}
}
