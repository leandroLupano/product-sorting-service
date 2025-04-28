package com.inditex.productsort.model.domain;

public class SalesUnitsCriterion extends ScoringCriterion {

    public SalesUnitsCriterion(double weight, String name) {
        super(weight, name);
    }

    @Override
    public double computeScore(Product product) {
        return product.getSalesUnits() * weight;
    }
}
