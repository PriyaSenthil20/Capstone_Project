package com.techelevator.dao;

import com.techelevator.model.ProductOption;
import com.techelevator.model.Size;

import java.math.BigDecimal;
import java.util.List;

public interface SizeDao {
    public List<Size> getSizes();


    public Size getSizeById(int id);

    public Size createSize(Size newSize);

    public Size setSizeMultiplierById(int id, BigDecimal price);
}
