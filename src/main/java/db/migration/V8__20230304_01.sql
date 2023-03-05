-- DROP TABLE IF EXISTS unidademedida;
CREATE TABLE unidademedida(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	sigla CHARACTER VARYING(10) NOT NULL,
	descricao  CHARACTER VARYING(60) NOT NULL,
	CONSTRAINT unidademedida_pk PRIMARY KEY(id),
	CONSTRAINT unidademedida_unique_01 UNIQUE(sigla)
);
ALTER TABLE unidademedida OWNER TO postgres;

INSERT INTO unidademedida VALUES ('6eeedf68-dda6-4058-95cb-513a9daa8637','Peça','Peça');
INSERT INTO unidademedida VALUES ('1a06d5e3-00e1-4454-8bad-5f97a528501b','Unidade','Unidade');
INSERT INTO unidademedida VALUES ('a2e95e0b-0680-40e9-9b33-1fe17658a4e5','k','quilo');
INSERT INTO unidademedida VALUES ('2f6f100a-85a7-490c-b01d-4feddf92cef5','kg','quilograma');
INSERT INTO unidademedida VALUES ('7725d061-99ba-4cd4-aea0-98ea2937edd5','grama','grama');
INSERT INTO unidademedida VALUES ('660fc4e6-12b6-4dbf-b2d4-e58464537c09','m','metro');
INSERT INTO unidademedida VALUES ('c65ef8e6-3302-4e6b-b7ce-98be54340d8d','cm','centímetro');
INSERT INTO unidademedida VALUES ('1efcd43e-3658-4d20-af68-de7cfa896747','mm','Milímetro');
INSERT INTO unidademedida VALUES ('73729c1b-bf66-4111-adec-a24406933135','l','litro');
INSERT INTO unidademedida VALUES ('5b8b7b25-28af-4f8a-99f8-8adabdced13a','ml','mililitro');

-- DROP TABLE IF EXISTS produto;
CREATE TABLE produto(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	nome CHARACTER VARYING(120) NOT NULL,
	descricao CHARACTER VARYING(2000),
	numerovisualizacoes INTEGER NOT NULL DEFAULT 0.00,
	linkyoutube CHARACTER VARYING(200),
	marca_id UUID NOT NULL,
	CONSTRAINT produto_pk PRIMARY KEY(id),
	CONSTRAINT produto_fk02 FOREIGN KEY(marca_id) REFERENCES marca(id)
);
ALTER TABLE produto OWNER TO postgres;

-- DROP TABLE IF EXISTS produtocategoria;
CREATE TABLE produtocategoria(
	produto_id UUID NOT NULL,
	categoria_id UUID NOT NULL,
	CONSTRAINT produtocategoria_pk PRIMARY KEY(produto_id,categoria_id),
	CONSTRAINT produtocategoria_fk01 FOREIGN KEY(produto_id) REFERENCES produto(id),
	CONSTRAINT produtocategoria_fk02 FOREIGN KEY(categoria_id) REFERENCES categoria(id)
);
ALTER TABLE produtocategoria OWNER TO postgres;

-- DROP TABLE IF EXISTS produtoconfiguracao;
CREATE TABLE produtoconfiguracao(
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	cor CHARACTER VARYING(15),
	altura NUMERIC(7,3) NOT NULL DEFAULT 0.00,
	largura NUMERIC(7,3) NOT NULL DEFAULT 0.00,
	profundidade NUMERIC(7,3) NOT NULL DEFAULT 0.00,
	peso NUMERIC(7,3) NOT NULL DEFAULT 0.00,
	estoqueatual NUMERIC(10,3) NOT NULL DEFAULT 0.00,
	estoqueminimo NUMERIC(10,3) NOT NULL DEFAULT 0.00,
	unidademedida_id UUID NOT NULL,
	numerovisualizacoes INTEGER NOT NULL DEFAULT 0.00,
	CONSTRAINT produtoconfiguracao_pk PRIMARY KEY(id),
	CONSTRAINT produtoconfiguracao_fk01 FOREIGN KEY(unidademedida_id) REFERENCES unidademedida(id)
);
ALTER TABLE produtoconfiguracao OWNER TO postgres;

INSERT INTO marca (id,marca)
 SELECT 'f09126b2-d291-41e4-999d-86356859ea6f'::UUID,'Timboré Acqua' WHERE NOT EXISTS (SELECT 1 FROM marca WHERE id = 'f09126b2-d291-41e4-999d-86356859ea6f'::UUID);
 
INSERT INTO categoria (id,categoria)
 SELECT '74304ccb-c8df-4366-ab87-aa9c0d8a438d'::UUID id,'BEACHWEAR' descricao
 WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE id = '74304ccb-c8df-4366-ab87-aa9c0d8a438d'::UUID)
 UNION
 SELECT '5443d0b1-9e2b-49fa-bb02-6683d74217a0'::UUID id,'FITNESS' descricao
 WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE id ='5443d0b1-9e2b-49fa-bb02-6683d74217a0'::UUID);


