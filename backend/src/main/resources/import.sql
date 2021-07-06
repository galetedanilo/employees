INSERT INTO tb_user (email, password) VALUES ('ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (email, password) VALUES ('bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_department(name) VALUES ('Sales');
INSERT INTO tb_department(name) VALUES ('Management');
INSERT INTO tb_department(name) VALUES ('Training');

INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Maria', 'Aparecida', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Ana','Beatriz', 'maria@gmail.com', 2);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Bob', 'Marlli', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Marco', 'Barbosa', 'maria@gmail.com', 2);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Alex', 'Campo', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Claudia', 'Dias', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Rodrigo', 'Pedron', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Elisa', 'Macron', 'maria@gmail.com', 2);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Lucas', 'Lima', 'maria@gmail.com', 3);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Rafael', 'Dutra', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Tiago', 'Silva', 'maria@gmail.com', 1);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Andressa', 'Cambelli', 'maria@gmail.com', 2);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Meire', 'Bomfim', 'maria@gmail.com', 3);
INSERT INTO tb_employee(first_name, last_name, email, department_id) VALUES ('Carol', 'Castro', 'maria@gmail.com', 1);
