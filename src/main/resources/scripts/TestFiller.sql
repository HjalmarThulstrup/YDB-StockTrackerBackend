DELETE FROM UserStock;
DELETE FROM user_roles;
DELETE FROM users;


INSERT INTO users (user_name, user_pass) VALUES ('TestStockListUser', '1234');
INSERT INTO roles (role_name) VALUES ('user')