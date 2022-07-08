package app.repository;


import app.entity.AbstractEntity;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface EntityRepository<T extends AbstractEntity> {

    void save(T entity);

    void saveAll(List<T> entities);

    T getById(Long id);

    List<T> getAll();

    List<T> getByCriteria(List<Criterion> criterionList);

    T deleteById(Long id);

    void update(T entity);
}
