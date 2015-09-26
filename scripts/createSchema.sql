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