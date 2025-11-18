-- Insert initial data into users table
-- Note: Passwords should be hashed, but for initial data, we'll insert a hashed version of 'admin'
-- In a real scenario, use BCryptPasswordEncoder to hash passwords

INSERT INTO users (email, password, name) VALUES
('admin@admin.com', '$2a$10$exampleHashedPasswordForAdmin', 'Admin User');

-- Insert roles for the admin user
INSERT INTO user_roles (user_id, roles) VALUES
((SELECT id FROM users WHERE email = 'admin@admin.com'), 'ADMIN');
