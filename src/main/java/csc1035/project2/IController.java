package csc1035.project2;

import java.util.List;

/**
 * Database access controller.
 *
 * @param <E> Type that maps to the table being accessed.
 */
public interface IController<E> {
    /**
     * Insert a new record into the database.
     *
     * @param e Object representing the record to insert.
     */
    void save(E e);

    /**
     * Insert a new record into the database or update the record if it is already present.
     *
     * @param e Object representing the record to update.
     */
    void update(E e);

    /**
     * Get an object representing the record with the given integer primary key.
     *
     * @param c Type representing the table to select from.
     * @param id Primary key of the record to fetch.
     * @return Object representing the fetched record.
     */
    E getById(Class<E> c, int id);

    /**
     * Get an object representing the record with the given string primary key.
     *
     * @param c Type representing the table to select from.
     * @param id Primary key of the record to fetch.
     * @return Object representing the fetched record.
     */
    E getById(Class<E> c, String id);

    /**
     * Get a list of objects representing all records in a table.
     *
     * @param c Type representing the table to fetch.
     * @return List of objects representing the fetched table.
     */
    List<E> getAll(Class<E> c);

    /**
     * Delete a record with the given integer primary key.
     *
     * @param c Type representing the table to delete from.
     * @param id Primary key of the record to delete.
     */
    void delete(Class<E> c, int id);

    /**
     * Delete a record with the given string primary key.
     *
     * @param c Type representing the table to delete from.
     * @param id Primary key of the record to delete.
     */
    void delete(Class<E> c, String id);

    /**
     * Insert records for a list of objects into the database.
     *
     * @param e List of objects to create records for.
     */
    void bulkListSave(List<E> e);
}
