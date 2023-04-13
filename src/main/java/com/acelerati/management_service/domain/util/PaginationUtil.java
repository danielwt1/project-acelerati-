package com.acelerati.management_service.domain.util;

import com.acelerati.management_service.domain.exception.PageOutOfBoundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationUtil {
    public static final Logger logger = LoggerFactory.getLogger(PaginationUtil.class);
    public static final Long DEFAULT_PAGE_SIZE = 20L;
    public static final String NO_RECORDS_FOUND = "No records found";

    private Long pageSize;
    private Long pageNumber;
    private String description;
    private Long firstResultIndex;
    private Long lastResultIndex;
    private Long totalResults;

    public PaginationUtil() {
        // This is used to prevent ambiguous constructors to Mapstructs
    }

    public PaginationUtil(Long pageSize, Long pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public String getDescription() {
        return description;
    }


    public Long getTotalResults() {
        return totalResults;
    }


    /**
     * Calculates the index of the first record of the page to select.
     * This method assumes that pageNumber and pageSize are already set for the pageable object.
     * @return the index which the database will use to start pulling the results.
     */
    public Long getFirstResultIndex() {
        return firstResultIndex;
    }

    public Long getLastResultIndex() {
        return lastResultIndex;
    }

    public Long calculateOffset() {
        return (pageNumber - 1L) * pageSize;
    }

    public <T> void updateAttributesFromListResults(List<T> results) throws PageOutOfBoundsException {
        if (results.isEmpty()) {
            totalResults = 0L;
            description = NO_RECORDS_FOUND;
            firstResultIndex = 0L;
            lastResultIndex = 0L;
        } else {
            totalResults = (long) results.size();
            if (isPageOutOfBounds()) {
                throw new PageOutOfBoundsException("This record set cannot be navigated any further");
            }
            firstResultIndex = calculateOffset();
            lastResultIndex = Math.min(totalResults - 1, firstResultIndex + pageSize - 1);
            description = String.format("Showing %d to %d of %d results.", firstResultIndex + 1, lastResultIndex + 1, totalResults);
        }
    }

    private boolean isPageOutOfBounds() {
        return calculateOffset() >= totalResults;
    }

    public <T> List<T> getPaginatedData(List<T> unpaginatedData) {
        if (!unpaginatedData.isEmpty()) {
            logger.debug("Applying pagination, skipping {} and limiting to {} records", firstResultIndex, lastResultIndex + 1 - firstResultIndex);
            return unpaginatedData.stream()
                    .skip(firstResultIndex)
                    .limit(lastResultIndex + 1 - firstResultIndex)
                    .collect(Collectors.toList());
        }
        return unpaginatedData;
    }
}
