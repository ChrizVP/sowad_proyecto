
INSERT INTO role (role_id, role) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, role) VALUES (2, 'USER');
INSERT INTO role (role_id, role) VALUES (3, 'ADMIN');

INSERT INTO user (user_id, nombre, password, username) VALUES (1, 'christian', '$2a$10$QspJ6wFAmD99DOfpc6yqGuExZq3UuGFQLHNVXNnyfmGAgCuRGYNFG', 'summer@gmail.com');
INSERT INTO user (user_id, nombre, password, username) VALUES (2, 'Raul', '$2a$10$SAOFoFmQE49FofADSEsO5.srkT2P3OPKkwMdrHjMI7a32FGaMI496', 'summer9620@gmail.com');
INSERT INTO user (user_id, nombre, password, username) VALUES (999, 'chester', '$2a$10$Sxp9ZJ4MxJsljUE3q/kq5OUVe.RGy0O6rQNyhe830WM.jq1XNZEUO', 'chester@gmail.com');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (999, 3);

