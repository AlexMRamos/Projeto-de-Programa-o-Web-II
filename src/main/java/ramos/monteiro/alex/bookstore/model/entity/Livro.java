package ramos.monteiro.alex.bookstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn", nullable = false, unique = false)
    private String isbn;
    @JoinColumn(name = "id_categoria", nullable = false)
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "id_editora", nullable = false)
    @ManyToOne
    private Editora editora;
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
}
