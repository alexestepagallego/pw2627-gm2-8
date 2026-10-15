package es.uco.pw.refugio.model.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uco.pw.refugio.model.domain.Administrativo;
import es.uco.pw.refugio.model.domain.Monitor;
import es.uco.pw.refugio.model.domain.Trabajador;
import es.uco.pw.refugio.model.domain.Veterinario;
import es.uco.pw.refugio.model.domain.enums.CategoriaTrabajador;
import es.uco.pw.refugio.model.domain.enums.DiaSemana;

@Repository
public class TrabajadorRepository {

    private JdbcTemplate jdbcTemplate;
    private Properties sqlQueries;
    private String sqlQueriesFileName;

    public TrabajadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSQLQueriesFileName(String sqlQueriesFileName) {
        this.sqlQueriesFileName = sqlQueriesFileName;
        createProperties();
    }

    private void createProperties() {
        sqlQueries = new Properties();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(sqlQueriesFileName)));
            sqlQueries.load(reader);
        } catch (IOException e) {
            System.err.println("Error creating properties object for SQL queries");
            e.printStackTrace();
        }
    }

    public List<Trabajador> findAllTrabajadores() {
        try {
            String query = sqlQueries.getProperty("select-findAllTrabajadores");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Trabajador>() {
                    @Override
                    public Trabajador mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return construirTrabajadorDesdeResultSet(rs);
                    }
                });
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajadores");
            exception.printStackTrace();
            return null;
        }
    }

    public Trabajador findTrabajadorById(int id) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadorById");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToTrabajador, id);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajador with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

    public Trabajador findTrabajadorByDni(String dni) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadorByDni");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToTrabajador, dni);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajador with dni=" + dni);
            exception.printStackTrace();
            return null;
        }
    }

    public List<Trabajador> findTrabajadoresByCategoria(CategoriaTrabajador categoria) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadoresByCategoria");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Trabajador>() {
                    @Override
                    public Trabajador mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return construirTrabajadorDesdeResultSet(rs);
                    }
                }, categoria.toString());
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajadores with categoria=" + categoria.toString());
            exception.printStackTrace();
            return null;
        }
    }

    public boolean addTrabajador(Trabajador trabajador) {
        try {
            String query = sqlQueries.getProperty("insert-addTrabajador");
            if (query != null) {
                String numColegiado = null;
                if (trabajador instanceof Veterinario) {
                    numColegiado = ((Veterinario) trabajador).getNumeroColegiado();
                }

                int result = jdbcTemplate.update(query,
                    trabajador.getNombre(),
                    trabajador.getApellidos(),
                    trabajador.getDni(),
                    trabajador.getFechaNacimiento().toString(),
                    trabajador.getDireccion(),
                    trabajador.getFechaAlta().toString(),
                    trabajador.getCategoria().toString(),
                    numColegiado
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert trabajador in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean updateTrabajador(Trabajador trabajador) {
        try {
            String query = sqlQueries.getProperty("update-updateTrabajador");
            if (query != null) {
                String numColegiado = null;
                if (trabajador instanceof Veterinario) {
                    numColegiado = ((Veterinario) trabajador).getNumeroColegiado();
                }

                int result = jdbcTemplate.update(query,
                    trabajador.getNombre(),
                    trabajador.getApellidos(),
                    trabajador.getDni(),
                    trabajador.getFechaNacimiento().toString(),
                    trabajador.getDireccion(),
                    trabajador.getFechaAlta().toString(),
                    trabajador.getCategoria().toString(),
                    numColegiado,
                    trabajador.getId()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to update trabajador in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteTrabajador(int id) {
        try {
            String query = sqlQueries.getProperty("delete-deleteTrabajador");
            if (query != null) {
                int result = jdbcTemplate.update(query, id);
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete trabajador from the database");
            exception.printStackTrace();
            return false;
        }
    }

    private Trabajador mapRowToTrabajador(ResultSet row) {
        try {
            if (row.first()) {
                return construirTrabajadorDesdeResultSet(row);
            }
            return null;
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return null;
        }
    }

    private static Trabajador construirTrabajadorDesdeResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String dni = rs.getString("dni");
        LocalDate fechaNac = Date.valueOf(rs.getString("fecha_nacimiento")).toLocalDate();
        String direccion = rs.getString("direccion");
        LocalDate fechaAlta = Date.valueOf(rs.getString("fecha_alta")).toLocalDate();
        CategoriaTrabajador categoria = CategoriaTrabajador.valueOf(rs.getString("categoria"));
        String numColegiado = rs.getString("num_colegiado");

        switch (categoria) {
            case VETERINARIO:
                return new Veterinario(id, nombre, apellidos, dni, fechaNac, direccion, fechaAlta, numColegiado);
            case MONITOR:
                return new Monitor(id, nombre, apellidos, dni, fechaNac, direccion, fechaAlta, EnumSet.noneOf(DiaSemana.class));
            case ADMINISTRATIVO:
            default:
                return new Administrativo(id, nombre, apellidos, dni, fechaNac, direccion, fechaAlta);
        }
    }
}
