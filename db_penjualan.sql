-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 09, 2024 at 11:02 AM
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
-- Database: `db_penjualan`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_brg` varchar(12) NOT NULL,
  `nama_brg` varchar(100) NOT NULL,
  `stok_brg` int NOT NULL,
  `harga_brg` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_brg`, `nama_brg`, `stok_brg`, `harga_brg`) VALUES
('BENG', 'Beng-beng', 110, 2000),
('BIMOLI1L', 'Bimoli 1L', 55, 19100),
('BUAV250', 'Buavita 250ML', 90, 9800),
('CHIPS1', 'Choco', 1, 9000),
('CLEO550', 'Cleo 550ML', 150, 3500),
('FF2000', 'French Fries 2000', 60, 14800),
('GOOD', 'Good Day', 120, 4000),
('INDOGOR', 'Indomie Goreng', 40, 2900),
('INDOMIE', 'Mie Gaga', 1, 1000),
('KACANGG', 'Kacang Garuda', 70, 19000),
('KECAPCB', 'Kecap Cap Bango', 95, 8000),
('KPA', 'Kopi Kapal Api', 50, 2000),
('NGT', 'Nu Green Tea', 100, 4900),
('OREO1', 'Oreo Vanila', 10, 10000),
('OREOV', 'Oreo Vanilla', 80, 8000),
('PCR', 'Pocari Sweet New', 111, 9100),
('SAUSABC', 'Saus ABC', 85, 7000),
('SOSISSO', 'Sosis So Good', 130, 5000),
('SQ', 'SilverQueen', 12, 12000),
('TBS', 'Teh Botol Sosro', 200, 6900);

-- --------------------------------------------------------

--
-- Table structure for table `faktur`
--

CREATE TABLE `faktur` (
  `nomor_faktur` varchar(12) NOT NULL,
  `tgl_faktur` date NOT NULL,
  `kode_plg` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `faktur`
--

INSERT INTO `faktur` (`nomor_faktur`, `tgl_faktur`, `kode_plg`) VALUES
('AHMAD3124', '2024-01-03', 'AHMAD'),
('AKMAL2124', '2024-01-02', 'AKMAL'),
('NICKY', '2024-01-01', 'NICKY'),
('VIRGI301223', '2023-12-30', 'VIRGIAWAN');

-- --------------------------------------------------------

--
-- Table structure for table `faktur_detail`
--

CREATE TABLE `faktur_detail` (
  `kode_faktur` varchar(12) NOT NULL,
  `nomor_faktur` varchar(12) NOT NULL,
  `kode_brg` varchar(12) NOT NULL,
  `qty` int NOT NULL,
  `harga_brg` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `faktur_detail`
--

INSERT INTO `faktur_detail` (`kode_faktur`, `nomor_faktur`, `kode_brg`, `qty`, `harga_brg`) VALUES
('DFAHMAD3', 'AHMAD3124', 'KPA', 6, 12000),
('DFVIRGI30', 'VIRGI301223', 'KACANGG', 9, 171000);

-- --------------------------------------------------------

--
-- Table structure for table `kwitansi`
--

CREATE TABLE `kwitansi` (
  `nomor_kwt` varchar(12) NOT NULL,
  `tgl_kwt` date NOT NULL,
  `nomor_faktur` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kwitansi`
--

INSERT INTO `kwitansi` (`nomor_kwt`, `tgl_kwt`, `nomor_faktur`) VALUES
('KAHMAD3124', '2024-01-03', 'AHMAD3124'),
('KAKMAL2124', '2024-01-02', 'AKMAL2124'),
('KVIRGI301223', '2023-12-30', 'VIRGI301223');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kode_plg` varchar(12) NOT NULL,
  `nama_plg` varchar(100) NOT NULL,
  `alamat_plg` text NOT NULL,
  `hp_plg` varchar(15) NOT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kode_plg`, `nama_plg`, `alamat_plg`, `hp_plg`, `jenis_kelamin`) VALUES
('AHMAD', 'Ahmad Albar', 'Jl. Kedondong no. 220, Cinere, Depok', '08567777221', 'Laki-Laki'),
('AKMAL', 'Akmal Hisyam Pradhana', 'JL Mastrip Nganjuk', '0891223444', 'Laki-Laki'),
('HAI', 'Haikal Qolbu Mahardika', 'Jl Lengkong, Nganjuk, Jawa Timur', '081236289366', 'Laki-Laki'),
('NICKY', 'Nicky Astria', 'Bandung Jawa Barat', '081777281122', 'Perempuan'),
('VIRGIAWAN', 'Virgiawan Listanto', 'Desa Leuwinanggung, Cimanggis, Depok', '0812222355', 'Laki-Laki');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` enum('user','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'user', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_brg`);

--
-- Indexes for table `faktur`
--
ALTER TABLE `faktur`
  ADD PRIMARY KEY (`nomor_faktur`),
  ADD KEY `kode_plg` (`kode_plg`);

--
-- Indexes for table `faktur_detail`
--
ALTER TABLE `faktur_detail`
  ADD PRIMARY KEY (`kode_faktur`),
  ADD KEY `nomor_faktur` (`nomor_faktur`),
  ADD KEY `kode_brg` (`kode_brg`);

--
-- Indexes for table `kwitansi`
--
ALTER TABLE `kwitansi`
  ADD PRIMARY KEY (`nomor_kwt`),
  ADD KEY `nomor_faktur` (`nomor_faktur`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kode_plg`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `faktur`
--
ALTER TABLE `faktur`
  ADD CONSTRAINT `faktur_ibfk_1` FOREIGN KEY (`kode_plg`) REFERENCES `pelanggan` (`kode_plg`);

--
-- Constraints for table `faktur_detail`
--
ALTER TABLE `faktur_detail`
  ADD CONSTRAINT `faktur_detail_ibfk_1` FOREIGN KEY (`nomor_faktur`) REFERENCES `faktur` (`nomor_faktur`),
  ADD CONSTRAINT `faktur_detail_ibfk_2` FOREIGN KEY (`kode_brg`) REFERENCES `barang` (`kode_brg`);

--
-- Constraints for table `kwitansi`
--
ALTER TABLE `kwitansi`
  ADD CONSTRAINT `kwitansi_ibfk_1` FOREIGN KEY (`nomor_faktur`) REFERENCES `faktur` (`nomor_faktur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
