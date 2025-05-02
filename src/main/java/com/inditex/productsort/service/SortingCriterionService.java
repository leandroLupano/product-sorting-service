package com.inditex.productsort.service;

import com.inditex.productsort.model.domain.SortingCriterion;
import com.inditex.productsort.model.dto.SortingCriterionDTO;
import com.inditex.productsort.utils.SortingCriterionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SortingCriterionService {

    private final SortingCriterionRegistry sortingCriterionRegistry;

    /**
     * Assigns weights to each registered sorting criterion based on client input.
     *
     * @param weights a map of criterion name to weight
     * @return list of instantiated SortingCriterion with associated weights
     */
    public List<SortingCriterion> getCriteriaWithWeights(Map<String, Double> weights) {
        log.debug("Getting sorting criteria with weights.");
        return weights.entrySet().stream()
                .map(entry -> sortingCriterionRegistry.create(entry.getKey(), entry.getValue()))
                .toList();
    }

    /**
     * Retrieves all available sorting criteria.
     *
     * @return list of available criteria as DTOs
     */
    public List<SortingCriterionDTO> getAvailableSortingCriteria() {
        log.debug("Retrieving all available sorting criteria.");
        return sortingCriterionRegistry.getAll().keySet().stream()
                .map(SortingCriterionDTO::new)
                .toList();
    }
}
