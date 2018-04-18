package br.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

public class DescontoTest {
    
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Sala sala = new Sala("Vip",new BigDecimal("20.5"));
		Filme filme = new Filme("Titanic", Duration.ofMinutes(120), "Drama",new BigDecimal("12"));
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"),filme,sala);
		Ingresso ingresso = new Ingresso(sessao,new SemDesconto());
		BigDecimal precoEsperado = new BigDecimal("32.50");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
}
