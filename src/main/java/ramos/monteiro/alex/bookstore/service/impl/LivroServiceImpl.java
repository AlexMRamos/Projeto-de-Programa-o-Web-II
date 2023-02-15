package ramos.monteiro.alex.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ramos.monteiro.alex.bookstore.model.dto.LivroDTO;
import ramos.monteiro.alex.bookstore.model.entity.Livro;
import ramos.monteiro.alex.bookstore.repository.LivroRepository;
import ramos.monteiro.alex.bookstore.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository repository;
	@Autowired
	private ModelMapper mapper;
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<LivroDTO> findAll() {
		return repository.findAll().stream().map(c -> mapper.map(c, LivroDTO.class)).toList();
	}

	@Override
	public LivroDTO findById(Long id) {
		Optional<Livro> livroOptional = repository.findById(id);
		if (livroOptional.isPresent()) {
			Livro livro = livroOptional.get();
			return mapper.map(livro, LivroDTO.class);
		}

		throw new EntityNotFoundException();
	}

	@Override
	public LivroDTO create(LivroDTO entity) {

		Livro livro = mapper.map(entity, Livro.class);
		livro.setId(null);
		repository.save(livro);
		return mapper.map(livro, LivroDTO.class);
	}

	@Override
	public LivroDTO edit(Long id, LivroDTO entity) {
		if (repository.existsById(id)) {
			Livro livro = mapper.map(entity, Livro.class);
			livro.setId(id);
			livro = repository.save(livro);
			return mapper.map(livro, LivroDTO.class);
		}

		throw new EntityNotFoundException();
	}

	@Override
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}

		repository.deleteById(id);
	}

	@Override
	public List<LivroDTO> searchLivroByCategory(String nome) {
		List<Livro> livros = repository.searchLivroByCategoria(nome);
		return livros.stream().map(c -> mapper.map(c, LivroDTO.class)).toList();
	}

	@Override
	public List<LivroDTO> searchLivroByEditora(String nome) {
		List<Livro> livros = repository.searchLivroByEditora(nome);
		return livros.stream().map(c -> mapper.map(c, LivroDTO.class)).toList();
	}

	@Override
	public List<LivroDTO> searchLivroByNameOrIsbn(String nome, String isbn) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Livro> cq = cb.createQuery(Livro.class);
	        Root<Livro> root = cq.from(Livro.class);

	        List<Predicate> predicates = new ArrayList<>();

	        if (!nome.isEmpty()) {
	            predicates.add(cb.like(root.get("nome"), "%" + nome + "%"));
	        }

	        if (!isbn.isEmpty()) {
	            predicates.add(cb.like(root.get("isbn"), "%" + isbn + "%"));
	        }

	        cq.where(cb.or(predicates.toArray(new Predicate[0])));

	        List<Livro>livros = em.createQuery(cq).getResultList();
	        
	        return livros.stream().map(c -> mapper.map(c, LivroDTO.class)).toList();
	}
}