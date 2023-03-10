-- DROP VIEW IF EXISTS produtocategoria_view;
CREATE OR REPLACE VIEW produtocategoria_view AS
 SELECT pro.*,
        proCat.produto_id,
		proCat.categoria_id,
		cat.categoria
   FROM produtocategoria proCat INNER JOIN produto pro ON (pro.id = proCat.produto_id)
                                INNER JOIN categoria cat ON (cat.id = proCat.categoria_id);
ALTER TABLE produtocategoria_view OWNER TO postgres;