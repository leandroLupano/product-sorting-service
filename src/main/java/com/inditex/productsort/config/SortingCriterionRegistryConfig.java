package com.inditex.productsort.config;

import com.inditex.productsort.model.domain.SalesUnitsSortingCriterion;
import com.inditex.productsort.model.domain.StockSortingCriterion;
import com.inditex.productsort.utils.SortingCriterionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SortingCriterionRegistryConfig {

    @Bean
    public SortingCriterionRegistry sortingCriterionRegistry() {
        SortingCriterionRegistry registry = new SortingCriterionRegistry();

        registry.register(SalesUnitsSortingCriterion.CRITERION_NAME, SalesUnitsSortingCriterion::new);
        registry.register(StockSortingCriterion.CRITERION_NAME, StockSortingCriterion::new);

        return registry;
    }
}
