package com.acelerati.management_service.infraestructure.output.entity.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReportProjection {
    public Integer getId_inventory();
    public Integer getAmount();
    public String getName_product();
    public BigDecimal getUnit_price();


    public LocalDate getSale_Date();




}
