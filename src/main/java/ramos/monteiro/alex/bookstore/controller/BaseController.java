package ramos.monteiro.alex.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ramos.monteiro.alex.bookstore.service.BaseService;




@Slf4j
public abstract class BaseController<T, S extends BaseService<T>> {

    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable("id") Long id) {
        try {

            return ResponseEntity.ok(service.findById(id));

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody @Valid T entidade) {
        try {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.create(entidade));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> edit(@PathVariable("id") Long id,
                                  @RequestBody @Valid T entidade) {
        try {

            return ResponseEntity.ok(service.edit(id, entidade));

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {

            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
