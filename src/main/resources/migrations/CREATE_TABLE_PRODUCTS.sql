CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    value FLOAT NOT NULL,
    image TEXT NOT NULL,
    amount INT NOT NULL,
    category TEXT NOT NULL
);