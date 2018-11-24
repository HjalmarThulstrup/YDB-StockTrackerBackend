DROP DATABASE IF EXISTS ydbStockTest;
CREATE DATABASE IF NOT EXISTS ydbStockTest;
USE ydbStockTest;

--
-- Table structure for table Stocks
--

CREATE TABLE Stocks (
  symbol varchar(10) NOT NULL,
  PRIMARY KEY (symbol)
) ENGINE=InnoDB;

--
-- Table structure for table users
--

CREATE TABLE users (
  user_name varchar(25) NOT NULL,
  user_pass varchar(255) DEFAULT NULL,
  PRIMARY KEY (user_name)
) ENGINE=InnoDB;

--
--
-- Table structure for table roles
--

CREATE TABLE roles (
  role_name varchar(20) NOT NULL,
  PRIMARY KEY (role_name)
) ENGINE=InnoDB;

--
-- Table structure for table user_roles
--

CREATE TABLE user_roles (
  role_name varchar(20) NOT NULL,
  user_name varchar(25) NOT NULL,
  PRIMARY KEY (role_name,user_name),
  KEY FK_user_roles_user_name (user_name),
  CONSTRAINT FK_user_roles_role_name FOREIGN KEY (role_name) REFERENCES roles (role_name),
  CONSTRAINT FK_user_roles_user_name FOREIGN KEY (user_name) REFERENCES users (user_name)
) ENGINE=InnoDB;

--
-- Table structure for table UserStock
--

CREATE TABLE UserStock (
  fk_stockSymbol varchar(255) NOT NULL,
  fk_user varchar(25) NOT NULL,
  PRIMARY KEY (fk_stockSymbol,fk_user),
  KEY FK_UserStock_fk_user (fk_user),
  CONSTRAINT FK_UserStock_fk_stockSymbol FOREIGN KEY (fk_stockSymbol) REFERENCES Stocks (symbol),
  CONSTRAINT FK_UserStock_fk_user FOREIGN KEY (fk_user) REFERENCES users (user_name)
) ENGINE=InnoDB;


INSERT INTO users (user_name, user_pass) VALUES ('TestStockListUser', 1234);