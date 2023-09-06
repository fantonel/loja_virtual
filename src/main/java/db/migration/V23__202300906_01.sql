-- DROP TABLE IF EXISTS tokensapis;
CREATE TABLE IF NOT EXISTS tokensapis(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    apinome character varying(256) COLLATE pg_catalog."default" NOT NULL,
    apitokentype character varying(50) COLLATE pg_catalog."default" NOT NULL,
    apiaccesstoken text COLLATE pg_catalog."default" NOT NULL,
    apiusername character varying(50) COLLATE pg_catalog."default" NOT NULL,
    apiscope character varying(15) COLLATE pg_catalog."default" NOT NULL,
    apijti character varying(256) COLLATE pg_catalog."default" NOT NULL,
    apidatahora timestamp without time zone NOT NULL,
    apiexpiresin numeric(10,0) NOT NULL,
    CONSTRAINT tokensapis_pk PRIMARY KEY (id)
) TABLESPACE pg_default;
ALTER TABLE IF EXISTS tokensapis OWNER to postgres;