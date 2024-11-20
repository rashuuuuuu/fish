-- liquibase formatted sql
--changeset rashmita.subedi:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS roles
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    icon          VARCHAR(255)          NULL,
    navigation    VARCHAR(255)          NULL,
    position      INT                   NULL,
    ui_group_name VARCHAR(255)          NULL,
    parent_role   BIGINT                NULL,
    parent_name   VARCHAR(255)          NULL,
    permission    VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
    );




