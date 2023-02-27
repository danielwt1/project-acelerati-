package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Validated
public class PaginationDTO {

    private final Integer pageSize;

    @NotNull
    @Min(1)
    private final Integer pageNumber;

}
