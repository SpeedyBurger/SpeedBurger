-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 14 oct. 2018 à 22:49
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `speedyburgertest`
--

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prix` double NOT NULL,
  `typeProduit` varchar(50) NOT NULL,
  `Taille` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `nom`, `prix`, `typeProduit`, `Taille`) VALUES
(1, 'Coca', 2, 'boisson', 'Normal'),
(2, 'Coca', 3, 'boisson', 'Grand'),
(3, 'Coca', 1, 'boisson', 'Petit'),
(4, 'Fanta', 1, 'boisson', 'Petit'),
(5, 'Fanta', 2, 'boisson', 'Normal'),
(6, 'Fanta', 3, 'boisson', 'Grand'),
(7, 'Sprite', 1, 'boisson', 'Petit'),
(8, 'Sprite', 2, 'boisson', 'Normal'),
(9, 'Sprite', 3, 'boisson', 'Grand'),
(10, 'Eau', 1, 'boisson', 'Petit'),
(11, 'Eau', 2, 'boisson', 'Normal'),
(12, 'Eau', 3, 'boisson', 'Grand'),
(13, 'Biere', 1, 'boisson', 'Petit'),
(14, 'Biere', 2, 'boisson', 'Normal'),
(15, 'Biere', 3, 'boisson', 'Grand'),
(16, 'Cafe', 1, 'boisson', 'Normal'),
(17, 'Hamburger', 1, 'sandwitch', 'Normal'),
(18, 'FastBurger', 1, 'sandwitch', 'Normal'),
(19, 'SpeedyBurger', 1, 'sandwitch', 'Normal'),
(20, 'BaconBurger', 1, 'sandwitch', 'Normal'),
(21, 'ChickenRun', 1, 'sandwitch', 'Normal'),
(22, 'FishSpeed', 1, 'sandwitch', 'Normal'),
(23, 'Chicken Stick x4', 1, 'sandwitch', 'Normal'),
(24, 'Chicken Stick x7', 1, 'sandwitch', 'Normal'),
(25, 'Salade Cléopatre', 1, 'salade', 'Normal'),
(26, 'Salade Chef', 1, 'salade', 'Normal'),
(27, 'Salade Poulet', 1, 'salade', 'Normal'),
(28, 'Salade Grecque', 1, 'salade', 'Normal'),
(29, 'Frite', 1, 'frite', 'Petit'),
(30, 'Frite', 2, 'frite', 'Moyen'),
(31, 'Frite', 3, 'frite', 'Grand'),
(32, 'Sundae Vanille', 1, 'dessert', 'Normal'),
(33, 'Sundae Chocolat', 1, 'dessert', 'Normal'),
(34, 'Sundae Fraise', 1, 'dessert', 'Normal'),
(35, 'Milkshake Vanille', 1, 'dessert', 'Normal'),
(36, 'Milkshake Banane', 1, 'dessert', 'Normal'),
(37, 'Milkshake Café', 1, 'dessert', 'Normal'),
(38, 'Brownies', 1, 'dessert', 'Normal'),
(39, 'SpeedosGlacé', 1, 'dessert', 'Normal'),
(40, 'Compote', 1, 'dessert', 'Normal'),
(41, 'Tranch\'pomme', 1, 'dessert', 'Normal'),
(42, 'Sauce Chinoise', 0, 'sauce', 'Normal'),
(43, 'Sauce Curry', 0, 'sauce', 'Normal'),
(44, 'Sauce Barbecue', 0, 'sauce', 'Normal'),
(45, 'Sauce Ketchup', 0, 'sauce', 'Normal'),
(46, 'Sauce Mayonnaise', 0, 'sauce', 'Normal'),
(47, 'Sauce Orientale', 0, 'sauce', 'Normal'),
(48, 'Sauce Hot Salsa', 0, 'sauce', 'Normal'),
(49, 'Sauce Aigre Douce', 0, 'sauce', 'Normal'),
(50, 'Menu Hamburger', 2.5, 'menu', 'Normal'),
(51, 'Menu Hamburger XL', 3.5, 'menu', 'Grand'),
(52, 'Menu Fast Burger', 2.5, 'menu', 'Normal'),
(53, 'Menu Fast Burger XL', 3.5, 'menu', 'Grand'),
(54, 'Menu Speedy Burger', 2.5, 'menu', 'Normal'),
(55, 'Menu Speedy Burger XL', 3.5, 'menu', 'Grand'),
(56, 'Menu Bacon Burger', 2.5, 'menu', 'Normal'),
(57, 'Menu Bacon Burger XL', 3.5, 'menu', 'Grand'),
(58, 'Menu Fish Speed', 2.5, 'menu', 'Normal'),
(59, 'Menu Fish Speed XL', 3.5, 'menu', 'Grand'),
(60, 'Menu Cléopatre', 2.5, 'menu', 'Normal'),
(61, 'Menu Cléopatre XL', 3.5, 'menu', 'Grand'),
(62, 'Menu Chicken Stick', 2.5, 'menu', 'Normal'),
(63, 'Menu Chicken Stick XL', 3.5, 'menu', 'Grand'),
(64, 'Menu Chicken Run', 2.5, 'menu', 'Normal'),
(65, 'Menu Chicken Run XL', 3.5, 'menu', 'Normal'),
(66, 'Menu Hamburger Enfant', 2, 'menu', 'Petit'),
(67, 'Hamburger', 2, 'sandwitch', 'Petit'),
(68, 'Menu Chicken Stick x4 Enfant', 2, 'menu', 'Petit'),
(69, 'Menu Chicken Run Enfant', 2, 'menu', 'Petit'),
(70, 'ChickenRun', 2, 'sandwitch', 'Petit');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
