package com.acelerati.management_service.infraestructure.output.repository;

public interface ReportRepository {

    void putItemInTable(String tableName,String date,String dateVal,String sales,String salesVal);

}
