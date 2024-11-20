-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO jumbo_crab (version, name, details, quantity, price, statusProduct, code, image, recorded_date)
VALUES (0, 'Fresh Dungeness Crab Meat',
        'Experience the mouth-watering taste of Seattle''s secret - Fresh Dungeness Crab Meat. No need to spend time cracking shells, just savor the succulent flavor! Pair it with crackers or elevate your salad game with this premium ingredient. ',
        '200kg',
        '$29.50 per pound', '1', uuid(), 'image', '2024-04-14');