package br.com.senac.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Disciplina;
import br.com.senac.service.DisciplinaService;

@Controller
@RequestMapping("disciplina")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodasDisciplinas() {
		ModelAndView mv = new ModelAndView("disciplina/paginaListaDisciplina");
		mv.addObject("disciplinas", disciplinaService.searchAll());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarDisciplina() {
		ModelAndView mv = new ModelAndView("disciplina/cadastrarDisciplina");
		mv.addObject("disciplina", new Disciplina());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarDisciplina(Disciplina disciplina) throws ObjectNotFoundException {
		disciplinaService.save(disciplina);
		return listaTodasDisciplinas();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarDisciplina(@PathVariable("id") Integer idDisciplina) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("disciplina/alterarDisciplina");
		mv.addObject("disciplina", disciplinaService.searchById(idDisciplina));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView salvarAlteraDisciplina(Disciplina disciplina) throws ObjectNotFoundException {
		disciplinaService.saveChange(disciplina);
		return listaTodasDisciplinas();
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAlteraDisciplina(@PathVariable("id") Integer idDisciplina) throws ObjectNotFoundException {
		disciplinaService.delete(idDisciplina);
		return listaTodasDisciplinas();
		
	}
}