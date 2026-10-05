package es.uco.pw.refugio.domain;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

import es.uco.pw.refugio.domain.enums.CategoriaTrabajador;
import es.uco.pw.refugio.domain.enums.DiaSemana;

/**
 * Trabajador que realiza las visitas escolares en los días de la semana en que está disponible.
 */
public class Monitor extends Trabajador {

    /** Los días de la semana en que el monitor está disponible para visitas. Al incorporarse un monitor el conjunto está vacío. */
    private Set<DiaSemana> diasDisponibles = EnumSet.noneOf(DiaSemana.class);

    /**
     * Crea un monitor vacío, para rellenarlo con los métodos {@code set}.
     */
    public Monitor() {
    }

    /**
     * Crea un monitor con todos sus datos.
     *
     * @param id el identificador numérico
     * @param nombre el nombre
     * @param apellidos los apellidos
     * @param dni el DNI
     * @param fechaNacimiento la fecha de nacimiento
     * @param direccion la dirección postal
     * @param fechaAlta la fecha de alta en el centro
     * @param diasDisponibles los días de la semana en que el monitor está disponible para visitas
     */
    public Monitor(
            Integer id,
            String nombre,
            String apellidos,
            String dni,
            LocalDate fechaNacimiento,
            String direccion,
            LocalDate fechaAlta,
            Set<DiaSemana> diasDisponibles) {
        super(id, nombre, apellidos, dni, fechaNacimiento, direccion, fechaAlta);
        this.diasDisponibles = diasDisponibles;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link CategoriaTrabajador#MONITOR}
     */
    @Override
    public CategoriaTrabajador getCategoria() {
        return CategoriaTrabajador.MONITOR;
    }

    /**
     * Devuelve los días de la semana en que el monitor está disponible para visitas. Al incorporarse un monitor el conjunto está vacío.
     *
     * @return los días de la semana en que el monitor está disponible para visitas
     */
    public Set<DiaSemana> getDiasDisponibles() {
        return diasDisponibles;
    }

    /**
     * Establece los días de la semana en que el monitor está disponible para visitas. Al incorporarse un monitor el conjunto está vacío.
     *
     * @param diasDisponibles los días de la semana en que el monitor está disponible para visitas
     */
    public void setDiasDisponibles(Set<DiaSemana> diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    /**
     * Devuelve una representación textual con todos los datos, útil para depuración.
     *
     * @return los datos del monitor en formato texto
     */
    @Override
    public String toString() {
        return "Monitor["
                + "id=" + getId()
                + ", nombre=" + getNombre()
                + ", apellidos=" + getApellidos()
                + ", dni=" + getDni()
                + ", fechaNacimiento=" + getFechaNacimiento()
                + ", direccion=" + getDireccion()
                + ", fechaAlta=" + getFechaAlta()
                + ", diasDisponibles=" + getDiasDisponibles()
                + ", categoria=" + getCategoria()
                + "]";
    }
}
