-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 08-Jun-2022 às 12:45
-- Versão do servidor: 8.0.29
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
-- Estrutura da tabela `atividades`
--

DROP TABLE IF EXISTS `atividades`;
CREATE TABLE IF NOT EXISTS `atividades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `atividade` varchar(160) NOT NULL,
  `servidor` int NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `atividades`
--

INSERT INTO `atividades` (`id`, `atividade`, `servidor`, `horas_semanais`, `dt_inicio`, `dt_termino`, `cadastrado`, `modificado`) VALUES
(1, 'Aula 1', 2, 24, '2022-05-17', '2022-06-18', '2022-05-27', NULL),
(2, 'Preparação da aula POO', 2, 45, '2022-05-27', '2022-05-28', '2022-05-27', NULL),
(3, 'Preparação da aula ESOF', 4, 40, '2022-06-08', '2022-06-09', '2022-06-08', NULL),
(5, 'Atividade 5', 2, 4, '2022-08-07', '2022-10-10', '2022-06-08', '2022-06-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `campus`
--

INSERT INTO `campus` (`id`, `nome`, `abreviacao`, `duracao_aula`, `dt_criacao_camp`, `cidade`, `bairro`, `rua`, `cep`, `cadastrado`, `modificado`) VALUES
(1, 'Instituto Federal de Uberaba', 'IFU', 40, '2000-05-20', 'Uberaba', 'Univerde', 'Rua 1', '2173128-34', '2022-05-17', NULL),
(3, 'Faculdade de Talentos Humanos', 'FACTHUS', 40, '2022-06-08', 'Uberaba', 'Olinda', 'Rua 233', '3178293-34', '2022-06-08', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `comissoes`
--

DROP TABLE IF EXISTS `comissoes`;
CREATE TABLE IF NOT EXISTS `comissoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comissao` varchar(100) NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `comissoes`
--

INSERT INTO `comissoes` (`id`, `comissao`, `horas_semanais`, `dt_inicio`, `dt_termino`, `estado`, `cadastrado`, `modificado`) VALUES
(1, 'Comissao A', 40, '2022-07-25', '2022-07-26', 'Inativo', '2022-05-17', '2022-05-28'),
(2, 'Comissao B', 5, '2022-06-08', '2022-10-10', 'Ativo', '2022-06-08', '2022-06-08');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curso` varchar(160) NOT NULL,
  `campus` int NOT NULL,
  `estado` varchar(10) NOT NULL,
  `ano_inicio` year NOT NULL,
  `ano_termino` year NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`id`, `curso`, `campus`, `estado`, `ano_inicio`, `ano_termino`, `cadastrado`, `modificado`) VALUES
