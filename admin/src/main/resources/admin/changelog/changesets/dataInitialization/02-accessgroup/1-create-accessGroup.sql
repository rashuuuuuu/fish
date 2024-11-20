-- liquibase formatted sql
-- changeset rashmita.subedi:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS access_group
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    version              BIGINT                NOT NULL,
    name                 VARCHAR(255)          NOT NULL,
    `description`        VARCHAR(255)          NULL,
    created_at           datetime              NULL,
    updated_at           datetime              NULL,
    status               BIGINT                NOT NULL,
    is_super_admin_group BIT(1)                NOT NULL,
    remarks              VARCHAR(255)          NULL,
    CONSTRAINT pk_access_group PRIMARY KEY (id)
    );
