-- liquibase formatted sql
--changeset rashmita.subedi:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS access_group_role_map
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    version      BIGINT                NOT NULL,
    access_group BIGINT                NOT NULL,
    is_active    BIT(1)                NOT NULL,
    roles        BIGINT                NOT NULL,
    CONSTRAINT pk_access_group_role_map PRIMARY KEY (id)
    );
