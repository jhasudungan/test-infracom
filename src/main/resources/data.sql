-- Initial Data
INSERT INTO user(id,name,age) values('user_1','Alisson Becker', 29);
INSERT INTO user(id,name,age) values('user_4','Virgil Can Dijk', 30);
INSERT INTO user(id,name,age) values('user_14','Jordan Henderson', 31);

INSERT INTO user_contact(id,address,user_id) values ('contact_1','Brazil','user_1');
INSERT INTO user_contact(id,address,user_id) values ('contact_2','Netherlands','user_4');
INSERT INTO user_contact(id,address,user_id) values ('contact_3','Suriname','user_4');
INSERT INTO user_contact(id,address,user_id) values ('contact_4','England','user_14');