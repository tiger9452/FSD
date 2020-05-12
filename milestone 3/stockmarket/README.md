
CREATE DATABASE cloudDB01;

use cloudDB01;

CREATE TABLE
    users
    (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(60) NOT NULL,
        password VARCHAR(60) NOT NULL,
        email_addr VARCHAR(50),
        mobile_number VARCHAR(20),
        type enum('admin','investor') NOT NULL,
        onetime_code VARCHAR(10),--验证码
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
        turnover VARCHAR(30) NOT NULL,--营业额
        ceo VARCHAR(50),
        board_directors TEXT,--董事会
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
    ipo_details --Initial Public Offering
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
    

INSERT INTO users (name, password, email_addr, mobile_number, type, confirmed, db_source) VALUES ('sam', 'Passw0rd', 'tiger9452@163.com', '18571562153', 'admin', true, database());
INSERT INTO users (name, password, email_addr, mobile_number, type, confirmed, db_source) VALUES ('tom', 'Passw1rd', 'tiger9452@163.com', '18571562153', 'user', false, database());

INSERT INTO company (name, code, turnover, ceo, board_directors, fk_sector_id, brief_writeup, db_source) VALUES ('IBM', '865372', '187.56', 'Ginni Rometty', 'Samuel J. Palmisano,Cathleen Black?Lucio A. Noto,Sidney Taurel,', 1, 'IBM or International Business Machines is a well-known American computer manufacturer, founded by Thomas J. Watson (born 1874-02-17). IBM is also known as "Big Blue" after the color of its logo. The company has made everything from mainframes to personal computers and has been immensely successful selling business computers.', database());
INSERT INTO company (name, code, turnover, ceo, board_directors, fk_sector_id, brief_writeup, db_source) VALUES ('State Bank of India', '500112 ', '23', 'Mahendra', null, 2, 'Clean, simple and a fantastic learning resource. This training course is well structured and provided both ease of access and depth while allowing you to go at your own pace. The training course helped me in all areas that I was previously unclear about, especially about Securing VPC.', database());

INSERT INTO sectors (name, brief, db_source) VALUES ('IT', 'information technology', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Education', 'education', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Finance', 'finance', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Healthcare Services', 'health care', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Pharmaceuticals', 'pharmaceuticals', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Hotels', 'hotels', database());
INSERT INTO sectors (name, brief, db_source) VALUES ('Internet Software & Services', 'software services', database());

INSERT INTO stock_exchange (name, brief_writeup, contact_addr, remarks, db_source) VALUES ('BSE(Bombay Stock Exchange)', 'The Decision to Temporarily Close the New York Stock Exchange Trading Floor. NYSE Group COO Michael Blaugrund breaks down the decision to temporarily close the Trading Floor & the impact that COVID-19 will have on the markets & trading.', 'NYSE Chicago Support. 1 312 663 2111. NYSE Arca Options Support. 1 212 896 2830, Option 1, 2. Direct Message.', 'The Decision to Temporarily Close the New York Stock Exchange Trading Floor.', database());
INSERT INTO stock_exchange (name, brief_writeup, contact_addr, remarks, db_source) VALUES ('NSE(New York Stock Exchange)', '27 Mar Notice on Issuing “Interim Provisions on Application and Recommendation of Enterprises for Issuance and Listing on SSE STAR Market”; 23 Mar SSE Launches Live-Streaming Lectures on Qualification for Directorate Secretaries of Companies Listed on SSE STAR Market; 16 Mar First Corporate Bond under Registration-Based System Issued on SSE', 'Pudong South Road No.528 Shanghai Stock Exchange Building F F15, Shanghai', 'Shanghai Composite Index advanced index charts by MarketWatch. View real-time SHCOMP index data and compare to other exchanges and stocks.', database());

INSERT INTO company2stock_exchange (fk_company_id, fk_stock_exchange_id, stock_code, db_source) VALUES (1, 1, '8CUC', database());
INSERT INTO company2stock_exchange (fk_company_id, fk_stock_exchange_id, stock_code, db_source) VALUES (1, 2, '5JLH', database());
INSERT INTO company2stock_exchange (fk_company_id, fk_stock_exchange_id, stock_code, db_source) VALUES (2, 1, '4JHG', database());

INSERT INTO ipo_details (fk_company_id, fk_stock_exchange_id, price_per_share, unit, total_number_shares, open_datetime, remarks, db_source) VALUES (2, 1, 32, 7731, 'Rs', '2019-01-01 00:00:00', 'test', database());
INSERT INTO ipo_details (fk_company_id, fk_stock_exchange_id, price_per_share, unit, total_number_shares, open_datetime, remarks, db_source) VALUES (1, 2, 224, 343543, 'Ud', '2000-01-01 00:00:00', 'test', database());
INSERT INTO ipo_details (fk_company_id, fk_stock_exchange_id, price_per_share, unit, total_number_shares, open_datetime, remarks, db_source) VALUES (1, 1, 312, 343423, 'Ud', '1900-01-01 00:00:00', 'test', database());

INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 1, 143, 'Ud', '2019-01-01', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 1, 145, 'Ud', '2019-01-05', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 1, 126, 'Ud', '2019-01-06', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 2, 156, 'Ud', '2019-01-01', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 2, 165, 'Ud', '2019-01-05', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (1, 2, 174, 'Ud', '2019-01-06', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (2, 1, 34, 'Rs', '2019-01-01', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (2, 1, 23, 'Rs', '2019-01-05', '9:00:00', database());
INSERT INTO stock (fk_company_id, fk_stock_exchange_id, price, unit, date4price, time4price, db_source) VALUES (2, 1, 41, 'Rs', '2019-01-06', '9:00:00', database());


