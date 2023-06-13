package com.acelerati.management_service.domain.model;

import java.math.BigDecimal;

public class ReportModel {

    private Integer id_inventory;
    private Integer amount;
    private String name_product;
    private BigDecimal unit_price;
    private String sale_date;
    private BigDecimal total_price;

    public ReportModel() {
    }
    public ReportModel(Integer id_inventory, Integer amount, String name_product, BigDecimal unit_price, String sale_date, BigDecimal total_price) {
        this.id_inventory = id_inventory;
        this.amount = amount;
        this.name_product = name_product;
        this.unit_price = unit_price;
        this.sale_date = sale_date;
        this.total_price = total_price;
    }

    public Integer getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(Integer id_inventory) {
        this.id_inventory = id_inventory;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
}
