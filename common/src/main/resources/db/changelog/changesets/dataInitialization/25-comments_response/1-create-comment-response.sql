-- liquibase formatted sql
--changeset rashmita.subedi:1

--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS comment_response
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    message       TEXT                  NOT NULL,
    code          BIGINT                NOT NULL,
    comment       VARCHAR(255)          NOT NULL,
    response_date DATE                  NOT NULL,
    CONSTRAINT pk_comment_response PRIMARY KEY (id)
);
