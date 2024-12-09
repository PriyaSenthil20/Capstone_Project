package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Size;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcSizeDao implements SizeDao{
    private final JdbcTemplate jdbcTemplate;

    private final String SELECTSIZESQL = "SELECT size_id, size_style, price_multiplier FROM size";

    public JdbcSizeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Size> getSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql  = SELECTSIZESQL;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Size size = mapRowToSize(results);
                sizes.add(size);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sizes;
    }


    @Override
    public Size getSizeById(int id) {
        Size size = null;
        String sql  = SELECTSIZESQL + " WHERE size_id=?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
            if (results.next()) {
                size = mapRowToSize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return size;
    }

    @Override
    public Size createSize(Size newSize) {
        int newSizeId;
        String sql  = "INSERT INTO public.size( size_style, price_multiplier) VALUES (?, ?) RETURNING size_id ";
        try {
            newSizeId = jdbcTemplate.queryForObject(sql, int.class, newSize.getSizeStyle(),newSize.getSizeMultiplier());
            return getSizeById(newSizeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Size setSizeMultiplierById(int id, BigDecimal price) {
        Size updatedSize = null;
        String sql = "UPDATE size " +
                "SET price_multiplier= ? " +
                "WHERE size_id = ?";
        try {

            int rowsAffected = jdbcTemplate.update(sql, price, id);
            if (rowsAffected != 1){
                throw new DaoException("DaoException");
            } else {
                updatedSize = getSizeById(id);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedSize;
    }

    private Size mapRowToSize(SqlRowSet rs) {
        Size size=new Size();
        size.setSizeId(rs.getInt("size_id"));
        size.setSizeStyle(rs.getString("size_style"));
        size.setSizeMultiplier(BigDecimal.valueOf(rs.getFloat("price_multiplier")));
        return size;
    }
}


