package es.uco.pw.refugio.model.domain.enums;

/**
 * Categorías profesionales de los trabajadores del refugio.
 * Se almacena en la columna {@code trabajador.categoria}.
 */
public enum CategoriaTrabajador {

    /** Personal de recepción que registra animales y tramita adopciones. */
    ADMINISTRATIVO,

    /** Personal que realiza las visitas dirigidas a centros educativos. */
    MONITOR,

    /** Personal con titulación de veterinaria que atiende a los animales. */
    VETERINARIO
}
