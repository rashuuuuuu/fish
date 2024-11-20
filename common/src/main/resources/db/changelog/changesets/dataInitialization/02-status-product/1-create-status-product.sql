-- liquibase formatted sql
--changeset rashmita.subedi:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS status_product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    icon          VARCHAR(255)          NULL,
    CONSTRAINT pk_status PRIMARY KEY (id)
);

