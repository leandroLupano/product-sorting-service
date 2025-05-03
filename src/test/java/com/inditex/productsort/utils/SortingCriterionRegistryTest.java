package com.inditex.productsort.utils;

import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.SortingCriterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.DoubleFunction;

import static org.junit.jupiter.api.Assertions.*;

class SortingCriterionRegistryTest {

    private SortingCriterionRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new SortingCriterionRegistry();
    }

    @Test
    void register_shouldStoreCriterionFactory() {
        registry.register("mock", w -> new SortingCriterion(w, "mock") {
            @Override
            public double computeScore(Product product) {
                return w;
            }
        });

        assertTrue(registry.getAll().containsKey("mock"));
    }

    @Test
    void register_shouldThrowExceptionWhenKeyAlreadyRegistered() {
        DoubleFunction<SortingCriterion> factory = weight -> new SortingCriterion(weight, "mock") {
            @Override
            public double computeScore(Product product) {
                return weight;
            }
        };
        registry.register("key", factory);

        Exception ex = assertThrows(IllegalStateException.class, () -> registry.register("key", factory));

        assertTrue(ex.getMessage().contains("already registered"));
    }

    @Test
    void create_shouldReturnCriterionFromRegisteredFactory() {
        registry.register("mock", weight -> new SortingCriterion(weight, "mock") {
            @Override
            public double computeScore(Product product) {
                return weight + 10;
            }
        });

        SortingCriterion criterion = registry.create("mock", 5.0);

        assertNotNull(criterion);
        assertEquals("mock", criterion.getName());
        assertEquals(5.0, criterion.getWeight());
        assertEquals(15.0, criterion.computeScore(null));
    }

    @Test
    void create_shouldThrowExceptionWhenKeyNotRegistered() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> registry.create("invalid", 1.0));
        assertTrue(ex.getMessage().contains("No sorting criterion registered"));
    }

    @Test
    void getAll_shouldReturnUnmodifiableMapWithRegisteredCriteria() {
        registry.register("x", weight -> new SortingCriterion(weight, "X") {
            @Override
            public double computeScore(Product product) {
                return weight;
            }
        });

        Map<String, DoubleFunction<SortingCriterion>> all = registry.getAll();

        assertEquals(1, all.size());
        assertTrue(all.containsKey("x"));
        assertThrows(UnsupportedOperationException.class, () -> all.put("y", weight -> null));
    }

}