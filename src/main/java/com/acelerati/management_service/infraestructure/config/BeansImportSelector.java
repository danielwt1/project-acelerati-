package com.acelerati.management_service.infraestructure.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


import java.util.Arrays;

import static com.acelerati.management_service.ManagementServiceApplication.ADAPTERS_ROUTES;
import static com.acelerati.management_service.ManagementServiceApplication.USECASES_ROUTE;

public class BeansImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] useCaseClasses = ScannerClasses.scannerClasses(USECASES_ROUTE);
        String[] adapterClasses = ScannerClasses.scannerClasses(ADAPTERS_ROUTES);

        String[] totalScanner = new String[useCaseClasses.length + adapterClasses.length];
        System.out.println(totalScanner.length);
        System.arraycopy(useCaseClasses, 0, totalScanner, 0, useCaseClasses.length);

        System.arraycopy(adapterClasses, 0, totalScanner, useCaseClasses.length, adapterClasses.length);

        //Logger.getLogger(BeanImportSelector.class.getName()).info("Imported Beans: " + StringUtils.arrayToCommaDelimitedString(totalScanner));

        return totalScanner;
    }
}

