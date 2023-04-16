CREATE DATABASE IF NOT EXISTS filtatos;
USE filtatos;

CREATE TABLE IF NOT EXISTS `EMPLOYEES` (`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100),
`phone` VARCHAR(20),
`address` VARCHAR(50),
`department` VARCHAR(20),
`bankname` VARCHAR(50),
`startdate` DATE,
`enddate` DATE,
`married` BOOLEAN,
`email` VARCHAR(50),
`iban` VARCHAR(40),
`issimv` BOOLEAN,
`isdidakt` BOOLEAN,
`isActive` BOOLEAN,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS  `children` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`employeid` INT,
	`age` INT,
	PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS  `salarydata` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`employeid` INT,
	`date` DATE,
	`amount` DOUBLE,
	PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `paydiagram`(

    `id` INT NOT NULL AUTO_INCREMENT,
    `salary` DOUBLE,
    `employeid` INT,
    `category` VARCHAR(50),
    `amount` DOUBLE,
    `family_allowance` DOUBLE,
    `yearsofservice` DOUBLE,
    PRIMARY KEY (`id`)

);
CREATE TABLE IF NOT EXISTS `general_data`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `mon_didakt` DOUBLE,
    `mon_doiik` DOUBLE,
    `family_allowance_per` DOUBLE,
    `search_allowance` DOUBLE,
    `library_allowance` DOUBLE,

    PRIMARY KEY (`id`)

);