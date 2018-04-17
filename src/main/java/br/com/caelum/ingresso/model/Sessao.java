package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
   @Id
   @GeneratedValue
   private Integer id;
   
   @ManyToOne
   private Sala sala;
   
   @ManyToOne
   private Filme filme;
   
   private LocalTime horario; 
   
   @Deprecated
   public Sessao() {
	   
   };
   
   public Sessao(LocalTime horario, Filme filme, Sala sala) {
	   this.horario = horario;
	   this.filme = filme;
	   this.sala = sala;
   }
   
   public LocalTime getHorarioTermino() {
	   return this.horario.plusMinutes(filme.getDuracao().toMinutes());
   }

   public void setId(Integer id) {
	this.id = id;
   }
   
   public Integer getSessaoId() {
	   return this.id;
   }

   public Sala getSala() {
	 return this.sala;
   }

   public LocalTime getHorario() {
	  return this.horario;
   }
}
