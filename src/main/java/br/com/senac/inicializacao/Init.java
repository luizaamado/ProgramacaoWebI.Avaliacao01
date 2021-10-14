package br.com.senac.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


import br.com.senac.domain.Curso;
import br.com.senac.domain.Disciplina;
import br.com.senac.domain.Professor;
import br.com.senac.service.CursoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	CursoService cursoService;
	@Autowired
	DisciplinaService disciplinaService;
	@Autowired
	ProfessorService professorService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Curso curso1 = new Curso();
		curso1.setNome("Curso 1");
		curso1.setDescricao("Descrição Curso 1");
		cursoService.save(curso1);
		
		Curso curso2 = new Curso();
		curso2.setNome("Curso 2");
		curso2.setDescricao("Descrição Curso 2");
		cursoService.save(curso2);
		
		Curso curso3 = new Curso();
		curso3.setNome("Curso 3");
		curso3.setDescricao("Descrição Curso 3");
		cursoService.save(curso3);
		
		List<Curso> listaCursos = cursoService.searchAll();

		for (Curso curso : listaCursos) {
			System.out.println(curso.getNome());
			System.out.println(curso.getDescricao());			
		}
		
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("Disciplina 1");
		disciplina1.setDescricao("Descrição Disciplina 1");
		disciplinaService.save(disciplina1);
		
		Disciplina disciplina2 = new Disciplina();
		disciplina2.setNome("Disciplina 2");
		disciplina2.setDescricao("Descrição Disciplina 2");
		disciplinaService.save(disciplina2);
		
		Disciplina disciplina3 = new Disciplina();
		disciplina3.setNome("Disciplina 3");
		disciplina3.setDescricao("Descrição Disciplina 3");
		disciplinaService.save(disciplina3);
		
		List<Disciplina> listarDisciplinas = disciplinaService.searchAll();

		for (Disciplina disciplina : listarDisciplinas) {
			System.out.println(disciplina.getNome());
		}
		
		Professor professor1 = new Professor();
		professor1.setNome("Nome 1");
		professor1.setSobrenome("Sobrenome 1");
		professor1.setDisciplina(disciplina1);
		professor1.setCurso(curso1);
		professorService.save(professor1);
		
		Professor professor2 = new Professor();
		professor2.setNome("Nome 2");
		professor2.setSobrenome("Sobrenome 2");
		professor2.setDisciplina(disciplina2);
		professor2.setCurso(curso2);
		professorService.save(professor2);
		
		Professor professor3 = new Professor();
		professor3.setNome("Nome 3");
		professor3.setSobrenome("Sobrenome 3");
		professor3.setDisciplina(disciplina3);
		professor3.setCurso(curso3);
		professorService.save(professor3);
		
		List<Professor> listaProfessores = professorService.searchAll();
		
		for(Professor professor:listaProfessores) {
			System.out.println(professor.getNome() + professor.getSobrenome() + ", Disciplina: "
					+ professor.getDisciplina().getNome() + ", Curso: " + professor.getCurso().getNome());
		}		
	}
}
