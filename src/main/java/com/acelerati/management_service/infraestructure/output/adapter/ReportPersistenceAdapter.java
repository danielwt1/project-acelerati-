package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.ReportModel;
import com.acelerati.management_service.domain.spi.ReportPersistencePort;
import com.acelerati.management_service.infraestructure.output.entity.projections.ReportProjection;
import com.acelerati.management_service.infraestructure.output.repository.SaleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportPersistenceAdapter implements ReportPersistencePort {

    private final SaleRepository saleRepository;

    public ReportPersistenceAdapter(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<ReportModel> findReportByDate(LocalDate dateSevenDaysBefore, LocalDate datenow) {
        return convertToReportModel(this.saleRepository.getReport(dateSevenDaysBefore, datenow));
    }

    private List<ReportModel> convertToReportModel(List<ReportProjection> reports){
        return reports.stream()
                .map(report-> new ReportModel(report.getId_inventory(),report.getAmount(),report.getName_product()
                        ,report.getUnit_price(),report.getSale_Date().toString()
                        ,getTotalPrice(report.getUnit_price(),report.getAmount())))
                .collect(Collectors.toList());
    }
    private BigDecimal getTotalPrice(BigDecimal unitPrice, Integer amount){
        return unitPrice.multiply(BigDecimal.valueOf(amount));
    }
}
