-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema spotify
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spotify
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spotify` DEFAULT CHARACTER SET utf8 ;
USE `spotify` ;

-- -----------------------------------------------------
-- Table `spotify`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotify`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spotify`.`credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotify`.`credentials` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dcrypt_password` VARCHAR(50) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `spotify`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spotify`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotify`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spotify`.`label`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotify`.`label` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spotify`.`playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotify`.`playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `number_of_songs` INT NULL DEFAULT NULL,
  `genre_id` INT NOT NULL,
  `label_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_playlist_genre_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_playlist_label1_idx` (`label_id` ASC) VISIBLE,
  INDEX `fk_playlist_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_genre`
    FOREIGN KEY (`genre_id`)
    REFERENCES `spotify`.`genre` (`id`),
  CONSTRAINT `fk_playlist_label1`
    FOREIGN KEY (`label_id`)
    REFERENCES `spotify`.`label` (`id`),
  CONSTRAINT `fk_playlist_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `spotify`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
