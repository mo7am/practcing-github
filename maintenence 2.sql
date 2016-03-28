-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2016 at 05:50 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 7.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `maintenance`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `BILL_ID` int(8) UNSIGNED NOT NULL,
  `BILL_DATE` varchar(12) NOT NULL,
  `BILL_COST` double UNSIGNED NOT NULL,
  `order_id` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BILL_ID`, `BILL_DATE`, `BILL_COST`, `order_id`) VALUES
(84, '2016-03-02', 8489.5, 0),
(648, '2016-03-22', 844.2100219726562, 0),
(8465, '2016-03-15', 4.239999771118164, 0),
(54894, '2016-03-24', 564.5599975585938, 0),
(65484, '2016-03-10', 541.1539916992188, 0);

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `BRANCH_ID` int(8) UNSIGNED NOT NULL,
  `MANAGER_ID` int(8) UNSIGNED NOT NULL,
  `BRANCH_LOCATION` varchar(30) NOT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`BRANCH_ID`, `MANAGER_ID`, `BRANCH_LOCATION`, `PHONE_NUMBER`) VALUES
(456848, 64646, 'asdawd', '46849816894989'),
(814984, 14984981, 'sdadawd', '561498149'),
(6248942, 86242, 'sdawdwa', '842842842824');

-- --------------------------------------------------------

--
-- Table structure for table `complain`
--

CREATE TABLE `complain` (
  `COMPLAIN_ID` int(8) UNSIGNED NOT NULL,
  `ORDER_ID` int(8) UNSIGNED NOT NULL,
  `CONTENT` text NOT NULL,
  `COMPLAIN_STATE` tinyint(1) NOT NULL DEFAULT '0',
  `RECIVER_ID` int(8) UNSIGNED NOT NULL,
  `DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complain`
--

INSERT INTO `complain` (`COMPLAIN_ID`, `ORDER_ID`, `CONTENT`, `COMPLAIN_STATE`, `RECIVER_ID`, `DATE`) VALUES
(5, 4, '545454?????????', 0, 0, '2016-03-28 03:45:14'),
(58, 1, 'ftgyhujiko', 1, 1, '2016-03-28 03:25:06');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CUST_ID` int(8) NOT NULL,
  `F_NAME` varchar(20) NOT NULL,
  `L_NAME` varchar(20) NOT NULL,
  `MAIL` varchar(30) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `QUESTION_NUMBER` int(2) NOT NULL,
  `PROTECT_QUESTION` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CUST_ID`, `F_NAME`, `L_NAME`, `MAIL`, `PASSWORD`, `PHONE`, `ADDRESS`, `QUESTION_NUMBER`, `PROTECT_QUESTION`) VALUES
(51, 'asdadqw', 'adawdwa', 'adwqdqw', 'dadwqad', 'dawdawd', 'wqdwadawd', 2, 'awdawdawdwad'),
(56448, 'sdsdf', 'esfsef', 'sdfsfew', 'fewfwef', 'ewfwef', 'ewfwefwe', 1, 'qwefwefwefewf');

-- --------------------------------------------------------

--
-- Table structure for table `damage_device`
--

CREATE TABLE `damage_device` (
  `CUST_ID` int(8) UNSIGNED NOT NULL,
  `DEVICE_ID` int(8) UNSIGNED NOT NULL,
  `DEVICE_MARK` varchar(20) NOT NULL,
  `DEVICE_TYPE` varchar(20) NOT NULL,
  `DESCRIBTION` text NOT NULL,
  `ADDRESS_2` varchar(30) NOT NULL,
  `PHONE_2` varchar(20) NOT NULL,
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  `REQUIST_ID` int(8) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `damage_device`
--

INSERT INTO `damage_device` (`CUST_ID`, `DEVICE_ID`, `DEVICE_MARK`, `DEVICE_TYPE`, `DESCRIBTION`, `ADDRESS_2`, `PHONE_2`, `STATE`, `REQUIST_ID`) VALUES
(68484, 8484, 'scsdc', 'esfsef', 'ewfdwefwe', 'fewefe', 'dewfwef', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `F_NAME` varchar(15) NOT NULL,
  `L_NAME` varchar(15) NOT NULL,
  `SSN` varchar(15) NOT NULL,
  `EMP_ID` int(8) UNSIGNED NOT NULL,
  `EMAIL` varchar(25) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `SALARY` double UNSIGNED NOT NULL,
  `BIRTHDATE` varchar(12) NOT NULL,
  `GENDER` varchar(8) NOT NULL,
  `DATE_OF_APPOINTMENT` varchar(12) NOT NULL,
  `BRANCH_ID` int(8) UNSIGNED NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `SPECIALIZATION` varchar(20) NOT NULL,
  `QUALITY` int(100) NOT NULL,
  `WORK_HOURS` int(3) NOT NULL,
  `ACCOUNT_BLOCK` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`F_NAME`, `L_NAME`, `SSN`, `EMP_ID`, `EMAIL`, `PASSWORD`, `SALARY`, `BIRTHDATE`, `GENDER`, `DATE_OF_APPOINTMENT`, `BRANCH_ID`, `PHONE`, `ADDRESS`, `SPECIALIZATION`, `QUALITY`, `WORK_HOURS`, `ACCOUNT_BLOCK`) VALUES
('moro', 'Clash', '123456789', 984951, 'moroclash@gmail.com', 'moroclash', 20, '20-10-15', 'male', '20-14-14', 1, '0112311154', '20-st/mamama', 'Tecnical', 50, 15, 0),
('moro', 'Clash', '123456789', 984952, 'moroclash@gmail.com', 'moroclash', 20, '20-10-15', 'male', '20-14-14', 1, '0112311154', '20-st/mamama', 'Service', 50, 15, 0);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `FEEDBACK_ID` int(8) UNSIGNED NOT NULL,
  `ORDER_ID` int(8) UNSIGNED NOT NULL,
  `SERVICE_QUALITY` int(100) UNSIGNED NOT NULL,
  `PERSONAL_OPINION` varchar(20) NOT NULL,
  `SYSTEM_QUALITY` int(100) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`FEEDBACK_ID`, `ORDER_ID`, `SERVICE_QUALITY`, `PERSONAL_OPINION`, `SYSTEM_QUALITY`) VALUES
(51651, 56165, 0, 'dwqdqw', 0);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `MESSAGE_ID` int(8) UNSIGNED NOT NULL,
  `RECIVER_ID` int(8) UNSIGNED NOT NULL,
  `CONTENT` text NOT NULL,
  `STATE` varchar(20) NOT NULL,
  `Date` varchar(12) NOT NULL,
  `SENDER_ID` int(8) UNSIGNED NOT NULL,
  `message_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`MESSAGE_ID`, `RECIVER_ID`, `CONTENT`, `STATE`, `Date`, `SENDER_ID`, `message_date`) VALUES
