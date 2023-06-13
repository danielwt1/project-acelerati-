package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.ReportModel;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface JasperReportPort {

    void generateReport(List<ReportModel> reports) throws JRException, FileNotFoundException;
}
