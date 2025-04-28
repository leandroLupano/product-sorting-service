package com.inditex.productsort.service;

import com.inditex.productsort.model.domain.ScoringCriterion;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ScoringRegistry {

    private final Map<String, BiFunction<Double, String, ScoringCriterion>> registry = new HashMap<>();

    public void register(String key, BiFunction<Double, String, ScoringCriterion> creator) {
        registry.put(key, creator);
    }

    public ScoringCriterion create(String key, double weight, String name) {
        BiFunction<Double, String, ScoringCriterion> creator = registry.get(key);
        if (creator == null) {
            throw new IllegalArgumentException("No scoring criterion registered for key: " + key);
        }
        return creator.apply(weight, name);
    }

    public Map<String, BiFunction<Double, String, ScoringCriterion>> getAll() {
        return registry;
    }
}
