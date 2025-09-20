CREATE TABLE IF NOT EXISTS produtos (
	id bigint NOT NULL UNIQUE,
	nome varchar(300) NOT NULL,
	descricao varchar(255) NOT NULL,
	quantidade_estoque numeric(12,2) NOT NULL,
	preco numeric(12,2) NOT NULL,
	ativo boolean NOT NULL,
	imagem varchar(100) NOT NULL,
	categoria_id bigint NOT NULL,
	marca_id bigint NOT NULL,
	PRIMARY KEY (id)
);
create sequence produtos_SEQ start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS categorias (
	id bigint NOT NULL UNIQUE,
	descricao varchar(100) NOT NULL,
	PRIMARY KEY (id)
);
create sequence categorias_SEQ start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS pedidos (
	id bigint NOT NULL UNIQUE,
	uuid varchar(32) NOT NULL,
	usuario_id bigint,
	data_criacao date NOT NULL,
	data_pedido date NOT NULL,
	data_entrega date NOT NULL,
	status varchar(20) NOT NULL,
	valor_total numeric(12,2) NOT NULL,
	PRIMARY KEY (id)
);
create sequence pedidos_SEQ start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS itens_pedido (
	id bigint NOT NULL UNIQUE,
	produto_id bigint NOT NULL,
	pedido_id bigint NOT NULL,
	quantidade numeric(12,2) NOT NULL,
	valor_unitario numeric(12,2) NOT NULL,
	PRIMARY KEY (id)
);
create sequence itens_pedido_SEQ start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS marcas (
	id bigint NOT NULL UNIQUE,
	descricao varchar(150) NOT NULL,
	PRIMARY KEY (id)
);
create sequence marcas_SEQ start with 1 increment by 50;

ALTER TABLE produtos ADD CONSTRAINT produtos_fk7 FOREIGN KEY (categoria_id) REFERENCES categorias(id);

ALTER TABLE produtos ADD CONSTRAINT produtos_fk8 FOREIGN KEY (marca_id) REFERENCES marcas(id);

ALTER TABLE pedidos ADD CONSTRAINT pedido_fk2 FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE itens_pedido ADD CONSTRAINT itens_pedido_fk1 FOREIGN KEY (produto_id) REFERENCES produtos(id);

ALTER TABLE itens_pedido ADD CONSTRAINT itens_pedido_fk2 FOREIGN KEY (pedido_id) REFERENCES pedidos(id);
