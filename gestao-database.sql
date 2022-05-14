-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 14-Maio-2022 às 13:44
-- Versão do servidor: 8.0.29
-- versão do PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gestao`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividades`
--

DROP TABLE IF EXISTS `atividades`;
CREATE TABLE IF NOT EXISTS `atividades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `atividade` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `servidor` int NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `campus`
--

DROP TABLE IF EXISTS `campus`;
CREATE TABLE IF NOT EXISTS `campus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(160) NOT NULL,
  `abreviacao` varchar(20) NOT NULL,
  `duracao_aula` double NOT NULL,
  `dt_criacao_camp` date NOT NULL,
  `cidade` varchar(160) NOT NULL,
  `bairro` varchar(160) NOT NULL,
  `rua` varchar(160) NOT NULL,
  `cep` varchar(160) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `comissoes`
--

DROP TABLE IF EXISTS `comissoes`;
CREATE TABLE IF NOT EXISTS `comissoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comissao` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `estado` int NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curso` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `campus` int NOT NULL,
  `estado` int NOT NULL,
  `ano_inicio` year NOT NULL,
  `ano_termino` year NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinas`
--

DROP TABLE IF EXISTS `disciplinas`;
CREATE TABLE IF NOT EXISTS `disciplinas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `disciplina` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `curso` int NOT NULL,
  `carga_horaria` double NOT NULL,
  `periodo` int NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ofertas`
--

DROP TABLE IF EXISTS `ofertas`;
CREATE TABLE IF NOT EXISTS `ofertas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curso` int NOT NULL,
  `disciplina` int NOT NULL,
  `servidor` int NOT NULL,
  `ano` int NOT NULL,
  `semestre` int NOT NULL,
  `aula_semanal` int NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`),
  KEY `disciplina` (`disciplina`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `orientacoes`
--

DROP TABLE IF EXISTS `orientacoes`;
CREATE TABLE IF NOT EXISTS `orientacoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(160) NOT NULL,
  `servidor` int NOT NULL,
  `aluno` varchar(160) DEFAULT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reuniao_presente`
--

DROP TABLE IF EXISTS `reuniao_presente`;
CREATE TABLE IF NOT EXISTS `reuniao_presente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comissao` int NOT NULL,
  `ata_reuniao` varchar(255) NOT NULL,
  `servidor` int NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comissao` (`comissao`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reunioes`
--

DROP TABLE IF EXISTS `reunioes`;
CREATE TABLE IF NOT EXISTS `reunioes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comissao` int NOT NULL,
  `servidor_secre` int NOT NULL,
  `conteudo_ata` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `dt_reuniao` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor_secre` (`servidor_secre`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servidores`
--

DROP TABLE IF EXISTS `servidores`;
CREATE TABLE IF NOT EXISTS `servidores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `campus` int NOT NULL,
  `email` varchar(160) NOT NULL,
  `cargo` varchar(60) NOT NULL,
  `login` varchar(160) NOT NULL,
  `senha` varchar(160) NOT NULL,
  `perfil` int NOT NULL,
  `horas_totais` double NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estrutura da tabela `vinculos`
--

DROP TABLE IF EXISTS `vinculos`;
CREATE TABLE IF NOT EXISTS `vinculos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `servidor` int NOT NULL,
  `comissao` int NOT NULL,
  `papel` varchar(60) NOT NULL,
  `dt_entrada` date NOT NULL,
  `dt_saida` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `atividades`
--
ALTER TABLE `atividades`
  ADD CONSTRAINT `atividades_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD CONSTRAINT `disciplinas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`disciplina`) REFERENCES `disciplinas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ofertas_ibfk_3` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `orientacoes`
--
ALTER TABLE `orientacoes`
  ADD CONSTRAINT `orientacoes_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `reuniao_presente`
--
ALTER TABLE `reuniao_presente`
  ADD CONSTRAINT `reuniao_presente_ibfk_1` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reuniao_presente_ibfk_2` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `reunioes`
--
ALTER TABLE `reunioes`
  ADD CONSTRAINT `reunioes_ibfk_2` FOREIGN KEY (`servidor_secre`) REFERENCES `comissoes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reunioes_ibfk_3` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `servidores`
--
ALTER TABLE `servidores`
  ADD CONSTRAINT `servidores_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `vinculos`
--
ALTER TABLE `vinculos`
  ADD CONSTRAINT `vinculos_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vinculos_ibfk_2` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
