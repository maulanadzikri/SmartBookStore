-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 26, 2024 at 03:24 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sbs_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_author`
--

CREATE TABLE `tb_author` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_author`
--

INSERT INTO `tb_author` (`id`, `name`) VALUES
(15, 'Abdul Kadir'),
(16, 'Endang Indriani '),
(24, 'Günter Enderle, Klaus Kansy, Günther Pfaff'),
(19, 'Indrajani'),
(4, 'Jane Eyre'),
(20, 'Jubilee Enterprise'),
(28, 'M. T. SOMASHEKARA, D. S. GURU, K. S. MANJUNATHA'),
(23, 'Michael Paluszek, Stephanie Thomas'),
(17, 'Ramachandran Bharath, James Drosen'),
(2, 'Rick Riordan'),
(18, 'Safiq Rosad'),
(25, 'Sekar Maya'),
(21, 'Shai Shalev-Shwartz, Shai Ben-David'),
(26, 'Syafrial Fachri Pane, Yogi Aditya Saputra'),
(27, 'Vivian Siahaan'),
(22, 'Vivian Siahaan, Rismon Hasiholan Sianipar');

-- --------------------------------------------------------

--
-- Table structure for table `tb_book`
--

CREATE TABLE `tb_book` (
  `id` int NOT NULL,
  `img` varchar(255) NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  `title` varchar(50) NOT NULL,
  `year` int NOT NULL,
  `admin_id` int DEFAULT NULL,
  `author_id` int NOT NULL,
  `category_id` int NOT NULL,
  `publisher_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_book`
--

INSERT INTO `tb_book` (`id`, `img`, `price`, `stock`, `title`, `year`, `admin_id`, `author_id`, `category_id`, `publisher_id`) VALUES
(9, 'http://books.google.com/books/content?id=6mXrDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 12000, 30, 'Logika Pemrograman Java', 2020, 11, 15, 4, 7),
(10, 'http://books.google.com/books/content?id=2qweDgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 15000, 34, 'HOMEMADE COOKING', 2017, 11, 16, 5, 8),
(11, 'http://books.google.com/books/content?id=7H0pAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api', 50000, 50, 'Neural Network Computing', 1994, 11, 17, 4, 9),
(12, 'http://books.google.com/books/content?id=DsOPEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 15000, 40, 'Bahasa Pemrograman Java Tingkat Dasar', 2022, 11, 18, 4, 10),
(15, 'http://books.google.com/books/content?id=94pKDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 20000, 50, 'Pengenalan PHP dan Java untuk Pemula', 2016, 11, 20, 4, 7),
(16, 'http://books.google.com/books/content?id=ttJkAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 18000, 44, 'Understanding Machine Learning', 2014, 11, 21, 4, 12),
(17, 'http://books.google.com/books/content?id=NbRvDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 16000, 48, 'Pengantar Pengolahan Citra Digital', 2018, 11, 22, 4, 13),
(19, 'http://books.google.com/books/content?id=eSzPDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 20000, 47, 'Practical MATLAB Deep Learning', 2020, 11, 23, 4, 14),
(20, 'http://books.google.com/books/content?id=FcaqCAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 20000, 49, 'Computer Graphics Programming', 2012, 11, 24, 4, 15),
(21, 'http://books.google.com/books/content?id=EwEjw5xJKRAC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 13000, 50, 'KKPK I Love Cooking', 2007, 11, 25, 8, 16),
(22, 'http://books.google.com/books/content?id=GFlgDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api', 30000, 50, 'Pemrograman C++: Dari Nol Sampai Master', 2019, 11, 27, 4, 18);

-- --------------------------------------------------------

--
-- Table structure for table `tb_category`
--

CREATE TABLE `tb_category` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_category`
--

INSERT INTO `tb_category` (`id`, `name`) VALUES
(7, 'Antiques & Collectibles'),
(9, 'Business & Economics'),
(8, 'Comics & Graphic Novels'),
(4, 'Computers'),
(5, 'Cooking'),
(1, 'Ensiklopedi'),
(6, 'Java (Computer program language)'),
(3, 'Komik');

-- --------------------------------------------------------

--
-- Table structure for table `tb_order`
--

CREATE TABLE `tb_order` (
  `id` int NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `total` int DEFAULT NULL,
  `customer_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_order`
--

INSERT INTO `tb_order` (`id`, `date`, `total`, `customer_id`) VALUES
(6, '06/22/2024', 45000, 12),
(7, '06/23/2024', 45000, 12),
(8, '06/23/2024', 45000, 12),
(9, '06/23/2024', 30000, 13),
(10, '06/23/2024', 90000, 12),
(11, '06/23/2024', 30000, 12),
(12, '06/24/2024', 32000, 12),
(13, '06/25/2024', 40000, 13),
(14, '06/26/2024', 54000, 13),
(15, '06/26/2024', 36000, 13),
(16, '06/26/2024', 20000, 13),
(17, '06/26/2024', 20000, 13),
(18, '06/26/2024', 18000, 13);

-- --------------------------------------------------------

--
-- Table structure for table `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_privilege`
--

INSERT INTO `tb_privilege` (`id`, `name`) VALUES
(1, 'create_admin'),
(5, 'create_user'),
(4, 'delete_admin'),
(8, 'delete_user'),
(2, 'read_admin'),
(6, 'read_user'),
(3, 'update_admin'),
(7, 'update_user');

-- --------------------------------------------------------

--
-- Table structure for table `tb_publisher`
--

CREATE TABLE `tb_publisher` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_publisher`
--

INSERT INTO `tb_publisher` (`id`, `name`) VALUES
(14, 'Apress'),
(13, 'BALIGE PUBLISHING'),
(12, 'Cambridge University Press'),
(16, 'DAR! Mizan'),
(7, 'Elex Media Komputindo'),
(2, 'GagasMedia'),
(1, 'Gramedia'),
(8, 'Kawan Pustaka'),
(17, 'Kreatif'),
(9, 'McGraw-Hill Companies'),
(10, 'Nas Media Pustaka'),
(19, 'PHI Learning Pvt. Ltd.'),
(18, 'Sparta Publisher'),
(15, 'Springer Science & Business Media'),
(11, 'Uwais Inspirasi indonesia ');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_order_book`
--

CREATE TABLE `tb_tr_order_book` (
  `order_id` int NOT NULL,
  `book_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_tr_order_book`
--

INSERT INTO `tb_tr_order_book` (`order_id`, `book_id`) VALUES
(6, 10),
(7, 10),
(8, 10),
(9, 10),
(10, 10),
(11, 10),
(12, 17),
(13, 19),
(14, 16),
(15, 16),
(16, 20),
(17, 19),
(18, 16);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_role_privilege`
--

CREATE TABLE `tb_tr_role_privilege` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_tr_role_privilege`
--

INSERT INTO `tb_tr_role_privilege` (`role_id`, `privilege_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_role_privileges`
--

CREATE TABLE `tb_tr_role_privileges` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_user_role`
--

CREATE TABLE `tb_tr_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_tr_user_role`
--

INSERT INTO `tb_tr_user_role` (`user_id`, `role_id`) VALUES
(11, 1),
(12, 2),
(13, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `user_detail_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `password`, `username`, `is_enabled`, `user_detail_id`) VALUES
(11, '$2a$10$HTCmAWq3Ni/kUEe7NcH1MuwfX3Y0B3/QnrXN85orxuhQ0Iogmpu/a', 'admin', b'1', NULL),
(12, '$2a$10$ODU2tIWTsot03OSoGFo6a.tvczqQnCOoe4FFgT4WB.crgmAqHF3MK', 'user2', b'1', NULL),
(13, '$2a$10$bZFDNpH.F0LcoqvXACTeXee51RpiIpdxy8KIpPEb/ciqZfdPEr1UK', 'user', b'1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user_detail`
--

CREATE TABLE `tb_user_detail` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_user_detail`
--

INSERT INTO `tb_user_detail` (`id`, `email`, `fullname`, `phone`) VALUES
(11, 'sbs.admin1@gmail.com', 'Admin 1', '080111111111'),
(12, 'user22@gmail.com', 'User 2', '080111000000'),
(13, 'user1@gmail.com', 'User', '08022200000');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_author`
--
ALTER TABLE `tb_author`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6f7pu6hq7r5byjx9gfb04dh27` (`name`);

--
-- Indexes for table `tb_book`
--
ALTER TABLE `tb_book`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_p5lcdg7dcprbg8gakbw2t0j2y` (`title`),
  ADD KEY `FKlguucrdk731pm5g6nxkcckeqp` (`admin_id`),
  ADD KEY `FKc27yltypnyytr71q1a0vdg8w9` (`author_id`),
  ADD KEY `FKciuinldyblgvrg7ef6sksof8b` (`category_id`),
  ADD KEY `FKnirnq5sunln2aixln0wfrlx1o` (`publisher_id`);

--
-- Indexes for table `tb_category`
--
ALTER TABLE `tb_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4gpl7afmaxiecnv7fv7unn2mp` (`name`);

--
-- Indexes for table `tb_order`
--
ALTER TABLE `tb_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK88nvdv879yhokc78g3u08aa5f` (`customer_id`);

--
-- Indexes for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t4vvgri0dgkkyq3uu04gfa3hu` (`name`);

--
-- Indexes for table `tb_publisher`
--
ALTER TABLE `tb_publisher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_adcvukyn33p8sq3u9cgkaobx1` (`name`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1ncmoedv5ta7r19y9d4oidn0y` (`name`);

--
-- Indexes for table `tb_tr_order_book`
--
ALTER TABLE `tb_tr_order_book`
  ADD KEY `FK5caj2cqb3bjvaof26noyiei0r` (`order_id`),
  ADD KEY `FK_fqqy6jwqdsyfxbqqonf6rmo47` (`book_id`);

--
-- Indexes for table `tb_tr_role_privilege`
--
ALTER TABLE `tb_tr_role_privilege`
  ADD KEY `FK1mxqp25o03axbfetfl77upkt1` (`privilege_id`),
  ADD KEY `FKk3bioyxr0o4pbsk297dap6s1c` (`role_id`);

--
-- Indexes for table `tb_tr_role_privileges`
--
ALTER TABLE `tb_tr_role_privileges`
  ADD KEY `FKoinl09cgr0e55ear05xtebe0h` (`privilege_id`),
  ADD KEY `FKd5meaiq1964lid86av797de3l` (`role_id`);

--
-- Indexes for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD KEY `FKgra7ve9sw7nn23oprc87454tx` (`role_id`),
  ADD KEY `FKcn7k4v4pdk0gro6c6pi64uewx` (`user_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`),
  ADD KEY `FKl712bd9hdgpnvx29moyoaas3v` (`user_detail_id`);

--
-- Indexes for table `tb_user_detail`
--
ALTER TABLE `tb_user_detail`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9qax0bah7my75iasgklbmspv1` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKo3fc4qors56rvpj2dlbpdroi6` (`role_id`),
  ADD KEY `FKlqb868dhpatxi3e1m1nu3ukr5` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_author`
--
ALTER TABLE `tb_author`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `tb_book`
--
ALTER TABLE `tb_book`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `tb_category`
--
ALTER TABLE `tb_category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_order`
--
ALTER TABLE `tb_order`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_publisher`
--
ALTER TABLE `tb_publisher`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_user_detail`
--
ALTER TABLE `tb_user_detail`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_book`
--
ALTER TABLE `tb_book`
  ADD CONSTRAINT `FKc27yltypnyytr71q1a0vdg8w9` FOREIGN KEY (`author_id`) REFERENCES `tb_author` (`id`),
  ADD CONSTRAINT `FKciuinldyblgvrg7ef6sksof8b` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`),
  ADD CONSTRAINT `FKlguucrdk731pm5g6nxkcckeqp` FOREIGN KEY (`admin_id`) REFERENCES `tb_user_detail` (`id`),
  ADD CONSTRAINT `FKnirnq5sunln2aixln0wfrlx1o` FOREIGN KEY (`publisher_id`) REFERENCES `tb_publisher` (`id`);

--
-- Constraints for table `tb_order`
--
ALTER TABLE `tb_order`
  ADD CONSTRAINT `FK88nvdv879yhokc78g3u08aa5f` FOREIGN KEY (`customer_id`) REFERENCES `tb_user_detail` (`id`);

--
-- Constraints for table `tb_tr_order_book`
--
ALTER TABLE `tb_tr_order_book`
  ADD CONSTRAINT `FK5caj2cqb3bjvaof26noyiei0r` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`),
  ADD CONSTRAINT `FK_fqqy6jwqdsyfxbqqonf6rmo47` FOREIGN KEY (`book_id`) REFERENCES `tb_book` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKfqqy6jwqdsyfxbqqonf6rmo47` FOREIGN KEY (`book_id`) REFERENCES `tb_book` (`id`);

--
-- Constraints for table `tb_tr_role_privilege`
--
ALTER TABLE `tb_tr_role_privilege`
  ADD CONSTRAINT `FK1mxqp25o03axbfetfl77upkt1` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`),
  ADD CONSTRAINT `FKk3bioyxr0o4pbsk297dap6s1c` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_tr_role_privileges`
--
ALTER TABLE `tb_tr_role_privileges`
  ADD CONSTRAINT `FKd5meaiq1964lid86av797de3l` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`),
  ADD CONSTRAINT `FKoinl09cgr0e55ear05xtebe0h` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`);

--
-- Constraints for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD CONSTRAINT `FKcn7k4v4pdk0gro6c6pi64uewx` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKgra7ve9sw7nn23oprc87454tx` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `FKl712bd9hdgpnvx29moyoaas3v` FOREIGN KEY (`user_detail_id`) REFERENCES `tb_user_detail` (`id`);

--
-- Constraints for table `tb_user_detail`
--
ALTER TABLE `tb_user_detail`
  ADD CONSTRAINT `FKmtpag76d1q2y0kt1vm8i2ofbd` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKlqb868dhpatxi3e1m1nu3ukr5` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKo3fc4qors56rvpj2dlbpdroi6` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
