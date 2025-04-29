package com.todocode.bazar.service.inteface;

import com.todocode.bazar.dto.response.SaleResponseDto;
import com.todocode.bazar.dto.request.SaleRequestDto;

import java.util.List;

public interface ISaleService {
    public SaleResponseDto addSale(SaleRequestDto saleRequestDto);
    public List<SaleResponseDto> getAllSales();
    public SaleResponseDto getSaleById(Long saleCode);
    public SaleResponseDto updateSale(Long saleCode, SaleRequestDto saleRequestDto);

    void deleteSale(Long saleCode);
}
