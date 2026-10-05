package es.uco.pw.refugio.domain;

import java.time.LocalDate;

/**
 * Persona particular que adopta uno o varios animales del refugio.
 * Se registra una única vez, en su primera adopción.
 */
public class Adoptante extends Persona {

    /** El teléfono de contacto del adoptante. */
    private String telefono;

    /**
     * Crea un adoptante vacío, para rellenarlo con los métodos {@code set}.
     */
    public Adoptante() {
    }

    /**
     * Crea un adoptante con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     * @param telefono el teléfono de contacto
     */
    public Adoptante(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion,
            String telefono) {
        super(id, nombre, apellidos, dni, fechaNacimiento, direccion);
        this.telefono = telefono;
    }

    /**
     * Devuelve el teléfono de contacto del adoptante.
     *
     * @return el teléfono de contacto del adoptante
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono de contacto del adoptante.
     *
     * @param telefono el teléfono de contacto del adoptante
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del adoptante en formato texto
     */
    @Override
    public String toString() {
        return "Adoptante["
                + "id=" + getId()
                + ", nombre=" + getNombre()
                + ", apellidos=" + getApellidos()
                + ", dni=" + getDni()
                + ", fechaNacimiento=" + getFechaNacimiento()
                + ", direccion=" + getDireccion()
                + ", telefono=" + getTelefono()
                + "]";
    }
}
