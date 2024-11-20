-- liquibase formatted sql
-- changeset rashmita.subedi:1

CREATE TABLE IF NOT EXISTS jumbo_crab
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    details       TEXT                  NOT NULL,
    code          VARCHAR(255)          NOT NULL,
    image         VARCHAR(255)          NOT NULL,
    quantity      VARCHAR(255)          NOT NULL,
    price         VARCHAR(255)          NOT NULL,
    recorded_date    datetime               NOT NULL,
    statusProduct BIGINT                NOT NULL
);
