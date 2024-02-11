-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2023 at 07:16 PM
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
-- Database: `vehiclerentalsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `bikes`
--

CREATE TABLE `bikes` (
  `Regno` varchar(10) NOT NULL,
  `Bike_Brand` varchar(30) DEFAULT NULL,
  `Bike_Model` varchar(30) DEFAULT NULL,
  `Bike_FType` varchar(20) DEFAULT NULL,
  `Bike_Color` varchar(20) DEFAULT NULL,
  `Bike_Features` varchar(20) DEFAULT NULL,
  `Bike_Type` varchar(30) DEFAULT NULL,
  `Bike_Price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bikes`
--

INSERT INTO `bikes` (`Regno`, `Bike_Brand`, `Bike_Model`, `Bike_FType`, `Bike_Color`, `Bike_Features`, `Bike_Type`, `Bike_Price`) VALUES
('B0001', 'Honda', 'CBR1000RR', 'Petrol', 'Red', 'ABS', 'Sport', 1500),
('B0002', 'Kawasaki', 'Ninja 400', 'Petrol', 'Green', 'LED Lights', 'Sport', 750),
('B0003', 'Yamaha', 'YZF-R6', 'Petrol', 'Blue', 'Quick Shifter', 'Sport', 1100),
('B0004', 'Ducati', 'Panigale V4', 'Petrol', 'Red', 'Öhlins Suspension', 'Sport', 2200),
('B0005', 'Harley-Davidson', 'Sportster Iron 883', 'Petrol', 'Black', 'Custom Bobber Style', 'Cruiser', 1200),
('B0006', 'KTM', 'RC 390', 'Petrol', 'Orange', 'Slipper Clutch', 'Sport', 850),
('B0007', 'Triumph', 'Street Triple RS', 'Petrol', 'Silver', 'TFT Display', 'Sport', 1300),
('B0008', 'Benz', 'eBike Model A', 'Electric', 'White', 'Lithium-Ion Battery', 'Electric', 1800),
('B0009', 'Toyota', 'eBike Model X', 'Electric', 'Silver', 'Regenerative Braking', 'Electric', 1600),
('B0010', 'Nissan', 'eBike Model S', 'Electric', 'Blue', 'Eco Mode', 'Electric', 1400),
('B0011', 'Suzuki', 'GSX-S1000', 'Petrol', 'Black', 'Traction Control', 'Street', 1050),
('B0012', 'Mitsubishi', 'Ninja 650', 'Petrol', 'Silver', 'ABS', 'Sport', 850),
('B0013', 'TATA', 'eBike Model Y', 'Electric', 'Red', 'Smart Display', 'Electric', 1700),
('B0014', 'Ford', 'eBike Model Z', 'Electric', 'Blue', 'All-Weather Tires', 'Electric', 1500),
('B0015', 'Volkswagen', 'eBike Model V', 'Electric', 'Gray', 'GPS Navigation', 'Electric', 1900);

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `Regno` varchar(10) NOT NULL,
  `Car_Brand` varchar(30) DEFAULT NULL,
  `Car_Model` varchar(30) DEFAULT NULL,
  `Car_Ftype` varchar(20) DEFAULT NULL,
  `Car_Color` varchar(20) DEFAULT NULL,
  `Car_Class` varchar(20) DEFAULT NULL,
  `Car_Passengers` int(11) DEFAULT NULL,
  `Car_Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`Regno`, `Car_Brand`, `Car_Model`, `Car_Ftype`, `Car_Color`, `Car_Class`, `Car_Passengers`, `Car_Price`) VALUES
