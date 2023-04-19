package com.acelerati.management_service.domain.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaginationUtilTest {
    private PaginationUtil paginationUtil;

    @BeforeEach
    void setUp() {
        paginationUtil = new PaginationUtil();
    }

    @Test
    void whenUpdatingFieldsFromPaginationUtilThenItsValuesMustChange() {
        paginationUtil.setPageNumber(1L);
        paginationUtil.setDescription(PaginationUtil.NO_RECORDS_FOUND);
        paginationUtil.setFirstResultIndex(0L);
        paginationUtil.setLastResultIndex(0L);
        paginationUtil.setTotalResults(0L);

        assertEquals(1L, paginationUtil.getPageNumber());
        assertEquals(PaginationUtil.NO_RECORDS_FOUND, paginationUtil.getDescription());
        assertEquals(0L, paginationUtil.getFirstResultIndex());
        assertEquals(0L, paginationUtil.getLastResultIndex());
        assertEquals(0L, paginationUtil.getTotalResults());
    }

    @Test
    void whenPaginationUtilCreatedWithDefaultConstructorItsValuesMustBeNull() {
        assertNull(paginationUtil.getPageNumber());
        assertNull(paginationUtil.getDescription());
        assertNull(paginationUtil.getFirstResultIndex());
        assertNull(paginationUtil.getLastResultIndex());
        assertNull(paginationUtil.getTotalResults());
    }
}
