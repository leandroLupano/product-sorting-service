package com.inditex.productsort.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object containing weights for each sorting criterion.")
public class SortingRequestDTO {

    @Schema(description = "Map of criterion name to weight. Example: {\"sales\": 0.7, \"stock\": 0.3}")
    private Map<String, Double> weights;
}
