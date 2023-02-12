package ramos.monteiro.alex.bookstore.service;

import java.util.List;

public interface BaseService<T> {

    List<T> findAll();
    T findById(Long id);
    T create(T entity);
    T edit(Long id, T entity);
    void delete(Long id);
}
