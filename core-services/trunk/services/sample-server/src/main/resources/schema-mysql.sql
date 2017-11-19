create table if not exists notes(
id bigint not null auto_increment,
title varchar(255),
content varchar(255),
created_at datetime,
updated_at datetime,
PRIMARY KEY (id)
);