package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Curso;
import br.com.senac.repository.CursoRepository;


@Service
public class CursoService {
	
	@Autowired
	CursoRepository repo;
	
	public Curso save(Curso curso) {
		return repo.save(curso);
	}
	
	public List<Curso> searchAll(){
		return repo.findAll();
	}
	
	public Curso searchById(Integer id) throws ObjectNotFoundException{
		Optional<Curso> curso = repo.findById(id);
		return curso.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado:" + id, null));
	}
	
	public Curso saveChange(Curso curso) throws ObjectNotFoundException {
		Curso cursoAtual = searchById(curso.getId());
		cursoAtual.setNome(curso.getNome());
		cursoAtual.setDescricao(curso.getDescricao());
		return save(cursoAtual);
	}
	
	public void delete(Integer idCurso) {
		repo.deleteById(idCurso);
	}
}
