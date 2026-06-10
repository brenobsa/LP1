-- 1. Cria o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS farmacia;
USE farmacia;

-- 2. Tabela Cliente (cpf, nome, emailContato)
CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    emailContato VARCHAR(100) NOT NULL
);

-- 3. Tabela Medicamento (nome, preco, estoque)
CREATE TABLE IF NOT EXISTS medicamento (
    nome VARCHAR(100) PRIMARY KEY,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL
);

-- 4. Tabela Pedido (numero, valor, status)
CREATE TABLE IF NOT EXISTS pedido (
    numero INT PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL
);