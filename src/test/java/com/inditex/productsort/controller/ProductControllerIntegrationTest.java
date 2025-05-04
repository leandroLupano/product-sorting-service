package com.inditex.productsort.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.productsort.model.dto.SortingRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listSortingCriteria_shouldReturnAvailableCriteria() throws Exception {
        mockMvc.perform(get("/products/criteria"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[?(@.name == 'sales_units_criterion')]").exists())
                .andExpect(jsonPath("$[?(@.name == 'stock_criterion')]").exists());
    }

    @Test
    void sortProducts_shouldReturnSortedProductList() throws Exception {
        SortingRequestDTO request = new SortingRequestDTO(Map.of(
                "sales_units_criterion", 1.0,
                "stock_criterion", 1.0
        ));

        mockMvc.perform(post("/products/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(6))
                .andExpect(jsonPath("$[0].sortingScore").isNumber())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void sortProducts_shouldReturnProductsSortedBySalesUnitsCriterion() throws Exception {
        SortingRequestDTO request = new SortingRequestDTO(Map.of(
                "sales_units_criterion", 0.99,
                "stock_criterion", 0.01
        ));

        mockMvc.perform(post("/products/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CONTRASTING LACE T-SHIRT"))
                .andExpect(jsonPath("$[1].name").value("V-NECH BASIC SHIRT"))
                .andExpect(jsonPath("$[5].name").value("PLEATED T-SHIRT"))
                .andExpect(jsonPath("$.length()").value(6));
    }

    @Test
    void sortProducts_shouldReturnErrorForInvalidCriterionKey() throws Exception {
        SortingRequestDTO request = new SortingRequestDTO(Map.of("test", 1.0)); // criterio inválido

        mockMvc.perform(post("/products/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void sortProducts_shouldReturnBadRequestForEmptyBody() throws Exception {
        mockMvc.perform(post("/products/sort")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void sortProducts_shouldReturnBadRequestForMalformedJson() throws Exception {
        String malformedJson = "{ weights: [ 'stock_criterion': 1.0 }";

        mockMvc.perform(post("/products/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest());
    }
}