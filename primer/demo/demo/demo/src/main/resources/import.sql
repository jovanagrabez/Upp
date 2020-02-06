INSERT INTO user (name, last_name, email,password, enabled, username, recenzent) VALUES ('Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' , false);
INSERT INTO user (name, last_name, email,password, enabled, username, recenzent) VALUES ('Milica','Matic','jovana@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'jovana@yahoo.com' , false);
INSERT INTO user (name, last_name, email,password, enabled, username, recenzent) VALUES ('Milica','Matic','grabez@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'grabez@yahoo.com' , false);
INSERT INTO user (name, last_name, email,password, enabled, username, recenzent) VALUES ('Milica','Matic','elena@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'elena@yahoo.com' , false);
INSERT INTO user (name, last_name, email,password, enabled, username, recenzent) VALUES ('Milica','Matic','andjela@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'andjela@yahoo.com' , false);



INSERT INTO authority (id, name) VALUES (1, 'ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'RECENZENT');
INSERT INTO authority (id, name) VALUES (3, 'UREDNIK');


INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 2);


insert into naucnaoblast (id, name) values (1,'Racunarstvo');
insert into naucnaoblast (id, name) values (2,'Hemija');
insert into naucnaoblast (id, name) values (3,'Medicina');
insert into naucnaoblast (id, name) values (4,'Biologija');
insert into naucnaoblast (id, name) values (5,'Elektrotehnika');




