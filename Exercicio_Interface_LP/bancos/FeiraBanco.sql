DROP DATABASE IF EXISTS feira_db;
CREATE DATABASE feira_db;
USE feira_db;

CREATE TABLE banca (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(50) NOT NULL
);

CREATE TABLE produto (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco_quilo DECIMAL(10, 2) NOT NULL
);

CREATE TABLE venda (
    id INT PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    banca_id INT NOT NULL,
    FOREIGN KEY (banca_id) REFERENCES banca(id)
);