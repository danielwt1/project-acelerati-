package com.acelerati.management_service.domain.model;

public class PaginationModel {

    public static final Integer DEFAULT_PAGE_SIZE = 20;

    private Integer pageSize;
    private Integer pageNumber;
    private Long totalResults;
    private String description;

    public PaginationModel(Integer pageSize, Integer pageNumber, Long totalResults, String description) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalResults = totalResults;
        this.description = description;
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
}
