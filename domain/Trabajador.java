package es.uco.pw.refugio.domain;

import java.time.LocalDate;

import es.uco.pw.refugio.domain.enums.CategoriaTrabajador;

/**
 * Trabajador del refugio. Cada subclase corresponde a una categoría profesional.
 * En la base de datos todos se guardan en la tabla {@code trabajador}, usando
 * {@link #getCategoria()} como discriminador.
 */
public abstract class Trabajador extends Persona {

    /** La fecha de alta en el centro del trabajador. Se asigna al completar la incorporación. */
    private LocalDate fechaAlta;

    /**
     * Crea un trabajador vacío, para rellenarlo con los métodos {@code set}.
     */
    protected Trabajador() {
    }

    /**
     * Crea un trabajador con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     * @param fechaAlta la fecha de alta en el centro
     */
    protected Trabajador(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion,
            LocalDate fechaAlta) {
        super(id, nombre, apellidos, dni, fechaNacimiento, direccion);
        this.fechaAlta = fechaAlta;
    }

    /**
     * Devuelve la categoría profesional del trabajador. Cada subclase devuelve un valor fijo.
     *
     * @return la categoría del trabajador
     */
    public abstract CategoriaTrabajador getCategoria();

    /**
     * Devuelve la fecha de alta en el centro del trabajador. Se asigna al completar la incorporación.
     *
     * @return la fecha de alta en el centro del trabajador
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece la fecha de alta en el centro del trabajador. Se asigna al completar la incorporación.
     *
     * @param fechaAlta la fecha de alta en el centro del trabajador
     */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
