package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Driver;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDriverDao implements DriverDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDriverDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT driver_id, driver_name FROM drivers";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                drivers.add(mapRowToDriver(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }

        return drivers;
    }

    @Override
    public Driver getDriverById(int driverId) {
        Driver driver = null;
        String sql = "SELECT driver_id, driver_name, active FROM drivers WHERE driver_id = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, driverId);
            if (result.next()) {
                driver = mapRowToDriver(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }

        return driver;
    }

    @Override
    public Driver createDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driver_id, driver_name, active) " +
                "VALUES (?, ?, ?) RETURNING driver_id";

        try {
            Integer driverId = jdbcTemplate.queryForObject(sql, Integer.class,
                    driver.getDriverId(), driver.getDriverName(), driver.isActive());

            if (driverId != null) {
                driver.setDriverId(driverId);
                return driver;
            } else {
                throw new DaoException("Failed to create driver. ID is null.");
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to create driver.", e);
        }
    }

    @Override
    public Driver updateDriver(Driver driver) {
        String sql = "UPDATE drivers SET driver_name = ?, active = ? WHERE driver_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    driver.getDriverName(), driver.isActive(), driver.getDriverId());

            if (rowsAffected == 1) {
                return driver;
            } else {
                throw new DaoException("Failed to update driver. Driver not found.");
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to update driver.", e);
        }
    }

    private Driver mapRowToDriver(SqlRowSet rs) {
        Driver driver = new Driver();
        driver.setDriverId(rs.getInt("driver_id"));
        driver.setDriverName(rs.getString("driver_name"));
        return driver;
    }
}
