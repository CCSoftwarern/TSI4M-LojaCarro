-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Maio-2026 às 16:35
-- Versão do servidor: 10.4.27-MariaDB
-- versão do PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `lojacarros`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `carro`
--

CREATE TABLE `carro` (
  `id` bigint(20) NOT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `ano` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `carro`
--

INSERT INTO `carro` (`id`, `modelo`, `ano`) VALUES
(10, 'Civic', 2024),
(11, 'Civic', 2024),
(14, 'Civic', 2024),
(15, 'Civic', 2024),
(18, 'Civic', 2024),
(22, 'HB20', 2022),
(23, 'Civic', 2024),
(26, 'HB20', 2022),
(27, 'Civic', 2024),
(30, 'HB20', 2022),
(31, 'Civic', 2024),
(34, 'HB20', 2022),
(35, 'Civic', 2024),
(38, 'HB20', 2022),
(39, 'Civic', 2024),
(42, 'HB20', 2022),
(43, 'Civic', 2024),
(46, 'HB20', 2022),
(47, 'Civic', 2024),
(50, 'HB20', 2022),
(51, 'Civic', 2024),
(54, 'HB20', 2022),
(55, 'Civic', 2024),
(58, 'HB20', 2022),
(59, 'Civic', 2024),
(62, 'HB20', 2022),
(63, 'Civic', 2024),
(66, 'HB20', 2022),
(67, 'Civic', 2024),
(70, 'HB20', 2022),
(71, 'Civic', 2024),
(74, 'HB20', 2022),
(75, 'Civic', 2024),
(78, 'HB20', 2022),
(79, 'Civic', 2024),
(82, 'HB20', 2022),
(83, 'Civic', 2024),
(86, 'HB20', 2022),
(87, 'Civic', 2024),
(88, 'HB20', 2022),
(89, 'HB20', 2022),
(90, 'HB20', 2022),
(93, 'HB20', 2022),
(94, 'Civic', 2024),
(97, 'HB20', 2022),
(98, 'Civic', 2024);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `carro`
--
ALTER TABLE `carro`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `carro`
--
ALTER TABLE `carro`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
