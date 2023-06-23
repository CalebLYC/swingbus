-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2023 at 03:51 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swingbus`
--

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `id` int(11) NOT NULL,
  `immatriculation` varchar(10) DEFAULT NULL,
  `marque` varchar(50) DEFAULT NULL,
  `max_places` int(11) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`id`, `immatriculation`, `marque`, `max_places`, `model`, `year`) VALUES
(4, 'TG-001-AA', 'Ford', 50, 'Mustang', 2020),
(5, 'TG-002-BB', 'Toyota', 176, 'Camry', 2019),
(6, 'TG-003-CC', 'Honda', 140, 'Civic', 2022),
(7, 'TG-004-DD', 'Chevrolet', 180, 'Silverado', 2021),
(8, 'TG-005-EE', 'Volkswagen', 200, 'Golf', 2018),
(9, 'TG-006-FF', 'BMW', 145, 'X5', 2023),
(10, 'TG-007-GG', 'Mercedes-Benz', 200, 'C-Class', 2020),
(11, 'TG-008-HH', 'Audi', 160, 'A4', 2019),
(12, 'TG-009-II', 'Hyundai', 200, 'Santa Fe', 2022),
(13, 'TG-010-JJ', 'Kia', 150, 'Sorento', 2021);

-- --------------------------------------------------------

--
-- Table structure for table `lignes`
--

CREATE TABLE `lignes` (
  `numero` int(11) NOT NULL,
  `depart` varchar(100) DEFAULT NULL,
  `destination` varchar(100) DEFAULT NULL,
  `distance` float DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `lignes`
--

INSERT INTO `lignes` (`numero`, `depart`, `destination`, `distance`, `nom`) VALUES
(10, 'Campus UL', 'Zanguéra', 20, 'Zanguéra'),
(12, 'Campus UL', 'Agoè Sogbossito', 25, 'Agoè Sogbossito'),
(13, 'Campus UL', 'Adétikopé', 22, 'Adétikopé'),
(15, 'Campus UL', 'Entreprise de l\'Union', 8, 'Entreprise de l\'Union'),
(16, 'Campus UL', 'Atigangomé', 10, 'Atigangomé'),
(18, 'Campus UL', 'Adidogomé', 15, 'Adidogomé');

-- --------------------------------------------------------

--
-- Table structure for table `postes`
--

CREATE TABLE `postes` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `postes`
--

INSERT INTO `postes` (`id`, `description`, `libelle`) VALUES
(2, NULL, 'Délégué'),
(3, NULL, 'Conducteur');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `type` varchar(5) NOT NULL,
  `id` int(11) NOT NULL,
  `date_naissance` datetime(6) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(200) DEFAULT NULL,
  `poste_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`type`, `id`, `date_naissance`, `nom`, `prenom`, `poste_id`) VALUES
('PERSO', 6, '1990-01-01 00:00:00.000000', 'Williams', 'David', 3),
('PERSO', 7, '1990-01-01 00:00:00.000000', 'Smith', 'John', 2),
('PERSO', 8, '1990-01-01 00:00:00.000000', 'Johnson', 'Michael', 2),
('PERSO', 9, '1990-01-01 00:00:00.000000', 'Williams', 'William', 2),
('PERSO', 10, '1990-01-01 00:00:00.000000', 'Brown', 'David', 3),
('PERSO', 11, '1990-01-01 00:00:00.000000', 'Jones', 'Robert', 3),
('PERSO', 12, '1990-01-01 00:00:00.000000', 'Miller', 'James', 3),
('PERSO', 13, '1990-01-01 00:00:00.000000', 'Taylor', 'Christopher', 3),
('PERSO', 14, '1990-01-01 00:00:00.000000', 'Anderson', 'Daniel', 3),
('PERSO', 15, '1990-01-01 00:00:00.000000', 'Thomas', 'Matthew', 3),
('PERSO', 16, '1990-01-01 00:00:00.000000', 'Jackson', 'Joseph', 3);

