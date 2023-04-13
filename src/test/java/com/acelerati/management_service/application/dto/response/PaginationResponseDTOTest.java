package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PaginationResponseDTOTest {
    private static PaginationResponseDTO paginationResponseDTO;

    @BeforeAll
    static void beforeAll() {
        paginationResponseDTO = new PaginationResponseDTO(10L, 1L, 0L, 8L, 9L, "Showing 1 to 9 of 9 results.");
    }

    @Test
    void whenPageSizeAssignedItShouldBePreserved() {
        assertEquals(10L, paginationResponseDTO.getPageSize());
    }

    @Test
    void whenPageNumberAssignedItShouldBePreserved() {
        assertEquals(1L, paginationResponseDTO.getPageNumber());
    }

    @Test
    void whenFirstResultAssignedItShouldBePreserved() {
        assertEquals(0L, paginationResponseDTO.getFirstResultIndex());
    }

    @Test
    void whenLastResultAssignedItShouldBePreserved() {
        assertEquals(8L, paginationResponseDTO.getLastResultIndex());
    }

    @Test
    void whenTotalResultsAssignedItShouldBePreserved() {
        assertEquals(9L, paginationResponseDTO.getTotalResults());
    }

    @Test
    void whenDescriptionAssignedItShouldBePreserved() {
        assertEquals("Showing 1 to 9 of 9 results.", paginationResponseDTO.getDescription());
    }
}
