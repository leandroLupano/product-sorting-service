package com.inditex.productsort.service;

import com.inditex.productsort.model.domain.SortingCriterion;
import com.inditex.productsort.model.dto.SortingCriterionDTO;
import com.inditex.productsort.utils.SortingCriterionRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SortingCriterionServiceTest {

    private SortingCriterionRegistry sortingCriterionRegistry;
    private SortingCriterionService sortingCriterionservice;

    @BeforeEach
    void setUp() {
        sortingCriterionRegistry = mock(SortingCriterionRegistry.class);
        sortingCriterionservice = new SortingCriterionService(sortingCriterionRegistry);
    }

    @Test
    void getCriteriaWithWeights_shouldReturnListOfCriteria() {
        SortingCriterion mockSortingCriterion = mock(SortingCriterion.class);
        when(sortingCriterionRegistry.create("sales", 0.5)).thenReturn(mockSortingCriterion);

        Map<String, Double> weights = Map.of("sales", 0.5);
        List<SortingCriterion> result = sortingCriterionservice.getCriteriaWithWeights(weights);

        assertEquals(1, result.size());
        assertSame(mockSortingCriterion, result.getFirst());
    }

    @Test
    void getCriteriaWithWeights_shouldThrowExceptionForInvalidKey() {
        Map<String, Double> weights = Map.of("invalid", 1.0);

        when(sortingCriterionRegistry.create("invalid", 1.0)).thenThrow(new IllegalArgumentException("No sorting criterion registered for key: invalid"));

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sortingCriterionservice.getCriteriaWithWeights(weights));

        assertEquals("No sorting criterion registered for key: invalid", exception.getMessage());
    }

    @Test
    void getAvailableSortingCriteria_shouldReturnDTOList() {
        when(sortingCriterionRegistry.getAll()).thenReturn(Map.of("sales", weight -> mock(SortingCriterion.class)));

        List<SortingCriterionDTO> result = sortingCriterionservice.getAvailableSortingCriteria();

        assertEquals(1, result.size());
        assertEquals("sales", result.getFirst().getName());
    }

}