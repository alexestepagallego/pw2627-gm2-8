package es.uco.pw.refugio.domain.enums;

/**
 * Días laborables en los que se organizan visitas escolares.
 * Solo incluye de lunes a viernes. El orden de declaración es el orden cronológico,
 * por lo que {@link #ordinal()} y {@link #compareTo(Enum)} sirven para recorrer los días
 * en el orden natural que exige la asignación de monitores.
 */
public enum DiaSemana {

    /** Lunes. */
    LUNES,

    /** Martes. */
    MARTES,

    /** Miércoles. */
    MIERCOLES,

    /** Jueves. */
    JUEVES,

    /** Viernes. */
    VIERNES
}
