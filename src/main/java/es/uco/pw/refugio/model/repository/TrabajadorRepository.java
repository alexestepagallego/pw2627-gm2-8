package es.uco.pw.refugio.model.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uco.pw.refugio.model.domain.Trabajador;
import es.uco.pw.refugio.model.domain.enums.CategoriaTrabajador;

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
            BufferedReader reader;
            File f = new File(sqlQueriesFileName);
            reader = new BufferedReader(new FileReader(f));
            sqlQueries.load(reader);
        } catch (IOException e) {
            System.err.println("Error creating properties object for SQL queries");
            e.printStackTrace();
        }
    }

    // 1. Obtener todos los trabajadores
    public List<Trabajador> findAllTrabajadores() {
        try {
            String query = sqlQueries.getProperty("select-findAllTrabajadores");
            if (query != null) {
                List<Trabajador> result = jdbcTemplate.query(query, new RowMapper<Trabajador>() {
                    public Trabajador mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return new Trabajador(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("dni"),
                            Date.valueOf(rs.getString("fecha_nacimiento")).toLocalDate(),
                            rs.getString("direccion"),
                            Date.valueOf(rs.getString("fecha_alta")).toLocalDate(),
                            CategoriaTrabajador.valueOf(rs.getString("categoria")),
                            rs.getString("num_colegiado") // Será null si no es veterinario
                        );
                    }
                });
                return result;
            } else {
                return null;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajadores");
            exception.printStackTrace();
            return null;
        }
    }

    // 2. Buscar trabajador por ID (consulta parametrizada de 1 fila)
    public Trabajador findTrabajadorById(int id) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadorById");
            if (query != null) {
                Trabajador result = jdbcTemplate.query(query, this::mapRowToTrabajador, id);
                return result;
            } else {
                return null;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajador with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

    // 3. Buscar trabajador por DNI
    public Trabajador findTrabajadorByDni(String dni) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadorByDni");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToTrabajador, dni);
            } else {
                return null;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajador with dni=" + dni);
            exception.printStackTrace();
            return null;
        }
    }

    // 4. Buscar trabajadores por Categoría (consulta parametrizada de varias filas)
    public List<Trabajador> findTrabajadoresByCategoria(CategoriaTrabajador categoria) {
        try {
            String query = sqlQueries.getProperty("select-findTrabajadoresByCategoria");
            if (query != null) {
                List<Trabajador> result = jdbcTemplate.query(query, new RowMapper<Trabajador>() {
                    public Trabajador mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return new Trabajador(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("dni"),
                            Date.valueOf(rs.getString("fecha_nacimiento")).toLocalDate(),
                            rs.getString("direccion"),
                            Date.valueOf(rs.getString("fecha_alta")).toLocalDate(),
                            categoria,
                            rs.getString("num_colegiado")
                        );
                    }
                }, categoria.toString());
                return result;
            } else {
                return null;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to find trabajadores with categoria=" + categoria.toString());
            exception.printStackTrace();
            return null;
        }
    }

    // 5. Insertar nuevo trabajador
    public boolean addTrabajador(Trabajador trabajador) {
        try {
            String query = sqlQueries.getProperty("insert-addTrabajador");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    trabajador.getNombre(),
                    trabajador.getApellidos(),
                    trabajador.getDni(),
                    trabajador.getFechaNacimiento().toString(),
                    trabajador.getDireccion(),
                    trabajador.getFechaAlta().toString(),
                    trabajador.getCategoria().toString(),
                    trabajador.getNumColegiado()
                );
                return result > 0;
            } else {
                return false;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert trabajador in the database");
            exception.printStackTrace();
            return false;
        }
    }

    // 6. Actualizar trabajador
    public boolean updateTrabajador(Trabajador trabajador) {
        try {
            String query = sqlQueries.getProperty("update-updateTrabajador");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    trabajador.getNombre(),
                    trabajador.getApellidos(),
                    trabajador.getDni(),
                    trabajador.getFechaNacimiento().toString(),
                    trabajador.getDireccion(),
                    trabajador.getFechaAlta().toString(),
                    trabajador.getCategoria().toString(),
                    trabajador.getNumColegiado(),
                    trabajador.getId()
                );
                return result > 0;
            } else {
                return false;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to update trabajador in the database");
            exception.printStackTrace();
            return false;
        }
    }

    // 7. Eliminar trabajador
    public boolean deleteTrabajador(int id) {
        try {
            String query = sqlQueries.getProperty("delete-deleteTrabajador");
            if (query != null) {
                int result = jdbcTemplate.update(query, id);
                return result > 0;
            } else {
                return false;
            }
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete trabajador from the database");
            exception.printStackTrace();
            return false;
        }
    }

    // Método extractor privado para consultas que devuelven 1 sola fila
    private Trabajador mapRowToTrabajador(ResultSet row) {
        try {
            if (row.first()) {
                int id = row.getInt("id");
                String nombre = row.getString("nombre");
                String apellidos = row.getString("apellidos");
                String dni = row.getString("dni");
                Date fechaNac = Date.valueOf(row.getString("fecha_nacimiento"));
                String direccion = row.getString("direccion");
                Date fechaAlta = Date.valueOf(row.getString("fecha_alta"));
                CategoriaTrabajador categoria = CategoriaTrabajador.valueOf(row.getString("categoria"));
                String numColegiado = row.getString("num_colegiado");

                return new Trabajador(id, nombre, apellidos, dni, fechaNac.toLocalDate(), direccion, fechaAlta.toLocalDate(), categoria, numColegiado);
            } else {
                return null;
            }
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return null;
        }
    }
}
