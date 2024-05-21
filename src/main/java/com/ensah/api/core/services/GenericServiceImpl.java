package com.ensah.api.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GenericServiceImpl<T> implements GenericService<T>{

    private final JpaRepository<T, Long> dao;

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public T save(T entity) {
        return dao.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

}
