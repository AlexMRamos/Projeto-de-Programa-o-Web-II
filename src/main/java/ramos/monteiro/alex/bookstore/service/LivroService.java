package ramos.monteiro.alex.bookstore.service;

import java.util.List;

import ramos.monteiro.alex.bookstore.model.dto.LivroDTO;

public interface LivroService extends BaseService<LivroDTO> {
	
	List<LivroDTO> searchLivroByCategory(String categoria);

	List<LivroDTO> searchLivroByEditora(String categoria);

}
