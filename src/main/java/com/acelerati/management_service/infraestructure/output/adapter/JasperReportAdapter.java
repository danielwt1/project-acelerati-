package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.ReportModel;
import com.acelerati.management_service.domain.spi.JasperReportPort;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportAdapter implements JasperReportPort {
    private static final String JASPER_ROUTE_BASE_CLASSPATH = "classpath:jasper/";
    private static final String DEFAULT_PDF_FORMAT = ".pdf";

    @Value("${report.jasper.path.generation}")
    private String reportPathGeneration;
    @Value("${report.jasper.path.image.header}")
    private String imageHeader;
    @Value("${report.jasper.path.image.footer}")
    private String imageFooter;

    private Map<String, Object> parameters;

    @Override
    @Async
    public void generateReport(List<ReportModel> reports) throws JRException, FileNotFoundException {
        parameters = new HashMap<>();
        try(        InputStream logoPresentacionStream = getClass().getResourceAsStream(imageHeader);
                    InputStream logoFooterStream = getClass().getResourceAsStream(imageFooter))
        {
            // se puede crear validacion para ver en que formato se quier eel reporte
            JasperReport reportJasper = compileReport(JASPER_ROUTE_BASE_CLASSPATH + "Leaf_Red.jrxml");
            //JasperReport reportJasper = loadCompiledReport(JASPER_ROUTE_BASE_CLASSPATH + "Leaf_Red.jasper");
            JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(reports.toArray());

            parameters.put("logoPresentacion", logoPresentacionStream);
            parameters.put("logofooter", logoFooterStream);
            parameters.put("type_report", "SALES REPORT FOR ACELERATI");
            parameters.put("ds", dataSource);
            JasperPrint print = printJasperReport(reportJasper, parameters, dataSource);
            // aca se puede validar segun lo que se quiera, el default sera pdf
            JasperExportManager.exportReportToPdfFile(print, reportPathGeneration + generateNameReport("SALES_REPORT_", DEFAULT_PDF_FORMAT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private JasperReport compileReport(String path) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(path);
        return JasperCompileManager.compileReport(file.getAbsolutePath());
    }

    private JasperPrint printJasperReport(JasperReport jasperReport, Map<String, Object> parameters, JRBeanArrayDataSource datasource) throws JRException {
        return JasperFillManager.fillReport(jasperReport, parameters, datasource);
    }
    private JasperReport loadCompiledReport(String path) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(path);
        return (JasperReport) JRLoader.loadObject(file);
    }
    private String generateNameReport(String type, String extension) {
        StringBuilder nameReport = new StringBuilder(type);
        LocalDate now = LocalDate.now();
        nameReport.append(now.getYear());
        nameReport.append(now.getMonthValue());
        nameReport.append(now.getDayOfMonth());
        nameReport.append(extension);
        return nameReport.toString();
    }
}
