INSERT INTO user (id, first_name, last_name, email, password) VALUES
(1, 'John', 'Smith', 'john.smith@email.cz', 'john1234'),
(2, 'Peter', 'Griffin', 'peter.griffin@gmail.com', 'password');

INSERT INTO role (id, role_name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
(1,1),
(2,1),
(2,2);

