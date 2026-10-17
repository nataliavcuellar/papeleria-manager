package co.edu.unbosque.model;

import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * Represents the composite primary key for the {@link CuentaProducto} entity.
 * <p>
 * This class combines both the product ID and the payable account ID to uniquely
 * identify each record in the <b>cuentaproducto</b> table.
 * </p>
 * 
 * <p>
 * It is marked as {@link Embeddable}, which allows it to be embedded within the
 * entity as a composite key.
 * </p>
 * 
 * @author InnovaCore
 */
@Embeddable
public class CuentaProductoId {

    /** Identifier of the product associated with the account. */
    private int idProducto;

    /** Identifier of the payable account associated with the product. */
    private int idCuenta;

    /**
     * Default constructor.
     */
    public CuentaProductoId() {
        // Default constructor
    }

    /**
     * Parameterized constructor that initializes the composite key.
     * 
     * @param idProducto the product ID
     * @param idCuenta   the payable account ID
     */
    public CuentaProductoId(int idProducto, int idCuenta) {
        super();
        this.idProducto = idProducto;
        this.idCuenta = idCuenta;
    }

    /**
     * Compares this object to another for equality based on both ID fields.
     * 
     * @param obj the object to compare
     * @return true if both IDs are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CuentaProductoId))
            return false;
        CuentaProductoId that = (CuentaProductoId) obj;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(idCuenta, that.idCuenta);
    }

    /**
     * Generates a hash code based on both IDs.
     * 
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idCuenta);
    }

    /**
     * @return the product ID
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the product ID to set
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the payable account ID
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the payable account ID to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
}
