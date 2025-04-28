package com.inditex.productsort.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int salesUnits;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_stocks", joinColumns = @JoinColumn(name = "product_id"))
    private List<StockEmbeddable> stocks;
}
