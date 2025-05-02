package com.inditex.productsort.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Product information including computed score for sorting.")
public class ProductDTO {

    @Schema(description = "Product identifier", example = "1")
    private Long id;

    @Schema(description = "Product name", example = "V-NECK BASIC SHIRT")
    private String name;

    @Schema(description = "Final sorting score based on selected criteria and weights", example = "85.5")
    private double sortingScore;
}
