ALTER TABLE IF EXISTS pedido ALTER COLUMN cupomdesconto_id DROP NOT NULL;
ALTER TABLE pedido ADD COLUMN datahoracadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW();
ALTER TABLE pedidoprodutos ADD COLUMN datahoracadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW();
ALTER TABLE pedidoprodutos ALTER COLUMN valordescto TYPE numeric(10,4);

-- DROP FUNCTION IF EXISTS proc_trg_pedidoprodutos_atualiza_valores();
CREATE OR REPLACE FUNCTION proc_trg_pedidoprodutos_atualiza_valores() returns trigger AS 
$BODY$
 DECLARE
    v_pedido 				pedido%ROWTYPE;	
	v_cupomDesconto 		cupomdesconto%ROWTYPE;
	v_pedidoprotudos 		pedidoprodutos%ROWTYPE;	
	v_percDescto			cupomdesconto.percentual%TYPE;
	v_valorDescto			cupomdesconto.percentual%TYPE;
	v_qtdeMultValor			pedidoprodutos.valor%TYPE;
	v_valorcalc				pedidoprodutos.valor%TYPE;
	v_valorfinal			pedidoprodutos.valorfinal%TYPE;	
	
	v_valorcomfrete			pedido.valorcomfrete%TYPE;
	v_sumValoDesctolItens	pedidoprodutos.valorfinal%TYPE;		
	v_sumValorParcialItens  pedidoprodutos.valorfinal%TYPE;
	
	v_sumValorFinalItens	pedidoprodutos.valorfinal%TYPE;
	v_pronome 				produto.nome%TYPE;
BEGIN
    v_percDescto    = 0.00;
	v_valorDescto   = 0.00;
	v_qtdeMultValor = 0.00;
    SELECT ped.* INTO v_pedido FROM pedido ped WHERE ped.id = NEW.pedido_id;	
	IF (v_pedido.id IS NOT NULL) THEN	   
		-- Soma o valor final de cada item do pedido
		UPDATE pedidoprodutos SET valorfinal = NEW.qtde * NEW.valor WHERE id = NEW.id;
		SELECT SUM(valorfinal) INTO v_sumValorFinalItens
		  FROM pedidoprodutos pp
		 WHERE pp.pedido_id = v_pedido.id;		
		-- Verifico a existência de cupom de desconto, para aplicar em cima do valor total do pedido.
	    IF (v_pedido.cupomdesconto_id IS NOT NULL) THEN
			SELECT * INTO v_cupomDesconto
			  FROM cupomdesconto cd
			 WHERE cd.id = v_pedido.cupomdesconto_id
			   AND CURRENT_DATE >= cd.validadeinicial AND (cd.validadefinal IS NULL OR CURRENT_DATE <= cd.validadefinal);
			 IF (v_cupomDesconto.id IS NOT NULL) THEN
			 	IF (v_cupomDesconto.percentual > 0.00) THEN
				    v_percDescto  = v_cupomDesconto.percentual;					
					v_valorcalc = v_sumValorFinalItens - (v_sumValorFinalItens * (v_cupomDesconto.percentual/100.00));
					v_valorDescto = v_sumValorFinalItens - v_valorcalc;					
				ELSIF (v_cupomDesconto.valor > 0.00) THEN				    
					v_valorDescto = v_cupomDesconto.valor;
					v_valorcalc = v_sumValorFinalItens - v_cupomDesconto.valor;
					v_percDescto = 100.00 - ((v_valorcalc / v_sumValorFinalItens) * 100);
				END IF;
			 END IF;
		END IF;		 
		UPDATE pedido SET desconto=v_valorDescto,valor=v_sumValorFinalItens,
		                  valorcomdescto= (v_sumValorFinalItens - v_valorDescto),
						  valorcomfrete = (v_sumValorFinalItens - v_valorDescto + v_pedido.frete) 
         WHERE id = v_pedido.id;
	ELSE
		RAISE EXCEPTION 'Pedido/Produto inválido(s), verifique!';
	END IF;
	
	RETURN NULL;	 
END
$BODY$
LANGUAGE 'plpgsql' VOLATILE;

CREATE TRIGGER trg_pedidoprodutos_atualiza_valores AFTER INSERT ON pedidoprodutos FOR EACH ROW EXECUTE PROCEDURE proc_trg_pedidoprodutos_atualiza_valores();