-- --------------------------------------------------------

--
-- Table structure for table `voyages`
--

CREATE TABLE `voyages` (
  `id` int(11) NOT NULL,
  `date_heure` datetime(6) DEFAULT NULL,
  `nombre_passagers` int(11) DEFAULT NULL,
  `vers_peripherie` bit(1) DEFAULT NULL,
  `bus_id` int(11) DEFAULT NULL,
  `ligne_numero` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `voyages`
--

INSERT INTO `voyages` (`id`, `date_heure`, `nombre_passagers`, `vers_peripherie`, `bus_id`, `ligne_numero`) VALUES
(10, '2023-06-23 01:46:43.743000', NULL, b'0', 4, 10),
(11, '2023-06-23 01:46:48.293000', NULL, b'0', 6, 13),
(12, '2023-06-23 01:46:58.344000', NULL, b'1', 7, 15),
(13, '2023-06-23 01:47:35.181000', NULL, b'0', 8, 16),
(14, '2023-06-23 01:47:54.928000', NULL, b'0', 4, 10),
(15, '2023-06-23 01:47:59.460000', NULL, b'1', 6, 13),
(16, '2023-06-23 01:48:21.182000', NULL, b'1', 9, 18),
(17, '2023-06-23 01:49:23.561000', NULL, b'0', 7, 15);

-- --------------------------------------------------------

--
-- Table structure for table `voyages_personnel`
--

CREATE TABLE `voyages_personnel` (
  `voyage_id` int(11) NOT NULL,
  `personnel_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `voyages_personnel`
--

INSERT INTO `voyages_personnel` (`voyage_id`, `personnel_id`) VALUES
(10, 6),
(11, 11),
(12, 12),
(13, 13),
(14, 6),
(15, 11),
(16, 14),
(17, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_loxwiism3a5yw9b7x35pm7mtk` (`immatriculation`);

--
-- Indexes for table `lignes`
--
ALTER TABLE `lignes`
  ADD PRIMARY KEY (`numero`),
  ADD UNIQUE KEY `UK_rdrcgmitfqf7r57b14wkd609r` (`destination`),
  ADD UNIQUE KEY `UK_nukniexo97y06w36c8sjh7phx` (`nom`);

--
-- Indexes for table `postes`
--
ALTER TABLE `postes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_o5i4sc5y557g89rt0iha75fi1` (`libelle`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9wnd5hufyr77eabdwxiv14yro` (`poste_id`);

--
-- Indexes for table `voyages`
--
ALTER TABLE `voyages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp4y5emq4ruebmyd49bn8lp0c4` (`bus_id`),
  ADD KEY `FKltf1g50slaqv56q08i7qtoaim` (`ligne_numero`);

--
-- Indexes for table `voyages_personnel`
--
ALTER TABLE `voyages_personnel`
  ADD KEY `FKi0mxiutd4j84t3m74kpy8xxs9` (`personnel_id`),
  ADD KEY `FKl2f2xblnd8r5f33qrjj0g0rub` (`voyage_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `postes`
--
ALTER TABLE `postes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `voyages`
--
ALTER TABLE `voyages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK9wnd5hufyr77eabdwxiv14yro` FOREIGN KEY (`poste_id`) REFERENCES `postes` (`id`);

--
-- Constraints for table `voyages`
--
ALTER TABLE `voyages`
  ADD CONSTRAINT `FKltf1g50slaqv56q08i7qtoaim` FOREIGN KEY (`ligne_numero`) REFERENCES `lignes` (`numero`),
  ADD CONSTRAINT `FKp4y5emq4ruebmyd49bn8lp0c4` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`);

--
-- Constraints for table `voyages_personnel`
--
ALTER TABLE `voyages_personnel`
  ADD CONSTRAINT `FKi0mxiutd4j84t3m74kpy8xxs9` FOREIGN KEY (`personnel_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKl2f2xblnd8r5f33qrjj0g0rub` FOREIGN KEY (`voyage_id`) REFERENCES `voyages` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
