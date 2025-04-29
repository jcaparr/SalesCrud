package com.todocode.bazar.controller;

import com.todocode.bazar.dto.response.SaleResponseDto;
import com.todocode.bazar.dto.request.SaleRequestDto;
import com.todocode.bazar.service.inteface.ISaleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final ISaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<SaleResponseDto> createSale(@Valid @RequestBody SaleRequestDto saleRequestDto){
        SaleResponseDto createdSale = saleService.addSale(saleRequestDto);
        URI location = URI.create("/sales/get" + createdSale.getSaleCode());
        return ResponseEntity.created(location).body(createdSale);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<SaleResponseDto>> getAllSales(){
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/get/{saleCode}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Long saleCode){
        return ResponseEntity.ok(saleService.getSaleById(saleCode));
    }

    @PutMapping("/update/{saleCode}")
    public ResponseEntity<SaleResponseDto> updateSale(@PathVariable Long saleCode,
                                                      @Valid @RequestBody SaleRequestDto saleRequestDto){
        return ResponseEntity.ok(saleService.updateSale(saleCode,saleRequestDto));
    }

    @DeleteMapping("/delete/{saleCode}")
    public ResponseEntity<?> deleteSale(@PathVariable Long saleCode){
        saleService.deleteSale(saleCode);
        return ResponseEntity.noContent().build();

    }

}
