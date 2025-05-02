package com.inditex.productsort.model.domain;

import lombok.Getter;

@Getter
public abstract class SortingCriterion {

    protected final double weight;
    protected final String name;

    protected SortingCriterion(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public abstract double computeScore(Product product);
}
