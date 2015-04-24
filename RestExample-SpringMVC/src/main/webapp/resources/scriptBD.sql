-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.27 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para rest_example_spring_mvc
CREATE DATABASE IF NOT EXISTS `rest_example_spring_mvc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rest_example_spring_mvc`;


-- Volcando estructura para tabla rest_example_spring_mvc.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dni` varchar(8) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1429596846973 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla rest_example_spring_mvc.empleados: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`id`, `dni`, `nombre`, `apellidos`) VALUES
	(1, '46282875', 'william', 'marchan'),
	(2, '11223344', 'Danny', 'marchan aranda');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;


-- Volcando estructura para tabla rest_example_spring_mvc.springperfiles
CREATE TABLE IF NOT EXISTS `springperfiles` (
  `id` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla rest_example_spring_mvc.springperfiles: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `springperfiles` DISABLE KEYS */;
INSERT INTO `springperfiles` (`id`, `descripcion`) VALUES
	('ROLE_ADMINISTRADOR', 'Rol Admin'),
	('ROLE_EMPLEADO', 'Rol Empleado');
/*!40000 ALTER TABLE `springperfiles` ENABLE KEYS */;


-- Volcando estructura para tabla rest_example_spring_mvc.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) DEFAULT NULL,
  `clave` varchar(200) DEFAULT NULL,
  `expirado` varchar(1) DEFAULT 'N',
  `bloqueado` varchar(1) DEFAULT 'N',
  `credencial_expirado` varchar(1) DEFAULT 'N',
  `habilitado` varchar(1) DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla rest_example_spring_mvc.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `usuario`, `clave`, `expirado`, `bloqueado`, `credencial_expirado`, `habilitado`) VALUES
	(1, 'admin', 'admin', 'N', 'N', 'N', 'S'),
	(2, 'user', 'user', 'N', 'N', 'N', 'S');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;


-- Volcando estructura para tabla rest_example_spring_mvc.usuarios_perfiles
CREATE TABLE IF NOT EXISTS `usuarios_perfiles` (
  `idUsuario` bigint(20) NOT NULL,
  `idPerfil` varchar(50) NOT NULL,
  KEY `FK_usuarios_perfiles_usuarios` (`idUsuario`),
  KEY `FK_usuarios_perfiles_springperfiles` (`idPerfil`),
  CONSTRAINT `FK_usuarios_perfiles_springperfiles` FOREIGN KEY (`idPerfil`) REFERENCES `springperfiles` (`id`),
  CONSTRAINT `FK_usuarios_perfiles_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla rest_example_spring_mvc.usuarios_perfiles: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios_perfiles` DISABLE KEYS */;
INSERT INTO `usuarios_perfiles` (`idUsuario`, `idPerfil`) VALUES
	(1, 'ROLE_ADMINISTRADOR'),
	(2, 'ROLE_EMPLEADO');
/*!40000 ALTER TABLE `usuarios_perfiles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
