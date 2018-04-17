package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;

@Entity
public class Sessao {
   
   private Integer id;
   private Sala sala;
   private Filme filme;
   private LocalTime horario; 
   
   @Deprecated
   public Sessao();
   
   public Sessao(LocalTime horario, Filme filme, Sala sala) {
	   this.horario = horario;
	   this.filme = filme;
	   this.sala = sala;
   }
   
   public LocalTime getHorarioTermino() {
	   return this.horario.plusMinutes(filme.getDuracao().toMinutes());
   }
   
   
   
}
