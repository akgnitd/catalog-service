create database assignment;

use assignment;

create table category (
	ID INT,
	NAME varchar(64) NOT NULL,
	DESCRIPTION varchar(64), 
	CREATED_BY varchar(64), 
	CREATED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) comment "Created Date",
	MODIFIED_BY varchar(64),
	MODIFIED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) comment "Modified Date",
	PRIMARY KEY (ID))
COMMENT = 'Table to store categories';


create table attribute (
	ID INT,
	NAME varchar(64) NOT NULL,
	DESCRIPTION varchar(64),
	CREATED_BY varchar(64), 
	CREATED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) comment "Created Date",
	MODIFIED_BY varchar(64),
	MODIFIED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) comment "Modified Date",
PRIMARY KEY (ID))
COMMENT = 'Table to store attributes master data';


create table category_attribute (
	ID INT,
	ATTRIBUTE_ID INT NOT NULL,
	ATTRIBUTE_NAME varchar(32) NOT NULL,
	ATTRIBUTE_VALUE varchar(32),
	CATEGORY_ID INT NOT NULL,
	CREATED_BY varchar(64),
	CREATED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) comment "Created Date",
	MODIFIED_BY varchar(64),
	MODIFIED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) comment "Modified Date",
CONSTRAINT fk_attribute_category FOREIGN KEY (CATEGORY_ID) REFERENCES category(ID),
PRIMARY KEY (ID))
COMMENT = 'Table to store attributes for each categories';



create table product (
	ID INT,
	PRODUCT_NAME varchar(60) NOT NULL,
	DESCRIPTION varchar(64) NOT NULL,
	CATEGORY_ID INT NOT NULL,
	CATEGORY_NAME varchar(64) NOT NULL,
	CATEGORY_ATTRIBUTE mediumtext,
	CREATED_BY varchar(64),
	CREATED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) comment "Created Date",
	MODIFIED_BY varchar(64),
	MODIFIED_ON TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) comment "Modified Date",
CONSTRAINT fk_product_category FOREIGN KEY (CATEGORY_ID) REFERENCES category(ID),
PRIMARY KEY (ID))
COMMENT = 'Table to store products';