package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.ReportModel;
import com.acelerati.management_service.domain.spi.ReportDynamoDBPort;
import com.acelerati.management_service.infraestructure.output.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportDynamoAdapter implements ReportDynamoDBPort {

    private final ReportRepository reportRepository;

    public ReportDynamoAdapter(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Override
    @Async
    public void insertReport(List<ReportModel> reportList) {
        Map<String, List<ReportModel>> groupByDate = reportList.stream().collect(
                Collectors.groupingBy(ReportModel::getSale_date));
        groupByDate.forEach((key, value) -> reportRepository.putItemInTable("report", "date", key.toString(), "sales", convertSalesToJson(value)));
    }

    private String convertSalesToJson(List<ReportModel> object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String valuesSalesJson = null;
        try {
            valuesSalesJson = objectMapper.writeValueAsString(object);
            return valuesSalesJson;
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
