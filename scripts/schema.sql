CREATE SCHEMA study;

CREATE USER study_user@'%'
  IDENTIFIED BY '1q2aw3zse4xdrcfv';

GRANT ALL ON study.* TO study_user@'%'
WITH GRANT OPTION;

USE study;

CREATE TABLE sinner (
  id        INT AUTO_INCREMENT NOT NULL,
  user_name VARCHAR(30)        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE karma (
  id              INT AUTO_INCREMENT NOT NULL,
  sinner_id       INT                NOT NULL,
  drunk_bottles   INT                NOT NULL,
  malicious_level INT                NOT NULL,
  seen_blasphemy  BOOLEAN,
  PRIMARY KEY (id),
  FOREIGN KEY (sinner_id) REFERENCES sinner (id)
);

ALTER TABLE karma ADD COLUMN is_processed BOOLEAN NOT NULL DEFAULT FALSE;