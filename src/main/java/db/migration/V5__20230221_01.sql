-- DROP TABLE IF EXISTS formapagamento;
CREATE TABLE formapagamento(
 id UUID NOT NULL DEFAULT uuid_generate_v4(),
 formapagamento CHARACTER VARYING(60) NOT NULL,
 CONSTRAINT  formapagamento_pk PRIMARY KEY(id),
 CONSTRAINT  formapagamento_unique_01 UNIQUE(formapagamento) 
);
ALTER TABLE formapagamento OWNER TO postgres;
INSERT INTO formapagamento(formapagamento) VALUES ('BOLETO BANCÁRIO'),('CARTÃO DE CRÉDITO'),('CARTÃO DE DÉBITO'),('PIX');

-- DROP TABLE IF EXISTS cupomdesconto;
CREATE TABLE cupomdesconto(
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  codigo VARCHAR(20) NOT NULL,
  descricao VARCHAR(120) NOT NULL,
  percentual NUMERIC(5,2) DEFAULT 0.00,
  valor NUMERIC(10,5) DEFAULT 0.00,
  validadeinicial DATE NOT NULL,
  validadefinal DATE NOT NULL,
  CONSTRAINT cupomdesconto_pk PRIMARY KEY(id),
  CONSTRAINT cupomdesconto_unique_01 UNIQUE(codigo)
);
ALTER TABLE formapagamento OWNER TO postgres;
INSERT INTO cupomdesconto(codigo,descricao,percentual,validadeinicial,validadefinal) 
     VALUES ('FOLIA5%','CARNAVAL DE OFERTAS, 5% PARA PAGAMETNO À VISTA',5.00,'2023-02-01'::DATE,'2023-02-21'::DATE),
	        ('PASCOA10%','PÁSCOA DE DESCONTO, 10% PARA PAGAMETNO À VISTA',10.00,'2023-04-01'::DATE,'2023-04-09'::DATE);