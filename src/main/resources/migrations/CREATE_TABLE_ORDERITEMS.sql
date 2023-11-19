CREATE TABLE IF NOT EXISTS orderItems (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    orderId BIGINT NOT NULL,
    productName TEXT NOT NULL,
    productValue FLOAT NOT NULL,
    quantity INT NOT NULL,
    subTotalValue FLOAT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders(id)
);