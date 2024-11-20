-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `customer`
(
    id                           BIGINT AUTO_INCREMENT NOT NULL,
    version                      BIGINT                NOT NULL,
    first_name                   VARCHAR(255)          NOT NULL,
    last_name                    VARCHAR(255)          NOT NULL,
    password                     VARCHAR(255)          NOT NULL,
    username                     VARCHAR(255)          NOT NULL,
    is_active                    BIT(1)                NOT NULL,
    email                        VARCHAR(255)          NOT NULL UNIQUE,
    mobile_number                VARCHAR(255)          NOT NULL UNIQUE,
    status                       BIGINT                NOT NULL,
    registered_date              datetime              NULL,
    password_changed_date        datetime              NULL,
    last_logged_in_time          datetime              NULL,
    wrong_password_attempt_count INT                   NULL,
    profile_picture_name         VARCHAR(255)          NULL,
    otp_auth_secret              VARCHAR(255)          NULL,
    two_factor_enabled           BIT(1)                NOT NULL,
    wrong_oto_auth_attempt_count INT                   NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);



--changeset rashmita.subedi:2
--precondition-on-fail:MARK_RAN
--preconditions
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'customer' AND constraint_name = 'FK_CUSTOMER_ON_STATUS'
ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);