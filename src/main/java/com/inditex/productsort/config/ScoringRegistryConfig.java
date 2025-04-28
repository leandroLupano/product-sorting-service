package com.inditex.productsort.config;

import com.inditex.productsort.model.domain.SalesUnitsCriterion;
import com.inditex.productsort.model.domain.StockCriterion;
import com.inditex.productsort.service.ScoringRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoringRegistryConfig {

    @Bean
    public ScoringRegistry scoringRegistry() {
        ScoringRegistry registry = new ScoringRegistry();

        registry.register("sales_units_criterion", SalesUnitsCriterion::new);
        registry.register("stock_criterion", StockCriterion::new);

        return registry;
    }
}
