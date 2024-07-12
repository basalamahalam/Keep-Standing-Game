-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2023 at 06:25 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_game`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbgame`
--

CREATE TABLE `tbgame` (
  `username` varchar(50) NOT NULL,
  `score` int(11) NOT NULL,
  `standing` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbgame`
--

INSERT INTO `tbgame` (`username`, `score`, `standing`) VALUES
('123', 1950, 41),
('345', 1100, 25),
('45', 860, 16),
('aca', 750, 17),
('asd', 3060, 52),
('ipman', 1230, 24),
('jetli', 930, 18),
('qw', 670, 15),
('qwe', 1890, 41),
('RRT', 880, 21),
('ty', 750, 15),
('yu', 600, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbgame`
--
ALTER TABLE `tbgame`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
