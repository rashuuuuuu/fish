-- liquibase formatted sql
--changeset rashmita.subedi:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `customer_unblock_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     TEXT                  NOT NULL,
    admin       BIGINT                NOT NULL,
    customer    BIGINT                NOT NULL,
    unblocked_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_customer_block_log PRIMARY KEY (id)
    );