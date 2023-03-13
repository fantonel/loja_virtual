-- DROP TABLE IF EXISTS produtoimagem;
CREATE TABLE produtoimagem(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	titulo CHARACTER VARYING(60) DEFAULT NULL,
	imagemoriginal CHARACTER VARYING(300) NOT NULL,
	imagemminiatura CHARACTER VARYING(300) NOT NULL,
	produto_id UUID NOT NULL,
	CONSTRAINT produtoimagem_pk PRIMARY KEY(id),
	CONSTRAINT produtoimagem_fk01 FOREIGN KEY(produto_id) REFERENCES produto(id)
);
ALTER TABLE produtoimagem OWNER TO postgres;
