package com.inditex.productsort.model.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockSortingCriterionTest {

    @Test
    void computeScore_shouldCountAvailableSizes() {
        List<Stock> stocks = List.of(
                new Stock(Size.S, 0),
                new Stock(Size.M, 3),
                new Stock(Size.L, 5)
        );
        Product product = new Product(1L, "Test", 0, stocks);
        SortingCriterion criterion = new StockSortingCriterion(1.5);

        double score = criterion.computeScore(product);

        assertEquals(2 * 1.5, score);
    }

    @Test
    void computeScore_shouldReturnZeroWhenNoStocks() {
        Product product = new Product(1L, "Test", 0, null);
        SortingCriterion criterion = new StockSortingCriterion(1.0);

        double score = criterion.computeScore(product);

        assertEquals(0.0, score);
    }
}