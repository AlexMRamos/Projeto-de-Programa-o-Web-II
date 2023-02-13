package ramos.monteiro.alex.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ramos.monteiro.alex.bookstore.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	@Query("SELECT l FROM Livro l INNER JOIN Categoria c ON l.categoria.nome = :nome AND c.nome = :nome")
	public List<Livro> searchLivroByCategoria(String nome);
	
	@Query("SELECT l FROM Livro l INNER JOIN Editora e ON l.editora.nome = :nome AND e.nome = :nome")
	public List<Livro> searchLivroByEditora(String nome);
}