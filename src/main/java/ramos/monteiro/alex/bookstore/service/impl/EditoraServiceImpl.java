package ramos.monteiro.alex.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ramos.monteiro.alex.bookstore.model.dto.EditoraDTO;
import ramos.monteiro.alex.bookstore.model.entity.Editora;
import ramos.monteiro.alex.bookstore.repository.EditoraRepository;
import ramos.monteiro.alex.bookstore.service.EditoraService;

@Service
public class EditoraServiceImpl implements EditoraService {

	@Autowired
    private  EditoraRepository repository;
	@Autowired
    private  ModelMapper mapper;



    @Override
    public List<EditoraDTO> findAll() {
        return repository.findAll().stream().map(c -> mapper.map(c, EditoraDTO.class)).toList();
    }

    @Override
    public EditoraDTO findById(Long id) {

        Optional<Editora> editoraOptional = repository.findById(id);
        if (editoraOptional.isPresent()) {
            Editora editora = editoraOptional.get();
            return mapper.map(editora, EditoraDTO.class);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public EditoraDTO create(EditoraDTO entity) {

        Editora editora = mapper.map(entity, Editora.class);
        editora.setId(null);
        repository.save(editora);
        return mapper.map(editora, EditoraDTO.class);
    }

    @Override
    public EditoraDTO edit(Long id, EditoraDTO entity) {

        if (repository.existsById(id)) {
            Editora editora = mapper.map(entity, Editora.class);
            editora.setId(id);
            editora = repository.save(editora);
            return mapper.map(editora, EditoraDTO.class);
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