('C0001', 'Mercedes-Benz', 'C-Class', 'Diesel', 'Silver', 'Sedan', 5, 4000),
('C0002', 'Toyota', 'Camry', 'Petrol', 'Black', 'Sedan', 5, 3000),
('C0003', 'BMW', 'i3', 'Electric', 'White', 'Hatchback', 4, 4200),
('C0004', 'Nissan', 'Altima', 'Diesel', 'Blue', 'Sedan', 5, 2700),
('C0005', 'Ford', 'Mustang Mach-E', 'Electric', 'Gray', 'SUV', 5, 4500),
('C0006', 'Suzuki', 'Swift', 'Petrol', 'Red', 'Hatchback', 4, 1500),
('C0007', 'Mitsubishi', 'Outlander', 'Petrol', 'Red', 'Hatchback', 4, 1500),
('C0008', 'Chevrolet', 'Bolt EV', 'Electric', 'Silver', 'Hatchback', 5, 3500),
('C0009', 'BMW', '3 Series', 'Diesel', 'White', 'Sedan', 5, 4200),
('C0010', 'Honda', 'Civic', 'Petrol', 'Black', 'Sedan', 5, 2800),
('C0011', 'Volkswagen', 'Golf', 'Diesel', 'Blue', 'Hatchback', 5, 2300),
('C0012', 'Audi', 'A4', 'Petrol', 'Silver', 'Sedan', 5, 3800),
('C0013', 'Nissan', 'Leaf', 'Electric', 'Blue', 'Hatchback', 5, 4000),
('C0014', 'Kia', 'Optima', 'Petrol', 'White', 'Sedan', 5, 2900),
('C0015', 'Subaru', 'Outback', 'Diesel', 'Green', 'SUV', 5, 3400),
('C0016', 'Ford', 'Fusion', 'Petrol', 'Gray', 'Sedan', 5, 3100),
('C0017', 'Chevrolet', 'Malibu', 'Diesel', 'Blue', 'Sedan', 5, 2700),
('C0018', 'Peugeot', '308', 'Petrol', 'Black', 'Hatchback', 5, 2600),
('C0019', 'Tesla', 'Model S', 'Electric', 'Red', 'Sedan', 5, 5000),
('C0020', 'Hyundai', 'Tucson', 'Diesel', 'Red', 'SUV', 5, 3200);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Cus_Id` varchar(10) NOT NULL,
  `Cus_Name` varchar(30) NOT NULL,
  `Cus_DOB` varchar(20) NOT NULL,
  `Cus_NIC` varchar(15) NOT NULL,
  `Cus_Mobile` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Cus_Id`, `Cus_Name`, `Cus_DOB`, `Cus_NIC`, `Cus_Mobile`) VALUES
('00001', 'John Doe', '1990-05-15', '12345', '555-1234'),
('00002', 'Jane Smith', '1985-07-20', '67890', '555-5678'),
('00003', 'Alice Johnson', '1998-02-10', '54321', '555-9876'),
('00004', 'Bob Brown', '1980-11-30', '98765', '555-4321'),
('00005', 'Eva Williams', '2000-04-25', '23456', '555-8765'),
('00006', 'Michael Davis', '1995-09-05', '87654', '555-3456'),
('00007', 'Olivia Taylor', '1987-12-15', '76543', '555-6543'),
('00008', 'William Lee', '1992-03-22', '45678', '555-2345'),
('00009', 'Sophia Martin', '1993-06-17', '65432', '555-7654'),
('00010', 'David Wilson', '1982-08-08', '34567', '555-5432');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Username` varchar(20) NOT NULL,
  `First Name` varchar(20) NOT NULL,
  `Last Name` varchar(20) NOT NULL,
  `Address` varchar(30) NOT NULL,
  `City` varchar(20) NOT NULL,
  `Postal Code` int(10) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `DOB` varchar(20) NOT NULL,
  `Phone Number` varchar(10) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Job Title` varchar(30) NOT NULL,
  `Employment Status` varchar(20) NOT NULL,
  `Pay Frequency` varchar(20) NOT NULL,
  `Work Shedule` varchar(30) NOT NULL,
  `Salary Basic` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Username`, `First Name`, `Last Name`, `Address`, `City`, `Postal Code`, `NIC`, `Gender`, `DOB`, `Phone Number`, `Email`, `Password`, `Job Title`, `Employment Status`, `Pay Frequency`, `Work Shedule`, `Salary Basic`) VALUES
('akindu', 'Akindu', 'Hettiarachchi', 'abc, aldl', 'Nugegoda', 12456, '200215469953', 'Male', '2002-10-25', '0756895231', 'akindu@gmail.com', 'akindu2002', 'Rental Agent', 'Full-Time', 'Weekly', 'Sat-Sun', 80000),
('dunith', 'Dunith', 'Deelana', 'Sakya, Udatiyawella', 'Mahagama', 12210, '200247895566', 'Male', '2002-05-26', '0769722419', 'dunith26@gmail.com', 'dunith2002', 'Rental Agent', 'Full-Time', 'Monthly', 'Mon-Fri', 100000),
('harikirushna', 'Harikirushna', 'Raj', 'sdw, jachi', 'Malabe', 15236, '200215746698', 'Male', '2002-04-17', '0763259684', 'harikirushna@gmail.com', 'harikirushna2002', 'Cashier', 'Part-Time', 'Monthly', 'Mon-Fri', 80000),
('lakindu', 'Lakindu', 'Liyanage', 'abc, kdkd', 'Mathugama', 12541, '200317465539', 'Male', '2003-01-17', '0715896325', 'lakindu@gmail.com', 'lakindu2003', 'Cashier', 'Part-Time', 'Monthly', 'Mon-Fri', 80000);

-- --------------------------------------------------------

--
-- Table structure for table `financial`
--

CREATE TABLE `financial` (
  `Sub_Total` double NOT NULL,
  `Discount` double NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `Rent_Id` varchar(10) NOT NULL,
  `Cus_Id` varchar(10) NOT NULL,
  `Regno` varchar(10) NOT NULL,
  `Date` varchar(15) NOT NULL,
  `Duration` int(3) NOT NULL,
  `Return_Date` varchar(15) NOT NULL,
  `VehicleType` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vans`
