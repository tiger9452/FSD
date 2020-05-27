--DROP DATABASE IF EXISTS `cloudDB02`;
--CREATE DATABASE `cloudDB02`;

use cloudDB02;

CREATE TABLE
    users
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,
        password VARCHAR(60) NOT NULL,
        email_addr VARCHAR(50),
        mobile_number VARCHAR(20),
        type enum('admin','investor') NOT NULL,
        onetime_code VARCHAR(50),-- 验证码
        confirmed BIT DEFAULT b'0' NOT NULL,
        jwt_token VARCHAR(255),-- or OAuth2, Spring Security
        db_source VARCHAR(10),
        PRIMARY KEY (id),
        CONSTRAINT uniqueId UNIQUE (name)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    company
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,
        code VARCHAR(20) NOT NULL,
        turnover VARCHAR(30) NOT NULL,-- 营业额
        ceo VARCHAR(50),
        board_directors TEXT,-- 董事会
        fk_sector_id bigint NOT NULL,
        brief_writeup TEXT,
        active BIT DEFAULT b'1' NOT NULL,
        db_source VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    stock_exchange
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,
        brief_writeup TEXT,
        contact_addr VARCHAR(255) NOT NULL,
        remarks TEXT,
        db_source VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE
    stock
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        fk_company_id BIGINT NOT NULL,
        fk_stock_exchange_id BIGINT NOT NULL,
        price DECIMAL(9.3),
        unit VARCHAR(10),
        date4price VARCHAR(30),
        time4price VARCHAR(30),
        db_source VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8; 
    
CREATE TABLE
    company2stock_exchange
    (
        fk_company_id BIGINT NOT NULL,
        fk_stock_exchange_id BIGINT NOT NULL,
        stock_code VARCHAR(60),
        db_source VARCHAR(10),
        CONSTRAINT uniqueId UNIQUE (stock_code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE
    sectors
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,
        brief VARCHAR(255),
        db_source VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    ipo_details -- Initial Public Offering
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        fk_company_id BIGINT NOT NULL,
        fk_stock_exchange_id BIGINT NOT NULL,
        price_per_share DECIMAL(9.3),
        unit VARCHAR(10),
        total_number_shares INT,
        open_datetime DATETIME,
        remarks TEXT,
        db_source VARCHAR(10),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;