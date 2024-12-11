package com.techelevator.dao;

import com.techelevator.model.ProductOption;
import com.techelevator.model.Size;

import java.math.BigDecimal;
import java.util.List;

public interface SizeDao {
    List<Size> getSizes();


    Size getSizeById(int id);

    Size createSize(Size newSize);

    Size setSizeMultiplierById(int id, BigDecimal price);
}
