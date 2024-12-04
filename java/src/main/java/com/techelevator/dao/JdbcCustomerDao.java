package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Objects;

public class JdbcCustomerDao implements CustomerDao {
    @Override
    public List<Customer> getCustomer() {
        return null;
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public Customer getCustomerByUserName(String username) {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String customerEmail) {
        return null;
    }

    @Override
    public Customer createCustomer(CustomerDto customer) {
        return null;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }

    private Customer mapRowToCustomer(SqlRowSet rs) {
        return null;


    }
}
