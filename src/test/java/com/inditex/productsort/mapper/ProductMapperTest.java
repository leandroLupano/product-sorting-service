package com.inditex.productsort.mapper;

import com.inditex.productsort.model.domain.Product;
import com.inditex.productsort.model.domain.Size;
import com.inditex.productsort.model.domain.Stock;
import com.inditex.productsort.model.dto.ProductDTO;
import com.inditex.productsort.model.entities.ProductEntity;
import com.inditex.productsort.model.entities.StockEmbeddable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void toDomain_shouldMapCorrectly() {
        StockEmbeddable stockEntity1 = new StockEmbeddable(Size.S, 5);
        StockEmbeddable stockEntity2 = new StockEmbeddable(Size.M, 10);

        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Test Product");
        entity.setSalesUnits(100);
        entity.setStocks(List.of(stockEntity1, stockEntity2));

        Product result = productMapper.toDomain(entity);

        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getName());
        assertEquals(100, result.getSalesUnits());
        assertEquals(2, result.getStocks().size());

        Stock stock1 = result.getStocks().getFirst();
        assertEquals(Size.S, stock1.getSize());
        assertEquals(5, stock1.getQuantity());
    }

    @Test
    void toDTO_shouldMapCorrectly() {
        Product product = new Product(2L, "Another Product", 200, List.of());
        double score = 89.25;

        ProductDTO dto = productMapper.toDTO(product, score);

        assertEquals(2L, dto.getId());
        assertEquals("Another Product", dto.getName());
        assertEquals(89.25, dto.getSortingScore(), 0.01);
    }

}