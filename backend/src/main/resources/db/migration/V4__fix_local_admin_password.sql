-- Senha do usuário local: password
UPDATE app_user
SET password_hash = '$2a$10$45n981U4AUhNFy0N8YlMN.KLgBiEQ3B3AlZu3m2zn2dQxtp.YbSve'
WHERE email = 'admin@example.com';
