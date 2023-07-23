ALTER TABLE pedido ADD CONSTRAINT pedido_fk06 FOREIGN KEY(transportadora_id) REFERENCES transportadora(id);

ALTER TABLE transportadora ADD CONSTRAINT transportadora_fk01 FOREIGN KEY (pessoajuridica_id) REFERENCES pessoajuridica(id);  

INSERT INTO pessoajuridica(id,cnpj,emailprincipal,ie,im,nomefantasia,razaosocial,excluida) 
 SELECT (uuid_generate_v4()),'','','','','Correios','Empresa Brasileira de Correios e Telégrafos',false
  WHERE NOT EXISTS (SELECT 1 FROM pessoajuridica WHERE nomefantasia='Correios');
  
INSERT INTO pessoajuridica(id,cnpj,emailprincipal,ie,im,nomefantasia,razaosocial,excluida) 
 SELECT (uuid_generate_v4()),'','','','','JadLog','JadLog',false
  WHERE NOT EXISTS (SELECT 1 FROM pessoajuridica WHERE nomefantasia='JadLog');

INSERT INTO transportadora(id,pessoajuridica_id,ativa)
 SELECT (uuid_generate_v4()),pesjur.id,TRUE
   FROM pessoajuridica pesjur
  WHERE pesjur.nomefantasia='Correios'
    AND NOT EXISTS (SELECT 1 FROM transportadora WHERE pessoajuridica_id = pesjur.id);
    
INSERT INTO transportadora(id,pessoajuridica_id,ativa)
 SELECT (uuid_generate_v4()),pesjur.id,TRUE
   FROM pessoajuridica pesjur
  WHERE pesjur.nomefantasia='JadLog'
    AND NOT EXISTS (SELECT 1 FROM transportadora WHERE pessoajuridica_id = pesjur.id);
