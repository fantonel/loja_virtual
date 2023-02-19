INSERT INTO acesso(id,descricao)
 SELECT (uuid_generate_v4()),'ROLE_ADMIN'
  WHERE NOT EXISTS (SELECT 1 FROM acesso ace WHERE ace.descricao='ROLE_ADMIN')
 UNION
 SELECT (uuid_generate_v4()),'ROLE_USER'
  WHERE NOT EXISTS (SELECT 1 FROM acesso ace WHERE ace.descricao='ROLE_USER');

INSERT INTO pessoafisica(id,datanascto,dthrcadastro,nome)
 SELECT (uuid_generate_v4()),'1979-08-22'::DATE,CURRENT_DATE,'Usuário Modelo'
  WHERE NOT EXISTS (SELECT 1 FROM pessoafisica pef WHERE pef.nome='Usuário Modelo');
  
INSERT INTO usuario(id,login,senha,pessoa_id,dataatualizacao)
 SELECT (uuid_generate_v4()),'usuariomodelo@gmail.com','$2a$10$8DIrtOVIqwVD2Femtgd73uIQBVvU6Q/yRVMZmNQzBdOPlbYOZIawK',
         (SELECT id FROM pessoafisica pef WHERE pef.nome='Usuário Modelo'),CURRENT_DATE
  WHERE NOT EXISTS (SELECT 1 FROM usuario usu WHERE usu.pessoa_id=(SELECT id FROM pessoafisica pef WHERE pef.nome='Usuário Modelo'));
  
INSERT INTO usuarioacessos(usuario_id,acesso_id)
 SELECT id,(SELECT id FROM acesso ace WHERE ace.descricao='ROLE_ADMIN')
   FROM usuario usu  
  WHERE usu.pessoa_id =(SELECT id FROM pessoafisica pef  WHERE pef.nome='Usuário Modelo')
    AND NOT EXISTS (SELECT 1 
					  FROM usuarioacessos uace 
					 WHERE uace.usuario_id=usu.id
				   );