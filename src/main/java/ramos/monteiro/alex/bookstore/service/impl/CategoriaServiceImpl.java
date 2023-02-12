package ramos.monteiro.alex.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ramos.monteiro.alex.bookstore.model.dto.CategoriaDTO;
import ramos.monteiro.alex.bookstore.model.entity.Categoria;
import ramos.monteiro.alex.bookstore.repository.CategoriaRepository;
import ramos.monteiro.alex.bookstore.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
    private CategoriaRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;
    


    @Override
    public List<CategoriaDTO> findAll() {
        return repository.findAll().stream().map(c -> modelMapper.map(c, CategoriaDTO.class)).toList();
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoriaProdutoOp = repository.findById(id);
        if (categoriaProdutoOp.isPresent()) {
            Categoria categoria = categoriaProdutoOp.get();
            return modelMapper.map(categoria, CategoriaDTO.class);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public CategoriaDTO create(CategoriaDTO entity) {
        Categoria categoria = modelMapper.map(entity, Categoria.class);
        categoria.setId(null);
        repository.save(categoria);
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO edit(Long id, CategoriaDTO entity) {
        if (repository.existsById(id)) {
            Categoria categoria = modelMapper.map(entity, Categoria.class);
            categoria.setId(id);
            categoria = repository.save(categoria);
            return modelMapper.map(categoria, CategoriaDTO.class);
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
}
