package es.uco.pw.refugio.domain.enums;

/**
 * Estado de salud de un animal. Solo los animales en estado {@link #SANO}
 * pueden darse en adopción o alojarse en un recinto.
 */
public enum EstadoSalud {

    /** El animal está sano. */
    SANO,

    /** El animal requiere atención veterinaria y debe estar en el hospital. */
    REQUIERE_ATENCION
}
