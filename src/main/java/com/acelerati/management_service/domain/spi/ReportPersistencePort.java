package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.ReportModel;

import java.time.LocalDate;
import java.util.List;

public interface ReportPersistencePort {
    List<ReportModel> findReportByDate(LocalDate dateSevenDaysBefore, LocalDate datenow);

}
