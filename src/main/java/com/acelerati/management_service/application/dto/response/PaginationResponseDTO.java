package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationResponseDTO {

    private Integer pageSize;
    private Integer pageNumber;
    private Integer firstResultIndex;
    private Integer lastResultIndex;
    private Long totalResults;
    private String description;

}
