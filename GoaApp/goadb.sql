-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Mar 29, 2020 at 05:20 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `goadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `img_id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(500) NOT NULL,
  `img_location` varchar(500) NOT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`img_id`, `img_name`, `img_location`) VALUES
(4, 'image1', 'uploads/myimage.jpg'),
(5, 'image1', 'uploads/myimage1.jpg'),
(6, 'image1', 'uploads/myimage1.jpg'),
(7, 'image1', 'uploads/myimage12.jpg'),
(8, 'image1', 'uploads/myimage12.jpg'),
(9, 'image1', 'uploads/myimage12.jpg'),
(10, 'image1', 'uploads/myimage12.jpg'),
(11, 'image1', 'uploads/myimage12.jpg'),
(12, 'image1', 'uploads/myimage12.jpg'),
(13, 'image1', 'uploads/myimage12.jpg'),
(14, 'image1', 'uploads/myimage12.jpg'),
(15, 'image1', 'uploads/myimage12.jpg'),
(16, 'image1', 'uploads/myimage12.jpg'),
(17, 'image1', 'uploads/myimage12.jpg'),
(18, 'image1', 'uploads/myimage12.jpg'),
(19, 'image1', 'uploads/myimage12.jpg'),
(20, 'image1', 'uploads/myimage12.jpg'),
(21, 'image1', 'uploads/myimage12.jpg'),
(22, 'image1', 'uploads/myimage12.jpg'),
(23, 'image1', 'uploads/myimage12.jpg'),
(24, 'image1', 'uploads/myimage12.jpg'),
(25, 'image1', 'uploads/myimage12.jpg'),
(26, 'image1', 'uploads/myimage12.jpg'),
(27, 'image1', 'uploads/myimage12.jpg'),
(28, 'image1', 'uploads/myimage12.jpg'),
(29, 'image1', 'uploads/myimage12.jpg'),
(30, 'image1', 'uploads/myimage12.jpg'),
(31, 'image1', 'uploads/myimage12.jpg'),
(32, 'image1', 'uploads/myimage12.jpg'),
(33, 'image1', 'uploads/myimage12.jpg'),
(34, 'image1', 'uploads/myimage12.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `image_tb`
--

DROP TABLE IF EXISTS `image_tb`;
CREATE TABLE IF NOT EXISTS `image_tb` (
  `location_name` varchar(50) NOT NULL,
  `image_name` varchar(50) NOT NULL,
  `info` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `image_tb`
--

INSERT INTO `image_tb` (`location_name`, `image_name`, `info`) VALUES
('Basilica of Bom Jesus', 'BomJesus.jpg', 'The Basilica of Bom Jesus church is one of a kind in India and is known for its exemplary baroque architecture. Built in the year 1594 and consecrated in 1605, the building of this church coincides with the beginning of Christianity in India. The church is located in Old Goa in Bainguinim about 10 km away from Panjim. The oldest church in Goa, Basilica of Bom Jesus holds the remains of St. Francis Xavier, a special friend of St. Ignatius Loyola with whom he founded the Society of Jesus (Jesuits). Even after 400 years, the remains are in a good condition and are taken out once every decade.  A site with rich cultural and religious significance, the Basilica of Bom Jesus has been declared a World Heritage Site by UNESCO.'),
('Fort Aguada', 'FortAguada.jpg', 'Fort Aguada is an epitome of Portuguese architecture built in the 17th century. Its lighthouse and the fort itself have braved the ravages of time and stand like silent sentinels on the Sinquerim Beach, approximately 18 km from Panjim. Built-in 1612 as a protection from Dutch and Marathas, it was the most prized and crucial fort for the Portuguese and covers the entire peninsula at the southwestern tip of Bardez. The view from the fort is breathtaking as it overlooks the confluence of Mandovi River and the Arabian Sea. \r\n\r\nThe fort is so named after the Portuguese Word for water i.e. \'Agua\' and used to be a replenishing source of freshwater for sailors. In fact, it has the capacity to hold 2,376,000 gallons of water and was one of the biggest freshwater reservoirs of Asia.\r\nFort Aguada has also been used as Aguada jail. A statue in front of the jail salutes the freedom fighters, giving the fort a touch of patriotism.'),
('Calangute Beach', 'CalanguteBeach.jpg', 'Situated 15 km from Panjim, Calangute Beach is the longest beach in North Goa, stretching from Candolim to Baga. Due to its sheer size and popularity, it is a hub for tourists and backpackers from all over the world.\r\n\r\nPopular as the \"Queen of Beaches\", the Calangute Beach of Goa is among the top ten bathing beaches in the world. Being one of the busiest and most commercial beaches of Goa, it is swarming with eating joints, shacks and clubs serving cocktails, beer and seafood. The Calangute Beach is also known for its water sports activities like parasailing, water surfing, banana ride and jet-skiing. While the days here are filled with beach fun, nights call for upbeat parties and letting your hair down. Calangute is also popular for staying in Goa as it keeps you well-connected to the other beaches in the north such as Baga, Anjuna, Candolim, Aguada and many more.'),
('Church of St. Cajetan', 'ChurchCajetan.jpg', 'This church has a significant resemblance with the St. Peters Basilica in Rome. On the left, there are three altars dedicated to the Holy Family, Our Lady of Piety and St.Clare and the right-side altars are dedicated to St. Agnes, St. Cajetan and St. John. Though the building is 300 years old, it has been wonderfully preserved and is still visited in large numbers by Catholic devotees and tourists alike.\r\n\r\nThe Church of Saint Cajetan is the church of the Roman Catholic Archdiocese of Goa and Daman built in the second half of the 17th century. Initially called the Church of Our Lady of Divine Providence the church is a part of the World heritage site, churches, and convents. One of the must-visits of Goa, the church is admired for its wonderful and graceful architecture that reminds you of Basilica of St. Peter in Rome. The only surviving domed structure in Goa, the beautiful tourist destination is named after the founder of the Theatine order, St. Cajetan even though the church is dedicated to Our Lady of Divine Providence.');

-- --------------------------------------------------------

--
-- Table structure for table `latlong`
--

DROP TABLE IF EXISTS `latlong`;
CREATE TABLE IF NOT EXISTS `latlong` (
  `place` varchar(100) NOT NULL,
  `platitude` varchar(20) NOT NULL,
  `plongitude` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `latlong`
--

INSERT INTO `latlong` (`place`, `platitude`, `plongitude`) VALUES
('Candolim Beach', '15.515447', '73.768402'),
('Terekhol Fort', '15.721799', '73.686595'),
('Arambol Beach', '15.684898', '73.703230'),
('Mandrem Beach', '15.669936', '73.709542'),
('Ashwem Beach', '15.643991', '73.717698'),
('Morgim Beach', '15.615664', '73.736384'),
('Vagator Beach', '15.603376', '73.733638'),
('Anjuna Beach', '15.573972', '73.740717'),
('Baga Beach', '15.555650', '73.751763'),
('Calangute Beach', '15.550846', '73.753184'),
('Miramar Beach', '15.483774', '73.808490'),
('Grandmothers Hole Beach', '15.406470', '73.786318'),
('Baina Beach', '15.391780', '73.806823'),
('Bogmalo Beach', '15.369999', '73.833667'),
('Majorda Beach', '15.310964', '73.902015'),
('Colva Beach', '15.280827', '73.911861'),
('Benaulim Beach', '15.250909', '73.920570'),
('Varca Beach', '15.221349', '73.929012'),
('Cavelossim Beach', '15.171678', '73.941599'),
('Mobor Beach', '15.163498', '73.943830'),
('Agonda Beach', '15.043788', '73.985752'),
('Palolem Beach', '15.010058', '74.023213'),
('Patnem Beach', '14.996941', '74.033741'),
('Sahakari Spice Farm', '15.409520', '74.024156'),
('Shree Mangesh Temple', '15.444987', '73.966373'),
('Shri Shantadurga Temple', '15.513275', '73.769774'),
('Basilica of Bom Jesus', '15.500956', '73.911627'),
('Church of Mary Immaculate Conception', '15.498696', '73.829269'),
('Dona Paula View Point', '15.450685', '73.802988'),
('Three Kings Chapel', '15.360343', '73.900798'),
('Ancestral Goa- Big Foot Museum', '15.339586', '73.987537'),
('Archaeological Museum of Goa', '15.503259', '73.910844'),
('Indian Naval Aviation Museum', '15.374857', '73.838792'),
('Salaulim Dam', '15.176990', '74.191559'),
('Dudhsagar Waterfall', '15.314427', '74.314324'),
('Mayem Lake', '15.576097', '73.940011'),
('Bondla Wildlife Sanctuary', '15.440369', '74.106404'),
('Aguada Fort', '15.492662', '73.773147'),
('Dr. Salim Ali Bird Sanctuary', '15.513064', '73.870414'),
('Anjunem Keri Dam', '15.616837', '74.088487'),
('Harvalem Waterfall', '15.551104', '74.026664'),
('Harvalem Caves', '15.552847', '74.023059'),
('Kesarval Spring Verna Waterfall', '15.382338', '73.928806'),
('Cotigao Wildlife Sanctuary', '14.994379', '74.199359'),
('Netravali Wildlife Sanctuary', '15.085794', '74.229389'),
('Bhagwan Mahavir Wildlife Sanctuary', '15.329298', '74.281075'),
('Surla Waterfalls', '15.674659', '74.185985'),
('Chorla Ghat', '15.649723', '74.118903'),
('Carambolim Lake', '15.488935', '73.927860'),
('Kuske Waterfall', '15.025593', '74.214384'),
('Chapora Fort', '15.606144', '73.736439'),
('Mormugao Fort', '15.399992', '73.791321'),
('Cabo Raj Niwas', '15.463307', '73.788560'),
('Cabo de Rama Fort', '15.088826', '73.921588'),
('Mhadei Wildlife Sanctuary', '15.530005', '74.130111'),
('St. Augustine Church-Tower', '15.500540', '73.906446'),
('Church of St. Cajetan', '15.505649', '73.915037'),
('Se Cathredal', '15.503949', '73.912197'),
('Catholic Church of St. Francis of Assisi', '15.503130', '73.911234'),
('Safa Masjid', '15.406636', '73.999981'),
('Wax World Museum', '15.500401', '73.913742'),
('Amthane Dam', '15.670836', '73.907177'),
('Tito’s Street', '15.556544', '73.754037'),
('Galgibaga Beach', '14.960062', '74.049565'),
('Sinquerim Beach', '15.499169', '73.767464'),
('Butterfly Beach', '15.019605', '74.001838'),
('Bambolim Beach', '15.453696', '73.848949'),
('Chapora River', '15.620723', '73.744841'),
('Spice Plantations Visit', '15.452799', '74.010738'),
('Divar Island', '15.527697', '73.906587'),
('Convent of Santa Monica and The Chapel of The Weeping Cross', '15.501399', '73.907414'),
('Museum Of Christian Art', '15.501279', '73.907068'),
('Lamgao Buddhist Caves', '15.595954', '73.936539'),
('Corjuem Fort', '15.596820', '73.892789'),
('Monkey Beach', '15.400318', '73.791066'),
('Betalbatim Beach', '15.293107', '73.907835'),
('Cola Beach', '15.057338', '73.971036'),
('Velsao Beach', '15.354442', '73.883864'),
('Coco Beach', '15.498844', '73.786039'),
('Maruti Temple', '15.490432', '73.828322'),
('Mahalaxmi Temple', '15.495698', '73.826326'),
('Shri Mahalasa Narayani Temple', '15.359629', '73.944903'),
('Shree Ramnath Temple', '15.398790', '73.981450'),
('Reis Magos Fort', '15.497092', '73.809529'),
('Goa Science Centre & Planetarium', '15.477459', '73.808940');

-- --------------------------------------------------------

--
-- Table structure for table `rating_z`
--

DROP TABLE IF EXISTS `rating_z`;
CREATE TABLE IF NOT EXISTS `rating_z` (
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `location` varchar(100) CHARACTER SET utf8 NOT NULL,
  `date` varchar(100) CHARACTER SET utf8 NOT NULL,
  `time` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Q1` float NOT NULL,
  `Q2` float NOT NULL,
  `Q3` float NOT NULL,
  `Q4` float NOT NULL,
  `Q5` float NOT NULL,
  `Q6` float NOT NULL,
  `Q7` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating_z`
--

INSERT INTO `rating_z` (`username`, `location`, `date`, `time`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`) VALUES
('ABC', 'Church', '2020-02-02', '16:50', 1, 1.5, 3, 4.5, 3, 1.5, 1),
('DEF', 'Church', '2020-02-02', '16:50', 2, 3.5, 4, 4.5, 4, 3.5, 2),
('GHI', 'Church', '2020-02-02', '16:50', 1, 1.5, 3, 4.5, 3, 1.5, 1),
('JKL', 'Church', '2020-02-02', '16:50', 2, 3.5, 4, 4.5, 4, 3.5, 2),
('MNO', 'Church', '2020-02-02', '16:50', 1, 1.5, 3, 4.5, 3, 1.5, 1),
('PQR', 'Church', '2020-02-02', '16:50', 2, 3.5, 4, 4.5, 4, 3.5, 2),
('STU', 'Church', '2020-02-02', '16:50', 1, 1.5, 3, 4.5, 3, 1.5, 1),
('ABC', 'Church', '2020-02-03', '20:04:57', 3.5, 3, 4, 5, 4, 4, 4),
('ABC', 'Church', '2020-02-03', '21:20:22', 3, 4, 1.5, 4.5, 4, 2, 4),
('ABC', 'Church', '2020-02-04', '08:39:12', 4, 3, 2, 4.5, 4, 3, 3),
('ABC', 'Church', '2020-02-04', '08:43:21', 2.5, 4.5, 3, 3, 3.5, 3.5, 4),
('ABC', 'Church', '2020-02-04', '08:45:12', 2.5, 4.5, 4, 2, 4, 2.5, 3.5),
('ABC', 'Church', '2020-02-04', '09:31:38', 2.5, 3, 4, 1.5, 2.5, 2.5, 3),
('ABC', 'Church', '2020-02-04', '09:34:53', 3.5, 4.5, 4, 3.5, 4, 4, 4),
('ABC', 'Church', '2020-02-04', '12:09:16', 3, 2.5, 3, 4, 2.5, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
  `count` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `reviews` varchar(40000) NOT NULL,
  `source` varchar(1000) NOT NULL,
  PRIMARY KEY (`count`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`count`, `name`, `reviews`, `source`) VALUES
(1, 'ANCBD', 'dhdjd akall fhns', 'nssls ghtyeu ckss'),
(2, 'ABC', 'jhkjklkmloiuiut', 'gytuyjhjbn'),
(3, 'ABC', 'jugguiis', 'sjsndjnskk'),
(4, 'ABC', 'jugguiis', 'sjsndjnskk'),
(5, 'ABC', '', ''),
(6, 'ABC', '', ''),
(7, 'ABC', '', ''),
(8, '', '', ''),
(9, 'ABC', '', ''),
(10, 'ABC', '', ''),
(11, 'ABC', '', ''),
(12, '', '', ''),
(13, 'ABC', 'khup chan', 'oyotjfjd'),
(14, 'ABC', 'sgdjdvcs', 'wgejrvsv'),
(15, 'ABC', 'gedgsc', 'jddgh'),
(16, 'ABC', 'hi', 'hggigh');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
