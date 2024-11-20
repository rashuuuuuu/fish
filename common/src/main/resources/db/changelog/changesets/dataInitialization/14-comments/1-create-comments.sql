-- liquibase formatted sql
--changeset rashmita.subedi:1

--preconditions onFail:CONTINUE onError:HALT

CREATE TABLE IF NOT EXISTS comment(
    id                BIGINT AUTO_INCREMENT      NOT NULL,
    version           BIGINT                     NOT NULL,
    subject          VARCHAR(255)                NOT NULL,
    description      TEXT                        NOT NULL,
    is_replied        BIT(1)                     NOT NULL,
    customer         BIGINT                      NOT NULL,
    recorded_date    DATE                        NOT NULL,
    code             VARCHAR(255)                NOT NULL,
    CONSTRAINT       pk_queries                  PRIMARY KEY (id)
);
