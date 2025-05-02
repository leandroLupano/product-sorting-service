package com.inditex.productsort.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Criterion available for sorting products.")
public class SortingCriterionDTO {

    @Schema(description = "Name of the sorting criterion", example = "sales_units_criterion")
    private String name;
}
