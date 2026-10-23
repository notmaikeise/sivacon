package br.com.sivacon.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sivacon.dao.UserDao;
import br.com.sivacon.dao.UserDao.UserRow;

@Service
public class AuthService {
    private static final Duration SESSION_DURATION = Duration.ofHours(8);
    private final UserDao dao;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResult login(String email, String password) {
        UserRow user = dao.findActiveByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.passwordHash())) {
            throw new org.springframework.security.authentication.BadCredentialsException(
                    "Credenciais inválidas.");
        }
        var token = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        var expiresAt = Instant.now().plus(SESSION_DURATION);
        dao.createSession(user.id(), hash(token), expiresAt);
        return new LoginResult(token, expiresAt, user.email(), user.displayName(), user.role());
    }

    public UserPrincipal authenticate(String token) {
        var session = dao.findValidSession(hash(token));
        return session == null ? null : new UserPrincipal(session.userId(), session.email(),
                session.displayName(), session.role());
    }

    private String hash(String token) {
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256")
                    .digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 indisponível.", ex);
        }
    }

    public record LoginResult(String token, Instant expiresAt, String email,
                              String displayName, String role) {}
    public record UserPrincipal(UUID id, String email, String displayName, String role) {}
}
