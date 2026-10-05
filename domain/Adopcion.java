package es.uco.pw.refugio.domain;

import java.time.LocalDate;

/**
 * Adopción de un animal por parte de un adoptante, tramitada por un administrativo.
 * Una adopción deja de estar activa cuando fallece el animal.
 */
public class Adopcion {

    /** El identificador numérico de la adopción. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El código de registro del animal adoptado. */
    private String codigoAnimal;

    /** El identificador del adoptante. */
    private Integer idAdoptante;

    /** El identificador del administrativo que tramita la adopción. */
    private Integer idAdministrativo;

    /** La fecha de la adopción. */
    private LocalDate fechaAdopcion;

    /** Indica si la adopción está activa. Pasa a {@code false} al registrar el fallecimiento del animal. */
    private boolean activa = true;

    /**
     * Crea una adopción vacía, para rellenarla con los métodos {@code set}.
     */
    public Adopcion() {
    }

    /**
     * Crea una adopción con todos sus datos.
     *
     * @param id el identificador numérico
     * @param codigoAnimal el código de registro del animal adoptado
     * @param idAdoptante el identificador del adoptante
     * @param idAdministrativo el identificador del administrativo que tramita la adopción
     * @param fechaAdopcion la fecha de la adopción
     * @param activa si la adopción está activa
     */
    public Adopcion(
            Integer id,
            String codigoAnimal,
            Integer idAdoptante,
            Integer idAdministrativo,
            LocalDate fechaAdopcion,
            boolean activa) {
        this.id = id;
        this.codigoAnimal = codigoAnimal;
        this.idAdoptante = idAdoptante;
        this.idAdministrativo = idAdministrativo;
        this.fechaAdopcion = fechaAdopcion;
        this.activa = activa;
    }

    /**
     * Devuelve el identificador numérico de la adopción. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico de la adopción
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico de la adopción. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico de la adopción
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el código de registro del animal adoptado.
     *
     * @return el código de registro del animal adoptado
     */
    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    /**
     * Establece el código de registro del animal adoptado.
     *
     * @param codigoAnimal el código de registro del animal adoptado
     */
    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    /**
     * Devuelve el identificador del adoptante.
     *
     * @return el identificador del adoptante
     */
    public Integer getIdAdoptante() {
        return idAdoptante;
    }

    /**
     * Establece el identificador del adoptante.
     *
     * @param idAdoptante el identificador del adoptante
     */
    public void setIdAdoptante(Integer idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    /**
     * Devuelve el identificador del administrativo que tramita la adopción.
     *
     * @return el identificador del administrativo que tramita la adopción
     */
    public Integer getIdAdministrativo() {
        return idAdministrativo;
    }

    /**
     * Establece el identificador del administrativo que tramita la adopción.
     *
     * @param idAdministrativo el identificador del administrativo que tramita la adopción
     */
    public void setIdAdministrativo(Integer idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    /**
     * Devuelve la fecha de la adopción.
     *
     * @return la fecha de la adopción
     */
    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    /**
     * Establece la fecha de la adopción.
     *
     * @param fechaAdopcion la fecha de la adopción
     */
    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    /**
     * Indica si la adopción está activa. Pasa a {@code false} al registrar el fallecimiento del animal.
     *
     * @return {@code true} si la adopción está activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Establece si la adopción está activa. Pasa a {@code false} al registrar el fallecimiento del animal.
     *
     * @param activa si la adopción está activa
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos de la adopción en formato texto
     */
    @Override
    public String toString() {
        return "Adopcion["
                + "id=" + getId()
                + ", codigoAnimal=" + getCodigoAnimal()
                + ", idAdoptante=" + getIdAdoptante()
                + ", idAdministrativo=" + getIdAdministrativo()
                + ", fechaAdopcion=" + getFechaAdopcion()
                + ", activa=" + isActiva()
                + "]";
    }
}
