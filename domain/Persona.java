package es.uco.pw.refugio.domain;

import java.time.LocalDate;

/**
 * Datos personales comunes a las personas que gestiona la aplicación:
 * trabajadores del refugio y adoptantes.
 */
public abstract class Persona {

    /** El identificador numérico de la persona. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El nombre de la persona. */
    private String nombre;

    /** Los apellidos de la persona. */
    private String apellidos;

    /** El DNI de la persona. Es único. */
    private String dni;

    /** La fecha de nacimiento de la persona. */
    private LocalDate fechaNacimiento;

    /** La dirección postal de la persona. */
    private String direccion;

    /**
     * Crea una persona vacía, para rellenarla con los métodos {@code set}.
     */
    protected Persona() {
    }

    /**
     * Crea una persona con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     */
    protected Persona(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    /**
     * Devuelve el identificador numérico de la persona. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico de la persona
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico de la persona. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico de la persona
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre el nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los apellidos de la persona.
     *
     * @return los apellidos de la persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos de la persona.
     *
     * @param apellidos los apellidos de la persona
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Devuelve el DNI de la persona. Es único.
     *
     * @return el DNI de la persona
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI de la persona. Es único.
     *
     * @param dni el DNI de la persona
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve la fecha de nacimiento de la persona.
     *
     * @return la fecha de nacimiento de la persona
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento de la persona.
     *
     * @param fechaNacimiento la fecha de nacimiento de la persona
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Devuelve la dirección postal de la persona.
     *
     * @return la dirección postal de la persona
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección postal de la persona.
     *
     * @param direccion la dirección postal de la persona
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
