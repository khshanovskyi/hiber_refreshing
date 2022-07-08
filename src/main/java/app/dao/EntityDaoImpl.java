package app.dao;

import app.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static app.HibernateUtil.getSession;

@AllArgsConstructor
public class EntityDaoImpl<T extends AbstractEntity> implements EntityDao<T> {

    private Class<T> entityClass;

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception in attempt to save entity - " + entity.getClass() + "\n" + e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public T getById(Long id) {
        return getSession().get(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

//        builder.
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        criteria.from(entityClass);
        return session.createQuery(criteria).getResultList();
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<T> getByCriteria(List<Criterion> criterionList) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(entityClass);
        criterionList.stream()
                .filter(Objects::nonNull)
                .forEach(criteria::add);

        return criteria.list();
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception in attempt to update entity - " + entity.getClass() + "\n" + e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public T deleteById(Long id) {
        return delete(getById(id));
    }

    @Override
    public T delete(T entity) {
        if (Objects.nonNull(entity)) {
            getSession().delete(entity);
        }
        return entity;
    }
}
