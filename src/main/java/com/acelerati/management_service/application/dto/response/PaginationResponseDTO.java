package com.acelerati.management_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "PaginationResponse",
        description = "Wrapper object to describe how datasets were paginated.")
public class PaginationResponseDTO {
    @Schema(description = "The current page number the user requested", example = "1")
    private Long pageNumber;
    @Schema(description = "The amount of results per page.", example = "20")
    private Long pageSize;
    @Schema(description = "The total amount of results. It totalizes the paginated and non-paginated ones.",
            example = "100")
    private Long totalResults;
    private Long firstResultIndex;
    private Long lastResultIndex;
    private String description;

}
