-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM admin where username='superadmin'
INSERT INTO admin (name, password, username, is_active, email, mobile_number, status_id, access_group, wrong_password_attempt_count, two_factor_enabled, wrong_oto_auth_attempt_count, is_super_admin,version,code)
VALUES
    ('Super Admin', 'rashmita', 'superadmin', true, 'rashmitasubedi45@gmail.com', '9808590388', 1, 1, 0, false, 0, true,0,'dhgkjksk');

