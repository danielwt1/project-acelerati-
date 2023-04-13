package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationResponseDTO {

    private Long pageSize;
    private Long pageNumber;
    private Long firstResultIndex;
    private Long lastResultIndex;
    private Long totalResults;
    private String description;

}
