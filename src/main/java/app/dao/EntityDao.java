package app.dao;

import app.entity.AbstractEntity;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface EntityDao<T extends AbstractEntity> {

    void save(T entity);

    T getById(Long id);

    List<T> getAll();

    List<T> getByCriteria(List<Criterion> criterionList);

    void update(T entity);

    T deleteById(Long id);

    T delete(T entity);
}
