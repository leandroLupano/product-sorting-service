package com.inditex.productsort.model.domain;

public class SalesUnitsSortingCriterion extends SortingCriterion {

    public static final String CRITERION_NAME = "sales_units_criterion";

    public SalesUnitsSortingCriterion(double weight) {
        super(weight, CRITERION_NAME);
    }

    @Override
    public double computeScore(Product product) {
        return product.getSalesUnits() * weight;
    }
}
