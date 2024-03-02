CREATE TABLE IF NOT EXISTS produto (
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(30) NOT NULL,
    categoria enum ('LANCHE', 'ACOMPANHAMENTO', 'BEBIDA', 'SOBREMESA') NOT NULL,
    preco double NOT NULL,
    descricao text NOT NULL,
    imagem LONGBLOB,
	primary key (id)
);

CREATE TABLE IF NOT EXISTS endereco (
    id bigint NOT NULL AUTO_INCREMENT,
    logradouro varchar(20) NOT NULL,
    rua varchar(255) NOT NULL,
    numero int NOT NULL,
    bairro varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado varchar(2) NOT NULL,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    cpf varchar(14) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    telefone varchar(15) NOT NULL,
    endereco_id bigint NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id),
    primary key (id)
);
