-- DROP TABLE IF EXISTS public.pedidoprodutos;
CREATE TABLE IF NOT EXISTS public.pedidoprodutos(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    pedido_id uuid NOT NULL,
    produto_id uuid NOT NULL,
    qtde numeric(5,0) NOT NULL DEFAULT 1,
    valor numeric(10,4) NOT NULL DEFAULT 0.00,
    valorparcial numeric(10,4) NOT NULL DEFAULT 0.00,
    percdescto numeric(5,2),
    valordescto numeric(10,2),
	valorcalc numeric(10,4) NOT NULL DEFAULT 0.00,
    valorfinal numeric(10,4) NOT NULL DEFAULT 0.00,
    CONSTRAINT pedidoproduto_pk PRIMARY KEY (id),
    CONSTRAINT pedidoproduto_unique_01 UNIQUE (pedido_id, produto_id),
    CONSTRAINT pedidoproduto_fk01 FOREIGN KEY (pedido_id)
        REFERENCES public.pedido (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pedidoproduto_fk02 FOREIGN KEY (produto_id)
        REFERENCES public.produto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
