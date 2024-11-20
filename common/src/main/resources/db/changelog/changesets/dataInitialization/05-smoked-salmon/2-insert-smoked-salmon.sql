-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO smoked_salmon (version, name, details, quantity, price, statusProduct, code, image, recorded_date)
VALUES (0, 'Fresh Alderwood Smoked Salmon',
        'Moist King Salmon are lightly salted and then hot smoked over Alderwood coals to flaky perfection. A delicacy native to the Northwest but treasured the world over. This smoked salmon is sold fresh-cooked and ready to eat.',
        '200kg',
        '$29.50 per pound', '1', uuid(), 'image', '2024-04-14');