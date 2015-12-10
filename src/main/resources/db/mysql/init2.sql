CREATE DATABASE IF NOT EXISTS javafx CHARACTER SET utf8 COLLATE utf8_unicode_ci;
GRANT ALL PRIVILEGES ON root.* TO root@localhost IDENTIFIED BY 'root';

USE javafx;

CREATE TABLE IF NOT EXISTS persons (
  id 			VARCHAR(64) PRIMARY KEY,
  firstName  	VARCHAR(64),
  lastName    VARCHAR(64),
  street     	VARCHAR(64),
  postalCode  INT(5),
  city     	VARCHAR(64),
  birthday 	TIMESTAMP
)engine=InnoDB;