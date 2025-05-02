package com.inditex.productsort.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "stocks")
@EqualsAndHashCode(of = "id")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "sales_units")
    private int salesUnits;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_stocks", joinColumns = @JoinColumn(name = "product_id"))
    private List<StockEmbeddable> stocks;
}
