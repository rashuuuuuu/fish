-- liquibase formatted sql
-- changeset rashmita.subedi:1

CREATE TABLE IF NOT EXISTS product
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    details       TEXT                  NOT NULL,
    code          VARCHAR(255)          NOT NULL,
    image         VARCHAR(255)          NOT NULL,
    species       VARCHAR(255)           NOT NULL,
    recorded_date    datetime               NOT NULL,
    is_present        BIT(1)                NOT NULL,
    status_product   BIGINT                NOT NULL
);


