CREATE TABLE IF NOT EXISTS usuarios (
	id bigint NOT NULL UNIQUE,
	nome_completo varchar(300) NOT NULL,
	endereco varchar(500) NOT NULL,
	cidade varchar(100) NOT NULL,
	uf varchar(2) NOT NULL,
	ativo boolean NOT NULL,
	data_cadastro date NOT NULL,
	cep varchar(8) NOT NULL,
	email varchar(150) NOT NULL,
	PRIMARY KEY (id)
);

create sequence usuarios_SEQ start with 1 increment by 50;
