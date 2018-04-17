package br.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

public class GerenciadorDeSessaoTest {
    private Filme rogueOne;
    private Sala sala3D;
    private Sessao sessaoDasDez;
    private Sessao sessaoDasTreze;
    private Sessao sessaoDasDezoito;
    
    @Before
    public void preparaSessoes() {
    	this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "sci-fi");
    	this.sala3D = new Sala("Sala 3D");
    	
    	this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"),rogueOne,sala3D);
    	this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"),rogueOne,sala3D);
    	this.sessaoDasDezoito = new Sessao(LocalTime.parse("18:00:00"),rogueOne,sala3D);
    }
    
    @Test
    public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
    	List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
    	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
    	Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
    }
    
    @Test
    public void garanteQueNaoDevePermitirSessoesTerminandoDentHorarioDeUmaSessaoJaExistente() {
    	List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
    	Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1),rogueOne,sala3D);
    	
    	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
    	Assert.assertFalse(gerenciador.cabe(sessao));
    }
    
    @Test
    public void garanteQueNaoDevePermitirSessoesIniciandoDentHorarioDeUmaSessaoJaExistente() {
    	List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
    	
    	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
    	Sessao sessao = new Sessao(sessaoDasDez.getHorario().plusHours(1),rogueOne,sala3D);
    	
    	Assert.assertFalse(gerenciador.cabe(sessao));
    }
    
    @Test
    public void garanteQueNaoDevePermitirUmaInsercaoEntreDoisFilmes() {
    	List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
    	
    	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
    	
    	Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
    }
}