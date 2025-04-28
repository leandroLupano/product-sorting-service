package com.inditex.productsort.model.entities;

import com.inditex.productsort.model.domain.Size;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockEmbeddable {

    @Enumerated(EnumType.STRING)
    private Size size;

    private int quantity;
}
