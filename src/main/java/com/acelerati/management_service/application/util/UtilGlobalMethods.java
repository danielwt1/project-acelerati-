package com.acelerati.management_service.application.util;


import java.util.List;
import java.util.stream.Collectors;

public class UtilGlobalMethods {
    
    public static <T> List<T> dataPaginated(List<T> dataFiltered, int page, int elementPerPage){

        return dataFiltered.stream()
                .skip((long) (page - 1) * elementPerPage)
                .limit(elementPerPage)
                .collect(Collectors.toList());
    }
}
