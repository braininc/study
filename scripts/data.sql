CREATE PROCEDURE generate_sinners()
  BEGIN
    DECLARE count INT DEFAULT 0;

    WHILE count <= 1000 DO
      SET count = count + 1;
      INSERT INTO sinner (userName) VALUES (substr(md5(round(rand() * 100000)), 1, 7));
      SET @sinner_id = last_insert_id();

      DECLARE size INT;
      SET size = round(rand()) + round(rand()) + round(rand());
      INSERT INTO karma (sinner_id, drunkBottles, maliciousLevel, seenBlasphemy)
      VALUES (@sinner_id, round(rand() * 1000), round(rand() * 100), round(rand()));
      INSERT INTO karma (sinner_id, drunkBottles, maliciousLevel, seenBlasphemy)
      VALUES (@sinner_id, round(rand() * 1000), round(rand() * 100), round(rand()));
    END WHILE;

  END;

CALL generate_sinners();

