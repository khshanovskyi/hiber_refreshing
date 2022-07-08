package app.repository;

import app.dao.EntityDao;
import app.dao.EntityDaoImpl;
import app.entity.AbstractEntity;
import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Objects;

public class EntityRepositoryImpl<T extends AbstractEntity> implements EntityRepository<T> {

    private EntityDao<T> entityDao;

    public EntityRepositoryImpl(Class<T> entityClass) {
        this.entityDao = new EntityDaoImpl(entityClass);
    }

    @Override
    public void save(T entity) {
        entityDao.save(entity);
    }

    @Override
    public void saveAll(List<T> entities) {
        entities.stream()
                .filter(Objects::nonNull)
                .forEach(entityDao::save);
    }

    @Override
    public T getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return entityDao.getAll();
    }

    @Override
    public List<T> getByCriteria(List<Criterion> criterionList){
        return entityDao.getByCriteria(criterionList);
    }

    @Override
    public T deleteById(Long id) {
        return entityDao.deleteById(id);
    }

    @Override
    public void update(T entity) {
        entityDao.update(entity);
    }
}
