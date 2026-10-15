package es.uco.pw.refugio.model.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uco.pw.refugio.model.domain.CentroEducativo;

@Repository
public class CentroEducativoRepository {

    private JdbcTemplate jdbcTemplate;
    private Properties sqlQueries;
    private String sqlQueriesFileName;

    public CentroEducativoRepository(JdbcTemplate jdbcTemplate) {
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

    public List<CentroEducativo> findAllCentros() {
        try {
            String query = sqlQueries.getProperty("select-findAllCentros");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<CentroEducativo>() {
                    public CentroEducativo mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return new CentroEducativo(
                            rs.getString("codigo_centro"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("nombre_profesor"),
                            rs.getString("apellidos_profesor"),
                            rs.getString("email_profesor")
                        );
                    }
                });
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find centros educativos");
            exception.printStackTrace();
            return null;
        }
    }

    public CentroEducativo findCentroByCodigo(String codigoCentro) {
        try {
            String query = sqlQueries.getProperty("select-findCentroByCodigo");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToCentro, codigoCentro);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find centro educativo with codigo=" + codigoCentro);
            exception.printStackTrace();
            return null;
        }
    }

    public boolean addCentro(CentroEducativo centro) {
        try {
            String query = sqlQueries.getProperty("insert-addCentro");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    centro.getCodigoCentro(),
                    centro.getNombre(),
                    centro.getDireccion(),
                    centro.getNombreProfesor(),
                    centro.getApellidosProfesor(),
                    centro.getEmailProfesor()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert centro educativo in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean updateCentro(CentroEducativo centro) {
        try {
            String query = sqlQueries.getProperty("update-updateCentro");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    centro.getNombre(),
                    centro.getDireccion(),
                    centro.getNombreProfesor(),
                    centro.getApellidosProfesor(),
                    centro.getEmailProfesor(),
                    centro.getCodigoCentro()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to update centro educativo in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteCentro(String codigoCentro) {
        try {
            String query = sqlQueries.getProperty("delete-deleteCentro");
            if (query != null) {
                int result = jdbcTemplate.update(query, codigoCentro);
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete centro educativo");
            exception.printStackTrace();
            return false;
        }
    }

    private CentroEducativo mapRowToCentro(ResultSet row) {
        try {
            if (row.first()) {
                return new CentroEducativo(
                    row.getString("codigo_centro"),
                    row.getString("nombre"),
                    row.getString("direccion"),
                    row.getString("nombre_profesor"),
                    row.getString("apellidos_profesor"),
                    row.getString("email_profesor")
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
