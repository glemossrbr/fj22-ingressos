package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.caelum.ingresso.dao.*;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {
	
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		
		form.setSalaId(salaId);
		
		ModelAndView mav = new ModelAndView("sessao/sessao");
		
		mav.addObject("sala", salaDao.findOne(salaId));
		mav.addObject("filmes",filmeDao.findAll());
		mav.addObject("form",form);
		
		return mav;
   } // end form
	
	@GetMapping("/admin/{idSessao}/lugares") 
	public ModelAndView lugares(@PathVariable Integer idSessao) {
		
		ModelAndView mav = new ModelAndView("sessao/lugares"); //jsp - mapeamento das views
		Sessao sessao = sessaoDao.findOne(idSessao);
		
		mav.addObject("sessao", sessao);
		
		return mav;
   } // end form
	
	@PostMapping(value = "/admin/sessao")
	@Transactional
	public ModelAndView grava(@Valid SessaoForm form, BindingResult result) {
		
		if(result.hasErrors())
			return form(form.getSalaId(), form);
		
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		
		List<Sessao> sessoesExistentes = sessaoDao.buscaSessoesDaSala(sessao.getSala());
		
		GerenciadorDeSessao gerSessoes = new GerenciadorDeSessao(sessoesExistentes);
		gerSessoes.cabe(sessao);
		
		sessaoDao.save(sessao);
		return new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
	} // end salva

	
}