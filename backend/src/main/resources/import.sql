INSERT INTO tb_users (id, username, password, created_At) VALUES (1, 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TIMESTAMP WITH TIME ZONE '2022-01-01T10:00:00Z');
INSERT INTO tb_users (id, username, password, created_At) VALUES (2, 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TIMESTAMP WITH TIME ZONE '2022-01-01T10:00:00Z');

INSERT INTO tb_roles (id, authority) VALUES (1, 'ROLE_OPERATOR');
INSERT INTO tb_roles (id, authority) VALUES (2, 'ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);