package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
   
	private Integer id;

	@NotNull
	private Integer salaId;
	   
	@NotNull
	private Integer filmeId;
	   
	@NotNull
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime horario; 
	
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		Filme filme = filmeDao.findOne(filmeId);
		Sala sala = salaDao.findOne(salaId);
		
		Sessao sessao = new Sessao(horario, filme, sala);
		sessao.setId(id);
		
		return sessao;
	}

	public Integer getSalaId() {
		return this.salaId;
		
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;		
	}
}
