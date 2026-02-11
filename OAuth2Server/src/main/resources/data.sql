
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1000 increment by 1;


-- Contraseña: Admin1
insert into usuarios (id, full_name, email, username, password, avatar, created_at, last_password_change_at) 
values (1, 'Admin admin', 'admin@oauth.net','admin','$2a$10$55jeMnYoXio8BTaQDtnc0OxjdQyv43TQ0D48YAkMGAFuswr944ZBC','https://api.adorable.io/avatars/285/admin@oauth.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');


-- Contraseña: Felix1
insert into usuarios (id, full_name, email, username, password, avatar, created_at, last_password_change_at, proveedor, saldo) 
values (2, 'Felix Marin', 'felixmarin@oauth.net','felixmarin','$2a$10$vLuI7c8Rf5e3y.W0WCsnz.T/8PqgvRNdbYbCFGyJlxsOi1QcNHuEi','https://api.adorable.io/avatars/285/felixmarin@oauth.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'codere', 350.15);

insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: User1
insert into usuarios (id, full_name, email, username, password, avatar, created_at, last_password_change_at, proveedor, saldo) 
values (3, 'user 1', 'usuario1@oauth.net','user1','$2a$10$y2dD9vEfi2gqio6sxJ.kJeNcsqMWiBekKZgAJxzXRyydMFphtOj.i','https://api.adorable.io/avatars/285/usuario1@oauth.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BWIN', 234.12);

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: User2
insert into usuarios (id, full_name, email, username, password, avatar, created_at, last_password_change_at, proveedor, saldo) 
values (4, 'user 2', 'usuario2@oauth.net','user2','$2a$10$1nicdnvXt5bYYdKvlz/YUeRLG.ySV8nmQfu6BXloLlP/h/qEcPU0m','https://api.adorable.io/avatars/285/usuario2@oauth.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BET365', 543.43);

insert into user_entity_roles (user_entity_id, roles) values (4,'USER');