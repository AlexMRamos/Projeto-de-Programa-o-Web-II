package ramos.monteiro.alex.bookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ramos.monteiro.alex.bookstore.model.dto.LivroDTO;
import ramos.monteiro.alex.bookstore.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController extends BaseController<LivroDTO, LivroService> {
	
	
    public LivroController(LivroService service) {
        super(service);
    }
    
    
    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<LivroDTO>> searchLivroByCategory(@PathVariable("nome") String nome) {
    	List<LivroDTO> livros =  service.searchLivroByCategory(nome);
    	return ResponseEntity.ok(livros);
    }
    
    @GetMapping("/editora/{nome}")
    public ResponseEntity<List<LivroDTO>> searchLivroByEditora(@PathVariable("nome") String nome) {
    	List<LivroDTO> livros =  service.searchLivroByEditora(nome);
    	return ResponseEntity.ok(livros);
    }
    
    
}