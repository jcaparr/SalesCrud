package com.todocode.bazar.service.inteface;

import com.todocode.bazar.dto.ProductDto;
import com.todocode.bazar.dto.UpdateProductDto;

import java.util.List;

public interface IProductService {
    public ProductDto addProduct(UpdateProductDto updateProductDto);
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Long product_id);
    public ProductDto updateProduct(Long product_id, UpdateProductDto updateProductDto);
    public void deleteProduct(Long product_id);
}
