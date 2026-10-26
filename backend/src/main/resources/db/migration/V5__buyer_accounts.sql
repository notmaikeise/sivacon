ALTER TABLE app_user DROP CONSTRAINT app_user_role_check;
ALTER TABLE app_user ADD CONSTRAINT app_user_role_check
    CHECK (role IN ('ADMIN', 'MANAGER', 'SELLER', 'STOCK', 'BUYER'));

CREATE UNIQUE INDEX ux_buyer_email_lower ON buyer (lower(email))
    WHERE email IS NOT NULL;
