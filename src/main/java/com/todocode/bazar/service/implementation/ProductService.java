package com.todocode.bazar.service.implementation;

import com.todocode.bazar.dto.response.ProductResponseDto;
import com.todocode.bazar.dto.request.ProductRequestDto;
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
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        if (productRepository.existsByBrandAndName(productRequestDto.getBrand(), productRequestDto.getName())) {
            throw new AlreadyExist("A product with brand: " + productRequestDto.getBrand()
                    + " and name: " + productRequestDto.getName() + " already exists.");
        }

        Product productSaved = productRepository.save(
                mapperUtils.mapDtoToEntity(productRequestDto, Product.class)
        );

        return mapperUtils.mapEntityToDto(productSaved, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new NotFoundException("No products found");
        }
        return mapperUtils.mapEntityListToDtoList(productList, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto getProductById(Long productCode){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        return mapperUtils.mapEntityToDto(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto updateProduct(Long productCode, ProductRequestDto productRequestDto){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        product.setCost(productRequestDto.getCost());
        product.setName(productRequestDto.getName());
        product.setBrand(productRequestDto.getBrand());
        product.setAvailableQuantity(productRequestDto.getAvailableQuantity());

        productRepository.save(product);
        return mapperUtils.mapEntityToDto(product, ProductResponseDto.class);
    }

    @Override
    public void deleteProduct(Long productCode){
        Product product = productRepository.findById(productCode)
                .orElseThrow(()-> new NotFoundException("Product with id: " + productCode + " not found"));

        productRepository.deleteById(productCode);
    }
}
