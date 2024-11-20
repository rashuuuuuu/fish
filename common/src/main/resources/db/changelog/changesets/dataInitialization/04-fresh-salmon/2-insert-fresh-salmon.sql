-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO fresh_salmon (version, name, details, quantity, price, statusProduct, code, image, recorded_date)
VALUES (0, 'Fresh Northwest King Salmon Fillet',
        'King Salmon is a favorite everywhere. A true Seattle tradition. It is high in essential Omega-3 fatty acids, and no other fish has a more distinctive flavor than Fresh King Salmon.',
        '200kg',
        '$29.50 per pound', '1', uuid(), 'image', '2024-04-14');