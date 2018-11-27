DELETE FROM users;
DELETE FROM UserStock

INSERT INTO users (user_name, user_pass) VALUES ('TestStockListUser', '1234');
INSERT INTO roles (role_name) VALUES ('user')