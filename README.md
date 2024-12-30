# Bank Database System
 That is a Bank database such that user can sign in or register as a customer or an employee. Developed by using java and mysql.

Since it is made with java and sql codes together it is a system that does not work without sql. SQL codes:

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `project`.`account_view` AS select `a`.`Account_No` AS `Account_No`,`c`.`Cust_ID` AS `Cust_ID`,`c`.`name` AS `Customer_Name`,`c`.`Surname` AS `Customer_Surname`,`a`.`Balance` AS `Balance`,`a`.`B_Name` AS `Bank_Name`,`b`.`Address` AS `Bank_Address`,`b`.`City` AS `Bank_City` from ((`project`.`account` `a` join `project`.`customer` `c` on((`a`.`Cust_ID` = `c`.`Cust_ID`))) join `project`.`bank` `b` on((`a`.`B_Name` = `b`.`B_Name`)));
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `project`.`transaction_view` AS select `project`.`customer`.`name` AS `Name`,`project`.`customer`.`Cust_ID` AS `Cust_ID`,`project`.`account`.`Account_No` AS `Account_No`,`project`.`account`.`Balance` AS `Balance`,`project`.`transaction`.`LoanAmount` AS `LoanAmount` from ((`project`.`account` join `project`.`customer` on((`project`.`account`.`Cust_ID` = `project`.`customer`.`Cust_ID`))) join `project`.`transaction` on((`project`.`account`.`Account_No` = `project`.`transaction`.`AccNo`)));
CREATE TABLE `customer` (
  `Cust_ID` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `Surname` varchar(50) NOT NULL,
  `Age` int NOT NULL,
  `mobile_no` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Cust_ID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `mobile_no` (`mobile_no`),
  CONSTRAINT `customer_chk_1` CHECK ((`Age` >= 18))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `EmpId` int NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Surname` varchar(50) DEFAULT NULL,
  `Mobile_No` varchar(15) DEFAULT NULL,
  `Adress` varchar(55) DEFAULT NULL,
  `Security_Key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EmpId`),
  CONSTRAINT `employee_chk_1` CHECK ((`Security_Key` = _utf8mb4'22x-12y-34Z'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `bank` (
  `B_Name` varchar(50) DEFAULT 'Ziraat Bankası',
  `Address` varchar(100) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Bank_Code` int NOT NULL,
  PRIMARY KEY (`Bank_Code`),
  KEY `idx_bank_bname` (`B_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `account` (
  `Account_No` int NOT NULL AUTO_INCREMENT,
  `Cust_ID` int DEFAULT NULL,
  `B_Name` varchar(50) DEFAULT 'ziraat',
  `Balance` decimal(10,2) DEFAULT '100.00',
  PRIMARY KEY (`Account_No`),
  KEY `Cust_ID` (`Cust_ID`),
  KEY `B_Name` (`B_Name`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`Cust_ID`) REFERENCES `customer` (`Cust_ID`) ON UPDATE CASCADE,
  CONSTRAINT `account_ibfk_2` FOREIGN KEY (`B_Name`) REFERENCES `bank` (`B_Name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `transaction` (
  `LoanAmount` int NOT NULL,
  `AccNo` int NOT NULL,
  PRIMARY KEY (`AccNo`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`AccNo`) REFERENCES `account` (`Account_No`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE ALGORITHM=UNDEFINED 
DEFINER=`root`@`localhost` 
SQL SECURITY DEFINER 
VIEW `project`.`account_view` AS 
SELECT 
    `a`.`Account_No` AS `Account_No`,
    `c`.`Cust_ID` AS `Cust_ID`,
    `c`.`name` AS `Customer_Name`,
    `c`.`Surname` AS `Customer_Surname`,
    `a`.`Balance` AS `Balance`,
    `a`.`B_Name` AS `Bank_Name`,
    `b`.`Address` AS `Bank_Address`,
    `b`.`City` AS `Bank_City`
FROM 
    `project`.`account` `a` 
JOIN 
    `project`.`customer` `c` 
ON 
    `a`.`Cust_ID` = `c`.`Cust_ID`
JOIN 
    `project`.`bank` `b` 
ON 
    `a`.`B_Name` = `b`.`B_Name`;

CREATE ALGORITHM=UNDEFINED 
DEFINER=`root`@`localhost` 
SQL SECURITY DEFINER 
VIEW `project`.`transaction_view` AS 
SELECT 
    `c`.`name` AS `Name`,
    `c`.`Cust_ID` AS `Cust_ID`,
    `a`.`Account_No` AS `Account_No`,
    `a`.`Balance` AS `Balance`,
    `t`.`LoanAmount` AS `LoanAmount`
FROM 
    `project`.`account` `a`
JOIN 
    `project`.`customer` `c` 
ON 
    `a`.`Cust_ID` = `c`.`Cust_ID`
JOIN 
    `project`.`transaction` `t` 
ON 
    `a`.`Account_No` = `t`.`AccNo`;
