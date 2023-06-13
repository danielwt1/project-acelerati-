package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.ReportModel;

import java.util.List;

public interface ReportDynamoDBPort {

    void insertReport(List<ReportModel> reportList);

}
