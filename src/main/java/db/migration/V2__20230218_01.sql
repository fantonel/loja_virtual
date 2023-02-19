CREATE TABLE IF NOT EXISTS pessoafisica(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    datanascto TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    dthrcadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nome CHARACTER VARYING(120) COLLATE pg_catalog."default",
    CONSTRAINT pessoafisica_pk PRIMARY KEY (id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS pessoafisica OWNER to postgres;

CREATE TABLE IF NOT EXISTS pessoajuridica(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    cnpj CHARACTER VARYING(14) COLLATE pg_catalog."default" NOT NULL,
    email_principal CHARACTER VARYING(120) COLLATE pg_catalog."default" NOT NULL,
    ie CHARACTER VARYING(20) COLLATE pg_catalog."default" NOT NULL,
    im CHARACTER VARYING(20) COLLATE pg_catalog."default",
    nome_fantasia CHARACTER VARYING(120) COLLATE pg_catalog."default" NOT NULL,
    razao_social CHARACTER VARYING(120) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pessoajuridica_pk PRIMARY KEY (id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS pessoajuridica OWNER to postgres;

CREATE TABLE IF NOT EXISTS usuario(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    dataatualizacao date NOT NULL,
    login CHARACTER VARYING(120) COLLATE pg_catalog."default" NOT NULL,
    senha CHARACTER VARYING(255) COLLATE pg_catalog."default" NOT NULL,
    pessoa_id uuid,
    empresa_id uuid,
    CONSTRAINT usuario_pk PRIMARY KEY (id),    
    CONSTRAINT usuario_fk01 FOREIGN KEY (pessoa_id) REFERENCES pessoafisica (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT usuario_fk02 FOREIGN KEY (empresa_id) REFERENCES pessoajuridica (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT usuario_idx01 UNIQUE (login)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS usuario OWNER to postgres;

CREATE TABLE IF NOT EXISTS acesso(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    descricao CHARACTER VARYING(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT acesso_pk PRIMARY KEY (id),
    CONSTRAINT acesso_idx01 UNIQUE (descricao)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS acesso OWNER to postgres;

CREATE TABLE IF NOT EXISTS usuarioacessos(
    usuario_id uuid NOT NULL,
    acesso_id uuid NOT NULL,
    CONSTRAINT usuarioacessos_pk UNIQUE (usuario_id, acesso_id),
    CONSTRAINT usuarioacessos_fk01 FOREIGN KEY (usuario_id) REFERENCES usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT usuarioacessos_fk02 FOREIGN KEY (acesso_id) REFERENCES acesso (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS usuarioacessos OWNER to postgres;

-- FUNCTION: public.func_remove_jpa_constraint_error()
-- DROP FUNCTION IF EXISTS func_remove_jpa_constraint_error();
CREATE OR REPLACE FUNCTION func_remove_jpa_constraint_error(
	)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$

 DECLARE
  v_constraint_name varchar(100);
BEGIN
  SELECT ctu.constraint_name INTO v_constraint_name
    FROM information_schema.constraint_column_usage ctu
   WHERE table_name='usuario_acessos'
     AND constraint_name <> 'acesso_usuario_pk'
	 AND constraint_name NOT ILIKE '%usuario_fk%';
  IF (v_constraint_name IS NOT NULL) THEN
    -- raise exception '%',v_constraint_name;
  	EXECUTE 'ALTER TABLE IF EXISTS usuario_acessos DROP CONSTRAINT IF EXISTS '||v_constraint_name;	
    -- RAISE EXCEPTION 'Nonexistent ID --> %', v_constraint_name USING HINT = 'Please check your user ID';	
	RETURN true;
  END IF;
  RETURN false;  
END;
$BODY$;
ALTER FUNCTION func_remove_jpa_constraint_error() OWNER TO postgres;

