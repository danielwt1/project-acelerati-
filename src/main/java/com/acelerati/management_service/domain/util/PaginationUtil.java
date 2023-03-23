package com.acelerati.management_service.domain.util;

import java.util.List;

public class PaginationUtil {

    public static final Integer DEFAULT_PAGE_SIZE = 20;
    public static final String NO_RECORDS_FOUND = "No records found";

    private Integer pageSize;
    private Integer pageNumber;
    private String description;
    private Integer firstResultIndex;
    private Integer lastResultIndex;
    private Long totalResults;

    public PaginationUtil() {
        // This is used to prevent ambiguous constructors to Mapstructs
    }
    public PaginationUtil(Integer pageSize, Integer pageNumber, String description, Integer firstResultIndex,
                           Integer lastResultIndex, Long totalResults) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.description = description;
        this.firstResultIndex = firstResultIndex;
        this.lastResultIndex = lastResultIndex;
        this.totalResults = totalResults;
    }

    public PaginationModel(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Calculates the index of the first record of the page to select.
     * This method assumes that pageNumber and pageSize are already set for the pageable object.
     * @return the index which the database will use to start pulling the results.
     */
    public Integer getFirstResultIndex() {
        return firstResultIndex;
    }

    public Integer getLastResultIndex() {
        return lastResultIndex;
    }

    public void setLastResultIndex(Integer lastResultIndex) {
        this.lastResultIndex = lastResultIndex;
    }

    public Integer calculateOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public void updateAttributesFromListResults(List<?> results) {
        if (results.isEmpty()) {
            description = NO_RECORDS_FOUND;
            firstResultIndex = null;
            lastResultIndex = null;
            totalResults = null;
        } else {
            firstResultIndex = calculateOffset();
            lastResultIndex = firstResultIndex + results.size();
            description = String.format("Showing %d to %d of %d results.", firstResultIndex + 1, lastResultIndex, totalResults);
        }
    }
}
