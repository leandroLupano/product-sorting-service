package com.inditex.productsort.service;

import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.ScoringCriterion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SortingService {

    private final ScoringRegistry scoringRegistry;

    /**
     * Sorts a list of products based on the provided weights for each scoring criterion.
     *
     * @param products List of products to sort
     * @param criteria List of scoring criteria
     * @return List of sorted products
     */
    public List<Product> sortProducts(List<Product> products, List<ScoringCriterion> criteria) {
        log.debug("Applying sorting service.");
        return products.stream()
                .sorted(Comparator.comparingDouble(product -> -calculateSortingScore(product, criteria)))
                .toList();
    }

    /**
     * Calculates the total sorting score of a product by applying all the active criteria.
     *
     * @param product Product to score
     * @param criteria List of active scoring criteria
     * @return Calculated score
     */
    public double calculateSortingScore(Product product, List<ScoringCriterion> criteria) {
        log.debug("Calculating sorting score.");
        return criteria.stream()
                .map(criterion -> criterion.computeScore(product))
                .reduce(0.0, Double::sum);
    }
}
