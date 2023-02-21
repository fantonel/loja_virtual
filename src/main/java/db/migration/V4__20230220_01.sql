-- DROP TABLE IF EXISTS marca;
CREATE TABLE marca(
 id UUID NOT NULL DEFAULT uuid_generate_v4(),
 marca CHARACTER VARYING(60) NOT NULL,
 CONSTRAINT marca_pk PRIMARY KEY(id),
 CONSTRAINT marca_unique_01 UNIQUE(marca)
);
ALTER TABLE marca OWNER TO postgres;
INSERT INTO marca(marca) VALUES ('Timboré'),('Atomic'),('Suzuka'),('Ellipse'),('Atlantic'),('Splendos'),('Happiness'),('Royale');

-- DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria(
 id UUID NOT NULL DEFAULT uuid_generate_v4(),
 categoria CHARACTER VARYING(60) NOT NULL,
 CONSTRAINT mcategoria_pk PRIMARY KEY(id),
 CONSTRAINT categoria_unique_01 UNIQUE(categoria)
);
ALTER TABLE IF EXISTS pessoafisica OWNER to postgres;
INSERT INTO categoria(categoria) VALUES ('Vestuário'),('Acessórios'),('Calçados');