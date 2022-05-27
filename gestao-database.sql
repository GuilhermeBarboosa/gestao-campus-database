-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 27-Maio-2022 às 20:46
-- Versão do servidor: 5.7.36
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `atividade` varchar(160) NOT NULL,
  `servidor` int(11) NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor` (`servidor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `atividades`
--

INSERT INTO `atividades` (`id`, `atividade`, `servidor`, `horas_semanais`, `dt_inicio`, `dt_termino`, `cadastrado`, `modificado`) VALUES
(1, 'Aula 1', 2, 24, '2022-05-17', '2022-06-18', '2022-05-27', NULL),
(2, 'Preparação da aula POO', 2, 45, '2022-05-27', '2022-05-28', '2022-05-27', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `campus`
--

INSERT INTO `campus` (`id`, `nome`, `abreviacao`, `duracao_aula`, `dt_criacao_camp`, `cidade`, `bairro`, `rua`, `cep`, `cadastrado`, `modificado`) VALUES
(1, 'Instituto Federal de Uberaba', 'IFU', 40, '2000-05-20', 'Uberaba', 'Univerde', 'Rua 1', '2173128-34', '2022-05-17', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `comissoes`
--

DROP TABLE IF EXISTS `comissoes`;
CREATE TABLE IF NOT EXISTS `comissoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` varchar(100) NOT NULL,
  `horas_semanais` double NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_termino` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `comissoes`
--

INSERT INTO `comissoes` (`id`, `comissao`, `horas_semanais`, `dt_inicio`, `dt_termino`, `estado`, `cadastrado`, `modificado`) VALUES
(1, 'Comissao A', 40, '2022-07-25', '2022-07-26', 'Ativo', '2022-05-17', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(160) NOT NULL,
  `campus` int(11) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `ano_inicio` year(4) NOT NULL,
  `ano_termino` year(4) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`id`, `curso`, `campus`, `estado`, `ano_inicio`, `ano_termino`, `cadastrado`, `modificado`) VALUES
(1, 'ADS', 1, 'ativo', 2022, 2023, '2022-05-27', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinas`
--

DROP TABLE IF EXISTS `disciplinas`;
CREATE TABLE IF NOT EXISTS `disciplinas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disciplina` varchar(160) NOT NULL,
  `curso` int(11) NOT NULL,
  `carga_horaria` double NOT NULL,
  `periodo` int(11) NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `disciplinas`
--

INSERT INTO `disciplinas` (`id`, `disciplina`, `curso`, `carga_horaria`, `periodo`, `cadastrado`, `modificado`) VALUES
(1, 'POO', 1, 45, 6, '2022-05-27', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ofertas`
--

DROP TABLE IF EXISTS `ofertas`;
CREATE TABLE IF NOT EXISTS `ofertas` (
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

--
-- Extraindo dados da tabela `ofertas`
--

INSERT INTO `ofertas` (`id`, `curso`, `disciplina`, `servidor`, `ano`, `semestre`, `aula_semanal`, `cadastrado`, `modificado`) VALUES
(1, 1, 1, 2, 2022, 4, 5, '2022-05-27', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `orientacoes`
--

DROP TABLE IF EXISTS `orientacoes`;
CREATE TABLE IF NOT EXISTS `orientacoes` (
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

--
-- Extraindo dados da tabela `orientacoes`
--

INSERT INTO `orientacoes` (`id`, `tipo`, `servidor`, `aluno`, `horas_semanais`, `dt_inicio`, `dt_termino`, `cadastrado`, `modificado`) VALUES
(1, 'TCC', 2, 'GUIZIN', 5, '2022-05-27', '2022-06-28', '2022-05-27', NULL);

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
-- Estrutura da tabela `reunioes`
--

DROP TABLE IF EXISTS `reunioes`;
CREATE TABLE IF NOT EXISTS `reunioes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` int(11) NOT NULL,
  `servidor_secre` int(11) NOT NULL,
  `conteudo_ata` varchar(160) NOT NULL,
  `dt_reuniao` date NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `servidor_secre` (`servidor_secre`),
  KEY `comissao` (`comissao`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `reunioes`
--

INSERT INTO `reunioes` (`id`, `comissao`, `servidor_secre`, `conteudo_ata`, `dt_reuniao`, `cadastrado`, `modificado`) VALUES
(3, 1, 2, 'aaaaaaaaaaaaaaaa', '2022-06-26', '2022-05-27', NULL),
(4, 1, 2, 'hhhhhhhhhhhhhh', '2022-06-27', '2022-05-27', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `servidores`
--

DROP TABLE IF EXISTS `servidores`;
CREATE TABLE IF NOT EXISTS `servidores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(160) NOT NULL,
  `campus` int(11) NOT NULL,
  `email` varchar(160) NOT NULL,
  `cargo` varchar(60) NOT NULL,
  `login` varchar(160) NOT NULL,
  `senha` varchar(160) NOT NULL,
  `perfil` int(11) NOT NULL,
  `horas_totais` double NOT NULL,
  `cadastrado` date NOT NULL,
  `modificado` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `servidores`
--

INSERT INTO `servidores` (`id`, `nome`, `campus`, `email`, `cargo`, `login`, `senha`, `perfil`, `horas_totais`, `cadastrado`, `modificado`) VALUES
(2, 'Guilherme', 1, 'gui@gmail.com', 'tae', 'g', 'g', 1, 114, '2022-05-17', '2022-05-27');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vinculos`
--

DROP TABLE IF EXISTS `vinculos`;
CREATE TABLE IF NOT EXISTS `vinculos` (
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
-- Extraindo dados da tabela `vinculos`
--

INSERT INTO `vinculos` (`id`, `servidor`, `comissao`, `papel`, `dt_entrada`, `dt_saida`, `cadastrado`, `modificado`) VALUES
(1, 2, 1, 'presidente', '2022-05-27', '2022-05-28', '2022-05-27', NULL);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `atividades`
--
ALTER TABLE `atividades`
  ADD CONSTRAINT `atividades_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`);

--
-- Limitadores para a tabela `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`);

--
-- Limitadores para a tabela `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD CONSTRAINT `disciplinas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`);

--
-- Limitadores para a tabela `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`),
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`disciplina`) REFERENCES `disciplinas` (`id`),
  ADD CONSTRAINT `ofertas_ibfk_3` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`);

--
-- Limitadores para a tabela `orientacoes`
--
ALTER TABLE `orientacoes`
  ADD CONSTRAINT `orientacoes_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`);

--
-- Limitadores para a tabela `reuniao_presente`
--
ALTER TABLE `reuniao_presente`
  ADD CONSTRAINT `reuniao_presente_ibfk_1` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`),
  ADD CONSTRAINT `reuniao_presente_ibfk_2` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`);

--
-- Limitadores para a tabela `reunioes`
--
ALTER TABLE `reunioes`
  ADD CONSTRAINT `reunioes_ibfk_2` FOREIGN KEY (`servidor_secre`) REFERENCES `servidores` (`id`),
  ADD CONSTRAINT `reunioes_ibfk_3` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`);

--
-- Limitadores para a tabela `servidores`
--
ALTER TABLE `servidores`
  ADD CONSTRAINT `servidores_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `campus` (`id`);

--
-- Limitadores para a tabela `vinculos`
--
ALTER TABLE `vinculos`
  ADD CONSTRAINT `vinculos_ibfk_1` FOREIGN KEY (`servidor`) REFERENCES `servidores` (`id`),
  ADD CONSTRAINT `vinculos_ibfk_2` FOREIGN KEY (`comissao`) REFERENCES `comissoes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
