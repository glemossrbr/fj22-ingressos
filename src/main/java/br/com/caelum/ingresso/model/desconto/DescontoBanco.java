package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto{
	@Override
	 public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		 return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}

	private BigDecimal trintaPorCentoSobre(BigDecimal valor) {
		return valor.multiply(new BigDecimal("0.3"));
	}
}
