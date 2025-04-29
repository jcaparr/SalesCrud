package com.todocode.bazar.service.implementation;

import com.todocode.bazar.dto.ProductDto;
import com.todocode.bazar.dto.UpdateProductDto;
import com.todocode.bazar.exception.AlreadyExist;
import com.todocode.bazar.exception.NotFoundException;
import com.todocode.bazar.model.Product;
import com.todocode.bazar.repository.IProductRepository;
import com.todocode.bazar.service.inteface.IProductService;
import com.todocode.bazar.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final MapperUtils mapperUtils;
    private final IProductRepository productRepository;

    @Override
    public ProductDto addProduct(UpdateProductDto updateProductDto){
        if (productRepository.existsByBrandAndName(updateProductDto.getBrand(), updateProductDto.getName())) {
            throw new AlreadyExist("A product with brand: " + updateProductDto.getBrand()
                    + " and name: " + updateProductDto.getName() + " already exists.");
        }

        Product productSaved = productRepository.save(
                mapperUtils.mapDtoToEntity(updateProductDto, Product.class)
        );

        return mapperUtils.mapEntityToDto(productSaved, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new NotFoundException("No products found");
        }
        return mapperUtils.mapEntityListToDtoList(productList, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(Long productCode){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        return mapperUtils.mapEntityToDto(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long productCode, UpdateProductDto updateProductDto){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        product.setCost(updateProductDto.getCost());
        product.setName(updateProductDto.getName());
        product.setBrand(updateProductDto.getBrand());
        product.setAvailableQuantity(updateProductDto.getAvailableQuantity());

        productRepository.save(product);
        return mapperUtils.mapEntityToDto(product, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productCode){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        productRepository.deleteById(productCode);
    }
}
