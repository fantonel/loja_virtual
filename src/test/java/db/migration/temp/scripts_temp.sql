DROP TABLE IF EXISTS melhorenvio;
CREATE TABLE melhorenvio(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	pedido_id UUID NOT NULL,	
	transportadora_id UUID,
	melhorenvio_freteservice NUMERIC(2),
	melhorenvio_freteagency NUMERIC(2),
	melhorenvio_freteid NUMERIC(2),
	melhorenvio_fretename CHARACTER VARYING(120),
	melhorenvio_freteprice NUMERIC(10,4),
	melhorenvio_fretecustomprice NUMERIC(10,4),
	melhorenvio_fretedeliverytime NUMERIC(4),
	melhorenvio_fretecustomdeliverytime NUMERIC(4),
	melhorenvio_inserirfreteid CHARACTER VARYING(50),
	melhorenvio_comprarfreteid CHARACTER VARYING(50),
	melhorenvio_urletiqueta CHARACTER VARYING(256),
	melhorenvio_json_consultafrete TEXT, -- será alterado posteriormente, armazenados em banco NoSql;
	melhorenvio_json_inserefrete TEXT,   -- será alterado posteriormente, armazenados em banco NoSql;
	melhorenvio_json_comprafrete TEXT,   -- será alterado posteriormente, armazenados em banco NoSql;
	CONSTRAINT melhorenvio_pk PRIMARY KEY(id),
	CONSTRAINT melhorenvio_fk01 FOREIGN KEY(pedido_id) REFERENCES pedido(id),
	CONSTRAINT melhorenvio_fk02 FOREIGN KEY(transportadora_id) REFERENCES transportadora(id)
);
ALTER TABLE IF EXISTS melhorenvio OWNER to postgres;
COMMENT ON COLUMN public.melhorenvio.melhorenvio_inserirfreteid IS 'id do frete escolhi que fora inserido nos sistema da API, a partir da opção selecionada na consulta dos fretes.';
COMMENT ON COLUMN public.melhorenvio.melhorenvio_comprarfreteid IS 'id da compra do frete escolhido.';
COMMENT ON COLUMN public.melhorenvio.melhorenvio_urletiqueta IS 'url para geração da(s) etiqueta(s) dos produtos constantes da compra do frete.';
COMMENT ON COLUMN public.melhorenvio.melhorenvio_freteservice IS 'É o identificador do serviço (opção de frete - Correios, Jadlog, etc) escolhido pelo cliente';
COMMENT ON COLUMN public.melhorenvio.melhorenvio_freteagency IS 'Para algumas empresas (jadlog p.e), refere-se a agência que irá coletar os produtos';

INSERT INTO melhorenvio(id,pedido_id,transportadora_id)
 SELECT (SELECT uuid_generate_v4()),ped.id,ped.transportadora_id
   FROM pedido ped
  WHERE NOT EXISTS (SELECT 1 FROM melhorenvio me WHERE me.pedido_id = ped.id);  

UPDATE pedidoprodutos SET produtoconfiguracao_id = NULL;
UPDATE notafiscalvenda SET pedido_id = null;
DROP TABLE IF EXISTS public.notafiscalvenda;
ALTER TABLE IF EXISTS pedidoprodutos DROP CONSTRAINT IF EXISTS pedidoproduto_fk03;
ALTER TABLE IF EXISTS pedidoprodutos DROP COLUMN IF EXISTS produtoconfiguracao_id;

ALTER TABLE IF EXISTS pessoafisica DROP COLUMN IF EXISTS telefoneprincipal;
ALTER TABLE IF EXISTS pessoajuridica DROP COLUMN IF EXISTS telefoneprincipal;
ALTER TABLE IF EXISTS pedido DROP CONSTRAINT IF EXISTS pedido_fk07;
ALTER TABLE IF EXISTS pedido ALTER COLUMN empresa_id DROP NOT NULL;
ALTER TABLE IF EXISTS pedido DROP COLUMN empresa_id;
DROP TABLE IF EXISTS empresa;
ALTER TABLE IF EXISTS pessoafisica DROP COLUMN IF EXISTS telefoneprincipal;
DROP TABLE IF EXISTS melhorenvio;
delete from flyway_schema_history where installed_rank=20;
ALTER TABLE IF EXISTS public.pedido DROP COLUMN IF EXISTS pedido_id;
