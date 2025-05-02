package com.inditex.productsort.controller;

import com.inditex.productsort.model.dto.SortingCriterionDTO;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.dto.SortingRequestDTO;
import com.inditex.productsort.service.SortingCriterionService;
import com.inditex.productsort.service.SortingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Sorting API", description = "Endpoints for sorting and managing products based on configurable criteria")

public class ProductController {

    private final SortingService sortingService;
    private final SortingCriterionService sortingCriterionService;

    @Operation(summary = "Get all available sorting criteria",
            description = "Retrieve all criteria that can be used to sort the products.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Criteria retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SortingCriterionDTO.class)))
            })
    @GetMapping("/criteria")
    public ResponseEntity<List<SortingCriterionDTO>> listSortingCriteria() {
        log.info("Fetching all available sorting criteria.");
        return ResponseEntity.ok(sortingCriterionService.getAvailableSortingCriteria());
    }

    @Operation(summary = "Sort products based on criteria weights",
            description = "Sorts the products by applying the weights provided for each criterion.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"weights\": {\"sales_units_criterion\": 0.7, \"stock_criterion\": 0.3}}"
                            ),
                            schema = @Schema(implementation = SortingRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products sorted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDTO.class)))
            })
    @PostMapping("/sort")
    public ResponseEntity<List<ProductDTO>> sortProducts(@Valid @RequestBody SortingRequestDTO request) {
        log.info("Sorting products with weights: {}", request.getWeights());
        List<ProductDTO> sortedProducts = sortingService.sortProducts(request);
        log.info("Products successfully sorted. Total: {}", sortedProducts.size());
        return ResponseEntity.ok(sortedProducts);
    }
}
