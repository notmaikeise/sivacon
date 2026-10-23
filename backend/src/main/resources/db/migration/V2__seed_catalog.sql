INSERT INTO store_configuration (store_name, email)
VALUES ('SIVACon Motos', 'contato@sivacon.local');

INSERT INTO vehicle_model (brand, name, engine_description, category)
VALUES
    ('Honda', 'CG 160 Titan', '160cc', 'Street'),
    ('Yamaha', 'MT-03', '321cc', 'Naked');

INSERT INTO vehicle_unit (model_id, chassis, price, condition, status, published, description)
SELECT id, 'SIVACON-HONDA-001', 18990.00, 'NEW', 'AVAILABLE', true,
       'Moto urbana fictícia para demonstração do catálogo.'
FROM vehicle_model WHERE brand = 'Honda' AND name = 'CG 160 Titan';

INSERT INTO vehicle_unit (model_id, chassis, price, condition, status, published, description)
SELECT id, 'SIVACON-YAMAHA-001', 32990.00, 'USED', 'AVAILABLE', true,
       'Unidade fictícia usada, revisada para a demonstração.'
FROM vehicle_model WHERE brand = 'Yamaha' AND name = 'MT-03';
