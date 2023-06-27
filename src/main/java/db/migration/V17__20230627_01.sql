-- DROP TABLE IF EXISTS public.pedidoprodutos;
CREATE TABLE IF NOT EXISTS public.pedidoprodutos(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    pedido_id uuid NOT NULL,
    produto_id uuid NOT NULL,
	marca_id uuid NOT NULL,
    qtde NUMERIC(5,0) NOT NULL DEFAULT 1,
    valor NUMERIC(10,4) NOT NULL default 0.00,
    CONSTRAINT pedidoproduto_pk PRIMARY KEY (id),
    CONSTRAINT pedidoproduto_unique_01 UNIQUE (pedido_id, produto_id),
    CONSTRAINT pedidoproduto_fk01 FOREIGN KEY (pedido_id)
        REFERENCES public.pedido (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pedidoproduto_fk02 FOREIGN KEY (produto_id)
        REFERENCES public.produto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pedidoproduto_fk03 FOREIGN KEY (marca_id)
        REFERENCES public.marca (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

/*
INSERT INTO pedidoprodutos(id,pedido_id,produto_id,marca_id,qtde,valor)
  SELECT (uuid_generate_v4()),(SELECT id FROM pedido LIMIT 1),pro.id,(SELECT id FROM marca LIMIT 1),1,10.00
    FROM produto pro
   WHERE NOT EXISTS (SELECT 1 FROM pedidoprodutos where produto_id=pro.id);
*/	