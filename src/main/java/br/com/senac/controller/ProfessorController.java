package br.com.senac.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Professor;
import br.com.senac.service.CursoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodosProfessores() {
		ModelAndView mv = new ModelAndView("professor/paginaListaProfessor");
		mv.addObject("professores", professorService.searchAll());
		return mv;
		
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarProfessor() {
		ModelAndView mv = new ModelAndView("professor/cadastrarProfessor");
		mv.addObject("professor", new Professor());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarProfessor(Professor professor) throws ObjectNotFoundException {
		professorService.save(professor);
		return listaTodosProfessores();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarProfessor(@PathVariable("id") Integer idProfessor) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("professor/alterarProfessor");
		mv.addObject("professor", professorService.searchById(idProfessor));
		mv.addObject("disciplina", disciplinaService.searchAll());
		mv.addObject("curso", cursoService.searchAll());
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView salvarAlteraProfessor(Professor professor) throws ObjectNotFoundException {
		professorService.saveChange(professor);
		return listaTodosProfessores();
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAlteraProfessor(@PathVariable("id") Integer idProfessor) throws ObjectNotFoundException {
		professorService.delete(idProfessor);
		return listaTodosProfessores();
		
	}	
}