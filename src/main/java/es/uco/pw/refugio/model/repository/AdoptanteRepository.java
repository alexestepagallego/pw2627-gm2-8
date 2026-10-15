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

import es.uco.pw.refugio.model.domain.Adoptante;

@Repository
public class AdoptanteRepository {

    private JdbcTemplate jdbcTemplate;
    private Properties sqlQueries;
    private String sqlQueriesFileName;

    public AdoptanteRepository(JdbcTemplate jdbcTemplate) {
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

    public List<Adoptante> findAllAdoptantes() {
        try {
            String query = sqlQueries.getProperty("select-findAllAdoptantes");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Adoptante>() {
                    public Adoptante mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return new Adoptante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("dni"),
                            Date.valueOf(rs.getString("fecha_nacimiento")).toLocalDate(),
                            rs.getString("direccion"),
                            rs.getString("telefono")
                        );
                    }
                });
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find adoptantes");
            exception.printStackTrace();
            return null;
        }
    }

    public Adoptante findAdoptanteById(int id) {
        try {
            String query = sqlQueries.getProperty("select-findAdoptanteById");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToAdoptante, id);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find adoptante with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

    public Adoptante findAdoptanteByDni(String dni) {
        try {
            String query = sqlQueries.getProperty("select-findAdoptanteByDni");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToAdoptante, dni);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find adoptante with dni=" + dni);
            exception.printStackTrace();
            return null;
        }
    }

    public boolean addAdoptante(Adoptante adoptante) {
        try {
            String query = sqlQueries.getProperty("insert-addAdoptante");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    adoptante.getNombre(),
                    adoptante.getApellidos(),
                    adoptante.getDni(),
                    adoptante.getDireccion(),
                    adoptante.getFechaNacimiento().toString(),
                    adoptante.getTelefono()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert adoptante in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean updateAdoptante(Adoptante adoptante) {
        try {
            String query = sqlQueries.getProperty("update-updateAdoptante");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    adoptante.getNombre(),
                    adoptante.getApellidos(),
                    adoptante.getDni(),
                    adoptante.getDireccion(),
                    adoptante.getFechaNacimiento().toString(),
                    adoptante.getTelefono(),
                    adoptante.getId()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to update adoptante in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteAdoptante(int id) {
        try {
            String query = sqlQueries.getProperty("delete-deleteAdoptante");
            if (query != null) {
                int result = jdbcTemplate.update(query, id);
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete adoptante");
            exception.printStackTrace();
            return false;
        }
    }

    private Adoptante mapRowToAdoptante(ResultSet row) {
        try {
            if (row.first()) {
                return new Adoptante(
                    row.getInt("id"),
                    row.getString("nombre"),
                    row.getString("apellidos"),
                    row.getString("dni"),
                    Date.valueOf(row.getString("fecha_nacimiento")).toLocalDate(),
                    row.getString("direccion"),
                    row.getString("telefono")
                );
            }
            return null;
        } catch (SQLException exception) {
            System.err.println("Unable to retrieve results from the database");
            exception.printStackTrace();
            return null;
        }
    }
}
