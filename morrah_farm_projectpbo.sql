-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2024 at 06:23 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `morrah_farm_projectpbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `about_us`
--

CREATE TABLE `about_us` (
  `id_about` int(11) NOT NULL,
  `nama_pemilik` varchar(30) NOT NULL,
  `nama_manager` varchar(30) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `deskripsi` text NOT NULL,
  `contact` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `about_us`
--

INSERT INTO `about_us` (`id_about`, `nama_pemilik`, `nama_manager`, `alamat`, `deskripsi`, `contact`) VALUES
(1, 'Charles Panjaitan', 'Yasheni', 'Desa Bahal Batu I, Kecamatan Siborong-borong, Kabupaten Tapanuli Utara', 'Morrah Farm Koperasi Guna Satwa Mandiri merupakan koperasi yang bergerak memproduksi Susu Kerbau Murni di Desa Bahal Batu I, Kecamatan Siborong-borong, Kabupaten Tapanuli Utara. Susu tersebut diolah melalui proses pasteurisasi sehingga telah menghilangkan bakteri dan siap untuk diminum. Morrah Farm didirikan pada bulan Juni tahun 2020 dan beroperasi pada bulan Januari tahun 2021, usaha ini didirikan oleh Charles Panjaitan. Susu kerbau Murni asli tanpa  pengawet ini bertahan selama 14 hari jika disimpan di dalam lemari es dan akan bertahan 12 jam jika tidak disimpan di dalam lemari es. Susu kerbau tersebut dihasilkan dari kerbau Morrah yang berasal dari negara India. Tidak hanya susu saja, Dali horbo juga menjadi bahan produksi Morrah Farm, dan Keju mozarella juga akan menyusul untuk menjadi bahan produksi Morrah Farm hingga penjualan terhadap kerbau jantan.\n', '081234567890');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan_tugas`
--

CREATE TABLE `karyawan_tugas` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `waktu` datetime NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `status` varchar(15) NOT NULL DEFAULT 'belum selesai'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karyawan_tugas`
--

INSERT INTO `karyawan_tugas` (`id`, `user_id`, `waktu`, `deskripsi`, `status`) VALUES
(11, 5, '2023-05-22 00:00:00', 'beri makan kerbau', 'selesai'),
(12, 6, '2023-05-23 00:00:00', 'perah susu kerbau', 'belum selesai'),
(13, 9, '2023-05-24 00:00:00', 'bersihkan kandang kerbau', 'selesai'),
(14, 9, '2023-05-25 00:00:00', 'antar hasil perahan susu ke tim produksi', 'selesai'),
(15, 6, '2023-05-22 00:00:00', 'cek kerbau jantan siap jual', 'selesai'),
(16, 7, '2023-05-24 00:00:00', 'produksi susu varian rasa coklat dan mocca', 'selesai'),
(17, 7, '2023-05-25 00:00:00', 'antarkan pesanan ke dolok sanggul', 'belum selesai'),
(18, 8, '2023-05-24 00:00:00', 'tambahkan data susu mocca pada database', 'selesai');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `pembelian_jumlah` int(11) NOT NULL,
  `harga_total` int(11) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `user_id`, `produk_id`, `pembelian_jumlah`, `harga_total`, `status`) VALUES
(4, 2, 17, 2, 2000, 'Sudah Dikonfirmasi'),
(5, 2, 16, 2, 20000, 'Sudah Dikonfirmasi'),
(6, 2, 16, 2, 20000, 'Sudah Dikonfirmasi'),
(7, 2, 17, 2, 2000, 'Sudah Dikonfirmasi'),
(8, 3, 16, 2, 20000, 'order'),
(9, 3, 16, 2, 20000, 'Sudah Dikonfirmasi'),
(10, 10, 15, 1, 1000000, 'Sudah Dikonfirmasi');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `stok` int(11) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama`, `stok`, `deskripsi`, `harga`) VALUES
(15, 'gambar', -1, 'sangat epic dan langka', 1000000),
(16, 'dadu', 14, 'bagus dan warna warni', 10000),
(17, 'panah', 18, 'mantap', 1000),
(19, 'Susu Coklat', 4, 'Segar', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` enum('user','admin','produksi','peternak') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `email`, `password`, `role`) VALUES
(1, 'gelek', 'gelek@gmail.com', '12345678', 'admin'),
(2, 'mahes', 'mahes@gmail.com', '12345678', 'user'),
(3, 'edu', 'edu@gmail.com', '12345678', 'user'),
(4, 'admin', 'admin@gmail.com', '12345678', 'admin'),
(5, 'eduard', 'edu@gmail.com', '12345678', 'peternak'),
(6, 'hans', 'hans@gmail.com', '12345678', 'peternak'),
(7, 'suep', 'produksi@gmail.com', '12345678', 'produksi'),
(8, 'acep', 'acep@gmail.com', '12345678', 'produksi'),
(9, 'yaya', 'peternak@gmail.com', '12345678', 'peternak'),
(10, 'sthevani', 'sthevani@gmail.com', 'sthevani', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `about_us`
--
ALTER TABLE `about_us`
  ADD PRIMARY KEY (`id_about`);

--
-- Indexes for table `karyawan_tugas`
--
ALTER TABLE `karyawan_tugas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `produk_id` (`produk_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `about_us`
--
ALTER TABLE `about_us`
  MODIFY `id_about` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `karyawan_tugas`
--
ALTER TABLE `karyawan_tugas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `id_pembelian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karyawan_tugas`
--
ALTER TABLE `karyawan_tugas`
  ADD CONSTRAINT `karyawan_tugas_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`produk_id`) REFERENCES `produk` (`id_produk`),
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
