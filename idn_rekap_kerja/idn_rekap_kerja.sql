-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 11, 2020 at 11:14 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `idn_rekap_kerja`
--

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id_history` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `nama_user` varchar(35) NOT NULL,
  `nama_kerjaan` text NOT NULL,
  `total_poin` int(11) NOT NULL,
  `tgl_dibuat` date NOT NULL,
  `keterangan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id_history`, `id_user`, `nama_user`, `nama_kerjaan`, `total_poin`, `tgl_dibuat`, `keterangan`) VALUES
('85a9b604-7d5c-11ea-b69b-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 'M Qalbun Saliim Bakhri', 'history mingguan', 121, '2020-04-13', 'Mingguan');

-- --------------------------------------------------------

--
-- Table structure for table `list_kerjaan`
--

CREATE TABLE `list_kerjaan` (
  `id_kerjaan` char(36) NOT NULL,
  `nama_kerjaan` text NOT NULL,
  `level_kerjaan` varchar(7) NOT NULL,
  `hari_kerjaan` varchar(9) NOT NULL,
  `waktu_kerjaan` time NOT NULL,
  `poin_kerjaan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `list_kerjaan`
--

INSERT INTO `list_kerjaan` (`id_kerjaan`, `nama_kerjaan`, `level_kerjaan`, `hari_kerjaan`, `waktu_kerjaan`, `poin_kerjaan`) VALUES
('0848b87c-78bb-11ea-a80b-3c075420daf5', 'Patroli Malam', 'Musyrif', 'Tuesday', '21:00:00', 1),
('0ce38e4e-3242-11ea-b800-3c075420daf5', 'Rapat', 'Guru', 'Sunday', '07:00:00', 8),
('6c3326f2-313e-11ea-bd88-3c075420daf5', 'Bangunin Subuh', 'Musyrif', 'Sunday', '04:00:00', 10),
('97419bd0-313e-11ea-bd88-3c075420daf5', 'Ngajar', 'Guru', 'Sunday', '08:00:00', 9),
('e907df72-7961-11ea-8899-3c075420daf5', 'Holaqoh Pagi', 'Musyrif', 'Wednesday', '05:00:00', 10);

-- --------------------------------------------------------

--
-- Table structure for table `selesai`
--

CREATE TABLE `selesai` (
  `id_selesai` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `kerjaan_selesai` text NOT NULL,
  `poin_selesai` int(11) NOT NULL,
  `tgl_selesai` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `selesai`
--

INSERT INTO `selesai` (`id_selesai`, `id_user`, `kerjaan_selesai`, `poin_selesai`, `tgl_selesai`) VALUES
('1636d2da-7964-11ea-8899-3c075420daf5', 'ea31a584-7963-11ea-8899-3c075420daf5', 'Holaqoh Pagi', 10, '2020-04-08 13:42:16'),
('1ea3bb7c-7964-11ea-8899-3c075420daf5', 'ea31a584-7963-11ea-8899-3c075420daf5', 'Holaqoh Pagi', 10, '2020-04-08 13:42:30'),
('3d5dc1de-3175-11ea-bd88-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 'Ngajar', 9, '2020-01-08 00:43:39'),
('934a1a04-3add-11ea-8dcd-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 'Rapat', 8, '2020-01-20 00:03:12'),
('9495b260-3add-11ea-8dcd-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 'Ngajar', 9, '2020-01-20 00:03:14');

-- --------------------------------------------------------

--
-- Table structure for table `stats_bulanan`
--

CREATE TABLE `stats_bulanan` (
  `id_stats_bulanan` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `total_poin` int(11) NOT NULL,
  `tgl_dibuat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stats_bulanan`
--

INSERT INTO `stats_bulanan` (`id_stats_bulanan`, `id_user`, `total_poin`, `tgl_dibuat`) VALUES
('3e1cfbf6-7bdc-11ea-a8c5-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 400, '2020-04-11'),
('4425c456-7bdc-11ea-a8c5-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 401, '2020-04-11');

-- --------------------------------------------------------

--
-- Table structure for table `stats_mingguan`
--

CREATE TABLE `stats_mingguan` (
  `id_stats_mingguan` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `total_poin` int(11) NOT NULL,
  `tgl_dibuat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stats_mingguan`
--

INSERT INTO `stats_mingguan` (`id_stats_mingguan`, `id_user`, `total_poin`, `tgl_dibuat`) VALUES
('e5ed484e-7bd8-11ea-a8c5-3c075420daf5', '5b19552a-3d4c-11ea-a6d3-3c075420daf5', 100, '2020-04-11');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` char(36) NOT NULL,
  `nama_user` varchar(35) NOT NULL,
  `username_user` varchar(20) NOT NULL,
  `password_user` text NOT NULL,
  `level_user` varchar(8) NOT NULL,
  `kelas_user` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `nama_user`, `username_user`, `password_user`, `level_user`, `kelas_user`) VALUES
('5b19552a-3d4c-11ea-a6d3-3c075420daf5', 'M Qalbun Saliim Bakhri', 'salim', 'ca6b147b8fbdd688d8ebcaa3b803c22a', 'Guru', '12 RPL'),
('80f99774-77d9-11ea-8672-3c075420daf5', 'Doddy Rochman', 'pimpinan', '90973652b88fe07d05a4304f0a945de8', 'Pimpinan', '-'),
('ea31a584-7963-11ea-8899-3c075420daf5', 'Fauzan', 'tes', '28b662d883b6d76fd96e4ddc5e9ba780', 'Musyrif', '10 RPL');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id_history`);

--
-- Indexes for table `list_kerjaan`
--
ALTER TABLE `list_kerjaan`
  ADD PRIMARY KEY (`id_kerjaan`);

--
-- Indexes for table `selesai`
--
ALTER TABLE `selesai`
  ADD PRIMARY KEY (`id_selesai`);

--
-- Indexes for table `stats_bulanan`
--
ALTER TABLE `stats_bulanan`
  ADD PRIMARY KEY (`id_stats_bulanan`);

--
-- Indexes for table `stats_mingguan`
--
ALTER TABLE `stats_mingguan`
  ADD PRIMARY KEY (`id_stats_mingguan`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
