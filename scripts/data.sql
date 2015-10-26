USE study;

DROP PROCEDURE IF EXISTS gegenerate_sinners;

CREATE PROCEDURE generate_sinners(amount INT)
  BEGIN
    DECLARE count INT DEFAULT 0;
    DECLARE size INT DEFAULT 0;

    WHILE count <= amount DO
      SET count = count + 1;
      INSERT INTO sinner (user_name) VALUES (concat('Vasiliy_', substr(md5(round(rand() * 100000)), 1, 7)));
      SET @sinner_id = last_insert_id();
      SET size = round(rand()) + round(rand()) + round(rand());
      INSERT INTO karma (sinner_id, drunk_bottles, malicious_level, seen_blasphemy)
      VALUES (@sinner_id, round(rand() * 1000), round(rand() * 100), round(rand()));
      INSERT INTO karma (sinner_id, drunk_bottles, malicious_level, seen_blasphemy)
      VALUES (@sinner_id, round(rand() * 1000), round(rand() * 100), round(rand()));
    END WHILE;

  END;

CALL generate_sinners(35);

