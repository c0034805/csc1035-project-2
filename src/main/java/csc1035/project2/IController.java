package csc1035.project2;

import java.util.List;

public interface IController<E> {
    void save(E e);

    void update(E e);

    E getById(Class<E> c, int id);

    E getById(Class<E> c, String id);

    List<E> getAll(Class<E> c);

    void delete(Class<E> c, int id);

    void delete(Class<E> c, String id);

    void bulkListSave(List<E> e);
}
