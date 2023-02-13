package ramos.monteiro.alex.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO{
        private Long id;
        @NotNull(message = "O campo Categoria é obrigatório!") 
        private CategoriaDTO categoria;
        @NotNull(message = "O campo Editora é obrigatório!") 
        private EditoraDTO editora;
        @NotBlank(message = "O campo nome  é obrigatório!") 
        private String nome;
        @NotNull(message = "O campo isbn  é obrigatório!")
        private String isbn;

}
