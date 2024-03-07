use rpg;

CREATE TABLE TBL_USER (
user_code int primary key auto_increment,
user_name varchar(225),
user_charm int,
user_bag int,
user_equipped int,
user_money int
) engine = innodb;

CREATE TABLE TBL_NPC (
npc_code int primary key auto_increment,
npc_name varchar(225),
npc_charm int
);

CREATE TABLE TBL_ITEM (
item_code int primary key auto_increment,
item_category int,
item_name varchar(225),
item_price int unsigned,
item_charm int
);

CREATE TABLE TBL_BAG (
bag_code int primary key auto_increment,
item_code int
);

desc tbl_bag;

CREATE TABLE TBL_SHOP (
shop_code int primary key auto_increment,
item_code int,
foreign key (item_code) references tbl_item (item_code)
);

desc tbl_user;
desc tbl_bag;
desc tbl_item;

insert into tbl_shop (shop_code, item_code)
values (1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

insert into tbl_item (item_code, item_category, item_name, item_price, item_charm)
values (1, 1, "정장", 100000, 30),
	(2, 1, "셔츠와 청바지", 25000, 5),
(3, 1, "체크 셔츠에 멜빵바지", 15000, -10),
 (4, 1, "구찌백", 1000000, -1000);


insert into tbl_item (item_code, item_category, item_name, item_price, item_charm)
values (5, 2, "꽃다발", 30000, 20),
	(6, 2, "케이크", 45000, 30),
(7, 2, "발가락 양말", 3000, -20),
 (8, 2, "슈퍼카", 100000000, -1000);

insert into tbl_npc (npc_name, npc_charm)
values ("금혁수", -20),
("구자윤", 0),
("조현", 15);

insert into tbl_bag values (1,1);
insert into tbl_user (user_name, user_charm, user_bag, user_equipped, user_money)
values ("강동원", 100, 1, 1, 0);