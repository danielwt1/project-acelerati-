package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.ReportServicePort;
import com.acelerati.management_service.domain.model.ReportModel;
import com.acelerati.management_service.domain.spi.JasperReportPort;
import com.acelerati.management_service.domain.spi.ReportDynamoDBPort;
import com.acelerati.management_service.domain.spi.ReportPersistencePort;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class CreateReportUseCase implements ReportServicePort {

    private final ReportDynamoDBPort reportDynamoDBPort;
    private final ReportPersistencePort reportPersistencePort;
    private final JasperReportPort jasperReportPort;

    public CreateReportUseCase(ReportDynamoDBPort reportDynamoDBPort, ReportPersistencePort reportPersistencePort, JasperReportPort jasperReportPort) {
        this.reportDynamoDBPort = reportDynamoDBPort;
        this.reportPersistencePort = reportPersistencePort;
        this.jasperReportPort = jasperReportPort;
    }

    public void executeReport() throws JRException, FileNotFoundException {
        LocalDate now = LocalDate.now();
        LocalDate sevenDaysBefore = now.minusDays(7);
        List<ReportModel> reports = reportPersistencePort.findReportByDate(sevenDaysBefore,now);
        reportDynamoDBPort.insertReport(reports);
        jasperReportPort.generateReport(reports);


    }
}