(1, 'ADS', 1, 'ativo', 2022, 2023, '2022-05-27', NULL),
(2, 'ENG', 1, 'ativo', 2002, 2004, '2022-05-28', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinas`
--

DROP TABLE IF EXISTS `disciplinas`;
CREATE TABLE IF NOT EXISTS `disciplinas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `disciplina` varchar(160) NOT NULL,
  `curso` int NOT NULL,
  `carga_horaria` double NOT NULL,
  `periodo` int NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `disciplinas`
--

INSERT INTO `disciplinas` (`id`, `disciplina`, `curso`, `carga_horaria`, `periodo`, `cadastrado`, `modificado`) VALUES
(1, 'POO', 1, 45, 6, '2022-05-27', NULL),
(2, 'ESOF', 2, 40, 4, '2022-06-08', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `ofertas`
--

INSERT INTO `ofertas` (`id`, `curso`, `disciplina`, `servidor`, `ano`, `semestre`, `aula_semanal`, `cadastrado`, `modificado`) VALUES
(1, 1, 1, 2, 2022, 4, 5, '2022-05-27', NULL),
(2, 1, 2, 4, 2023, 3, 4, '2022-06-08', NULL),
(3, 1, 2, 3, 2022, 3, 4, '2022-06-08', '2022-06-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `orientacoes`
--

INSERT INTO `orientacoes` (`id`, `tipo`, `servidor`, `aluno`, `horas_semanais`, `dt_inicio`, `dt_termino`, `cadastrado`, `modificado`) VALUES
(1, 'TCC', 2, 'GUIZIN', 5, '2022-05-27', '2022-06-28', '2022-05-27', NULL),
(2, 'tcc', 3, 'Carol', 4, '2022-07-08', '2022-07-10', '2022-06-08', NULL),
(3, 'Estagio', 4, 'Joao', 5, '2022-01-01', '2022-02-02', '2022-06-08', '2022-06-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `reuniao_presente`
--

INSERT INTO `reuniao_presente` (`id`, `comissao`, `ata_reuniao`, `servidor`, `cadastrado`, `modificado`) VALUES
(1, 1, 'aaaaaaa', 2, '2022-06-08', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `reunioes`
--

DROP TABLE IF EXISTS `reunioes`;
CREATE TABLE IF NOT EXISTS `reunioes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comissao` int NOT NULL,
  `servidor_secre` int NOT NULL,
  `conteudo_ata` varchar(160) NOT NULL,
  `dt_reuniao` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor_secre` (`servidor_secre`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `reunioes`
--

INSERT INTO `reunioes` (`id`, `comissao`, `servidor_secre`, `conteudo_ata`, `dt_reuniao`, `cadastrado`, `modificado`) VALUES
(3, 1, 2, 'sjhkdasjhjkldas', '2022-06-03', '2022-06-04', NULL),
(4, 1, 3, 'djaskldjklasjkl', '2022-06-05', '2022-06-04', NULL),
(5, 1, 2, 'coisa atoa', '2022-06-08', '2022-06-08', NULL),
(6, 2, 3, 'kkkkkkk', '2022-06-06', '2022-06-08', '2022-06-08');

-- --------------------------------------------------------

--
-- Estrutura da tabela `servidores`
--

DROP TABLE IF EXISTS `servidores`;
CREATE TABLE IF NOT EXISTS `servidores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(160) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `servidores`
--

INSERT INTO `servidores` (`id`, `nome`, `campus`, `email`, `cargo`, `login`, `senha`, `perfil`, `horas_totais`, `cadastrado`, `modificado`) VALUES
(2, 'Guilherme', 1, 'gui@gmail.com', 'tae', 'g', 'g', 1, 158, '2022-05-17', '2022-05-27'),
(3, 'Julia', 1, 'julia@gmail.com', 'tae', 'j', 'j', 2, 4, '2022-05-28', '2022-05-28'),
(4, 'Vanessa', 3, 'vanessa@gmail.com', 'pbtt', 'v', 'v', 2, 45, '2022-05-28', '2022-06-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `vinculos`
--

INSERT INTO `vinculos` (`id`, `servidor`, `comissao`, `papel`, `dt_entrada`, `dt_saida`, `cadastrado`, `modificado`) VALUES
(2, 2, 1, 'Presidente', '2022-06-08', '2022-10-10', '2022-06-08', '2022-06-08'),
(3, 3, 2, 'Presidente', '2022-06-08', '2022-10-10', '2022-06-08', NULL);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `atividades`
--
ALTER TABLE `atividades`
  ADD CONSTRAINT `atividades_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD CONSTRAINT `disciplinas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`disciplina`) REFERENCES `disciplinas` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `ofertas_ibfk_3` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `orientacoes`
--
ALTER TABLE `orientacoes`
  ADD CONSTRAINT `orientacoes_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `reuniao_presente`
--
ALTER TABLE `reuniao_presente`
  ADD CONSTRAINT `reuniao_presente_ibfk_1` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `reuniao_presente_ibfk_2` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `reunioes`
--
ALTER TABLE `reunioes`
  ADD CONSTRAINT `reunioes_ibfk_2` FOREIGN KEY (`servidor_secre`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `reunioes_ibfk_3` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `servidores`
--
ALTER TABLE `servidores`
  ADD CONSTRAINT `servidores_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `vinculos`
--
ALTER TABLE `vinculos`
  ADD CONSTRAINT `vinculos_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `vinculos_ibfk_2` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
