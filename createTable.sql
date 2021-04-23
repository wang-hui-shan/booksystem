create database booksystem;
use booksystem;
create table user(
userid int unsigned not null primary key auto_increment,
username varchar(100) not null unique,
userpass varchar(100) not null);

insert into user(username,userpass) values ("test1","111111"),("test2","111111");

create table admin(
userid int unsigned not null primary key auto_increment,
username varchar(100) not null unique,
userpass varchar(100) not null);

insert into admin(username,userpass) values ("admin1","111111"),("admin2","111111");

create table bookinfo(
bookid int unsigned not null primary key auto_increment,
bookname varchar(100) not null,
bookauthor varchar(100) not null,
booktheme varchar(100) not null,
bookstatus int default 0);

insert into bookinfo(bookname,bookauthor,booktheme) values ("房思琪的秘密花园","林奕含","小说,文学,伦理"),("java编程思想","埃克尔","计算机,技术"),("java编程思想","埃克尔","计算机,技术"),("朝花夕拾","鲁迅","文学,散文");

create table userbookinfo(
userid int unsigned not null ,
bookid int unsigned not null,
bookname varchar(100)
);
insert into userbookinfo values (1,1,"房思琪的秘密花园"),(2,2,"java编程思想"),(1,3,"java编程思想");
update bookinfo set bookstatus=1 where bookid=1 or bookid=2 or bookid=3;


