-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 26-03-2019 a las 00:18:27
-- Versión del servidor: 10.1.26-MariaDB-0+deb9u1
-- Versión de PHP: 7.0.33-0+deb9u3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `api`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dna`
--

CREATE TABLE `dna` (
  `dna_code` varchar(100) NOT NULL,
  `is_mutant` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Disparadores `dna`
--
DELIMITER $$
CREATE TRIGGER `update_stats` AFTER INSERT ON `dna` FOR EACH ROW IF NEW.is_mutant = 1 then
   update stats SET human_count_dna=human_count_dna+1,                   mutant_count_dna=mutant_count_dna+1, ratio=round((mutant_count_dna)/(human_count_dna),2);
ELSE
   update stats SET human_count_dna=human_count_dna+1, ratio=round((mutant_count_dna)/(human_count_dna),2);
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_stats_delete` AFTER UPDATE ON `dna` FOR EACH ROW IF NEW.is_mutant = 1 then
   update stats SET human_count_dna=human_count_dna+1,                   mutant_count_dna=mutant_count_dna+1, ratio=round((mutant_count_dna)/(human_count_dna),2);
ELSE
   update stats SET human_count_dna=human_count_dna+1, ratio=round((mutant_count_dna)/(human_count_dna),2);
END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stats`
--

CREATE TABLE `stats` (
  `id` int(11) NOT NULL,
  `human_count_dna` int(11) NOT NULL,
  `mutant_count_dna` int(11) NOT NULL,
  `ratio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `stats`
--

INSERT INTO `stats` (`id`, `human_count_dna`, `mutant_count_dna`, `ratio`) VALUES
(1, 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `dna`
--
ALTER TABLE `dna`
  ADD PRIMARY KEY (`dna_code`);

--
-- Indices de la tabla `stats`
--
ALTER TABLE `stats`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
