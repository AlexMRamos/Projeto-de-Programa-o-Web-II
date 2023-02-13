package ramos.monteiro.alex.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditoraDTO{
        private Long id;
        @NotBlank(message = "O campo nome é obrigatório") 
        private String nome;
        private String descricao;

}
