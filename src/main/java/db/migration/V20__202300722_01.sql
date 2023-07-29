ALTER TABLE pessoafisica ADD COLUMN telefoneprincipal VARCHAR(11);
ALTER TABLE pessoajuridica ADD COLUMN telefoneprincipal VARCHAR(11);

DROP TABLE IF EXISTS melhorenvio;
CREATE TABLE melhorenvio(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	pedido_id UUID NOT NULL,	
	transportadora_id UUID,
	melhorenvio_freteservice NUMERIC(2),
	melhorenvio_freteagency NUMERIC(2),
	melhorenvio_freteid UUID,
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

INSERT INTO melhorenvio(id,pedido_id,transportadora_id,melhorenvio_freteservice,melhorenvio_freteagency)
 SELECT (uuid_generate_v4()),ped.id,ped.transportadora_id,3,49
   FROM pedido ped
  WHERE NOT EXISTS (SELECT 1 FROM melhorenvio me WHERE me.pedido_id = ped.id);
  
DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	pessoajuridica_id UUID NOT NULL,
	ativa BOOLEAN NOT NULL DEFAULT true,
	CONSTRAINT empresa_pk PRIMARY KEY(id),
	CONSTRAINT empresa_fk01 FOREIGN KEY(pessoajuridica_id) REFERENCES pessoajuridica(id)
);
ALTER TABLE empresa OWNER TO postgres;
  
INSERT INTO empresa(id,pessoajuridica_id,ativa)
  SELECT (uuid_generate_v4()),pesj.id,true
    FROM pessoajuridica pesj
   WHERE pesj.nomefantasia = 'Empresa Modelo'
     AND NOT EXISTS (SELECT 1 FROM empresa emp WHERE emp.pessoajuridica_id = pesj.id);

ALTER TABLE IF EXISTS pedido ADD COLUMN empresa_id UUID;     
UPDATE pedido SET empresa_id=(SELECT emp.id FROM empresa emp) WHERE empresa_id IS NULL;
ALTER TABLE pedido ADD CONSTRAINT pedido_fk07 FOREIGN KEY(empresa_id) REFERENCES empresa(id);
ALTER TABLE IF EXISTS public.pedido ALTER COLUMN empresa_id SET NOT NULL;

INSERT INTO endereco(id,cep,tipologradouro,logradouro,numero,complemento,bairro,localidade,uf,tipoendereco,empresa_id)
 SELECT (uuid_generate_v4()),'16400-101','RUA','Floriano Peixoto','0000',null,'Centro','Lins','SP','ENVIO','7ddf222a-7bc5-4a15-823f-1c7f79065754'
  FROM pessoajuridica pj
 WHERE pj.nomefantasia = 'Empresa Modelo'
   AND NOT EXISTS (SELECT 1 FROM endereco ender WHERE ender.empresa_id = pj.id);
   
INSERT INTO produtoconfiguracao(id,produto_id,cor,altura,largura,profundidade,peso,estoqueatual,estoqueminimo,unidademedida_id,numerovisualizacoes)
  SELECT (uuid_generate_v4()) AS id, pro.id AS produto_id,'#D3D3D3',0.00,0.00,0.00,0.00,100,30,
         (SELECT un.id FROM unidademedida un WHERE un.sigla='Peça'),0
    FROM produto pro
   WHERE NOT EXISTS (SELECT 1 FROM produtoconfiguracao pc WHERE pc.produto_id = pro.id);
   
ALTER TABLE pedidoprodutos ADD COLUMN produtoconfiguracao_id UUID;
ALTER TABLE pedidoprodutos ADD CONSTRAINT pedidoproduto_fk03 FOREIGN KEY(produtoconfiguracao_id) REFERENCES produtoconfiguracao(id);
UPDATE pedidoprodutos pp SET produtoconfiguracao_id = (SELECT pconf.id FROM produtoconfiguracao pconf WHERE pconf.produto_id = pp.produto_id LIMIT 1);

-- DROP TABLE IF EXISTS public.notafiscalvenda;
CREATE TABLE IF NOT EXISTS public.notafiscalvenda(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    descricao character varying(2000) COLLATE pg_catalog."default",
    datavenda timestamp without time zone NOT NULL,
    dataemissao timestamp without time zone NOT NULL,
    numero character varying(60) COLLATE pg_catalog."default" NOT NULL,
    serie character varying(15) COLLATE pg_catalog."default" NOT NULL,
    valor numeric(10,4) NOT NULL DEFAULT 0.00,
    icms numeric(10,4) NOT NULL DEFAULT 0.00,
    desconto numeric(10,4) NOT NULL DEFAULT 0.00,
    total numeric(10,4) NOT NULL DEFAULT 0.00,
    pessoajuridica_id uuid,
    pessoafisica_id uuid,
    pedido_id uuid,
    CONSTRAINT notafiscalvenda_pk PRIMARY KEY (id),
    CONSTRAINT notafiscalvenda_unique01 UNIQUE (pessoafisica_id, numero),
    CONSTRAINT notafiscalvenda_unique021 UNIQUE (pessoajuridica_id, numero),
    CONSTRAINT notafiscalvenda_fk01 FOREIGN KEY (pessoafisica_id) REFERENCES public.pessoafisica (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT notafiscalvenda_fk02 FOREIGN KEY (pessoajuridica_id) REFERENCES public.pessoajuridica (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT notafiscalvenda_fk03 FOREIGN KEY (pedido_id) REFERENCES public.pedido (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.notafiscalvenda OWNER to postgres;

INSERT INTO notafiscalvenda(id,descricao,datavenda,dataemissao,numero,serie,valor,icms,desconto,total,pedido_id)
 SELECT (uuid_generate_v4()),'TESTE',ped.datapedido,ped.datapedido,'31190307586261000184550010000092481404848162','1','199.5500',0,0,199.5500,ped.id
   FROM pedido ped
  WHERE NOT EXISTS (SELECT 1 FROM notafiscalvenda nfv WHERE nfv.pedido_id = ped.id);

update pessoajuridica set telefoneprincipal='14123459874' where telefoneprincipal is null;
update pessoafisica set telefoneprincipal='18999991234' where telefoneprincipal is null;
