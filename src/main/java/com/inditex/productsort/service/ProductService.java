package com.inditex.productsort.service;

import com.inditex.productsort.mapper.ProductMapper;
import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.ScoringCriterion;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.dto.SortingRequestDTO;
import com.inditex.productsort.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SortingService sortingService;
    private final ScoringRegistry scoringRegistry;
    private final ProductMapper productMapper;

    public List<ProductDTO> sortProducts(SortingRequestDTO request) {
        Map<String, Double> weights = request.getWeights();

        log.debug("Retrieving all products from the database.");
        List<Product> products = productRepository.findAll().stream()
                .map(productMapper::toDomain)
                .toList();

        log.info("Total products retrieved: {}", products.size());

        List<ScoringCriterion> criteria = getCriteriaFromWeights(weights);

        List<Product> sortedProducts = sortingService.sortProducts(products, criteria);

        log.info("Sorting completed. Preparing DTOs with scores.");
        return sortedProducts.stream()
                .map(product -> {
                    double score = sortingService.calculateSortingScore(product, getCriteriaFromWeights(weights));
                    return productMapper.toDTO(product, score);
                })
                .toList();
    }

    private List<ScoringCriterion> getCriteriaFromWeights(Map<String, Double> weights) {
        log.debug("Getting sorting criteria.");
        return weights.entrySet().stream()
                .map(entry -> scoringRegistry.create(entry.getKey(), entry.getValue(), entry.getKey()))
                .toList();
    }
}
