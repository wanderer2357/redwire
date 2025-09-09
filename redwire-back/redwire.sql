DROP DATABASE IF EXISTS redwire;
CREATE DATABASE redwire;

use redwire;

create table client (
	id						INT NOT NULL AUTO_INCREMENT,
	firstname				VARCHAR(255) NOT NULL,
	lastname				VARCHAR(255) NOT NULL,	
	email					VARCHAR(255) UNIQUE NOT NULL,
	phone					VARCHAR(20) NOT NULL,
	password_hash 			VARCHAR(255) NOT NULL,
	salt 					VARCHAR(255) NOT NULL,
	created_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	status 					ENUM('active', 'inactive') DEFAULT 'active',
	PRIMARY KEY (id)
);

create table administrator (
	id						INT NOT NULL AUTO_INCREMENT,
	firstname				VARCHAR(255) NOT NULL,
	lastname				VARCHAR(255) NOT NULL,	
	email					VARCHAR(255) UNIQUE NOT NULL,
	phone					VARCHAR(20) NOT NULL,
	password_hash 			VARCHAR(255) NOT NULL,
	salt 					VARCHAR(255) NOT NULL,
	created_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	status 					ENUM('active', 'inactive') DEFAULT 'active',
	PRIMARY KEY (id)
);

create table address (
	id						INT NOT NULL AUTO_INCREMENT,
	client_id				INT NOT NULL,
	street1					VARCHAR(255)  NOT NULL,
	street2					VARCHAR(255),
	postal_code				VARCHAR(20),
	city					VARCHAR(255) NOT NULL,
	region_state_province 	VARCHAR(255),
	country 				VARCHAR(255) NOT NULL,
	created_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY(id),
	FOREIGN KEY (client_id) REFERENCES client(id)
);

create table article (
	id				INT NOT NULL AUTO_INCREMENT,
	code 			VARCHAR(50) UNIQUE NOT NULL,
	label			VARCHAR(255) NOT NULL,
	price     		DECIMAL(10, 2) NOT NULL,
	cost_price		DECIMAL(10, 2) NOT NULL,
	created_at 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	status 			ENUM('active', 'inactive') DEFAULT 'active',
	PRIMARY KEY(id)
		);

create table cart (
	client_id		INT NOT NULL,
	article_id		INT NOT NULL,
	quantity		INT NOT NULL,
	PRIMARY KEY (client_id, article_id),
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (article_id) REFERENCES article(id)
);

CREATE TABLE payment_cards (
	id 					INT NOT NULL AUTO_INCREMENT,
	client_id 			INT NOT NULL,
	card_type 			ENUM('Visa', 'MasterCard') NOT NULL,
	last_4_digits 		CHAR(4) NOT NULL,
	expiration_month 	CHAR(2) NOT NULL,
	expiration_year 	CHAR(4) NOT NULL,
	cardholder_name 	VARCHAR(255),
	card_token 			VARCHAR(255) NOT NULL,
	status 				ENUM('active', 'inactive', 'expired') DEFAULT 'active',
	PRIMARY KEY(id),
	FOREIGN KEY (client_id) REFERENCES client(id)
);

create table stockheader (
	id    				INT NOT NULL AUTO_INCREMENT,
	article_id			INT NOT NULL,
	maxi				INT DEFAULT 0,
	mini				INT DEFAULT 0,
	moq					INT DEFAULT 0,
	PRIMARY KEY (id)
);

create table stockdetail (
	id    				INT NOT NULL AUTO_INCREMENT,
	article_id			INT NOT NULL,
	location			VARCHAR(255),
	quantity			INT DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (article_id) REFERENCES article(id)
);

create table clientorderheader (
	id INT NOT NULL AUTO_INCREMENT,
	client_id INT NOT NULL,
	address_id	INT NOT NULL,
	status 	ENUM('pending', 'delivered') DEFAULT 'pending',
	created_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (address_id) REFERENCES address(id)
);

create table clientorderdetail (
	id INT NOT NULL AUTO_INCREMENT,
	clientorder_id	INT NOT NULL,
	article_id INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (clientorder_id) REFERENCES clientorderheader(id),
	FOREIGN KEY (article_id) REFERENCES article(id)
);

create table supplier (
	id						INT NOT NULL AUTO_INCREMENT,
	name 					VARCHAR(255) UNIQUE NOT NULL,
	email					VARCHAR(255) UNIQUE NOT NULL,
	phone					VARCHAR(20) NOT NULL,
	street1					VARCHAR(255) NOT NULL,
	street2					VARCHAR(255),
	postal_code				VARCHAR(20),
	city					VARCHAR(255) NOT NULL,
	region_state_province 	VARCHAR(255),
	country 				VARCHAR(255) NOT NULL,
	created_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	status 					ENUM('active', 'inactive') DEFAULT 'active',
	PRIMARY KEY (id)
);

create table supplierorderheader (
	id INT NOT NULL AUTO_INCREMENT,
	supplier_id INT NOT NULL,
	address_id	INT NOT NULL,
	status 	ENUM('pending', 'delivered') DEFAULT 'pending',
	PRIMARY KEY (id),
	FOREIGN KEY (supplier_id) REFERENCES supplier(id),
	FOREIGN KEY (address_id) REFERENCES address(id)
);

create table supplierorderdetail (
	id INT NOT NULL AUTO_INCREMENT,
	supplierorder_id INT NOT NULL,
	article_id INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (supplierorder_id) REFERENCES supplierorderheader(id),
	FOREIGN KEY (article_id) REFERENCES article(id)
);