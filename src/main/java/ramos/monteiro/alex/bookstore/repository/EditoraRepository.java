package ramos.monteiro.alex.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ramos.monteiro.alex.bookstore.model.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
