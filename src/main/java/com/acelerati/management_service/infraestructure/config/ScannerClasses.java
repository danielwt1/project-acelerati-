package com.acelerati.management_service.infraestructure.config;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.List;
import java.util.stream.Collectors;

public class ScannerClasses {


    public static String[] scannerClasses(String basePackage) {

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));

        List<String> classNames = scanner.findCandidateComponents(basePackage)
                .stream()
                .map(BeanDefinition::getBeanClassName).collect(Collectors.toList());

                //.toList();
        return classNames.toArray(new String[0]);
    }

}