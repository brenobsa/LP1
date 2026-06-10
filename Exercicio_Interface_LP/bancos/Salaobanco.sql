DROP DATABASE IF EXISTS salao_db;
CREATE DATABASE salao_db;
USE salao_db;

CREATE TABLE servico (
    id INT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

CREATE TABLE profissional (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

CREATE TABLE agendamento (
    id INT PRIMARY KEY,
    data_hora VARCHAR(50) NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL
);