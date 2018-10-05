

create database order_db;

use order_db;

create table Users(
id int primary key auto_increment,
username varchar(25) not null,
password varchar(20) not null,
role varchar(10)
);

create table products(
id char(5) primary key,
name varchar(55) not null,
category varchar(17) not null,
price double not null,
details varchar(90)
);

create table orders(
id int primary key auto_increment,
product_id char(5),
user_id int,
order_date date,
total_amount double,
foreign key(user_id) references users(id),
foreign key(product_id) references products(id)
);

DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertUsers`;
CREATE PROCEDURE insertUsers (IN user_name varchar(55),IN password VARCHAR(20),IN role VARCHAR(10))
BEGIN
       INSERT INTO users VALUES (null, user_name,password,role);
END$$

DELIMITER ;

DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertProducts`;
CREATE PROCEDURE insertProducts (IN product_id char(5),IN product_name varchar(55),IN category VARCHAR(17),IN price double, IN details varchar(90))
BEGIN
       INSERT INTO products VALUES ( product_id,product_name,category,price,details);
END$$

DELIMITER ;

CALL insertProducts('M1001',"Micromax Canvas","Mobile", 12000.0,'Low cost phablet' );
CALL insertProducts('M1002'," LG G3 D885","Mobile", 40000.0, 'High End mobile and winner of mobile of the year 2014');
CALL insertProducts('B1001',"The Lord of the Rings-JRR Tolkeins","Book", 1500.0,'One of the literary masterpiece of our generation' );
CALL insertProducts('B1002',"Rosie- Alan TitchMarsh","Book", 500.0,'A romantic novel set in the backdrop of coldwar' );

CALL insertUsers('admin','admin@123','ADMIN');
CALL insertUsers('customer','welcome@123','CUSTOMER');

