CREATE TABLE app_session (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES app_user(id),
    token_hash CHAR(64) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_app_session_token ON app_session (token_hash);
CREATE INDEX idx_app_session_expiration ON app_session (expires_at);

-- Usuário local de demonstração. Senha: password
INSERT INTO app_user (email, password_hash, display_name, role)
VALUES (
    'admin@example.com',
    '$2a$10$45n981U4AUhNFy0N8YlMN.KLgBiEQ3B3AlZu3m2zn2dQxtp.YbSve',
    'Administrador local',
    'ADMIN'
);
