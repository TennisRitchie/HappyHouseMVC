USE `happyhouse` ;

-- -----------------------------------------------------
-- Table `ssafyweb`.`ssafy_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`ssafy_member` ;

CREATE TABLE IF NOT EXISTS `happyhouse`.`ssafy_member` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `email` VARCHAR(50) NULL,
  `address` VARCHAR(100) NULL,
  `joindate` TIMESTAMP NULL DEFAULT current_timestamp,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB;

INSERT INTO ssafy_member (userid, username, userpwd, email, address)
VALUES('admin', '관리자', 'admin', 'admin@ssafy.com','서울시 역삼동');

INSERT INTO ssafy_member (userid, username, userpwd, email, address)
VALUES('ssafy', '김싸피', 'ssafy', 'ssafy@ssafy.com','대전시 덕명동');

commit;