-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO shellfish (version, name, details, quantity, price, statusProduct, code, image, recorded_date)
VALUES (0, 'Jumbo! Whole Dungeness Crab',
        'Experience the unparalleled flavor of the Dungeness Crab, known for its sweet and succulent taste. Wild-caught from the pristine waters of the North Pacific coastline, these crabs are a true Pacific Northwest delicacy! Our Whole Dungeness Crabs are expertly pre-cooked and ready to be steamed to perfection, whether you prefer them hot or chilled. Enjoy with our signature cocktail sauce or melted butter for an unforgettable dining experience.',
        '200kg',
        '$29.50 per pound', '1',uuid(), 'image', '2024-04-14');