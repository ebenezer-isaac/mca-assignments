-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2021 at 10:25 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `author_id` int(5) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`author_id`, `first_name`, `last_name`) VALUES
(1, 'Charles', 'Dickens'),
(2, 'William', 'Shakespeare'),
(3, 'Mark', 'Twain'),
(4, 'Oscar', 'Wilde');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(5) NOT NULL,
  `author_id` int(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `url` varchar(25) NOT NULL,
  `price` float(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `author_id`, `name`, `description`, `url`, `price`) VALUES
(1, 1, 'Oliver Twist', 'Oliver Twist; or, the Parish Boy\'s Progress is Charles Dickens\'s second novel, and was published as a serial from 1837 to 1839 and released as a three', 'oliver.jfif', 149.99),
(2, 1, 'A Christmas Carol', 'A Christmas Carol. In Prose. Being a Ghost Story of Christmas, commonly known as A Christmas Carol, is a novella by Charles Dickens, first published i', 'christmas_carol.jfif', 100.00),
(3, 1, 'David Copperfield', 'The Personal History, Adventures, Experience and Observation of David Copperfield the Younger of Blunderstone Rookery, commonly known as David Copperf', 'david_copperfield.jfif', 100.00),
(4, 1, 'Great Expectations', 'Great Expectations is the thirteenth novel by Charles Dickens and his penultimate completed novel. It depicts the education of an orphan nicknamed Pip', 'great_expectations.jfif', 125.00),
(5, 1, 'Bleak House', 'Bleak House is a novel by Charles Dickens, first published as a 20-episode serial between March 1852 and September 1853. The novel has many characters', 'bleak_house.jfif', 75.00),
(6, 2, 'Hamlet', 'The Tragedy of Hamlet, Prince of Denmark, often shortened to Hamlet, is a tragedy written by William Shakespeare sometime between 1599 and 1601. It is', 'hamlet.jfif', 130.00),
(7, 2, 'Romeo and Juliet', 'Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young Italian star-crossed lovers whose deaths ultimately r', 'romeo_and_juliet.jfif', 170.00),
(8, 2, 'The Tempest', 'The Tempest is a play by English playwright William Shakespeare, probably written in 1610–1611, and thought to be one of the last plays that Shakespea', 'tempest.jfif', 80.00),
(9, 2, 'Macbeth', 'Macbeth is a tragedy by William Shakespeare; it is thought to have been first performed in 1606. It dramatises the damaging physical and psychological', 'macbeth.jfif', 120.00),
(10, 2, 'King Lear', 'King Lear is a tragedy written by William Shakespeare. The play is based on the mythological Leir of Britain. King Lear relinquishes his power and lan', 'king_lear.jfif', 90.00),
(11, 3, 'Adventures of Tom Sawyer', 'The Adventures of Tom Sawyer is an 1876 novel by Mark Twain about a boy growing up along the Mississippi River. It is set in the 1840s in the town of ', 'tom_sawyer.jfif', 110.00),
(12, 3, 'Adventures of HuckleberryFinn', 'Adventures of Huckleberry Finn is a novel by American author Mark Twain, which was first published in the United Kingdom in December 1884 and in the U', 'huckleberry_finn.jfif', 80.00),
(13, 3, 'Life on the Mississippi', 'Life on the Mississippi is a memoir by Mark Twain of his days as a steamboat pilot on the Mississippi River before the American Civil War. It is also ', 'life_on_the_mississippi.j', 60.00),
(14, 3, 'The Innocents Abroad', 'The Innocents Abroad, or The New Pilgrims\' Progress is a travel book by American author Mark Twain. Published in 1869, it humorously chronicles what T', 'the_innocents_abroad.jfif', 150.00),
(15, 3, 'The Mysterious Stranger', 'A Tramp Abroad is a work of travel literature, including a mixture of autobiography and fictional events, by American author Mark Twain, published in ', 'mysterious_stranger.jfif', 90.00),
(16, 4, 'The Picture of Dorian Gray', 'The Picture of Dorian Gray is a philosophical novel by Oscar Wilde. A shorter novella length version published complete in the July 1890 issue of the ', 'dorian_gray.jfif', 180.00),
(17, 4, 'The Happy Prince', 'The Happy Prince and Other Tales is a collection of stories for children by Oscar Wilde first published in May 1888. It contains five stories: \"The Ha', 'happy_prince.jfif', 120.00),
(18, 4, 'The Canterville Ghost', '\"The Canterville Ghost\" is a humorous short story by Oscar Wilde. It was the first of Wilde\'s stories to be published, appearing in two parts in The C', 'canterville_ghost.jfif', 50.00),
(19, 4, 'The Selfish Giant', '\"The Selfish Giant\" is a short fantasy story for children by the Irish author Oscar Wilde.The story\'s plot revolves around a giant who builds a wall t', 'selfish_giant.jfif', 80.00),
(20, 4, 'A House of Pomegranates', 'A House of Pomegranates is a collection of fairy tales, written by Oscar Wilde, that was published in 1891 as a second collection for The Happy Prince', 'house_of_pomegranates.jfi', 100.00);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_item_id` int(5) NOT NULL,
  `customer_id` int(5) NOT NULL,
  `book_id` int(5) NOT NULL,
  `quantity` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(5) NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `name`, `email`, `password`, `address`) VALUES
(1, 'Ebenezer Isaac', 'mail@ebenezer-isaac.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Coimbatore');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `author_id` (`author_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_item_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `author_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_item_id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
