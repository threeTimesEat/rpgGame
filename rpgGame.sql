use rpg;

CREATE TABLE TBL_USER (
user_code int primary key auto_increment,
user_name varchar(225),
user_charm int,
user_bag int,
user_money int
-- foreign key (user_bag) references ... ()
) engine = innodb;

CREATE TABLE TBL_NPC (
npc_code int primary key auto_increment,
npc_name varchar(225)
);

CREATE TABLE TBL_CHARM (
charm int,
user_code int,
npc_code int,
foreign key (user_code) references TBL_USER (user_code),
foreign key (npc_code) references TBL_NPC (npc_code)
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
item_code int,
user_code int,
foreign key (item_code) references tbl_item (item_code),
foreign key (user_code) references TBL_USER (user_code)
);

alter table tbl_user add foreign key(user_bag) references tbl_bag(bag_code);

desc tbl_user;
desc tbl_bag;
desc tbl_item;

select * from tbl_item;

insert into tbl_item (item_code, item_category, item_name, item_price, item_charm)
values (1, 1, "정장", 100000, 30);




