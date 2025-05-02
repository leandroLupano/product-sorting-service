package com.inditex.productsort.utils;

import com.inditex.productsort.model.domain.SortingCriterion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleFunction;

/**
 * Registry that holds available sorting criteria and provides instances on demand using a factory function.
 */
public class SortingCriterionRegistry {

    private final Map<String, DoubleFunction<SortingCriterion>> registry = new HashMap<>();

    public void register(String key, DoubleFunction<SortingCriterion> factory) {
        if (registry.containsKey(key)) {
            throw new IllegalStateException("Criterion already registered: " + key);
        }
        registry.put(key, factory);
    }

    public SortingCriterion create(String key, double weight) {
        DoubleFunction<SortingCriterion> creator = registry.get(key);
        if (creator == null) {
            throw new IllegalArgumentException("No sorting criterion registered for key: " + key);
        }
        return creator.apply(weight);
    }

    public Map<String, DoubleFunction<SortingCriterion>> getAll() {
        return Map.copyOf(registry);
    }

}
