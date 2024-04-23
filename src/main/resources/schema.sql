CREATE TABLE `product_info` (
  `product_no` varchar(10) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_no`)
);