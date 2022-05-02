CREATE TABLE IF NOT EXISTS  product (
    ID INT PRIMARY KEY AUTO_INCREMENT ,
    NAME varchar(256) NOT NULL,
    ID_PRODUCT_CATEGORY INT NOT NULL,
    ID_SEASON INT NOT NULL,
    ID_COLOR INT NOT NULL,
    ID_COVER_IMG INT NOT NULL,
    DRY_CLEAN BOOLEAN NOT NULL,
    RETAIL_PRICE double NOT NULL,
    HIDDEN BOOLEAN DEFAULT FALSE,
    DELETED BOOLEAN DEFAULT FALSE,
    QUICK_DESC varchar(256) NOT NULL,
    MATERIAL varchar(256) NOT NULL,
    DESCRIPTION varchar(256) NOT NULL,
    FITTING_INFO varchar(256) NOT NULL,
    WASH_INFO varchar(256) NOT NULL,
    BRAND varchar(256) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS  product_category (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME varchar(256)
);

CREATE TABLE IF NOT EXISTS  product_occasion (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_PRODUCT INT,
    ID_OCCASION INT
);


CREATE TABLE IF NOT EXISTS  product_inventory (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_ID INT,
    ID_SIZE INT,
    STATUS ENUM ('BOOKED','IN_USE','WAITING_RETURN','STORED','WASH','DEACTIVATED') DEFAULT 'STORED'

);

CREATE TABLE IF NOT EXISTS  product_measurement (
    ID_PRODUCT INT PRIMARY KEY ,
    LENGTH varchar(256) NOT NULL,
    HIPS varchar(256) NOT NULL,
    WAIST varchar(256) NOT NULL,
    CHEST varchar(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS  product_image (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_PRODUCT INT,
    ID_IMAGE INT
);

CREATE TABLE IF NOT EXISTS  image (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PATH varchar(1000),
    FILE_NAME varchar(1000)
);

CREATE TABLE IF NOT EXISTS  occasion (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME varchar(256)
);

CREATE TABLE IF NOT EXISTS  size (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME varchar(256)
);

CREATE TABLE IF NOT EXISTS  color(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME varchar(256)
);

CREATE TABLE IF NOT EXISTS  season(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME varchar(256)
);

CREATE TABLE IF NOT EXISTS  booking_request(
    ID INT PRIMARY KEY AUTO_INCREMENT ,
    ID_USER varchar (36)NOT NULL,
    ID_PRODUCT_SIZE INT NOT NULL,
    START_DATE DATE NOT NULL,
    COLLECTION_PLACE varchar (256),
    STATUS ENUM ('WAITING_COLLECTION','ACTIVE','WAITING_RETURN', 'COMPLETE') DEFAULT 'WAITING_COLLECTION',
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS  user(
    ID varchar (36) PRIMARY KEY ,
    FIRST_NAME varchar (50) NOT NULL,
    LAST_NAME varchar (50) NOT NULL,
    PHONE varchar (10) NOT NULL,
    EMAIL varchar (75) NOT NULL UNIQUE ,
    DOB DATE NOT NULL,
    COUNTRY varchar (75) NOT NULL,
    CITY varchar (75) NOT NULL,
    EIRCODE varchar (20) NOT NULL,
    STATUS ENUM ('REQUESTED','ACTIVATED','DEACTIVATED', 'BLOCKED') DEFAULT 'REQUESTED',
    MEMBERSHIP INT,
    ADDRESS_LINE_ONE varchar (75) NOT NULL,
    ADDRESS_LINE_TWO varchar (75) NOT NULL,
    START_DATE TIMESTAMP,
    END_DATE TIMESTAMP,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
