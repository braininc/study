CREATE SCHEMA study;

CREATE USER study_user@'%'
  IDENTIFIED BY '1q2aw3zse4xdrcfv';

GRANT ALL ON study.* TO study_user@'%'
WITH GRANT OPTION;

USE study;

CREATE TABLE sinner (
  id       INT AUTO_INCREMENT NOT NULL,
  userName VARCHAR(30)        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE karma (
  id                INT AUTO_INCREMENT NOT NULL,
  sinner_id         INT                NOT NULL,
  drunkBottles      INT                NOT NULL,
  foulLanguageTimes INT                NOT NULL,
  maliciousLevel    INT                NOT NULL,
  seenBlasphemy     BOOLEAN,
  PRIMARY KEY (id),
  FOREIGN KEY (sinner_id) REFERENCES sinner (id)
);

CREATE SEQUENCE hibernate_sequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;