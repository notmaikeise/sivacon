package br.com.sivacon.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sivacon.dao.UserDao;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String PASSWORD_HASH =
            "$2a$10$45n981U4AUhNFy0N8YlMN.KLgBiEQ3B3AlZu3m2zn2dQxtp.YbSve";

    @Mock
    private UserDao dao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService service;

    @Test
    void createsSessionForValidCredentials() {
        var user = new UserDao.UserRow(UUID.randomUUID(), "admin@example.com",
                PASSWORD_HASH, "Administrador local", "ADMIN");
        when(dao.findActiveByEmail("admin@example.com")).thenReturn(user);
        when(passwordEncoder.matches("password", PASSWORD_HASH)).thenReturn(true);

        var result = service.login("admin@example.com", "password");

        assertThat(result.token()).isNotBlank();
        assertThat(result.role()).isEqualTo("ADMIN");
    }

    @Test
    void rejectsInvalidCredentials() {
        var user = new UserDao.UserRow(UUID.randomUUID(), "admin@example.com",
                PASSWORD_HASH, "Administrador local", "ADMIN");
        when(dao.findActiveByEmail("admin@example.com")).thenReturn(user);
        when(passwordEncoder.matches("wrong", PASSWORD_HASH)).thenReturn(false);

        assertThatThrownBy(() -> service.login("admin@example.com", "wrong"))
                .isInstanceOf(BadCredentialsException.class);
    }

}
