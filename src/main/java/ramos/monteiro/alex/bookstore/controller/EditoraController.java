package ramos.monteiro.alex.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ramos.monteiro.alex.bookstore.model.dto.EditoraDTO;
import ramos.monteiro.alex.bookstore.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController extends BaseController<EditoraDTO, EditoraService> {
    public EditoraController(EditoraService service) {
        super(service);
    }
}
