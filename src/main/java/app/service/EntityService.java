package app.service;

import app.entity.AbstractEntity;

import java.util.List;

public interface EntityService<T extends AbstractEntity> {

    void save(T entity);

    void saveAll(List<T> entities);

    T getById(Long id);

    List<T> getAll();

    T deleteById(Long id);

    void update(T entity);
}
