package br.com.senac.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Curso;
import br.com.senac.service.CursoService;

@Controller
@RequestMapping("curso")
public class CursoController {
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodosCursos() {
		ModelAndView mv = new ModelAndView("curso/paginaListaCursos");
		mv.addObject("cursos", cursoService.searchAll());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCurso() {
		ModelAndView mv = new ModelAndView("curso/cadastrarCurso");
		mv.addObject("curso", new Curso());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarCurso(Curso curso) throws ObjectNotFoundException {
		cursoService.save(curso);
		return listaTodosCursos();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarCurso(@PathVariable("id") Integer idCurso) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("curso/alterarCurso");
		mv.addObject("curso", cursoService.searchById(idCurso));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView salvarAlteraCurso(Curso curso) throws ObjectNotFoundException {
		cursoService.saveChange(curso);
		return listaTodosCursos();
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAlteraCurso(@PathVariable("id") Integer idCurso) throws ObjectNotFoundException {
		cursoService.delete(idCurso);
		return listaTodosCursos();
	}	
}