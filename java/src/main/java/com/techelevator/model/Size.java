package com.techelevator.model;

import java.beans.BeanInfo;
import java.math.BigDecimal;

public class Size {
    private int sizeId;
    private String sizeStyle;
    private BigDecimal sizeMultiplier;
    public Size(){}
    public Size(int sizeId, String sizeStyle, BigDecimal sizeMultiplier) {
        this.sizeId = sizeId;
        this.sizeStyle = sizeStyle;
        this.sizeMultiplier = sizeMultiplier;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeStyle() {
        return sizeStyle;
    }

    public void setSizeStyle(String sizeStyle) {
        this.sizeStyle = sizeStyle;
    }

    public BigDecimal getSizeMultiplier() {
        return sizeMultiplier;
    }

    public void setSizeMultiplier(BigDecimal sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
    }
}
