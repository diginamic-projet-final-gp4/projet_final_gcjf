-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 08 avr. 2024 à 10:00
-- Version du serveur : 11.2.2-MariaDB
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gcjf`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `dt_debut` datetime(6) DEFAULT NULL,
  `dt_fin` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `status` enum('INITIALE','EN_ATTENTE_VALIDATION','VALIDEE','REJETEE') DEFAULT NULL,
  `type` enum('FERIEE','RTT_EMPLOYER','RTT_EMPLOYEE','PAID_LEAVE','UNPAID_LEAVE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1i7cfupxv3s62v0u39e71munf` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`dt_debut`, `dt_fin`, `id`, `user_id`, `motif`, `status`, `type`) VALUES
('2024-04-25 01:00:00.000000', '2024-04-26 01:00:00.000000', 4, 2, 'test', 'VALIDEE', 'PAID_LEAVE'),
('2024-04-25 01:00:00.000000', '2024-04-26 01:00:00.000000', 5, 3, 'test', 'INITIALE', 'PAID_LEAVE'),
('2024-04-15 01:00:00.000000', '2024-04-16 01:00:00.000000', 6, 2, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-04-14 01:00:00.000000', '2024-04-15 01:00:00.000000', 7, 2, 'test', 'VALIDEE', 'UNPAID_LEAVE'),
('2024-04-14 01:00:00.000000', '2024-04-15 01:00:00.000000', 8, 2, 'test', 'VALIDEE', 'RTT_EMPLOYEE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 26, 10, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 27, 6, 'test', 'VALIDEE', 'UNPAID_LEAVE'),
('2024-02-01 01:00:00.000000', '2024-02-03 00:00:00.000000', 28, 10, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-02-01 01:00:00.000000', '2024-02-02 01:00:00.000000', 29, 10, 'test', 'EN_ATTENTE_VALIDATION', 'UNPAID_LEAVE'),
('2024-02-01 01:00:00.000000', '2024-02-02 01:00:00.000000', 30, 10, 'test', 'VALIDEE', 'RTT_EMPLOYEE'),
('2024-02-01 01:00:00.000000', '2024-02-02 01:00:00.000000', 32, 10, 'test', 'VALIDEE', 'UNPAID_LEAVE'),
('2024-02-01 01:00:00.000000', '2024-02-02 01:00:00.000000', 34, 10, 'test', 'VALIDEE', 'UNPAID_LEAVE'),
('2024-03-29 01:00:00.000000', '2024-03-30 01:00:00.000000', 37, 1, 'qsdfqsdfqsdf', 'VALIDEE', 'RTT_EMPLOYEE'),
('2024-02-01 01:00:00.000000', '2024-02-02 01:00:00.000000', 38, 10, 'test', 'VALIDEE', 'UNPAID_LEAVE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 40, 6, 'test', 'INITIALE', 'FERIEE'),
('2024-04-12 02:00:00.000000', '2024-04-12 02:00:00.000000', 41, 1, 'qsdf', 'VALIDEE', 'PAID_LEAVE'),
('2024-04-19 02:00:00.000000', '2024-04-26 02:00:00.000000', 42, 1, 'poiupoupoiupoupoupoiupoiupoiupoiupoiupoiuuopupoupoiu', 'VALIDEE', 'PAID_LEAVE'),
('2024-03-27 01:00:00.000000', '2024-04-10 01:00:00.000000', 43, 1, 'test ultime', 'VALIDEE', 'PAID_LEAVE'),
('2024-04-18 02:00:00.000000', '2024-04-18 02:00:00.000000', 44, 1, 'qsdfqsdf', 'VALIDEE', 'PAID_LEAVE'),
('2024-04-04 02:00:00.000000', '2024-04-04 02:00:00.000000', 45, 1, 'poipoipoi', 'INITIALE', 'PAID_LEAVE'),
('2024-04-05 02:00:00.000000', '2024-04-05 02:00:00.000000', 49, 1, 'testtest', 'VALIDEE', 'RTT_EMPLOYEE'),
('2024-04-19 02:00:00.000000', '2024-04-19 02:00:00.000000', 50, 9, 'aerazerazerazer', 'EN_ATTENTE_VALIDATION', 'RTT_EMPLOYEE'),
('2024-04-12 02:00:00.000000', '2024-04-12 02:00:00.000000', 51, 2, '', 'INITIALE', 'PAID_LEAVE'),
('2024-04-06 02:00:00.000000', '2024-04-11 02:00:00.000000', 52, 1, '', 'INITIALE', 'UNPAID_LEAVE'),
('2024-04-06 02:00:00.000000', '2024-04-10 02:00:00.000000', 53, 9, '', 'INITIALE', 'UNPAID_LEAVE'),
('2024-04-17 02:00:00.000000', '2024-04-18 02:00:00.000000', 54, 1, '', 'INITIALE', 'UNPAID_LEAVE');

-- --------------------------------------------------------

--
-- Structure de la table `organization`
--

DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization` (
  `public_holiday` float DEFAULT NULL,
  `rtt_employer` float DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `organization`
--

INSERT INTO `organization` (`public_holiday`, `rtt_employer`, `id`, `name`) VALUES
(0, 5, 1, 'organization1'),
(0, 5, 2, 'organization2');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoo7ppg9hxkcjrxlpjj3vk4epw` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id`, `organization_id`, `last_name`) VALUES
(1, 1, 'service dev'),
(2, 2, 'service support');

-- --------------------------------------------------------

--
-- Structure de la table `specific_absence`
--

DROP TABLE IF EXISTS `specific_absence`;
CREATE TABLE IF NOT EXISTS `specific_absence` (
  `dt_debut` datetime(6) DEFAULT NULL,
  `dt_fin` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `status` enum('INITIALE','EN_ATTENTE_VALIDATION','VALIDEE','REJETEE') DEFAULT NULL,
  `type` enum('FERIEE','RTT_EMPLOYER','RTT_EMPLOYEE','PAID_LEAVE','UNPAID_LEAVE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmpav7vrle6s9xl7od3jqpgcqx` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `specific_absence`
--

INSERT INTO `specific_absence` (`dt_debut`, `dt_fin`, `id`, `organization_id`, `motif`, `status`, `type`) VALUES
('2024-04-17 01:00:00.000000', '2024-04-18 01:00:00.000000', 1, 1, 'test', 'INITIALE', 'FERIEE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 2, NULL, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 3, NULL, 'test', 'INITIALE', 'FERIEE'),
('2024-04-15 01:00:00.000000', '2024-04-16 01:00:00.000000', 6, 1, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-04-15 01:00:00.000000', '2024-04-16 01:00:00.000000', 7, 1, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 10, 1, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 11, 1, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 12, 1, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 13, 2, 'test10', 'INITIALE', 'FERIEE'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 14, 1, 'test10', 'INITIALE', 'FERIEE'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 15, 2, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-03-15 00:00:00.000000', '2024-03-16 00:00:00.000000', 17, 2, 'test10', 'INITIALE', 'RTT_EMPLOYER'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 19, 1, 'test', 'INITIALE', 'RTT_EMPLOYER'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 20, NULL, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 21, NULL, 'test', 'INITIALE', 'UNPAID_LEAVE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 22, NULL, 'test', 'INITIALE', 'FERIEE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 23, 1, 'test', 'INITIALE', 'FERIEE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 24, 1, 'test', 'INITIALE', 'FERIEE'),
('2024-02-02 00:00:00.000000', '2024-02-03 00:00:00.000000', 25, 1, 'test', 'INITIALE', 'RTT_EMPLOYER'),
('2024-04-04 02:00:00.000000', '2024-04-04 02:00:00.000000', 26, 1, 'qsdfqsdf', NULL, 'FERIEE'),
('2024-04-04 02:00:00.000000', '2024-04-04 02:00:00.000000', 27, 1, 'qsdfqsdf', NULL, 'FERIEE'),
('2024-04-04 02:00:00.000000', '2024-04-04 02:00:00.000000', 28, 1, 'qsdfqsdf', NULL, 'FERIEE'),
('2024-04-11 02:00:00.000000', '2024-04-11 02:00:00.000000', 29, 1, 'ssdgfsdfgsfgsdgf sdfg sdg', NULL, 'RTT_EMPLOYER'),
('2024-04-11 02:00:00.000000', '2024-04-11 02:00:00.000000', 30, 1, 'ssdgfsdfgsfgsdgf sdfg sdg', NULL, 'RTT_EMPLOYER'),
('2024-04-17 02:00:00.000000', '2024-04-17 02:00:00.000000', 31, 1, 'mlkjmlj test2', NULL, 'RTT_EMPLOYER'),
('2024-04-17 02:00:00.000000', '2024-04-17 02:00:00.000000', 32, 1, 'mlkjmlj test2', NULL, 'RTT_EMPLOYER'),
('2024-04-25 02:00:00.000000', '2024-04-25 02:00:00.000000', 33, 1, 'Mabite', NULL, 'RTT_EMPLOYER'),
('2024-04-13 02:00:00.000000', '2024-04-14 02:00:00.000000', 34, 2, 'pouet pouet', NULL, 'FERIEE');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `paid_leave` float DEFAULT NULL,
  `rtt_employee` float DEFAULT NULL,
  `unpaid_leave` float DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manager_id` bigint(20) DEFAULT NULL,
  `service_id` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('MANAGER','ADMIN','EMPLOYEE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKl9blkgio1nb00hot7kaxoy7q9` (`manager_id`),
  KEY `FKbnre76q8qj377k5q2qjflbd9` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`paid_leave`, `rtt_employee`, `unpaid_leave`, `id`, `manager_id`, `service_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(15, 4, 0, 1, 2, 1, 'Jean.Bon@hotmail.com', 'Jean', 'Bon', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 0, 2, NULL, 1, 'NorrisGingras@jourrapide.com ', 'Norris', 'Gingras', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'MANAGER'),
(25, 6, 0, 3, NULL, 1, 'AliceFlamand@armyspy.com ', 'Alice ', 'Flamand', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 0, 4, NULL, 1, 'DonatienLambert@armyspy.com', 'Donatien ', 'Lambert', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 0, 5, NULL, 1, 'ChanningAllard@armyspy.com ', 'Channing', 'Allard', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 2, 6, NULL, 1, 'AlgernonFecteau@dayrep.com', 'Algernon ', 'Fecteau', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 0, 7, NULL, 2, 'XavierTessier@rhyta.com ', 'Xavier ', 'Tessier', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'MANAGER'),
(25, 6, 0, 8, NULL, 2, 'HortenseSalois@armyspy.com ', 'Hortense ', 'Salois', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'EMPLOYEE'),
(25, 6, 0, 9, NULL, 1, 'RomainLaforge@jourrapide.com ', 'Romain ', 'Laforge', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'ADMIN'),
(25, 4, 2, 10, NULL, 2, 'LandersRoy@jourrapide.com', 'Landers ', 'Roy', '$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q', 'ADMIN');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `FK1i7cfupxv3s62v0u39e71munf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FKoo7ppg9hxkcjrxlpjj3vk4epw` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`);

--
-- Contraintes pour la table `specific_absence`
--
ALTER TABLE `specific_absence`
  ADD CONSTRAINT `FKmpav7vrle6s9xl7od3jqpgcqx` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKbnre76q8qj377k5q2qjflbd9` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  ADD CONSTRAINT `FKl9blkgio1nb00hot7kaxoy7q9` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
