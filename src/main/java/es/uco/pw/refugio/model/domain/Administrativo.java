package es.uco.pw.refugio.model.domain;

import java.time.LocalDate;

import es.uco.pw.refugio.model.domain.enums.CategoriaTrabajador;

/**
 * Trabajador de recepción que registra la llegada de animales y tramita las adopciones.
 * No tiene información adicional respecto a {@link Trabajador}.
 */
public class Administrativo extends Trabajador {

    /**
     * Crea un administrativo vacío, para rellenarlo con los métodos {@code set}.
     */
    public Administrativo() {
    }

    /**
     * Crea un administrativo con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     * @param fechaAlta la fecha de alta en el centro
     */
    public Administrativo(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion,
            LocalDate fechaAlta) {
        super(id, nombre, apellidos, dni, fechaNacimiento, direccion, fechaAlta);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link CategoriaTrabajador#ADMINISTRATIVO}
     */
    @Override
    public CategoriaTrabajador getCategoria() {
        return CategoriaTrabajador.ADMINISTRATIVO;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del administrativo en formato texto
     */
    @Override
    public String toString() {
        return "Administrativo["
                + "id=" + getId()
                + ", nombre=" + getNombre()
                + ", apellidos=" + getApellidos()
                + ", dni=" + getDni()
                + ", fechaNacimiento=" + getFechaNacimiento()
                + ", direccion=" + getDireccion()
                + ", fechaAlta=" + getFechaAlta()
                + ", categoria=" + getCategoria()
                + "]";
    }
}
