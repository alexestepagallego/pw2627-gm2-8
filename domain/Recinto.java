package es.uco.pw.refugio.domain;

import es.uco.pw.refugio.domain.enums.Especie;

/**
 * Recinto del refugio en el que se alojan animales de una especie.
 * La capacidad actual no se almacena: se calcula contando los animales alojados.
 */
public class Recinto {

    /** El identificador numérico del recinto. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El nombre del recinto. Es único. */
    private String nombre;

    /** La ubicación del recinto. */
    private String ubicacion;

    /** La capacidad máxima del recinto. */
    private int capacidadMaxima;

    /** La especie de animal que aloja el recinto. */
    private Especie tipoAnimal;

    /**
     * Crea un recinto vacío, para rellenarlo con los métodos {@code set}.
     */
    public Recinto() {
    }

    /**
     * Crea un recinto con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param ubicacion la ubicación
     * @param capacidadMaxima la capacidad máxima
     * @param tipoAnimal la especie de animal que aloja el recinto
     */
    public Recinto(Integer id, String nombre, String ubicacion, int capacidadMaxima, Especie tipoAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.tipoAnimal = tipoAnimal;
    }

    /**
     * Devuelve el identificador numérico del recinto. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico del recinto
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico del recinto. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico del recinto
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del recinto. Es único.
     *
     * @return el nombre del recinto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del recinto. Es único.
     *
     * @param nombre el nombre del recinto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la ubicación del recinto.
     *
     * @return la ubicación del recinto
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación del recinto.
     *
     * @param ubicacion la ubicación del recinto
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Devuelve la capacidad máxima del recinto.
     *
     * @return la capacidad máxima del recinto
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Establece la capacidad máxima del recinto.
     *
     * @param capacidadMaxima la capacidad máxima del recinto
     */
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Devuelve la especie de animal que aloja el recinto.
     *
     * @return la especie de animal que aloja el recinto
     */
    public Especie getTipoAnimal() {
        return tipoAnimal;
    }

    /**
     * Establece la especie de animal que aloja el recinto.
     *
     * @param tipoAnimal la especie de animal que aloja el recinto
     */
    public void setTipoAnimal(Especie tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del recinto en formato texto
     */
    @Override
    public String toString() {
        return "Recinto["
                + "id=" + getId()
                + ", nombre=" + getNombre()
                + ", ubicacion=" + getUbicacion()
                + ", capacidadMaxima=" + getCapacidadMaxima()
                + ", tipoAnimal=" + getTipoAnimal()
                + "]";
    }
}
