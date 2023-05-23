-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

USE heroku_93eb5d7b9e41d59;
-- -----------------------------------------------------
-- Table `tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tour` (
  `seq` INT NOT NULL AUTO_INCREMENT, -- 관광지 seq
  `title` VARCHAR(20) NOT NULL, -- 제목
  `type_no` INT NULL DEFAULT NULL, -- 컨텐츠 번호
  `type_name` VARCHAR(10) NULL DEFAULT NULL, -- 컨텐츠 이름
  `telephone` VARCHAR(20) NULL DEFAULT NULL, -- 전화번호
  `address` VARCHAR(50) NOT NULL, -- 주소
  `latitude` FLOAT NOT NULL, -- 위도
  `longitude` FLOAT NOT NULL, -- 경도
  `hits` INT NULL DEFAULT '0', -- 조회수
  PRIMARY KEY (`seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `seq` INT NOT NULL AUTO_INCREMENT, -- 사용자 seq
  `id` VARCHAR(20) NOT NULL, -- 사용자 id
  `password` VARCHAR(30) NOT NULL, -- 비밀번호
  `name` VARCHAR(20) NOT NULL, -- 닉네임
  `email` VARCHAR(40) NOT NULL, -- 이메일
  `phone_no` VARCHAR(20) NULL DEFAULT NULL, -- 폰 번호
  `intro` VARCHAR(100) NULL DEFAULT NULL, -- 간단 자기소개
  `join_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP, -- 가입 날짜
  `modified_date` DATETIME NULL DEFAULT NULL, -- 수정 날짜
  `withdrawal_date` DATETIME NULL DEFAULT NULL, -- 탈퇴 날짜
  PRIMARY KEY (`seq`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review` (
  `seq` INT NOT NULL AUTO_INCREMENT, -- 후기 seq
  `title` VARCHAR(30) NOT NULL, -- 제목
  `content` VARCHAR(500) NOT NULL, -- 내용
  `user_name` VARCHAR(20) NOT NULL, -- 사용자 이름
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP, -- 생성 날짜
  `modified_date` DATETIME NULL DEFAULT NULL, -- 수정 날짜
  `deleted_date` DATETIME NULL DEFAULT NULL, -- 삭제 날짜
  PRIMARY KEY (`seq`)
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `review_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review_image` (
  `seq` INT NOT NULL, -- 사진 seq
  `review_seq` INT NOT NULL, -- 후기 seq
  `image_name` VARCHAR(50) NOT NULL, -- 사진 이름 (사용자용)
  `image_code` VARCHAR(100) NOT NULL, -- 사진 이름 (서버 저장용)
  PRIMARY KEY (`seq`)
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plan` (	
  `seq` INT NOT NULL AUTO_INCREMENT, -- 계획 seq
  `user_id` VARCHAR(20) NOT NULL, -- 사용자 id
  `title` VARCHAR(20) NOT NULL, -- 제목
  `content` VARCHAR(500) NOT NULL, -- 세부 설명
  `expected_time` VARCHAR(20), -- 예상 시간
  `expected_distance` FLOAT, -- 예상 거리
  `taxi_cost` INT, -- 택시비
  `fuel_cost` INT, -- 주유비
  `created_time` TIMESTAMP, -- 생성 날짜
  PRIMARY KEY (`seq`)
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `plan_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plan_order` (	
  `seq` INT NOT NULL AUTO_INCREMENT, -- 계획 순서 seq
  `plan_seq` INT NOT NULL, -- 계획 seq
  `order` INT NOT NULL, -- 순서
  PRIMARY KEY (`seq`)
  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `favorite` (	
  `seq` INT NOT NULL AUTO_INCREMENT, -- 즐겨찾기 seq
  `tour_seq` INT NOT NULL, -- 관광지 seq
  `user_id` INT NOT NULL, -- 사용자 id
  PRIMARY KEY (`seq`)
  )
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
