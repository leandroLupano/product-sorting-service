package com.inditex.productsort.model.domain;

public class StockSortingCriterion extends SortingCriterion {

    public static final String CRITERION_NAME = "stock_criterion";

    public StockSortingCriterion(double weight) {
        super(weight, CRITERION_NAME);
    }

    @Override
    public double computeScore(Product product) {
        if (product.getStocks() == null) return 0.0;

        long availableSizes = product.getStocks().stream()
                .filter(stock -> stock.getQuantity() > 0)
                .count();
        return availableSizes * weight;
    }
}
