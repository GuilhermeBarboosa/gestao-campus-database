-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 11-Maio-2022 às 10:47
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

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
-- Estrutura da tabela `atividade`
--

DROP TABLE IF EXISTS `atividade`;
CREATE TABLE IF NOT EXISTS `atividade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(160) NOT NULL,
  `servidor` int(11) NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `campus`
--

DROP TABLE IF EXISTS `campus`;
CREATE TABLE IF NOT EXISTS `campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `campus`
--

INSERT INTO `campus` (`id`, `nome`, `abreviacao`, `duracao_aula`, `dt_criacao_camp`, `cidade`, `bairro`, `rua`, `cep`, `cadastrado`, `modificado`) VALUES
(1, 'Instituto Federal de Uberaba', 'IFU', 40, '1980-02-20', 'Uberaba', 'Univerde', 'Rua 1', '23921931-43', '2022-05-09', NULL),
(4, 'Faculdade de Talentos Humanos', 'Facthus', 40, '1980-07-10', 'Uberaba', 'Centro', 'Rua Tristo', '3912391-43', '2022-05-09', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `comissao`
--

DROP TABLE IF EXISTS `comissao`;
CREATE TABLE IF NOT EXISTS `comissao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_comissao` varchar(100) NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `estado` int(1) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` int(11) NOT NULL,
  `nome_curso` varchar(160) NOT NULL,
  `estado` int(1) NOT NULL,
  `ano_inicio` year(4) NOT NULL,
  `ano_termino` year(4) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_disciplina` varchar(160) NOT NULL,
  `curso` int(11) NOT NULL,
  `carga_horaria` double NOT NULL,
  `periodo` int(2) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `oferta`
--

DROP TABLE IF EXISTS `oferta`;
CREATE TABLE IF NOT EXISTS `oferta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso` int(11) NOT NULL,
  `disciplina` int(11) NOT NULL,
  `servidor` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `semestre` int(11) NOT NULL,
  `aula_semanal` int(11) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`),
  KEY `disciplina` (`disciplina`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `orientacao`
--

DROP TABLE IF EXISTS `orientacao`;
CREATE TABLE IF NOT EXISTS `orientacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(160) NOT NULL,
  `servidor` int(11) NOT NULL,
  `aluno` varchar(160) DEFAULT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reuniao`
--

DROP TABLE IF EXISTS `reuniao`;
CREATE TABLE IF NOT EXISTS `reuniao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` int(11) NOT NULL,
  `dt_reuniao` date NOT NULL,
  `conteudo_ata` varchar(160) NOT NULL,
  `servidor_secre` int(11) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor_secre` (`servidor_secre`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reuniao_presente`
--

DROP TABLE IF EXISTS `reuniao_presente`;
CREATE TABLE IF NOT EXISTS `reuniao_presente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` int(11) NOT NULL,
  `ata_reuniao` varchar(255) NOT NULL,
  `servidor` int(11) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comissao` (`comissao`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servidor`
--

DROP TABLE IF EXISTS `servidor`;
CREATE TABLE IF NOT EXISTS `servidor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` int(11) NOT NULL,
  `nome` varchar(160) NOT NULL,
  `email` varchar(160) NOT NULL,
  `cargo` varchar(60) NOT NULL,
  `login` varchar(160) NOT NULL,
  `senha` varchar(160) NOT NULL,
  `perfil` int(1) NOT NULL,
  `horas_totais` double NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `servidor`
--

INSERT INTO `servidor` (`id`, `campus`, `nome`, `email`, `cargo`, `login`, `senha`, `perfil`, `horas_totais`, `cadastrado`, `modificado`) VALUES
(1, 1, 'Carol', 'carol@gmail.com', 'tae', 'carol', 'carol', 1, 0, '2022-05-09', '2022-05-09'),
(3, 1, 'guilherme', 'gui@gmail.com', 'pbtt', 'gui', 'gui', 2, 0, '2022-05-09', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vinculo`
--

DROP TABLE IF EXISTS `vinculo`;
CREATE TABLE IF NOT EXISTS `vinculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `servidor` int(11) NOT NULL,
  `comissao` int(11) NOT NULL,
  `papel` varchar(60) NOT NULL,
  `dt_entrada` date NOT NULL,
  `dt_saida` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `atividade`
--
ALTER TABLE `atividade`
  ADD CONSTRAINT `atividade_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `curso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `oferta_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `curso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `oferta_ibfk_2` FOREIGN KEY (`disciplina`) REFERENCES `disciplina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `oferta_ibfk_3` FOREIGN KEY (`servidor`) REFERENCES `servidor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `orientacao`
--
ALTER TABLE `orientacao`
  ADD CONSTRAINT `orientacao_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `reuniao`
--
ALTER TABLE `reuniao`
  ADD CONSTRAINT `reuniao_ibfk_2` FOREIGN KEY (`servidor_secre`) REFERENCES `comissao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reuniao_ibfk_3` FOREIGN KEY (`comissao`) REFERENCES `comissao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `reuniao_presente`
--
ALTER TABLE `reuniao_presente`
  ADD CONSTRAINT `reuniao_presente_ibfk_1` FOREIGN KEY (`comissao`) REFERENCES `comissao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reuniao_presente_ibfk_2` FOREIGN KEY (`servidor`) REFERENCES `servidor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `servidor`
--
ALTER TABLE `servidor`
  ADD CONSTRAINT `servidor_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `vinculo`
--
ALTER TABLE `vinculo`
  ADD CONSTRAINT `vinculo_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vinculo_ibfk_2` FOREIGN KEY (`comissao`) REFERENCES `comissao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
