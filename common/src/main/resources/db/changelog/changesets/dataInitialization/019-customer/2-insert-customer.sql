-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO customer (first_name, last_name, password, username, is_active, email, mobile_number,status, wrong_password_attempt_count, two_factor_enabled, wrong_oto_auth_attempt_count, version)
VALUES('rashmita', 'subedi', '$2a$12$ohszArwmIDp3RjFuP7z6SO09QAYe3cARZyD6mmJ7XsP5NOmjr6p6i', 'rashmita111',  true, 'rashmitasubedi@gmail.com', '9811178782', 1, 1, 1, 0,  0);