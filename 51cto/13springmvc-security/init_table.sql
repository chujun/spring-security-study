create table tb_users (uid int not null auto_increment PRIMARY key, phone varchar(11) not null, userpwd varchar(50) not null,
status int,is_del int default 0,create_date datetime) ;

--status
0:禁用
1:正常
2:已过期
3:黑名单
4:未激活


create table tb_roles (rid int not null auto_increment PRIMARY key, role_name varchar(50) not null, role_desc varchar(300),spath varchar(500));

create table tb_user_role (uid int not null, rid int not null);


select phone,userpwd,(case when status in (1,4) then 1 else 0 end) enabled from tb_users where phone=? and is_del=0

select a.phone,b.role_name from tb_users a,tb_roles b,tb_user_role c where a.is_del=0 and a.phone=? and c.uid=a.uid and c.rid=b.rid
