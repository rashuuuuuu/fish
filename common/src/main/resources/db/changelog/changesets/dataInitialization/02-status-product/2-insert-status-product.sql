-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM status_product
INSERT INTO status_product (description, icon, name, version)
VALUES ('AVAILABLE', 'available', 'AVAILABLE', 0),
       ('OUTOFSTOCK', 'outofstock', 'OUTOFSTOCK', 0),
       ('UNBLOCKED', 'unblocked', 'UNBLOCKED', 0),
       ('BLOCKED', 'blocked', 'BLOCKED', 0),
       ('DELETED', 'deleted', 'DELETED', 0);