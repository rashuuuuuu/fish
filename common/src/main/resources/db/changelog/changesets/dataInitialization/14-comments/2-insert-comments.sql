-- liquibase formatted sql
--changeset rashmita.subedi:1

--preconditions onFail:CONTINUE onError:HALT

INSERT INTO comment(subject, description,customer,recorded_date,code,version,is_replied)
    VALUES('Dummy subect', 'Dummy description',1,current_timestamp,uuid(),0,false);