package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class PaginationDTO {

    private final Integer pageSize;

    @NotNull(message = "The page number must be defined")
    @Min(value = 1, message = "The page number must be at least 1")
    private final Integer pageNumber;

}
