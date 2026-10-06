package es.uco.pw.refugio.model.domain;

import java.time.LocalDate;

import es.uco.pw.refugio.model.domain.enums.CategoriaTrabajador;

/**
 * Trabajador con titulación de veterinaria que revisa y trata a los animales.
 */
public class Veterinario extends Trabajador {

    /** El número de colegiado del veterinario. Es único. */
    private String numeroColegiado;

    /**
     * Crea un veterinario vacío, para rellenarlo con los métodos {@code set}.
     */
    public Veterinario() {
    }

    /**
     * Crea un veterinario con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     * @param fechaAlta la fecha de alta en el centro
     * @param numeroColegiado el número de colegiado
     */
    public Veterinario(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion,
            LocalDate fechaAlta,
            String numeroColegiado) {
        super(id, nombre, apellidos, dni, fechaNacimiento, direccion, fechaAlta);
        this.numeroColegiado = numeroColegiado;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link CategoriaTrabajador#VETERINARIO}
     */
    @Override
    public CategoriaTrabajador getCategoria() {
        return CategoriaTrabajador.VETERINARIO;
    }

    /**
     * Devuelve el número de colegiado del veterinario. Es único.
     *
     * @return el número de colegiado del veterinario
     */
    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    /**
     * Establece el número de colegiado del veterinario. Es único.
     *
     * @param numeroColegiado el número de colegiado del veterinario
     */
    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del veterinario en formato texto
     */
    @Override
    public String toString() {
        return "Veterinario["
                + "id=" + getId()
                + ", nombre=" + getNombre()
                + ", apellidos=" + getApellidos()
                + ", dni=" + getDni()
                + ", fechaNacimiento=" + getFechaNacimiento()
                + ", direccion=" + getDireccion()
                + ", fechaAlta=" + getFechaAlta()
                + ", numeroColegiado=" + getNumeroColegiado()
                + ", categoria=" + getCategoria()
                + "]";
    }
}
