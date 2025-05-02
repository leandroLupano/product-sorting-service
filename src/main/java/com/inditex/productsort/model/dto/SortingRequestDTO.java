package com.inditex.productsort.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object containing weights for each sorting criterion.")
public class SortingRequestDTO {

    @NotNull(message = "weights map must not be null")
    @NotEmpty(message = "weights map must not be empty")
    @Schema(description = "Map of criterion name to weight. Example: {\"sales_units_criterion\": 0.7, \"stock_criterion\": 0.3}")
    private Map<String, @NotNull(message = "Each weight value must be provided") Double> weights;
}
