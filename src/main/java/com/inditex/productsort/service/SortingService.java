package com.inditex.productsort.service;

import com.inditex.productsort.mapper.ProductMapper;
import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.SortingCriterion;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.dto.SortingRequestDTO;
import com.inditex.productsort.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SortingService {

    private final ProductRepository productRepository;
    private final SortingCriterionService sortingCriterionService;
    private final ProductMapper productMapper;

    /**
     * Sorts a list of products based on the provided weights for each scoring criterion.
     *
     * @param request object containing weights for each sorting criterion
     * @return List of sorted products
     */
    public List<ProductDTO> sortProducts(SortingRequestDTO request) {
        Map<String, Double> weights = request.getWeights();

        log.debug("Retrieving all products from the database.");
        List<Product> products = productRepository.findAll().stream()
                .map(productMapper::toDomain)
                .toList();

        if (products.isEmpty()) {
            log.warn("No products found in the database.");
            return List.of();
        }

        log.info("Total products retrieved: {}", products.size());

        List<SortingCriterion> criteria = sortingCriterionService.getCriteriaWithWeights(weights);

        log.debug("Calculating scores and sorting products.");
        return products.stream()
                .map(product -> {
                    double score = calculateSortingScore(product, criteria);
                    return Map.entry(product, score);
                })
                .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .map(entry -> productMapper.toDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

    /**
     * Calculates the total sorting score of a product by applying all the active criteria.
     *
     * @param product Product to score
     * @param criteria List of active scoring criteria
     * @return Calculated score
     */
    public double calculateSortingScore(Product product, List<SortingCriterion> criteria) {
        double totalScore = criteria.stream()
                .mapToDouble(criterion -> criterion.computeScore(product))
                .sum();
        log.debug("Calculated raw score for product {}: {}", product.getName(), totalScore);
        return BigDecimal.valueOf(totalScore)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
