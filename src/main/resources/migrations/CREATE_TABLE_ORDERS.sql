CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client TEXT NOT NULL,
    cpf TEXT NOT NULL,
    email TEXT NOT NULL,
    cep TEXT,
    adressNumber INT,
    adress TEXT,
    complement TEXT,
    phoneNumber TEXT NOT NULL,
    paymentMethod TEXT,
    moneyChange FLOAT,
    cartId BIGINT NOT NULL,
    orderDate DATETIME NOT NULL,
    status TEXT,
    FOREIGN KEY (cartId) REFERENCES cart(id)
);