package es.uco.pw.refugio.model.domain;

import java.time.LocalDate;

/**
 * Estancia de un animal en el hospital, vinculada a su historial clínico.
 * Un ingreso sin fecha de salida indica que el animal sigue hospitalizado.
 */
public class Ingreso {

    /** El identificador numérico del ingreso. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El identificador del historial clínico al que pertenece el ingreso. */
    private Integer idHistorial;

    /** La fecha de entrada en el hospital. */
    private LocalDate fechaEntrada;

    /** La fecha de salida del hospital. Es {@code null} mientras siga ingresado. */
    private LocalDate fechaSalida;

    /** La medicación prescrita durante el ingreso. */
    private String medicacion;

    /**
     * Crea un ingreso vacío, para rellenarlo con los métodos {@code set}.
     */
    public Ingreso() {
    }

    /**
     * Crea un ingreso con todos sus datos.
     *
     * @param id el identificador numérico
     * @param idHistorial el identificador del historial clínico al que pertenece el ingreso
     * @param fechaEntrada la fecha de entrada en el hospital
     * @param fechaSalida la fecha de salida del hospital
     * @param medicacion la medicación prescrita durante el ingreso
     */
    public Ingreso(
            Integer id,
            Integer idHistorial,
            LocalDate fechaEntrada,
            LocalDate fechaSalida,
            String medicacion) {
        this.id = id;
        this.idHistorial = idHistorial;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.medicacion = medicacion;
    }

    /**
     * Devuelve el identificador numérico del ingreso. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico del ingreso
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico del ingreso. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico del ingreso
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el identificador del historial clínico al que pertenece el ingreso.
     *
     * @return el identificador del historial clínico al que pertenece el ingreso
     */
    public Integer getIdHistorial() {
        return idHistorial;
    }

    /**
     * Establece el identificador del historial clínico al que pertenece el ingreso.
     *
     * @param idHistorial el identificador del historial clínico al que pertenece el ingreso
     */
    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    /**
     * Devuelve la fecha de entrada en el hospital.
     *
     * @return la fecha de entrada en el hospital
     */
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * Establece la fecha de entrada en el hospital.
     *
     * @param fechaEntrada la fecha de entrada en el hospital
     */
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * Devuelve la fecha de salida del hospital. Es {@code null} mientras siga ingresado.
     *
     * @return la fecha de salida del hospital
     */
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Establece la fecha de salida del hospital. Es {@code null} mientras siga ingresado.
     *
     * @param fechaSalida la fecha de salida del hospital
     */
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * Devuelve la medicación prescrita durante el ingreso.
     *
     * @return la medicación prescrita durante el ingreso
     */
    public String getMedicacion() {
        return medicacion;
    }

    /**
     * Establece la medicación prescrita durante el ingreso.
     *
     * @param medicacion la medicación prescrita durante el ingreso
     */
    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del ingreso en formato texto
     */
    @Override
    public String toString() {
        return "Ingreso["
                + "id=" + getId()
                + ", idHistorial=" + getIdHistorial()
                + ", fechaEntrada=" + getFechaEntrada()
                + ", fechaSalida=" + getFechaSalida()
                + ", medicacion=" + getMedicacion()
                + "]";
    }
}
