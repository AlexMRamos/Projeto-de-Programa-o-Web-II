package ramos.monteiro.alex.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ramos.monteiro.alex.bookstore.model.dto.CategoriaDTO;
import ramos.monteiro.alex.bookstore.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService> {

    public CategoriaController(CategoriaService service) {
        super(service);
    }
}
