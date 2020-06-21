package org.company.repository;
import org.company.db.HibernateUtil;
import org.company.domain.AbstractClient;
import org.company.domain.AbstractEntity;
import org.company.domain.Consumer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
public abstract class AbstractRepository<ID extends Serializable, T extends AbstractEntity<ID>>  {
    @FunctionalInterface
    interface SqlAction<R> {
        R process(Session session, Transaction tr) throws Exception;
    }

    @FunctionalInterface
    interface CriteriaSetting<R, T>  {
        void create(CriteriaBuilder cb, Root<T> root, CriteriaQuery<R> query);
    }

    protected abstract Class<T> getClazz();
    protected abstract String getTableName();

    protected <R>  R doIt(SqlAction<R> action) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction tr = session.beginTransaction();
            try {
                return action.process(session, tr);
            }
            catch (Exception e) {
                tr.rollback();
                throw e;
            }
            finally {
                if (tr.getStatus().equals(TransactionStatus.ACTIVE))
                    tr.commit();
            }
        }
    }
    public T getByLocalId(String id) throws Exception{
        if(id==null || id.isEmpty()){
            return null;

        }
        return (T) doIt(((session, tr) -> {
            Query<T> query = createCriteria(session,getClazz(), (cb, r, cq) -> {
                cq.select(r).where(cb.equal(r.get(AbstractClient.FLD_CLIENTID), id));
            });
            return query.getSingleResult();}));

    }

    public T getByName(String id) throws Exception{
        if(id==null || id.isEmpty()){
            return null;

        }
        return (T) doIt(((session, tr) -> {
            Query<T> query = createCriteria(session,getClazz(), (cb, r, cq) -> {
                cq.select(r).where(cb.equal(r.get(AbstractClient.FLD_NAME), id));
            });
            return query.getSingleResult();}));
    }


    public void save(T entity) throws Exception {
        if (entity==null)
            return;

        doIt(((session, tr) -> {
            session.save(entity);
            return null;
        }));
    }

    public T getById(ID id) throws Exception {
        if (id == null)
            return null;

        return doIt((session, tr) -> session.find(getClazz(), id));
    }

//    public List<T> getByName(String name) throws Exception {
//        if (name==null || name.isEmpty())
//            return Collections.emptyList();
//
//        return doIt(((session, tr) -> {
//            Query<T> query = createCriteria(session, getClazz(), (cb, r, cq) -> {
//                cq.select(r).where(cb.equal(r.get(AbstractPerson.FLD_NAME), name));
//            });
//            return query.getResultList();
//        }));
//    }





    public void delete(ID id) throws Exception {
        if (id==null)
            return;

        SqlAction<T> action = ((session, tr) -> {
            T obj = session.find(getClazz(), id);
            if (obj == null)
                return null;
            session.delete(obj);
            return null;
        });
        doIt(action);
    }

//    public void delete(Long id) throws Exception {
//        if (id == null){
//            return;}
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            String s=getTableName();
//            String hql = "DELETE FROM "+s+" stud WHERE stud.id=:id";
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            long rowCount = query.executeUpdate();
//
//
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//
//    }


    public List<T> getAll() throws Exception{

        return doIt(((session, tr) -> {
            Query<T> query = createCriteria(session,getClazz(), (cb, r, cq) -> {
                cq.select(r);
            });
            return query.getResultList();}));
    }


//    public long getCount() throws Exception {
//        SqlAction<Long> action = ((session, tr) -> {
//            Query<Long> query = createCriteria(session, Long.class, (cb, r, cq)-> {
//                cq.select(cb.count(r.get(AbstractPerson.FLD_ID)));
//            });
//
//            return query.getSingleResult();
//        });
//        return doIt(action);
//    }
    public T merge(T entity) throws Exception {
        if (entity==null)
            return null;

        return doIt((session, tr) -> (T) session.merge(entity));
    }

    public void persist(T entity) throws Exception {
        if (entity==null)
            return;

        doIt((session, tr) -> {
            session.persist(entity); return null;
        });
    }
    public List<T> getAll(int currentPage,int recordsPage) throws Exception{

        return doIt(((session, tr) -> {
            Query<T> query = createCriteria(session,getClazz(), (cb, r, cq) -> {
                cq.select(r);
            });
            query.setFirstResult(currentPage*recordsPage);
            query.setMaxResults(recordsPage);

            return query.getResultList();}));
    }

    protected <R> Query<R> createCriteria(Session session, Class<R> resultClass, CriteriaSetting<R, T> criteriaSetting) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<R> cq = cb.createQuery(resultClass);
        Root<T> root = cq.from(getClazz());
        criteriaSetting.create(cb, root, cq);
        return session.createQuery(cq);
    }

    public void update(T entity) throws Exception{
        if(entity==null){
            return;
        }
        doIt((((session, tr) -> {
            session.update(entity);
            return null;
        })));

    }
    public void saveOrupdate(T entity) throws Exception{
        if(entity==null){
            return;
        }
        doIt((((session, tr) -> {
            session.saveOrUpdate(entity);
            return null;
        })));

    }
}
