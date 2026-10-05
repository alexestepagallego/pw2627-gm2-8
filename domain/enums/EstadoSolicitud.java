package es.uco.pw.refugio.domain.enums;

/**
 * Estado de una solicitud de visita escolar.
 */
public enum EstadoSolicitud {

    /** Registrada, todavía sin monitor ni día asignados. */
    PENDIENTE,

    /** Con monitor y día asignados, pendiente de realizarse. */
    ASIGNADA,

    /** La visita se ha realizado y el monitor queda liberado para ese día. */
    FINALIZADA
}
