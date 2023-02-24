ALTER TABLE pessoafisica ADD COLUMN cpf CHARACTER VARYING(11);
ALTER TABLE pessoafisica ADD COLUMN rg CHARACTER VARYING(9);
ALTER TABLE pessoafisica ADD COLUMN email CHARACTER VARYING(120);
ALTER TABLE pessoafisica ADD COLUMN excluida BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE IF EXISTS public.pessoafisica DROP COLUMN IF EXISTS dthrcadastro;

CREATE UNIQUE INDEX pessoafisica_unique_01 ON pessoafisica(cpf);
CREATE UNIQUE INDEX pessoafisica_unique_02 ON pessoafisica(email);

ALTER TABLE pessoajuridica ADD COLUMN excluida BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE IF EXISTS public.pessoajuridica RENAME email_principal TO emailprincipal;

ALTER TABLE IF EXISTS public.pessoajuridica RENAME nome_fantasia TO nomefantasia;

ALTER TABLE IF EXISTS public.pessoajuridica RENAME razao_social TO razaosocial;

INSERT INTO pessoajuridica(id,cnpj,emailprincipal,ie,im,nomefantasia,razaosocial,excluida)
     VALUES ((SELECT uuid_generate_v4()),'71179493000117','empresamodelo@gmail.com','053665823030',null,'Empresa Modelo','Empresa Modelo Ltda.',false);