--

CREATE TABLE `vans` (
  `Regno` varchar(10) NOT NULL,
  `Van_Brand` varchar(20) DEFAULT NULL,
  `Van_Model` varchar(20) DEFAULT NULL,
  `Van_Ftype` varchar(30) DEFAULT NULL,
  `Van_Color` varchar(30) DEFAULT NULL,
  `Van_Features` varchar(255) DEFAULT NULL,
  `Van_Passengers` int(11) DEFAULT NULL,
  `Van_Price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vans`
--

INSERT INTO `vans` (`Regno`, `Van_Brand`, `Van_Model`, `Van_Ftype`, `Van_Color`, `Van_Features`, `Van_Passengers`, `Van_Price`) VALUES
('V0001', 'Ford', 'Transit', 'Diesel', 'White', 'Cargo Space, Rearview Camera', 3, 3000),
('V0002', 'Mercedes-Benz', 'Sprinter', 'Diesel', 'Silver', 'Navigation System, Leather Seats', 5, 5000),
('V0003', 'Toyota', 'Sienna', 'Petrol', 'Blue', 'Infotainment System, 8 Passengers', 8, 3500),
('V0004', 'Volkswagen', 'Crafter', 'Diesel', 'Gray', 'Cruise Control, Spacious Interior', 4, 4200),
('V0005', 'Chevrolet', 'Express', 'Petrol', 'White', 'Rear Cargo Space, V8 Engine', 2, 2800),
('V0006', 'Nissan', 'NV200', 'Petrol', 'White', 'Compact Size, Fuel-Efficient', 2, 2200),
('V0007', 'Dodge', 'Grand Caravan', 'Petrol', 'Red', 'Stow n Go Seats, Family-Friendly', 7, 3200),
('V0008', 'Ford', 'Transit Connect', 'Petrol', 'Silver', 'Compact Size, Cargo Space', 2, 2500),
('V0009', 'Ram', 'ProMaster', 'Diesel', 'White', 'High Roof, Powerful Engine', 3, 4000),
('V0010', 'GMC', 'Savana', 'Petrol', 'Gray', 'Versatile, Towing Capacity', 2, 2900),
('V0011', 'Tesla', 'Model X', 'Electric', 'Red', 'Electric, Spacious Interior', 5, 6000),
('V0012', 'Nissan', 'e-NV200', 'Electric', 'Blue', 'Compact Electric Van, Eco-Friendly', 2, 3500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bikes`
--
ALTER TABLE `bikes`
  ADD PRIMARY KEY (`Regno`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`Regno`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Cus_Id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`Rent_Id`);

--
-- Indexes for table `vans`
--
ALTER TABLE `vans`
  ADD PRIMARY KEY (`Regno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
