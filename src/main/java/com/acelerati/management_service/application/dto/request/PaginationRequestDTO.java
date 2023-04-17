package com.acelerati.management_service.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@ToString
@Schema(name = "PaginationRequest", description = "Represents how the data sets are going to be paginated once fetched")
public class PaginationRequestDTO {
    @Schema(description = "The page that will be displayed.", example = "1")
    @NotNull(message = "The page number must be defined")
    @Min(value = 1, message = "The page number must be at least 1")
    private final Long pageNumber;

    @Schema(description = "The amount of records in the dataset which will be included per page.",
            example = "20")
    private final Long pageSize;
}
