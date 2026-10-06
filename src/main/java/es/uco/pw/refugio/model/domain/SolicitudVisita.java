package es.uco.pw.refugio.model.domain;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

import es.uco.pw.refugio.model.domain.enums.DiaSemana;
import es.uco.pw.refugio.model.domain.enums.EstadoSolicitud;
import es.uco.pw.refugio.model.domain.enums.NivelEducativo;

/**
 * Solicitud de visita de un centro educativo. Mientras está {@link EstadoSolicitud#PENDIENTE}
 * no tiene monitor ni día asignados.
 */
public class SolicitudVisita {

    /** El identificador numérico de la solicitud. Lo asigna la base de datos; {@code null} antes de insertar. */
    private Integer id;

    /** El código del centro educativo solicitante. */
    private String codigoCentro;

    /** La fecha en que se registró la solicitud. */
    private LocalDate fechaSolicitud;

    /** El número de estudiantes que realizarán la visita. */
    private int numEstudiantes;

    /** El nivel educativo del grupo. */
    private NivelEducativo nivelEducativo;

    /** Los días de la semana preferidos por el centro. Debe contener al menos un día. */
    private Set<DiaSemana> diasPreferidos = EnumSet.noneOf(DiaSemana.class);

    /** El estado de la solicitud. */
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    /** El identificador del monitor asignado. Es {@code null} mientras esté pendiente. */
    private Integer idMonitor;

    /** El día de la semana asignado a la visita. Es {@code null} mientras esté pendiente. */
    private DiaSemana diaAsignado;

    /**
     * Crea una solicitud vacía, para rellenarla con los métodos {@code set}.
     */
    public SolicitudVisita() {
    }

    /**
     * Crea una solicitud con todos sus datos.
     *
     * @param id el identificador numérico
     * @param codigoCentro el código del centro educativo solicitante
     * @param fechaSolicitud la fecha en que se registró la solicitud
     * @param numEstudiantes el número de estudiantes que realizarán la visita
     * @param nivelEducativo el nivel educativo del grupo
     * @param diasPreferidos los días de la semana preferidos por el centro
     * @param estado el estado
     * @param idMonitor el identificador del monitor asignado
     * @param diaAsignado el día de la semana asignado a la visita
     */
    public SolicitudVisita(
            Integer id,
            String codigoCentro,
            LocalDate fechaSolicitud,
            int numEstudiantes,
            NivelEducativo nivelEducativo,
            Set<DiaSemana> diasPreferidos,
            EstadoSolicitud estado,
            Integer idMonitor,
            DiaSemana diaAsignado) {
        this.id = id;
        this.codigoCentro = codigoCentro;
        this.fechaSolicitud = fechaSolicitud;
        this.numEstudiantes = numEstudiantes;
        this.nivelEducativo = nivelEducativo;
        this.diasPreferidos = diasPreferidos;
        this.estado = estado;
        this.idMonitor = idMonitor;
        this.diaAsignado = diaAsignado;
    }

    /**
     * Devuelve el identificador numérico de la solicitud. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @return el identificador numérico de la solicitud
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador numérico de la solicitud. Lo asigna la base de datos; {@code null} antes de insertar.
     *
     * @param id el identificador numérico de la solicitud
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve el código del centro educativo solicitante.
     *
     * @return el código del centro educativo solicitante
     */
    public String getCodigoCentro() {
        return codigoCentro;
    }

    /**
     * Establece el código del centro educativo solicitante.
     *
     * @param codigoCentro el código del centro educativo solicitante
     */
    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    /**
     * Devuelve la fecha en que se registró la solicitud.
     *
     * @return la fecha en que se registró la solicitud
     */
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Establece la fecha en que se registró la solicitud.
     *
     * @param fechaSolicitud la fecha en que se registró la solicitud
     */
    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * Devuelve el número de estudiantes que realizarán la visita.
     *
     * @return el número de estudiantes que realizarán la visita
     */
    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    /**
     * Establece el número de estudiantes que realizarán la visita.
     *
     * @param numEstudiantes el número de estudiantes que realizarán la visita
     */
    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    /**
     * Devuelve el nivel educativo del grupo.
     *
     * @return el nivel educativo del grupo
     */
    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    /**
     * Establece el nivel educativo del grupo.
     *
     * @param nivelEducativo el nivel educativo del grupo
     */
    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    /**
     * Devuelve los días de la semana preferidos por el centro. Debe contener al menos un día.
     *
     * @return los días de la semana preferidos por el centro
     */
    public Set<DiaSemana> getDiasPreferidos() {
        return diasPreferidos;
    }

    /**
     * Establece los días de la semana preferidos por el centro. Debe contener al menos un día.
     *
     * @param diasPreferidos los días de la semana preferidos por el centro
     */
    public void setDiasPreferidos(Set<DiaSemana> diasPreferidos) {
        this.diasPreferidos = diasPreferidos;
    }

    /**
     * Devuelve el estado de la solicitud.
     *
     * @return el estado de la solicitud
     */
    public EstadoSolicitud getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la solicitud.
     *
     * @param estado el estado de la solicitud
     */
    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el identificador del monitor asignado. Es {@code null} mientras esté pendiente.
     *
     * @return el identificador del monitor asignado
     */
    public Integer getIdMonitor() {
        return idMonitor;
    }

    /**
     * Establece el identificador del monitor asignado. Es {@code null} mientras esté pendiente.
     *
     * @param idMonitor el identificador del monitor asignado
     */
    public void setIdMonitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    /**
     * Devuelve el día de la semana asignado a la visita. Es {@code null} mientras esté pendiente.
     *
     * @return el día de la semana asignado a la visita
     */
    public DiaSemana getDiaAsignado() {
        return diaAsignado;
    }

    /**
     * Establece el día de la semana asignado a la visita. Es {@code null} mientras esté pendiente.
     *
     * @param diaAsignado el día de la semana asignado a la visita
     */
    public void setDiaAsignado(DiaSemana diaAsignado) {
        this.diaAsignado = diaAsignado;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos de la solicitud en formato texto
     */
    @Override
    public String toString() {
        return "SolicitudVisita["
                + "id=" + getId()
                + ", codigoCentro=" + getCodigoCentro()
                + ", fechaSolicitud=" + getFechaSolicitud()
                + ", numEstudiantes=" + getNumEstudiantes()
                + ", nivelEducativo=" + getNivelEducativo()
                + ", diasPreferidos=" + getDiasPreferidos()
                + ", estado=" + getEstado()
                + ", idMonitor=" + getIdMonitor()
                + ", diaAsignado=" + getDiaAsignado()
                + "]";
    }
}
