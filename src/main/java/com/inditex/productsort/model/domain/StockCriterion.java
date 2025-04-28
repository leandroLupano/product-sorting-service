package com.inditex.productsort.model.domain;

public class StockCriterion extends ScoringCriterion {

    public StockCriterion(double weight, String name) {
        super(weight, name);
    }

    @Override
    public double computeScore(Product product) {
        long availableSizes = product.getStocks().stream()
                .filter(stock -> stock.getQuantity() > 0)
                .count();
        return availableSizes * weight;
    }
}
