CREATE TABLE IF NOT EXISTS users(
    email VARCHAR(255) NOT NULL,
    hash VARCHAR(255) NOT NULL,
    salt CHAR(36) NOT NULL,
    name VARCHAR(42) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    PRIMARY KEY(email)
);
CREATE TABLE IF NOT EXISTS cursos(
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(52) NOT NULL,
    description VARCHAR(2000),
    price DECIMAL(255, 2) NOT NULL,
    img VARCHAR(500),
    rating INTEGER NOT NULL,
    creationDate VARCHAR(100) NOT NULL,
    modificationDate VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
) AUTO_INCREMENT = 1;