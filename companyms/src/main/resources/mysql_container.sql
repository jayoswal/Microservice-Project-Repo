-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql_container
-- Generation Time: Jun 06, 2024 at 10:52 AM
-- Server version: 8.4.0
-- PHP Version: 8.2.19

--
-- Custom header Comment - Jay
--
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `company`
--
DROP DATABASE IF EXISTS `company`;
CREATE DATABASE IF NOT EXISTS `company` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `company`;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `average_rating` double NOT NULL,
  `number_of_reviews` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `description`, `name`, `average_rating`, `number_of_reviews`) VALUES
(1, 'Social', 'Meta', 6.833333333333333, 3),
(2, 'E-Commerce', 'Amazon', 0, 0),
(3, 'Tech', 'Microsoft', 0, 0),
(4, 'Search Tech', 'Google', 8.5, 2),
(6, 'EdTech', 'Udemy', 8.583333333333334, 6),
(7, 'Product', 'Apple', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Database: `job`
--
DROP DATABASE IF EXISTS `job`;
CREATE DATABASE IF NOT EXISTS `job` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `job`;

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` int NOT NULL,
  `company_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `max_salary` varchar(255) DEFAULT NULL,
  `min_salary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `job`
--

INSERT INTO `job` (`id`, `company_id`, `description`, `location`, `max_salary`, `min_salary`, `title`) VALUES
(1, 1, 'Developer', 'Korea', '80,000', '70,000', 'Software Engineer'),
(2, 2, 'Tester', 'Japan', '70,000', '70,000', 'Software Engineer'),
(3, 3, 'BA', 'NY', '70,000', '60,000', 'Software Engineer'),
(5, 1, 'GPT [updated 2]', 'China', '80,000', '70,000', 'AI Engineer'),
(7, 3, 'GPT', 'China', '80,000', '70,000', 'AI Engineer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Database: `review`
--
DROP DATABASE IF EXISTS `review`;
CREATE DATABASE IF NOT EXISTS `review` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `review`;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` int NOT NULL,
  `company_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rating` double NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `company_id`, `description`, `rating`, `title`) VALUES
(1, 1, 'Review for Company 1', 8.5, 'Review'),
(2, 2, 'Review for Company 2', 7.5, 'Review'),
(3, 3, 'Review for Company 3', 8.5, 'Review'),
(4, 4, 'Review for Company 4', 8, 'Review'),
(5, 1, 'Review for Company 1', 7, 'Review'),
(8, 6, 'COMPANY 6 DESC', 9, 'COMPANY 6 REVIEW 1'),
(9, 6, 'COMPANY 6 DESC', 8, 'COMPANY 6 REVIEW 2'),
(10, 6, 'COMPANY 6 DESC', 10, 'COMPANY 6 REVIEW 3'),
(11, 6, 'COMPANY 6 DESC', 7.5, 'COMPANY 6 REVIEW 4'),
(12, 6, 'COMPANY 6 DESC', 8, 'COMPANY 6 REVIEW 5'),
(13, 6, 'COMPANY 6 DESC', 9, 'COMPANY 6 REVIEW 6'),
(14, 1, 'COMPANY 6 DESC', 5, 'COMPANY 1 REVIEW 3'),
(15, 11, 'COMPANY 6 DESC', 10, 'COMPANY 1 REVIEW 6'),
(16, 11, 'COMPANY 6 DESC', 10, 'COMPANY 1 REVIEW 6'),
(17, 11, 'COMPANY 11 DESC', 10, 'COMPANY 11 REVIEW 3'),
(18, 11, 'COMPANY 11 DESC', 5, 'COMPANY 11 REVIEW 4'),
(19, 4, 'Great food.', 9, 'Google review 1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
