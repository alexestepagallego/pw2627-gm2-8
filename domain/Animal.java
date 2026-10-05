package es.uco.pw.refugio.domain;

import java.time.LocalDate;

import es.uco.pw.refugio.domain.enums.Especie;
import es.uco.pw.refugio.domain.enums.EstadoSalud;

/**
 * Datos administrativos de un animal del refugio. Los datos clínicos se guardan
 * por separado en {@link HistorialClinico}.
 */
public class Animal {

    /** El código de registro del animal. Tiene 15 caracteres e identifica al animal (clave primaria). */
    private String codigoRegistro;

    /** La especie del animal. */
    private Especie especie;

    /** La raza del animal. Puede ser {@code null} si no procede. */
    private String raza;

    /** El nombre del animal. */
    private String nombre;

    /** La edad en años del animal. Es la edad en el momento del registro. */
    private int edad;

    /** El estado de salud del animal. */
    private EstadoSalud estadoSalud;

    /** La fecha de llegada al refugio del animal. */
    private LocalDate fechaLlegada;

    /** La fecha de fallecimiento del animal. Es {@code null} mientras el animal siga vivo. */
    private LocalDate fechaFallecimiento;

    /** El identificador del recinto donde está alojado el animal. Es {@code null} si está en el hospital, adoptado o fallecido. */
    private Integer idRecinto;

    /**
     * Crea un animal vacío, para rellenarlo con los métodos {@code set}.
     */
    public Animal() {
    }

    /**
     * Crea un animal con todos sus datos.
     *
     * @param codigoRegistro el código de registro
     * @param especie la especie
     * @param raza la raza
     * @param nombre el nombre
     * @param edad la edad en años
     * @param estadoSalud el estado de salud
     * @param fechaLlegada la fecha de llegada al refugio
     * @param fechaFallecimiento la fecha de fallecimiento
     * @param idRecinto el identificador del recinto donde está alojado el animal
     */
    public Animal(
            String codigoRegistro,
            Especie especie,
            String raza,
            String nombre,
            int edad,
            EstadoSalud estadoSalud,
            LocalDate fechaLlegada,
            LocalDate fechaFallecimiento,
            Integer idRecinto) {
        this.codigoRegistro = codigoRegistro;
        this.especie = especie;
        this.raza = raza;
        this.nombre = nombre;
        this.edad = edad;
        this.estadoSalud = estadoSalud;
        this.fechaLlegada = fechaLlegada;
        this.fechaFallecimiento = fechaFallecimiento;
        this.idRecinto = idRecinto;
    }

    /**
     * Devuelve el código de registro del animal. Tiene 15 caracteres e identifica al animal (clave primaria).
     *
     * @return el código de registro del animal
     */
    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    /**
     * Establece el código de registro del animal. Tiene 15 caracteres e identifica al animal (clave primaria).
     *
     * @param codigoRegistro el código de registro del animal
     */
    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    /**
     * Devuelve la especie del animal.
     *
     * @return la especie del animal
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * Establece la especie del animal.
     *
     * @param especie la especie del animal
     */
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    /**
     * Devuelve la raza del animal. Puede ser {@code null} si no procede.
     *
     * @return la raza del animal
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza del animal. Puede ser {@code null} si no procede.
     *
     * @param raza la raza del animal
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Devuelve el nombre del animal.
     *
     * @return el nombre del animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del animal.
     *
     * @param nombre el nombre del animal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la edad en años del animal. Es la edad en el momento del registro.
     *
     * @return la edad en años del animal
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad en años del animal. Es la edad en el momento del registro.
     *
     * @param edad la edad en años del animal
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Devuelve el estado de salud del animal.
     *
     * @return el estado de salud del animal
     */
    public EstadoSalud getEstadoSalud() {
        return estadoSalud;
    }

    /**
     * Establece el estado de salud del animal.
     *
     * @param estadoSalud el estado de salud del animal
     */
    public void setEstadoSalud(EstadoSalud estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    /**
     * Devuelve la fecha de llegada al refugio del animal.
     *
     * @return la fecha de llegada al refugio del animal
     */
    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Establece la fecha de llegada al refugio del animal.
     *
     * @param fechaLlegada la fecha de llegada al refugio del animal
     */
    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * Devuelve la fecha de fallecimiento del animal. Es {@code null} mientras el animal siga vivo.
     *
     * @return la fecha de fallecimiento del animal
     */
    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    /**
     * Establece la fecha de fallecimiento del animal. Es {@code null} mientras el animal siga vivo.
     *
     * @param fechaFallecimiento la fecha de fallecimiento del animal
     */
    public void setFechaFallecimiento(LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    /**
     * Devuelve el identificador del recinto donde está alojado el animal. Es {@code null} si está en el hospital, adoptado o fallecido.
     *
     * @return el identificador del recinto donde está alojado el animal
     */
    public Integer getIdRecinto() {
        return idRecinto;
    }

    /**
     * Establece el identificador del recinto donde está alojado el animal. Es {@code null} si está en el hospital, adoptado o fallecido.
     *
     * @param idRecinto el identificador del recinto donde está alojado el animal
     */
    public void setIdRecinto(Integer idRecinto) {
        this.idRecinto = idRecinto;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del animal en formato texto
     */
    @Override
    public String toString() {
        return "Animal["
                + "codigoRegistro=" + getCodigoRegistro()
                + ", especie=" + getEspecie()
                + ", raza=" + getRaza()
                + ", nombre=" + getNombre()
                + ", edad=" + getEdad()
                + ", estadoSalud=" + getEstadoSalud()
                + ", fechaLlegada=" + getFechaLlegada()
                + ", fechaFallecimiento=" + getFechaFallecimiento()
                + ", idRecinto=" + getIdRecinto()
                + "]";
    }
}
