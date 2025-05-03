package com.inditex.productsort.service;

import com.inditex.productsort.mapper.ProductMapper;
import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.SortingCriterion;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.dto.SortingRequestDTO;
import com.inditex.productsort.model.entities.ProductEntity;
import com.inditex.productsort.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SortingServiceTest {

    private ProductRepository productRepository;
    private SortingCriterionService sortingCriterionService;
    private ProductMapper productMapper;
    private SortingService sortingService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        sortingCriterionService = mock(SortingCriterionService.class);
        productMapper = mock(ProductMapper.class);
        sortingService = new SortingService(productRepository, sortingCriterionService, productMapper);
    }

    @Test
    void sortProducts_shouldReturnSortedProducts() {
        Product product1 = new Product(); product1.setName("Name_A");
        Product product2 = new Product(); product2.setName("Name_B");
        ProductEntity productEntity1 = mock(ProductEntity.class);
        ProductEntity productEntity2 = mock(ProductEntity.class);
        SortingCriterion criterion = mock(SortingCriterion.class);

        when(productRepository.findAll()).thenReturn(List.of(productEntity1, productEntity2));
        when(productMapper.toDomain(productEntity1)).thenReturn(product1);
        when(productMapper.toDomain(productEntity2)).thenReturn(product2);
        when(sortingCriterionService.getCriteriaWithWeights(anyMap())).thenReturn(List.of(criterion));
        when(criterion.computeScore(product1)).thenReturn(1.0);
        when(criterion.computeScore(product2)).thenReturn(3.0);
        when(productMapper.toDTO(eq(product1), anyDouble())).thenReturn(new ProductDTO(1L, "Name_A", 0.8));
        when(productMapper.toDTO(eq(product2), anyDouble())).thenReturn(new ProductDTO(2L, "Name_B", 0.2));

        SortingRequestDTO request = new SortingRequestDTO(Map.of("mock", 1.0));
        List<ProductDTO> result = sortingService.sortProducts(request);

        assertEquals("Name_B", result.get(0).getName());
        assertEquals("Name_A", result.get(1).getName());
    }

    @Test
    void calculateSortingScore_shouldReturnCorrectScoreRounded() {
        Product product = new Product();
        SortingCriterion criterion1 = mock(SortingCriterion.class);
        SortingCriterion criterion2 = mock(SortingCriterion.class);
        when(criterion1.computeScore(product)).thenReturn(0.333);
        when(criterion2.computeScore(product)).thenReturn(0.666);

        double result = sortingService.calculateSortingScore(product, List.of(criterion1, criterion2));

        assertEquals(1.0, result);
    }

}