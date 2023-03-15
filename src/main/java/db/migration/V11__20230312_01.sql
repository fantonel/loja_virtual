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

-- DROP TABLE IF EXISTS notafiscalcompra;
CREATE TABLE IF NOT EXISTS public.notafiscalcompra(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    descricao character varying(2000),
    datacompra timestamp without time zone NOT NULL,
    dataemissao timestamp without time zone NOT NULL,
    numero character varying(60) NOT NULL,
    serie character varying(15) NOT NULL,
    valor numeric(10,4) NOT NULL DEFAULT 0.00,
    icms numeric(10,4) NOT NULL DEFAULT 0.00,
    desconto numeric(10,4) NOT NULL DEFAULT 0.00,
    total numeric(10,4) NOT NULL DEFAULT 0.00,
    pessoajuridica_id uuid NOT NULL,
    CONSTRAINT notafiscalcompra_pk PRIMARY KEY (id),
    CONSTRAINT notafiscalcompra_unique01 UNIQUE (pessoajuridica_id, numero),
    CONSTRAINT notafiscalcompra_fk01 FOREIGN KEY (pessoajuridica_id) REFERENCES public.pessoajuridica (id)
);
ALTER TABLE IF EXISTS notafiscalcompra OWNER to postgres;

-- DROP TABLE IF EXISTS notafiscalcompraitens;
CREATE TABLE IF NOT EXISTS notafiscalcompraitens(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    datahora timestamp without time zone NOT NULL,
    quantidade integer NOT NULL,
    valor numeric(10,4) NOT NULL,
    desconto numeric(10,4) NOT NULL DEFAULT 0.00,
    total numeric(10,4) NOT NULL,
    produto_id uuid NOT NULL,
    notafiscalcompra_id uuid NOT NULL,
    CONSTRAINT notafiscalcompraitens_pk PRIMARY KEY (id),
    CONSTRAINT notafiscalcompraitens_unique01 UNIQUE (produto_id, notafiscalcompra_id),
    CONSTRAINT notafiscalcompraitens_fk01 FOREIGN KEY (produto_id) REFERENCES public.produto (id),
    CONSTRAINT notafiscalcompraitens_fk02 FOREIGN KEY (notafiscalcompra_id)
        REFERENCES public.notafiscalcompra (id)
);
ALTER TABLE IF EXISTS notafiscalcompraitens OWNER to postgres;
