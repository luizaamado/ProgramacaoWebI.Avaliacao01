package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Professor;
import br.com.senac.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repo;
	
	public Professor save(Professor professor) {
		return repo.save(professor);
	}
	
	public List<Professor> searchAll(){
		return repo.findAll();
	}
	
	public Professor searchById(Integer id) throws ObjectNotFoundException{
		Optional<Professor> professor = repo.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException("Profesor não encontrado" + id, null));
	}
	
	public Professor saveChange(Professor professor) throws ObjectNotFoundException {
		Professor professorAtual = searchById(professor.getId());
		professorAtual.setNome(professor.getNome());
		professorAtual.setSobrenome(professor.getSobrenome());
		professorAtual.setDisciplina(professor.getDisciplina());
		professorAtual.setCurso(professor.getCurso());
		return save(professorAtual);
	}
	
	public void delete(Integer idProfessor) {
		repo.deleteById(idProfessor);	
	}
}