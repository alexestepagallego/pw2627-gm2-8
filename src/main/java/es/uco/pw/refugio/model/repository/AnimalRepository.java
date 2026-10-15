package es.uco.pw.refugio.model.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uco.pw.refugio.model.domain.Animal;
import es.uco.pw.refugio.model.domain.enums.EstadoSalud;
import es.uco.pw.refugio.model.domain.enums.TipoAnimal;

@Repository
public class AnimalRepository {

    private JdbcTemplate jdbcTemplate;
    private Properties sqlQueries;
    private String sqlQueriesFileName;

    public AnimalRepository(JdbcTemplate jdbcTemplate) {
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

    public List<Animal> findAllAnimales() {
        try {
            String query = sqlQueries.getProperty("select-findAllAnimales");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Animal>() {
                    public Animal mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        String fechaFallStr = rs.getString("fecha_fallecimiento");
                        LocalDate fechaFallecimiento = (fechaFallStr != null) ? Date.valueOf(fechaFallStr).toLocalDate() : null;

                        return new Animal(
                            rs.getString("codigo_registro"),
                            TipoAnimal.valueOf(rs.getString("especie")),
                            rs.getString("raza"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            EstadoSalud.valueOf(rs.getString("estado_salud")),
                            Date.valueOf(rs.getString("fecha_llegada")).toLocalDate(),
                            fechaFallecimiento
                        );
                    }
                });
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find animales");
            exception.printStackTrace();
            return null;
        }
    }

    public Animal findAnimalByCodigo(String codigoRegistro) {
        try {
            String query = sqlQueries.getProperty("select-findAnimalByCodigo");
            if (query != null) {
                return jdbcTemplate.query(query, this::mapRowToAnimal, codigoRegistro);
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find animal with codigo=" + codigoRegistro);
            exception.printStackTrace();
            return null;
        }
    }

    public List<Animal> findAnimalesByEspecie(TipoAnimal especie) {
        try {
            String query = sqlQueries.getProperty("select-findAnimalesByEspecie");
            if (query != null) {
                return jdbcTemplate.query(query, new RowMapper<Animal>() {
                    public Animal mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        String fechaFallStr = rs.getString("fecha_fallecimiento");
                        LocalDate fechaFallecimiento = (fechaFallStr != null) ? Date.valueOf(fechaFallStr).toLocalDate() : null;

                        return new Animal(
                            rs.getString("codigo_registro"),
                            especie,
                            rs.getString("raza"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            EstadoSalud.valueOf(rs.getString("estado_salud")),
                            Date.valueOf(rs.getString("fecha_llegada")).toLocalDate(),
                            fechaFallecimiento
                        );
                    }
                }, especie.toString());
            }
            return null;
        } catch (DataAccessException exception) {
            System.err.println("Unable to find animales with especie=" + especie.toString());
            exception.printStackTrace();
            return null;
        }
    }

    public boolean addAnimal(Animal animal) {
        try {
            String query = sqlQueries.getProperty("insert-addAnimal");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    animal.getCodigoRegistro(),
                    animal.getEspecie().toString(),
                    animal.getRaza(),
                    animal.getNombre(),
                    animal.getEdad(),
                    animal.getEstadoSalud().toString(),
                    animal.getFechaLlegada().toString(),
                    animal.getFechaFallecimiento() != null ? animal.getFechaFallecimiento().toString() : null
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to insert animal in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean updateAnimal(Animal animal) {
        try {
            String query = sqlQueries.getProperty("update-updateAnimal");
            if (query != null) {
                int result = jdbcTemplate.update(query,
                    animal.getEspecie().toString(),
                    animal.getRaza(),
                    animal.getNombre(),
                    animal.getEdad(),
                    animal.getEstadoSalud().toString(),
                    animal.getFechaLlegada().toString(),
                    animal.getFechaFallecimiento() != null ? animal.getFechaFallecimiento().toString() : null,
                    animal.getCodigoRegistro()
                );
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to update animal in the database");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteAnimal(String codigoRegistro) {
        try {
            String query = sqlQueries.getProperty("delete-deleteAnimal");
            if (query != null) {
                int result = jdbcTemplate.update(query, codigoRegistro);
                return result > 0;
            }
            return false;
        } catch (DataAccessException exception) {
            System.err.println("Unable to delete animal");
            exception.printStackTrace();
            return false;
        }
    }

    private Animal mapRowToAnimal(ResultSet row) {
        try {
            if (row.first()) {
                String fechaFallStr = row.getString("fecha_fallecimiento");
                LocalDate fechaFallecimiento = (fechaFallStr != null) ? Date.valueOf(fechaFallStr).toLocalDate() : null;

                return new Animal(
                    row.getString("codigo_registro"),
                    TipoAnimal.valueOf(row.getString("especie")),
                    row.getString("raza"),
                    row.getString("nombre"),
                    row.getInt("edad"),
                    EstadoSalud.valueOf(row.getString("estado_salud")),
                    Date.valueOf(row.getString("fecha_llegada")).toLocalDate(),
                    fechaFallecimiento
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
