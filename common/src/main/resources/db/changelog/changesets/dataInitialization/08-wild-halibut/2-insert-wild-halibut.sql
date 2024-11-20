-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO wild_halibut (version, name, details, quantity, price, statusProduct, code, image, recorded_date)
VALUES (0, 'JFRESH Alaskan Halibut Fillet!!',
        'Alaskan Halibut Fillet offers a delicate taste and a soft texture, making it a favorite among seafood lovers worldwide. As a versatile option for dinner, it can be prepared through baking or broiling.',
        '200kg',
        '$29.50 per pound', '1', uuid(), 'image', '2024-04-14');