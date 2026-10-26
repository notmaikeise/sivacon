package br.com.sivacon.dao;

import java.util.UUID;
import java.sql.Timestamp;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbc;

    public UserDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserRow findActiveByEmail(String email) {
        return jdbc.query("""
                SELECT id, email, password_hash, display_name, role
                FROM app_user WHERE lower(email) = lower(:email) AND active = true
                """, new MapSqlParameterSource("email", email),
                (rs, n) -> new UserRow(rs.getObject("id", UUID.class), rs.getString("email"),
                        rs.getString("password_hash"), rs.getString("display_name"),
                        rs.getString("role"))).stream().findFirst().orElse(null);
    }

    public UUID insertUser(String email, String passwordHash, String displayName, String role) {
        var id = UUID.randomUUID();
        jdbc.update("""
                INSERT INTO app_user (id, email, password_hash, display_name, role)
                VALUES (:id, :email, :passwordHash, :displayName, :role)
                """, new MapSqlParameterSource().addValue("id", id).addValue("email", email)
                .addValue("passwordHash", passwordHash).addValue("displayName", displayName)
                .addValue("role", role));
        return id;
    }

    public void insertBuyer(UUID id, String fullName, String email, String phone, UUID userId) {
        jdbc.update("""
                INSERT INTO buyer (id, full_name, email, phone, user_id)
                VALUES (:id, :fullName, :email, :phone, :userId)
                """, new MapSqlParameterSource().addValue("id", id).addValue("fullName", fullName)
                .addValue("email", email).addValue("phone", phone).addValue("userId", userId));
    }

    public void createSession(UUID userId, String tokenHash, java.time.Instant expiresAt) {
        jdbc.update("""
                INSERT INTO app_session (user_id, token_hash, expires_at)
                VALUES (:userId, :tokenHash, :expiresAt)
                """, new MapSqlParameterSource().addValue("userId", userId)
                .addValue("tokenHash", tokenHash)
                .addValue("expiresAt", Timestamp.from(expiresAt)));
    }

    public SessionRow findValidSession(String tokenHash) {
        return jdbc.query("""
                SELECT s.user_id, u.email, u.display_name, u.role
                FROM app_session s JOIN app_user u ON u.id = s.user_id
                WHERE s.token_hash = :tokenHash AND s.expires_at > CURRENT_TIMESTAMP
                  AND u.active = true
                """, new MapSqlParameterSource("tokenHash", tokenHash),
                (rs, n) -> new SessionRow(rs.getObject("user_id", UUID.class),
                        rs.getString("email"), rs.getString("display_name"),
                        rs.getString("role"))).stream().findFirst().orElse(null);
    }

    public record UserRow(UUID id, String email, String passwordHash, String displayName, String role) {}
    public record SessionRow(UUID userId, String email, String displayName, String role) {}
}
