-- DROP TABLE IF EXISTS produtoavaliacao;
CREATE TABLE produtoavaliacao(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	descricao CHARACTER VARYING(500) NOT NULL,
	nota INTEGER NOT NULL,
	datahora TIMESTAMP WITHOUT TIME ZONE,
	produto_id UUID NOT NULL,
	pessoafisica_id UUID NOT NULL,
	CONSTRAINT produtoavaliacao_pk PRIMARY KEY(id),
	CONSTRAINT produtoavaliacao_fk01 FOREIGN KEY(produto_id) REFERENCES produto(id),
	CONSTRAINT produtoavaliacao_fk02 FOREIGN KEY(pessoafisica_id) REFERENCES pessoafisica(id)
);
ALTER TABLE produtoavaliacao OWNER TO postgres;