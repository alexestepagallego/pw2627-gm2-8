package es.uco.pw.refugio.domain;

/**
 * Centro educativo que puede solicitar visitas al refugio, con los datos de contacto
 * de un profesor.
 */
public class CentroEducativo {

    /** El código de centro del centro educativo. Tiene 8 caracteres alfanuméricos e identifica al centro (clave primaria). */
    private String codigoCentro;

    /** El nombre del centro educativo. */
    private String nombre;

    /** La dirección del centro educativo. */
    private String direccion;

    /** El nombre del profesor de contacto. */
    private String nombreProfesor;

    /** Los apellidos del profesor de contacto. */
    private String apellidosProfesor;

    /** El correo electrónico del profesor de contacto. */
    private String emailProfesor;

    /**
     * Crea un centro educativo vacío, para rellenarlo con los métodos {@code set}.
     */
    public CentroEducativo() {
    }

    /**
     * Crea un centro educativo con todos sus datos.
     *
     * @param codigoCentro el código de centro
     * @param nombre el nombre
     * @param direccion la dirección
     * @param nombreProfesor el nombre del profesor de contacto
     * @param apellidosProfesor los apellidos del profesor de contacto
     * @param emailProfesor el correo electrónico del profesor de contacto
     */
    public CentroEducativo(
            String codigoCentro,
            String nombre,
            String direccion,
            String nombreProfesor,
            String apellidosProfesor,
            String emailProfesor) {
        this.codigoCentro = codigoCentro;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreProfesor = nombreProfesor;
        this.apellidosProfesor = apellidosProfesor;
        this.emailProfesor = emailProfesor;
    }

    /**
     * Devuelve el código de centro del centro educativo. Tiene 8 caracteres alfanuméricos e identifica al centro (clave primaria).
     *
     * @return el código de centro del centro educativo
     */
    public String getCodigoCentro() {
        return codigoCentro;
    }

    /**
     * Establece el código de centro del centro educativo. Tiene 8 caracteres alfanuméricos e identifica al centro (clave primaria).
     *
     * @param codigoCentro el código de centro del centro educativo
     */
    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    /**
     * Devuelve el nombre del centro educativo.
     *
     * @return el nombre del centro educativo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del centro educativo.
     *
     * @param nombre el nombre del centro educativo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la dirección del centro educativo.
     *
     * @return la dirección del centro educativo
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del centro educativo.
     *
     * @param direccion la dirección del centro educativo
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el nombre del profesor de contacto.
     *
     * @return el nombre del profesor de contacto
     */
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    /**
     * Establece el nombre del profesor de contacto.
     *
     * @param nombreProfesor el nombre del profesor de contacto
     */
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    /**
     * Devuelve los apellidos del profesor de contacto.
     *
     * @return los apellidos del profesor de contacto
     */
    public String getApellidosProfesor() {
        return apellidosProfesor;
    }

    /**
     * Establece los apellidos del profesor de contacto.
     *
     * @param apellidosProfesor los apellidos del profesor de contacto
     */
    public void setApellidosProfesor(String apellidosProfesor) {
        this.apellidosProfesor = apellidosProfesor;
    }

    /**
     * Devuelve el correo electrónico del profesor de contacto.
     *
     * @return el correo electrónico del profesor de contacto
     */
    public String getEmailProfesor() {
        return emailProfesor;
    }

    /**
     * Establece el correo electrónico del profesor de contacto.
     *
     * @param emailProfesor el correo electrónico del profesor de contacto
     */
    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del centro educativo en formato texto
     */
    @Override
    public String toString() {
        return "CentroEducativo["
                + "codigoCentro=" + getCodigoCentro()
                + ", nombre=" + getNombre()
                + ", direccion=" + getDireccion()
                + ", nombreProfesor=" + getNombreProfesor()
                + ", apellidosProfesor=" + getApellidosProfesor()
                + ", emailProfesor=" + getEmailProfesor()
                + "]";
    }
}
