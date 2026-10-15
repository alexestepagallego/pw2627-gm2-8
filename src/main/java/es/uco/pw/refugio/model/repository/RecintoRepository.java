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

import es.uco.pw.refugio.model.domain.Recinto;
import es.uco.pw.refugio.model.domain.enums.Especie;

@Repository
public class RecintoRepository {

    private JdbcTemplate jdbcTemplate;
    private Properties sqlQueries;
    private String sqlQueriesFileName;

    public RecintoRepository(JdbcTemplate jdbcTemplate) {
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

    public List<Recinto> findAllRecintos() {
        try {
            String query = sqlQueries.getProperty("select-findAllRecintos");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Recinto>() {
                    public Recinto mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        return new Recinto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("ubicacion"),
                            rs.getInt("capacidad_maxima"),
                            Especie.valueOf(rs.getString("tipo_animal"))
                        );
                    }
                });
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find recintos");
            exception.printStackTrace();
            return null;
        }
    }

    public Recinto findRecintoById(int id) {
        try {
            String query = sqlQueries.getProperty("select-findRecintoById");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToRecinto, id);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find recinto with id=" + id);
            exception.printStackTrace();
            return null;
        }
    }

    public boolean addRecinto(Recinto recinto) {
        try {
            String query = sqlQueries.getProperty("insert-addRecinto");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    recinto.getNombre(),
                    recinto.getUbicacion(),
                    recinto.getCapacidadMaxima(),
                    recinto.getTipoAnimal().toString()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert recinto in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean updateRecinto(Recinto recinto) {
        try {
            String query = sqlQueries.getProperty("update-updateRecinto");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    recinto.getNombre(),
                    recinto.getUbicacion(),
                    recinto.getCapacidadMaxima(),
                    recinto.getTipoAnimal().toString(),
                    recinto.getId()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to update recinto in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecinto(int id) {
        try {
            String query = sqlQueries.getProperty("delete-deleteRecinto");
            if (query != null) {
                int result = jdbcTemplate.update(query, id);
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete recinto");
            exception.printStackTrace();
            return false;
        }
    }

    private Recinto mapRowToRecinto(ResultSet row) {
        try {
            if (row.first()) {
                return new Recinto(
                    row.getInt("id"),
                    row.getString("nombre"),
                    row.getString("ubicacion"),
                    row.getInt("capacidad_maxima"),
                    Especie.valueOf(row.getString("tipo_animal"))
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
