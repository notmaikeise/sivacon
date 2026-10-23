CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE store_configuration (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    store_name VARCHAR(160) NOT NULL,
    document VARCHAR(32),
    email VARCHAR(254),
    phone VARCHAR(32),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(254) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(160) NOT NULL,
    role VARCHAR(32) NOT NULL CHECK (role IN ('ADMIN', 'MANAGER', 'SELLER', 'STOCK')),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE buyer (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(160) NOT NULL,
    document VARCHAR(32),
    email VARCHAR(254),
    phone VARCHAR(32),
    user_id UUID UNIQUE REFERENCES app_user(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vehicle_model (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    brand VARCHAR(100) NOT NULL,
    name VARCHAR(120) NOT NULL,
    engine_description VARCHAR(120),
    category VARCHAR(80),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (brand, name)
);

CREATE TABLE vehicle_unit (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model_id UUID NOT NULL REFERENCES vehicle_model(id),
    chassis VARCHAR(80) NOT NULL UNIQUE,
    price NUMERIC(14, 2) NOT NULL CHECK (price >= 0),
    condition VARCHAR(20) NOT NULL CHECK (condition IN ('NEW', 'USED')),
    status VARCHAR(20) NOT NULL CHECK (status IN ('DRAFT', 'AVAILABLE', 'SOLD', 'INACTIVE')),
    published BOOLEAN NOT NULL DEFAULT FALSE,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_vehicle_unit_catalog ON vehicle_unit (status, published);
CREATE INDEX idx_vehicle_unit_model ON vehicle_unit (model_id);

CREATE TABLE service_opportunity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    buyer_id UUID NOT NULL REFERENCES buyer(id),
    vehicle_unit_id UUID REFERENCES vehicle_unit(id),
    source VARCHAR(40) NOT NULL,
    stage VARCHAR(32) NOT NULL,
    assigned_to UUID REFERENCES app_user(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE service_interaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    opportunity_id UUID NOT NULL REFERENCES service_opportunity(id),
    author_id UUID REFERENCES app_user(id),
    channel VARCHAR(32) NOT NULL,
    content TEXT NOT NULL,
    occurred_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proposal (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    buyer_id UUID NOT NULL REFERENCES buyer(id),
    vehicle_unit_id UUID NOT NULL REFERENCES vehicle_unit(id),
    status VARCHAR(24) NOT NULL CHECK (status IN ('DRAFT', 'PENDING_APPROVAL', 'APPROVED', 'REJECTED', 'EXPIRED', 'CLOSED')),
    current_version INTEGER NOT NULL DEFAULT 1 CHECK (current_version > 0),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proposal_version (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proposal_id UUID NOT NULL REFERENCES proposal(id),
    version_number INTEGER NOT NULL CHECK (version_number > 0),
    negotiated_price NUMERIC(14, 2) NOT NULL CHECK (negotiated_price >= 0),
    down_payment NUMERIC(14, 2) NOT NULL DEFAULT 0 CHECK (down_payment >= 0),
    discount NUMERIC(14, 2) NOT NULL DEFAULT 0 CHECK (discount >= 0),
    payment_method VARCHAR(32) NOT NULL,
    valid_until DATE NOT NULL,
    terms JSONB NOT NULL DEFAULT '{}'::jsonb,
    created_by UUID REFERENCES app_user(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (proposal_id, version_number)
);

CREATE TABLE sale (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proposal_id UUID NOT NULL UNIQUE REFERENCES proposal(id),
    vehicle_unit_id UUID NOT NULL UNIQUE REFERENCES vehicle_unit(id),
    buyer_id UUID NOT NULL REFERENCES buyer(id),
    status VARCHAR(24) NOT NULL CHECK (status IN ('CONFIRMED', 'CANCELLED')),
    confirmed_by UUID REFERENCES app_user(id),
    confirmed_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE simulated_installment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proposal_version_id UUID NOT NULL REFERENCES proposal_version(id),
    installment_number INTEGER NOT NULL CHECK (installment_number > 0),
    due_date DATE NOT NULL,
    amount NUMERIC(14, 2) NOT NULL CHECK (amount >= 0),
    UNIQUE (proposal_version_id, installment_number)
);

CREATE TABLE audit_event (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    actor_id UUID REFERENCES app_user(id),
    event_type VARCHAR(80) NOT NULL,
    aggregate_type VARCHAR(80) NOT NULL,
    aggregate_id UUID,
    payload JSONB NOT NULL DEFAULT '{}'::jsonb,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
