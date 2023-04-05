-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ausias_bici
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ausias_bici
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ausias_bici` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `ausias_bici` ;

-- -----------------------------------------------------
-- Table `ausias_bici`.`bicicleta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ausias_bici`.`bicicleta` (
  `id_bicicleta` INT NOT NULL,
  `disponibilidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_bicicleta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `ausias_bici`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ausias_bici`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `id_bicicleta` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_usuario_bicicleta_idx` (`id_bicicleta` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_bicicleta`
    FOREIGN KEY (`id_bicicleta`)
    REFERENCES `ausias_bici`.`bicicleta` (`id_bicicleta`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `ausias_bici`.`bicicleta` (`id_bicicleta`, `disponibilidad`) VALUES ('0', 'true');
INSERT INTO `ausias_bici`.`bicicleta` (`id_bicicleta`, `disponibilidad`) VALUES ('1', 'false');
INSERT INTO `ausias_bici`.`bicicleta` (`id_bicicleta`, `disponibilidad`) VALUES ('2', 'true');
INSERT INTO `ausias_bici`.`bicicleta` (`id_bicicleta`, `disponibilidad`) VALUES ('3', 'true');
INSERT INTO `ausias_bici`.`bicicleta` (`id_bicicleta`, `disponibilidad`) VALUES ('4', 'false');


INSERT INTO `ausias_bici`.`usuario` (`id_usuario`, `nombre`, `telefono`, `dni`, `id_bicicleta`) VALUES ('1', 'Gonzalo', '678898989', '2323232G', '0');
INSERT INTO `ausias_bici`.`usuario` (`id_usuario`, `nombre`, `telefono`, `dni`, `id_bicicleta`) VALUES ('2', 'Lucía', '789898989', '25353544H', '1');
INSERT INTO `ausias_bici`.`usuario` (`id_usuario`, `nombre`, `telefono`, `dni`, `id_bicicleta`) VALUES ('3', 'Pepe', '765656565', '1212121O', '4');
INSERT INTO `ausias_bici`.`usuario` (`id_usuario`, `nombre`, `telefono`, `dni`, `id_bicicleta`) VALUES ('4', 'Juan', '654545434', '65454545P', '0');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
