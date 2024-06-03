package com.ensah.api.core.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    Long rowsNumber();
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
    void deleteById(Long id);
    T update(Long id, T newEntity);
}