INSERT INTO produto (id,nome,descricao,numerovisualizacoes,linkyoutube,marca_id)
 VALUES ('430d5257-5b38-4ee3-aa77-2009340d0a65','Legging Fusô Training Basic Suplex',
 'Unindo o conforto e sofisticação, este modelo veio para mostrar sua funcionalidade. trazendo uma modelagem que valoriza o corpo, esta peça traz feminilidade e comodidade à quem usa..Com um detalhe do bolso embutido no cós, esta calça compõe facilmente qualquer look, desde a academia a eventos mais descontraídos!
Esta peça pode ser usada no dia a dia ou na prática de atividades físicas.
Tecido: Suplex
Composição: 85% Poliamida 15% Elastano
O Suplex é um fibra bastante flexível, durável, resistente e macia. É ideal para quem pratica atividades físicas que requer liberdade de movimento.
O que mais chama atenção no Suplex com certeza é o toque macio, as peças nesse tecido são muito boas de pegar. Outro ponto forte é a aderência ao corpo e o conforto térmico.
As peças em Suplex não criam odores, são de fácil lavagem e não desbotam. Por essas e outras características, o Suplex é o queridinho de quem treina prezando conforto e qualidade.',
0,'https://www.timbore.com/legging-fuso-training-basic-suplex','8ed38ce5-fba6-46c5-970d-f20fff37fdcc'
),
('5507476a-55f4-46a7-9c24-d076bcc43c2a','Short Runner Timboré',
'Short super comfy e atual fabricado em tecido leve e macio de excelência elasticidade e toque geladinho. Modelo super versátil com mood despojado que pode ser usado dentro e fora da academia. Possui bolso com zíper e shortinho interno garantindo conforto e segurança. Detalhe do logo Timboré em termocolante.
Tecido Interno : Acqua Light 
Composição: 90% Poliamida 10% Elastano 
Tecido Externo : Dry 
Composição: 88% Poliamida 12% Elastano ',
0,'https://www.timbore.com/short-runner-timbore','8ed38ce5-fba6-46c5-970d-f20fff37fdcc'
),
('e8c5049d-4d8e-4e41-a7ae-bd76c94f2691','Saída de Praia Camisa Leisure',
'Saída de Praia Camisa Leisure',
0,'https://www.timbore.com/saida-chic-classic','f09126b2-d291-41e4-999d-86356859ea6f'
);

INSERT INTO produtocategoria (produto_id,categoria_id) 
 SELECT '430d5257-5b38-4ee3-aa77-2009340d0a65','5443d0b1-9e2b-49fa-bb02-6683d74217a0' WHERE NOT EXISTS (SELECT 1 FROM produtocategoria WHERE produto_id = '430d5257-5b38-4ee3-aa77-2009340d0a65' AND categoria_id = '5443d0b1-9e2b-49fa-bb02-6683d74217a0');
INSERT INTO produtocategoria (produto_id,categoria_id) 		 
 SELECT '5507476a-55f4-46a7-9c24-d076bcc43c2a','5443d0b1-9e2b-49fa-bb02-6683d74217a0' WHERE NOT EXISTS (SELECT 1 FROM produtocategoria WHERE produto_id = '5507476a-55f4-46a7-9c24-d076bcc43c2a' AND categoria_id = '5443d0b1-9e2b-49fa-bb02-6683d74217a0');
INSERT INTO produtocategoria (produto_id,categoria_id) 		 
 SELECT 'e8c5049d-4d8e-4e41-a7ae-bd76c94f2691','74304ccb-c8df-4366-ab87-aa9c0d8a438d' WHERE NOT EXISTS (SELECT 1 FROM produtocategoria WHERE produto_id = 'e8c5049d-4d8e-4e41-a7ae-bd76c94f2691' AND categoria_id = '74304ccb-c8df-4366-ab87-aa9c0d8a438d');

-- DROP VIEW IF EXISTS view_produtocategoriaview;
CREATE OR REPLACE VIEW view_produtocategoriaview AS
SELECT pro.id,pro.nome,pro.descricao,pro.numerovisualizacoes,pro.linkyoutube,
       pro.marca_id,mar.marca,
	   proCat.categoria_id,cat.categoria
  FROM produto pro INNER JOIN marca mar ON (mar.id = pro.marca_id)
                   INNER JOIN produtocategoria proCat ON (proCat.produto_id = pro.id) 
				   INNER JOIN categoria cat ON (cat.id = proCat.categoria_id);
ALTER TABLE view_produtocategoriaview OWNER TO postgres;
