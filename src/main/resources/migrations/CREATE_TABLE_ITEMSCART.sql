CREATE TABLE IF NOT EXISTS itemsCart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    productId BIGINT NOT NULL,
    cartId BIGINT NOT NULL,
    quantity INT NOT NULL,
    productValue FLOAT NOT NULL,
    subTotalValue FLOAT NOT NULL,
    FOREIGN KEY (productId) REFERENCES products(id),
    FOREIGN KEY (cartId) REFERENCES cart(id)
);