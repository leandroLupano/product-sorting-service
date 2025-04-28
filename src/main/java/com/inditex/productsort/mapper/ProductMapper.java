package com.inditex.productsort.mapper;

import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.Stock;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.entities.ProductEntity;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getSalesUnits(),
                entity.getStocks().stream()
                        .map(stock -> new Stock(stock.getSize(), stock.getQuantity()))
                        .collect(Collectors.toList())
        );
    }

    public ProductDTO toDTO(Product product, double sortingScore) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                sortingScore
        );
    }
}
