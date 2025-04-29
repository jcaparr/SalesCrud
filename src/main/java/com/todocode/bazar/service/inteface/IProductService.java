package com.todocode.bazar.service.inteface;

import com.todocode.bazar.dto.request.ProductRequestDto;
import com.todocode.bazar.dto.response.ProductResponseDto;

import java.util.List;

public interface IProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    public List<ProductResponseDto> getAllProducts();

    public ProductResponseDto getProductById(Long product_id);

    public ProductResponseDto updateProduct(Long product_id, ProductRequestDto productRequestDto);

    public void deleteProduct(Long product_id);

    public List<ProductResponseDto> getListByIds(List<Long> productIds);
}
