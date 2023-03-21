-- DROP TABLE IF EXISTS pais;
CREATE TABLE pais(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	codigoiso VARCHAR(2) NOT NULL,
	pais CHARACTER VARYING(120) NOT NULL,
	CONSTRAINT pais_pk PRIMARY KEY(id),
	CONSTRAINT pais_unique_01 UNIQUE(codigoiso)
);
ALTER TABLE pais OWNER TO postgres;
INSERT INTO pais(codigoiso,pais)
 SELECT 'BR','Brasil' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='BR')
 UNION SELECT 'AR','Argengina' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='AR')
 UNION SELECT 'BO','Bolívia' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='BO') 
 UNION SELECT 'PE','Peru' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='PE') 
 UNION SELECT 'VE','Venezuale' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='VE') 
 UNION  SELECT 'CO','Colômbia' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='CO') 
 UNION SELECT 'PY','Paraguai' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='PY') 
 UNION SELECT 'UY','Uruguai' WHERE NOT EXISTS (SELECT 1 FROM pais WHERE codigoiso='UY'); 

-- DROP TABLE IF EXISTS pedido;
CREATE TABLE pedido(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	pessoafisica_id UUID,
	pessoajuridica_id UUID,
	enderecocobranca_id UUID NOT NULL,
	enderecoentrega_id UUID NOT NULL,
	transportadora_id UUID,
	codigorastreio CHARACTER VARYING(200) NOT NULL,	
	cupomdesconto_id UUID NOT NULL,	
	desconto NUMERIC(10,4) NOT NULL DEFAULT 0.00,
	valor NUMERIC(10,4) NOT NULL DEFAULT 0.00,
	valorcomdescto NUMERIC(10,4) NOT NULL DEFAULT 0.00,
	frete NUMERIC(10,4) NOT NULL DEFAULT 0.00,	
	valorcomfrete NUMERIC(10,4) NOT NULL DEFAULT 0.00,
	datapedido DATE NOT NULL,
	CONSTRAINT pedido_pk PRIMARY KEY(id),
	CONSTRAINT pedido_fk01 FOREIGN KEY(pessoafisica_id) REFERENCES pessoafisica(id),
	CONSTRAINT pedido_fk02 FOREIGN KEY(pessoajuridica_id) REFERENCES pessoajuridica(id),
	CONSTRAINT pedido_fk03 FOREIGN KEY(enderecocobranca_id) REFERENCES endereco(id),
	CONSTRAINT pedido_fk04 FOREIGN KEY(enderecoentrega_id) REFERENCES endereco(id),
	CONSTRAINT pedido_fk05 FOREIGN KEY(cupomdesconto_id) REFERENCES cupomdesconto(id)
);
ALTER TABLE pedido OWNER TO postgres;

-- DROP TABLE IF EXISTS pedidoitens;
CREATE TABLE pedidoitens(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	pedido_id UUID NOT NULL,
	produto_id UUID NOT NULL,
	qtde NUMERIC(5) NOT NULL DEFAULT 1,
	valor NUMERIC(10,4) NOT NULL,
	CONSTRAINT pedidoitens_pk PRIMARY KEY(id),
	CONSTRAINT pedidoitens_fk01 FOREIGN KEY(pedido_id) REFERENCES pedido(id),
	CONSTRAINT pedidoitens_fk02 FOREIGN KEY(produto_id) REFERENCES produto(id),
	CONSTRAINT pedidoitens_unique_01 UNIQUE(pedido_id,produto_id)
);
ALTER TABLE pedidoitens OWNER TO postgres;

-- DROP TABLE IF EXISTS pedidorastreio;
CREATE TABLE pedidorastreio(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	pedido_id UUID NOT NULL,
	status CHARACTER VARYING(60) NOT NULL,
	datahora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	CONSTRAINT pedidorastreio_pk PRIMARY KEY(id),
	CONSTRAINT pedidorastreio_fk01 FOREIGN KEY(pedido_id) REFERENCES pedido(id)
);
ALTER TABLE pedidorastreio OWNER TO postgres;

