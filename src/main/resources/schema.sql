DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS `USER`;

CREATE TABLE BOOK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    size INT DEFAULT NULL
);

CREATE TABLE `USER` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);