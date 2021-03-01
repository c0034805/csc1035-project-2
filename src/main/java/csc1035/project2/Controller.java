package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class Controller<E> implements IController<E> {
    @Override
    public void save(E e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(e);
        s.getTransaction().commit();
    }

    @Override
    public void update(E e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.saveOrUpdate(e);
        s.getTransaction().commit();
    }

    @Override
    public E getById(Class<E> c, int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("FROM " + c.getSimpleName() + " WHERE id = " + id);
        E o = (E) q.getSingleResult();
        s.getTransaction().commit();
        return o;
    }

    @Override
    public E getById(Class<E> c, String id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("FROM " + c.getSimpleName() + " WHERE id LIKE ?1");
        q.setParameter(1, id);
        E o = (E) q.getSingleResult();
        s.getTransaction().commit();
        return o;
    }

    @Override
    public List<E> getAll(Class<E> c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        List<E> entries = s.createQuery("FROM " + c.getSimpleName()).list();
        s.getTransaction().commit();
        return entries;
    }

    @Override
    public void delete(Class<E> c, int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(s.get(c, id));
        s.getTransaction().commit();
    }

    @Override
    public void delete(Class<E> c, String id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(s.get(c, id));
        s.getTransaction().commit();
    }

    @Override
    public void bulkListSave(List<E> e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        for(E o : e) {
            save(o);
        }
        s.getTransaction().commit();
    }
}
