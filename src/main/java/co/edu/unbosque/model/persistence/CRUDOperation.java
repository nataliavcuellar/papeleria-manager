package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic interface that defines the basic CRUD (Create, Read, Update, Delete)
 * operations for any type of data object.
 *
 * <p>This interface provides a standardized contract for data access objects
 * (DAOs) to implement core persistence operations such as creating, updating,
 * retrieving, and deleting records from a data source.</p>
 *
 * @param <E> The data type (DTO or entity) that the CRUD operations will handle.
 * @author InnocaCore
 */
public interface CRUDOperation<E> {

    /**
     * Creates a new record in the data source.
     *
     * @param data The object to be created and stored.
     */
    public void crear(E data);

    /**
     * Deletes a record from the data source based on its ID.
     *
     * @param id The unique identifier of the record to delete.
     * @return {@code true} if the record was successfully deleted,
     *         {@code false} otherwise.
     */
    public Boolean eliminar(int id);

    /**
     * Updates an existing record with new data.
     *
     * @param id The unique identifier of the record to update.
     * @param newData The new data to apply to the record.
     * @return {@code true} if the update was successful,
     *         {@code false} otherwise.
     */
    public Boolean actualizar(int id, E newData);

    /**
     * Retrieves all records from the data source.
     *
     * @return An {@code ArrayList} containing all available records.
     */
    public ArrayList<E> findAll();

    /**
     * Finds a single record based on its ID.
     *
     * @param id The unique identifier of the record to find.
     * @return The matching object if found, or {@code null} if not found.
     */
    public E findOne(int id);

    /**
     * Deletes multiple records from the data source using their IDs.
     *
     * @param ids A list containing the IDs of the records to delete.
     */
    public void eliminarTodos(List<Integer> ids);
}
