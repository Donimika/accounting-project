-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Úte 02. dub 2024, 13:57
-- Verze serveru: 10.4.32-MariaDB
-- Verze PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `invoicedatabase`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `due_date` datetime DEFAULT NULL,
  `invoice_number` int(11) NOT NULL,
  `issued` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `price` bigint(20) NOT NULL,
  `product` varchar(255) DEFAULT NULL,
  `vat` int(11) NOT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkajlvcm61crtrs7d5gt7x8h0x` (`buyer_id`),
  KEY `FKcsnchr7oirhgjwx57ubp3xt7m` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Vypisuji data pro tabulku `invoice`
--

INSERT INTO `invoice` (`id`, `due_date`, `invoice_number`, `issued`, `note`, `price`, `product`, `vat`, `buyer_id`, `seller_id`) VALUES
(1, '2023-07-30 02:00:00', 20230012, '2023-07-23 02:00:00', 'Tvorba Spring článků', 100, 'Článek', 21, 4, 1),
(2, '2024-03-23 01:00:00', 2024001, '2024-03-16 01:00:00', 'Vývoj mobilní aplikace pro Android a iOS.', 2500, 'Vývoj mobilní aplikace', 21, 2, 1),
(3, '2024-03-23 01:00:00', 2024002, '2024-03-16 01:00:00', 'Vytvoření moderního webového designu.', 1500, 'Webový design', 21, 4, 3),
(4, '2024-03-23 01:00:00', 2024003, '2024-03-16 01:00:00', 'Optimalizace webu pro vyhledávače.', 800, 'SEO optimalizace', 21, 6, 5),
(5, '2024-03-23 01:00:00', 2024004, '2024-03-16 01:00:00', 'Konzultace a doporučení pro zvýšení bezpečnosti IT infrastruktury.', 500, 'Konzultace IT bezpečnosti', 21, 7, 2),
(6, '2024-03-23 01:00:00', 2024005, '2024-03-16 01:00:00', 'Poskytnutí hostingových služeb pro webovou aplikaci.', 200, 'Hosting služby', 21, 1, 4),
(7, '2024-03-23 01:00:00', 2024006, '2024-03-16 01:00:00', 'Vytvoření grafických prvků pro marketingovou kampaň.', 1200, 'Grafický design', 21, 9, 3),
(8, '2024-03-23 01:00:00', 2024007, '2024-03-16 01:00:00', 'Prodej licence na specializovaný software.', 300, 'Softwarová licence', 21, 5, 7),
(9, '2024-03-23 01:00:00', 2024008, '2024-03-16 01:00:00', 'Prodej licence na specializovaný software.', 3000, 'Softwarová licence', 21, 5, 7),
(10, '2024-03-23 01:00:00', 2024009, '2024-03-16 01:00:00', 'Prodej licence na specializovaný software.', 2000, 'Softwarová licence', 21, 5, 7),
(11, '2024-01-17 01:00:00', 2023011, '2024-01-10 01:00:00', 'Konzultace ohledně implementace IT strategie', 500, 'IT konzultační služby', 21, 6, 3),
(12, '2024-01-27 01:00:00', 2023012, '2024-01-20 01:00:00', 'Licence na používání profesionálních softwarových nástrojů', 1500, 'Licence na firemní software', 21, 7, 4),
(13, '2024-02-12 01:00:00', 2023013, '2024-02-05 01:00:00', 'Analýza toku dat pro optimalizaci procesů', 800, 'Analýza datových toků', 21, 8, 5),
(14, '2024-02-22 01:00:00', 2023014, '2024-02-15 01:00:00', 'Školení zaměřené na prevenci kybernetických hrozeb', 1000, 'Školení v oblasti kybernetické bezpečnosti', 21, 9, 6),
(15, '2024-03-08 01:00:00', 2023015, '2024-03-01 01:00:00', 'Nasazení a konfigurace CRM systému pro sledování zákaznických vztahů', 1200, 'Implementace CRM systému', 21, 10, 7),
(16, '2024-03-22 01:00:00', 2023016, '2024-03-15 01:00:00', 'Vývoj a nasazení webové aplikace pro online rezervace', 1500, 'Vývoj webové aplikace', 21, 11, 8),
(17, '2024-04-08 02:00:00', 2023017, '2024-04-01 02:00:00', 'Optimalizace webových stránek pro vyhledávače', 600, 'SEO optimalizace', 21, 12, 9),
(18, '2024-04-22 02:00:00', 2023018, '2024-04-15 02:00:00', 'Přístup k cloudovému úložišti pro ukládání dat', 300, 'Cloudové úložiště', 21, 13, 10);

-- --------------------------------------------------------

--
-- Struktura tabulky `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) NOT NULL,
  `bank_code` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `hidden` bit(1) NOT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `identification_number` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `tax_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) NOT NULL,
  `zip` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Vypisuji data pro tabulku `person`
--

INSERT INTO `person` (`id`, `account_number`, `bank_code`, `city`, `country`, `hidden`, `iban`, `identification_number`, `mail`, `name`, `note`, `street`, `tax_number`, `telephone`, `zip`) VALUES
(1, '123456789', '5500', 'Praha', 'CZECHIA', b'0', 'CZ000123456789', '05861381', 'redakce@itnetwork.cz', 'ITnetwork s.r.o.', 'Největší IT akademie v Česku.', 'Karlovo náměstí 290/16, Nové Město (Praha 2)', 'CZ05861381', '+420 123 123 123', '120 00'),
(2, '987654321', '3300', 'Praha', 'CZECHIA', b'0', 'CZ000987654321', '09876543', 'info@techsolutions.cz', 'TechSolutions s.r.o.', 'Specialisté na IT řešení pro malé a střední podniky.', 'Vinohradská 123/456, Vinohrady (Praha 3)', 'CZ09876543', '+420 111 222 333', '130 00'),
(3, '111222333', '2700', 'Praha', 'CZECHIA', b'0', 'CZ000111222333', '01234567', 'info@codemaster.cz', 'CodeMaster s.r.o.', 'Specializujeme se na vývoj softwaru pro firemní aplikace.', 'Štěpánská 789/321, Nové Město (Praha 1)', 'CZ01234567', '+420 444 555 666', '110 00'),
(4, '9876543210', '5500', 'Praha', 'CZECHIA', b'0', 'CZ0009876543210', '04567890', 'info@innovatech.cz', 'InnovaTech a.s.', 'Přinášíme inovativní technologická řešení pro firemní klienty.', 'Národní 321/789, Staré Město (Praha 1)', 'CZ04567890', '+420 777 888 999', '100 00'),
(5, '444555666', '2700', 'Brno', 'CZECHIA', b'0', 'CZ000444555666', '02345678', 'info@datatechgroup.cz', 'DataTech Group s.r.o.', 'Specialisté na zpracování dat a business intelligence.', 'Hlavní 456/789, Brno-střed', 'CZ02345678', '+420 333 444 555', '602 00'),
(6, '555666777', '2700', 'Praha', 'CZECHIA', b'1', 'CZ000555666777', '03456789', 'info@softwarewizards.cz', 'Software Wizards a.s.', 'Kouzelníci v oblasti softwarového vývoje a automatizace.', 'Masarykova 789/321, Nové Město (Praha 1)', 'CZ03456789', '+420 666 777 888', '110 00'),
(7, '888999000', '3300', 'Praha', 'CZECHIA', b'0', 'CZ000888999000', '05678901', 'info@datastream.cz', 'DataStream s.r.o.', 'Zprostředkování datových toků a analytických služeb.', 'Opletalova 987/654, Nové Město (Praha 1)', 'CZ05678901', '+420 999 000 111', '110 00'),
(8, '999000111', '5500', 'Praha', 'CZECHIA', b'0', 'CZ000999000111', '06789012', 'info@techgenius.cz', 'TechGenius a.s.', 'Technologičtí géniové přinášející revoluční řešení.', 'Sokolská 123/456, Nové Město (Praha 1)', 'CZ06789012', '+420 000 111 222', '110 00'),
(9, '111222333', '2700', 'Praha', 'CZECHIA', b'0', 'CZ000111222333', '07890123', 'info@cybertechsolutions.cz', 'CyberTech Solutions s.r.o.', 'Specialisté na kybernetickou bezpečnost a IT konzultace.', 'Na Příkopě 456/789, Staré Město (Praha 1)', 'CZ07890123', '+420 222 333 444', '110 00'),
(10, '222333444', '2700', 'Praha', 'CZECHIA', b'0', 'CZ000222333444', '08901234', 'info@techfusion.cz', 'TechFusion a.s.', 'Spojení technologií pro budoucnost firemního prostředí.', 'Jungmannova 789/123, Nové Město (Praha 1)', 'CZ08901234', '+420 333 444 555', '110 00'),
(11, '333444555', '2700', 'Praha', 'CZECHIA', b'1', 'CZ000333444555', '09012345', 'info@softtechinnovations.cz', 'SoftTech Innovations s.r.o.', 'Inovace v oblasti softwarových technologií a služeb.', 'Václavské náměstí 789/321, Nové Město (Praha 1)', 'CZ09012345', '+420 444 555 666', '110 00'),
(12, '333444555', '3030', 'Praha', 'CZECHIA', b'0', 'CZ000333444555', '09012345', 'info@softtechinnovations.cz', 'SoftTech Innovations s.r.o.', 'Inovace v oblasti softwarových technologií a služeb.', 'Václavské náměstí 789/321, Nové Město (Praha 1)', 'CZ09012345', '+420 444 555 666', '110 00'),
(13, '333444888', '2700', 'Praha', 'CZECHIA', b'0', 'CZ000333444666', '09012389', 'info@techinnns.cz', 'Tech Innns s.r.o.', 'Inovace v oblasti softwarových služeb.', 'Václavské náměstí 779/321, Staré Město (Praha 1)', 'CZ09012389', '+420 445 545 666', '110 00');

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKcsnchr7oirhgjwx57ubp3xt7m` FOREIGN KEY (`seller_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKkajlvcm61crtrs7d5gt7x8h0x` FOREIGN KEY (`buyer_id`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
