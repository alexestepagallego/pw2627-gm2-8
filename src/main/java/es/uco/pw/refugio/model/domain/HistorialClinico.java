package es.uco.pw.refugio.model.domain;

/**
 * Historial clínico de un animal (relación 1:1). Se crea en su primera hospitalización
 * y vincula al animal siempre con el mismo veterinario, aunque tenga varios ingresos.
 */
public class HistorialClinico {

    /** El identificador numérico del historial clínico. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El código de registro del animal. */
    private String codigoAnimal;

    /** El identificador del veterinario responsable. */
    private Integer idVeterinario;

    /**
     * Crea un historial clínico vacío, para rellenarlo con los métodos {@code set}.
     */
    public HistorialClinico() {
    }

    /**
     * Crea un historial clínico con todos sus datos.
     *
     * @param id el identificador numérico
     * @param codigoAnimal el código de registro del animal
     * @param idVeterinario el identificador del veterinario responsable
     */
    public HistorialClinico(Integer id, String codigoAnimal, Integer idVeterinario) {
        this.id = id;
        this.codigoAnimal = codigoAnimal;
        this.idVeterinario = idVeterinario;
    }

    /**
     * Devuelve el identificador numérico del historial clínico. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico del historial clínico
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico del historial clínico. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico del historial clínico
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el código de registro del animal.
     *
     * @return el código de registro del animal
     */
    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    /**
     * Establece el código de registro del animal.
     *
     * @param codigoAnimal el código de registro del animal
     */
    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    /**
     * Devuelve el identificador del veterinario responsable.
     *
     * @return el identificador del veterinario responsable
     */
    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    /**
     * Establece el identificador del veterinario responsable.
     *
     * @param idVeterinario el identificador del veterinario responsable
     */
    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del historial clínico en formato texto
     */
    @Override
    public String toString() {
        return "HistorialClinico["
                + "id=" + getId()
                + ", codigoAnimal=" + getCodigoAnimal()
                + ", idVeterinario=" + getIdVeterinario()
                + "]";
    }
}
