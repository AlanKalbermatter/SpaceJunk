-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Basura_espacial
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Basura_espacial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Basura_espacial` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `Basura_espacial` ;

-- -----------------------------------------------------
-- Table `Basura_espacial`.`Agencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Agencia` (
  `nombre` VARCHAR(50) NOT NULL,
  `numero_personas` INT NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Tipo_De_Nave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Tipo_De_Nave` (
  `cod` INT NOT NULL,
  PRIMARY KEY (`cod`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Nave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Nave` (
  `cod` INT NOT NULL,
  `matricula` INT NOT NULL,
  `mision` VARCHAR(50) NOT NULL,
  `agencia_name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE INDEX `cod` (`cod` ASC, `matricula` ASC) VISIBLE,
  INDEX `Nave_ibfk_2` (`agencia_name` ASC) VISIBLE,
  CONSTRAINT `Nave_ibfk_1`
    FOREIGN KEY (`cod`)
    REFERENCES `Basura_espacial`.`Tipo_De_Nave` (`cod`)
    ON UPDATE CASCADE,
  CONSTRAINT `Nave_ibfk_2`
    FOREIGN KEY (`agencia_name`)
    REFERENCES `Basura_espacial`.`Agencia` (`nombre`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Orbita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Orbita` (
  `id` INT NOT NULL,
  `coordR` DOUBLE NOT NULL,
  `coordFi` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Basura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Basura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cod_nav` INT NOT NULL,
  `speed` DOUBLE NULL DEFAULT NULL,
  `weight` DOUBLE NULL DEFAULT NULL,
  `size` DOUBLE NULL DEFAULT NULL,
  `orbita_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `cod_nav` (`cod_nav` ASC) VISIBLE,
  INDEX `orbita_id` (`orbita_id` ASC) VISIBLE,
  CONSTRAINT `Basura_ibfk_1`
    FOREIGN KEY (`cod_nav`)
    REFERENCES `Basura_espacial`.`Nave` (`cod`)
    ON UPDATE CASCADE,
  CONSTRAINT `Basura_ibfk_2`
    FOREIGN KEY (`orbita_id`)
    REFERENCES `Basura_espacial`.`Orbita` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Circular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Circular` (
  `id` INT NOT NULL,
  `orbita_id` INT NOT NULL,
  `geoestacionaria` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `Circular_ibfk_1`
    FOREIGN KEY (`orbita_id`)
    REFERENCES `Basura_espacial`.`Orbita` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Componentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Componentes` (
  `cod` INT NOT NULL,
  `diametro` DOUBLE NOT NULL,
  `peso` DOUBLE NOT NULL,
  PRIMARY KEY (`cod`),
  CONSTRAINT `Componentes_ibfk_1`
    FOREIGN KEY (`cod`)
    REFERENCES `Basura_espacial`.`Tipo_De_Nave` (`cod`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Eliptica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Eliptica` (
  `id` INT NOT NULL,
  `orbita_id` INT NOT NULL,
  `excentricidad` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `Eliptica_ibfk_1`
    FOREIGN KEY (`orbita_id`)
    REFERENCES `Basura_espacial`.`Orbita` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Empresa` (
  `CIF` VARCHAR(11) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  `capital` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`CIF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Estado` (
  `nombre` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Lanza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Lanza` (
  `cod` INT NOT NULL,
  `orbita_id` INT NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`cod`, `orbita_id`, `nombre`),
  INDEX `orbita_id` (`orbita_id` ASC) VISIBLE,
  INDEX `nombre` (`nombre` ASC) VISIBLE,
  CONSTRAINT `Lanza_ibfk_1`
    FOREIGN KEY (`orbita_id`)
    REFERENCES `Basura_espacial`.`Orbita` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `Lanza_ibfk_2`
    FOREIGN KEY (`cod`)
    REFERENCES `Basura_espacial`.`Nave` (`cod`)
    ON UPDATE CASCADE,
  CONSTRAINT `Lanza_ibfk_3`
    FOREIGN KEY (`nombre`)
    REFERENCES `Basura_espacial`.`Agencia` (`nombre`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Navega_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Navega_en` (
  `cod` INT NOT NULL,
  `orbita_id` INT NOT NULL,
  `fecha_ini` DATE NULL DEFAULT NULL,
  `hora_ini` TIME NULL DEFAULT NULL,
  `fecha_fin` DATE NULL DEFAULT NULL,
  `hora_fin` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`cod`, `orbita_id`),
  INDEX `orbita_id` (`orbita_id` ASC) VISIBLE,
  CONSTRAINT `Navega_en_ibfk_1`
    FOREIGN KEY (`orbita_id`)
    REFERENCES `Basura_espacial`.`Orbita` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `Navega_en_ibfk_2`
    FOREIGN KEY (`cod`)
    REFERENCES `Basura_espacial`.`Nave` (`cod`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Posicion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Posicion` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `coordR` DOUBLE NULL DEFAULT NULL,
  `coordFi` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`fecha`, `hora`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Publica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Publica` (
  `clave_publica` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`clave_publica`),
  CONSTRAINT `Publica_ibfk_1`
    FOREIGN KEY (`clave_publica`)
    REFERENCES `Basura_espacial`.`Agencia` (`nombre`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Privada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Privada` (
  `clave_privada` VARCHAR(50) NOT NULL,
  `clave_publica` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`clave_privada`),
  INDEX `clave_publica` (`clave_publica` ASC) VISIBLE,
  CONSTRAINT `Privada_ibfk_1`
    FOREIGN KEY (`clave_privada`)
    REFERENCES `Basura_espacial`.`Agencia` (`nombre`)
    ON UPDATE CASCADE,
  CONSTRAINT `Privada_ibfk_2`
    FOREIGN KEY (`clave_publica`)
    REFERENCES `Basura_espacial`.`Publica` (`clave_publica`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Tiene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Tiene` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`, `fecha`, `hora`),
  INDEX `fecha` (`fecha` ASC, `hora` ASC) VISIBLE,
  CONSTRAINT `Tiene_ibfk_1`
    FOREIGN KEY (`id`)
    REFERENCES `Basura_espacial`.`Basura` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Tiene_ibfk_2`
    FOREIGN KEY (`fecha` , `hora`)
    REFERENCES `Basura_espacial`.`Posicion` (`fecha` , `hora`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`Tripulantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`Tripulantes` (
  `dni` INT NOT NULL,
  `nombre` VARCHAR(15) NOT NULL,
  `cod_nav` INT NOT NULL,
  PRIMARY KEY (`dni`),
  INDEX `cod_nav` (`cod_nav` ASC) VISIBLE,
  CONSTRAINT `Tripulantes_ibfk_1`
    FOREIGN KEY (`cod_nav`)
    REFERENCES `Basura_espacial`.`Nave` (`cod`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`es_financiada_por`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`es_financiada_por` (
  `clave_nombre` VARCHAR(30) NOT NULL,
  `clave_publica` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`clave_nombre`, `clave_publica`),
  INDEX `clave_publica` (`clave_publica` ASC) VISIBLE,
  CONSTRAINT `es_financiada_por_ibfk_1`
    FOREIGN KEY (`clave_nombre`)
    REFERENCES `Basura_espacial`.`Estado` (`nombre`)
    ON UPDATE CASCADE,
  CONSTRAINT `es_financiada_por_ibfk_2`
    FOREIGN KEY (`clave_publica`)
    REFERENCES `Basura_espacial`.`Publica` (`clave_publica`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Basura_espacial`.`financiada_por`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basura_espacial`.`financiada_por` (
  `clave_empresa` VARCHAR(30) NOT NULL,
  `clave_privada` VARCHAR(30) NOT NULL,
  `porcentaje` DOUBLE NOT NULL,
  PRIMARY KEY (`clave_empresa`, `clave_privada`),
  INDEX `clave_privada` (`clave_privada` ASC) VISIBLE,
  CONSTRAINT `financiada_por_ibfk_1`
    FOREIGN KEY (`clave_empresa`)
    REFERENCES `Basura_espacial`.`Empresa` (`CIF`)
    ON UPDATE CASCADE,
  CONSTRAINT `financiada_por_ibfk_2`
    FOREIGN KEY (`clave_privada`)
    REFERENCES `Basura_espacial`.`Privada` (`clave_privada`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