(8484, 8484, 'sdfewfewf', 'efewfewf', '', 0, '2016-03-28 03:47:48'),
(8485, 121, 'ezy omk', '1212', '12-12-12', 124, '2016-03-28 03:47:48');

-- --------------------------------------------------------

--
-- Table structure for table `order_information`
--

CREATE TABLE `order_information` (
  `ORDER_ID` int(8) UNSIGNED NOT NULL,
  `TECHNICAL_ID` int(8) UNSIGNED NOT NULL,
  `DEVICE_ID` int(8) UNSIGNED NOT NULL,
  `START_DATE` varchar(12) NOT NULL,
  `APPOINTMENT_OF_RECIEPT` varchar(12) NOT NULL,
  `SERVICE_ID` int(13) UNSIGNED NOT NULL,
  `STATE` varchar(15) NOT NULL DEFAULT '0',
  `TECHNICAL_DESCRIPTION` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_information`
--

INSERT INTO `order_information` (`ORDER_ID`, `TECHNICAL_ID`, `DEVICE_ID`, `START_DATE`, `APPOINTMENT_OF_RECIEPT`, `SERVICE_ID`, `STATE`, `TECHNICAL_DESCRIPTION`) VALUES
(544, 201453, 464684, '2016-03-02', '2016-03-02', 20145, '0', NULL),
(48948, 8484, 8489489, '2016-03-23', '2016-03-17', 0, '0', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `requist`
--

CREATE TABLE `requist` (
  `REQUIST_ID` int(8) UNSIGNED NOT NULL,
  `CUSTOMER_ID` int(8) UNSIGNED NOT NULL,
  `DEVICE_ID` int(8) UNSIGNED NOT NULL,
  `REQUIST_DATE` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requist`
--

INSERT INTO `requist` (`REQUIST_ID`, `CUSTOMER_ID`, `DEVICE_ID`, `REQUIST_DATE`) VALUES
(2, 32, 23, 'sss'),
(22522, 32, 23, 'mohamed');

-- --------------------------------------------------------

--
-- Table structure for table `technical_region`
--

CREATE TABLE `technical_region` (
  `EMP_ID` int(8) UNSIGNED NOT NULL,
  `REGION` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `technical_region`
--

INSERT INTO `technical_region` (`EMP_ID`, `REGION`) VALUES
(548, 'wqdqwdwq'),
(484, 'sadasdasd'),
(48498, 'dadwad'),
(489498, 'sadaw'),
(56484, 'wdqadwasd'),
(984950, 'Cairo'),
(984950, 'Cairo');

-- --------------------------------------------------------

--
-- Table structure for table `timechoose`
--

CREATE TABLE `timechoose` (
  `order_id` int(11) NOT NULL,
  `time_1` varchar(15) NOT NULL,
  `time_2` varchar(15) NOT NULL,
  `time_3` varchar(15) NOT NULL,
  `chosee_time` varchar(15) NOT NULL,
  `state` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`BILL_ID`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`BRANCH_ID`);

--
-- Indexes for table `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`COMPLAIN_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CUST_ID`);

--
-- Indexes for table `damage_device`
--
ALTER TABLE `damage_device`
  ADD PRIMARY KEY (`DEVICE_ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EMP_ID`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`FEEDBACK_ID`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`MESSAGE_ID`);

--
-- Indexes for table `order_information`
--
ALTER TABLE `order_information`
  ADD PRIMARY KEY (`ORDER_ID`);

--
-- Indexes for table `requist`
--
ALTER TABLE `requist`
  ADD PRIMARY KEY (`REQUIST_ID`),
  ADD KEY `REQUIST_ID` (`REQUIST_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `BILL_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65485;
--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `BRANCH_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6248943;
--
-- AUTO_INCREMENT for table `complain`
--
ALTER TABLE `complain`
  MODIFY `COMPLAIN_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56565;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CUST_ID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56449;
--
-- AUTO_INCREMENT for table `damage_device`
--
ALTER TABLE `damage_device`
  MODIFY `DEVICE_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8485;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EMP_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=984953;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `FEEDBACK_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51652;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `MESSAGE_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8486;
--
-- AUTO_INCREMENT for table `order_information`
--
ALTER TABLE `order_information`
  MODIFY `ORDER_ID` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48949;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
