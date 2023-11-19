CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    totalValue FLOAT NOT NULL,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);