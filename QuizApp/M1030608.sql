create database quiz_db;

use quiz_db;

create table quiz(
id varchar(5) primary key,
quiz_name varchar(20)
);

create table questions(
id varchar(5) primary key,
questions varchar(100),
quiz_id varchar(5),
foreign key(quiz_id) references quiz(id));

DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertQuiz`;
CREATE PROCEDURE insertQuiz (IN id VARCHAR(5), IN quiz_name VARCHAR(20))
BEGIN
       INSERT INTO QUIZ VALUES (id,quiz_name);
END$$

DELIMITER ;

CALL insertQuiz('M1001','Core Java');
CALL insertQuiz('M1002','Geography of India');
CALL insertQuiz('M1003','Indian Economy');

create table options(
id int primary key auto_increment,
option1 varchar(15),
option2 varchar(15),
option3 varchar(15),
option4 varchar(15),
correct_option int,
quiz_id varchar(5),
question_id varchar(5),
foreign key(quiz_id) references quiz(id),
foreign key(question_id) references questions(id));