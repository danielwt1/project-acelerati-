package com.acelerati.management_service.application.handler.scheduled.task;

import com.acelerati.management_service.domain.api.ReportServicePort;
import net.sf.jasperreports.engine.JRException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ReportSpringService {
    private final ReportServicePort reportServicePort;

    public ReportSpringService(ReportServicePort reportServicePort) {
        this.reportServicePort = reportServicePort;
    }
    @Scheduled(cron = "0 * * * * ?")
    //@Scheduled(cron = "0 0 0 ? * MON")
    public void execute() throws JRException, FileNotFoundException {
        reportServicePort.executeReport();
    }
}
