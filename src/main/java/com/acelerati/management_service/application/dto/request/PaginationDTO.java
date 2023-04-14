package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@ToString
public class PaginationDTO {

    private final Long pageSize;

    @NotNull(message = "The page number must be defined")
    @Min(value = 1, message = "The page number must be at least 1")
    private final Long pageNumber;

}
