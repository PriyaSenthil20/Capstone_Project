package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
public class JdbcCustomerDao implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;
    private final String CUSTOMER_SELECT="SELECT customer_id,customer_first_name,customer_last_name,customer_address," +
            "customer_phone_number,customer_state,customer_city,customer_email_address,customer_zip_code ";
    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = CUSTOMER_SELECT+" FROM customers";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Customer customer = mapRowToCustomer(results);
                customers.add(customer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql =CUSTOMER_SELECT +"  FROM customers WHERE customer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                customer = mapRowToCustomer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }


    @Override
    public Customer getCustomerByEmail(String customerEmail) {
        Customer customer = null;
        String sql =CUSTOMER_SELECT +"  FROM customers WHERE customer_email_address = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerEmail);
            if (results.next()) {
                customer = mapRowToCustomer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }

    @Override
    public Customer createCustomer(CustomerDto customer) {
        Customer newCustomer = null;
        String insertCustomerSql = "INSERT INTO customers (customer_id, " +
                "customer_first_name,customer_last_name,customer_address," +
                "customer_phone_number,customer_state," +
                "customer_city,customer_email_address,customer_zip_code) values (?,UPPER(TRIM(?)),UPPER(TRIM(?)),?,?,?,LOWER(TRIM(?)),LOWER(TRIM(?)),?) ";

        try {
          jdbcTemplate.update(insertCustomerSql,customer.getCustomerId(),
                   customer.getCustomerFirstName(),customer.getCustomerLastName(),
                   customer.getCustomerAddress(),customer.getCustomerState(),
                   customer.getCustomerCity(),customer.getCustomerEmail(),
                   customer.getCustomerZipCode());
         return getCustomerById(customer.getCustomerId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private Customer mapRowToCustomer(SqlRowSet rs) {
        Customer customer=new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerFirstName(rs.getString("customer_first_name"));
        customer.setCustomerLastName(rs.getString("customer_last_name"));
        customer.setCustomerAddress(rs.getString("customer_address"));
        customer.setCustomerPhoneNumber(rs.getString("customer_phone_number"));
        customer.setCustomerCity(rs.getString("customer_city"));
        customer.setCustomerState(rs.getString("customer_state"));
        customer.setCustomerZipCode(rs.getInt("customer_zip_code"));
        customer.setCustomerEmail(rs.getString("customer_email_address"));
        return customer;



    }
}
