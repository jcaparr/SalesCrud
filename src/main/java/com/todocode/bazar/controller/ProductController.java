package com.todocode.bazar.controller;

import com.todocode.bazar.dto.ProductDto;
import com.todocode.bazar.dto.UpdateProductDto;
import com.todocode.bazar.service.inteface.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> addProduct(@Valid @RequestBody UpdateProductDto updateProductDto){
        ProductDto createdProduct = productService.addProduct(updateProductDto);
        URI location = URI.create("/products/get" + createdProduct.getProductCode());
        return ResponseEntity.created(location).body(createdProduct);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{productCode}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productCode){
        return ResponseEntity.ok(productService.getProductById(productCode));
    }

    @PutMapping("/update/{productCode}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productCode,
                                                    @Valid @RequestBody UpdateProductDto updateProductDto){
        return ResponseEntity.ok(productService.updateProduct(productCode, updateProductDto));
    }

    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productCode){
        productService.deleteProduct(productCode);
        return ResponseEntity.noContent().build();
    }
}
