-- DROP TABLE IF EXISTS public.boleto;
CREATE TABLE IF NOT EXISTS public.boleto(
    id uuid NOT NULL,
    idpix character varying(255),
    chargeicartao character varying(255),
    checkouturl character varying(255),
    code character varying(10),
    datavencimento character varying(255),
    idchrboleto character varying(255),
    imageinbase64 text,
    installment_link character varying(255),
    link character varying(255),
    payloadinbase64 text,
    quitado boolean,
    recorrencia integer,
    valor numeric(19,2),
    empresa_id uuid NOT NULL,
    pedido_id uuid NOT NULL,
    CONSTRAINT boleto_pkey PRIMARY KEY (id),
    CONSTRAINT boleto_fk001 FOREIGN KEY (pedido_id)
        REFERENCES public.pedido (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT boleto_fk002 FOREIGN KEY (empresa_id)
        REFERENCES public.empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.boleto OWNER to postgres;