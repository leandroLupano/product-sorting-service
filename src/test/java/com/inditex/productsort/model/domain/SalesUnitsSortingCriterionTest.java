package com.inditex.productsort.model.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesUnitsSortingCriterionTest {

    @Test
    void computeScore_shouldReturnSalesUnitsTimesWeight() {
        Product product = new Product(1L, "Test", 10, List.of());
        SortingCriterion criterion = new SalesUnitsSortingCriterion(2.0);

        double score = criterion.computeScore(product);

        assertEquals(20.0, score);
    }
}