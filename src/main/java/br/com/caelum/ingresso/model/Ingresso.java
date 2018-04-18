package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.caelum.ingresso.model.desconto.Desconto;

public class Ingresso {
  private Sessao sessao;
  private BigDecimal preco = BigDecimal.ZERO;

  public Ingresso() {
	  
  }
  
  public Ingresso(Sessao sessao, Desconto desconto) {
	  this.sessao = sessao;
	  this.preco = desconto.aplicaDesconto(sessao.getPreco());
  }

public BigDecimal getPreco() {
	return preco.setScale(2, RoundingMode.HALF_UP);
}

public void setPreco(BigDecimal preco) {
	this.preco = preco;
}
	
}
