-- liquibase formatted sql
--changeset rashmita.subedi:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `admin_block_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     TEXT                  NOT NULL,
    admin       BIGINT                NOT NULL,
    blocked_by   VARCHAR(255)        NOT NULL,
    blocked_date  DATE                NOT NULL,
    CONSTRAINT pk_admin_block_log PRIMARY KEY (id)
    );