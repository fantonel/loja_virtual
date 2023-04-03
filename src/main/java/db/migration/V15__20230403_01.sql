-- DROP TABLE IF EXISTS contapagar;
CREATE TABLE contapagar(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
	descricao CHARACTER VARYING(120) NOT NULL,
	valortotal NUMERIC(10,4) NOT NULL,
	pessoafisica_id UUID,
	pessoajuridica_id UUID,
	CONSTRAINT contapagar_pk PRIMARY KEY(id),
	CONSTRAINT contapagar_fk01 FOREIGN KEY(pessoafisica_id) REFERENCES pessoafisica(id),
	CONSTRAINT contapagar_fk02 FOREIGN KEY(pessoajuridica_id) REFERENCES pessoajuridica(id)
);