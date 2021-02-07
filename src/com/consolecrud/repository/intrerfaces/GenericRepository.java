package com.consolecrud.repository.intrerfaces;

public interface GenericRepository<T, ID extends Number> {

    T getById(ID id);
    void deleteById(ID id);
    Iterable<T> findAll();
    T save(T entity);
    T update(T entity);
}
