package com.todocode.bazar.controller;

import com.todocode.bazar.dto.response.ProductResponseDto;
import com.todocode.bazar.dto.request.ProductRequestDto;
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
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto createdProduct = productService.addProduct(productRequestDto);
        URI location = URI.create("/products/get" + createdProduct.getProductCode());
        return ResponseEntity.created(location).body(createdProduct);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{productCode}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productCode){
        return ResponseEntity.ok(productService.getProductById(productCode));
    }

    @PutMapping("/update/{productCode}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productCode,
                                                            @Valid @RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok(productService.updateProduct(productCode, productRequestDto));
    }

    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productCode){
        productService.deleteProduct(productCode);
        return ResponseEntity.noContent().build();
    }
}
