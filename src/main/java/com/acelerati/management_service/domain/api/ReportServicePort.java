package com.acelerati.management_service.domain.api;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportServicePort {
    void executeReport() throws JRException, FileNotFoundException;
}
