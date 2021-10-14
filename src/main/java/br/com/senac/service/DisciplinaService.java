package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Disciplina;
import br.com.senac.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository repo;
	
	public Disciplina save(Disciplina disciplina) {
		return repo.save(disciplina);
	}
	
	public List<Disciplina> searchAll(){
		return repo.findAll();
	}
	
	public Disciplina searchById(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = repo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException("Disciplina não encontrada" + id, null));
	}
	
	public Disciplina saveChange(Disciplina disciplina) throws ObjectNotFoundException {
		Disciplina disciplinaAtual = searchById(disciplina.getId());
		disciplinaAtual.setNome(disciplina.getNome());
		disciplinaAtual.setDescricao(disciplina.getDescricao());
		return save(disciplinaAtual);
	}
	
	public void delete(Integer idDisciplina) {
		repo.deleteById(idDisciplina);
		
	}	
}