package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

    private List<Sessao> sessoesDaSala;
    
    public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
    	this.sessoesDaSala = sessoesDaSala;
    }
    
    private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
    	LocalDateTime horarioNovaSessao = sessaoNova.getHorario().atDate(LocalDate.now());
    	LocalDateTime terminoNovaSessao = sessaoNova.getHorarioTermino().atDate(LocalDate.now());
    	LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(LocalDate.now());
    	LocalDateTime terminoSessaoExistente = sessaoExistente.getHorarioTermino().atDate(LocalDate.now());
    
    	
    	boolean terminaAntes = terminoNovaSessao.isBefore(horarioSessaoExistente);
    	boolean comecaDepois = horarioNovaSessao.isAfter(terminoSessaoExistente);
    	
    	if (terminaAntes || comecaDepois) {
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean cabe(Sessao sessaoNova) {
    	return sessoesDaSala.stream().noneMatch(sessaoExistente -> horarioIsConflitante(sessaoExistente, sessaoNova));
    }
}
